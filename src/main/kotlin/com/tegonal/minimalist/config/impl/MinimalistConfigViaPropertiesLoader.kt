package com.tegonal.minimalist.config.impl

import ch.tutteli.kbox.blankToNull
import ch.tutteli.kbox.takeIf
import com.tegonal.minimalist.config.Env
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.MinimalistConfigBuilder
import com.tegonal.minimalist.config.impl.MinimalistPropertiesParser.Companion.ERROR_DEADLINES_PREFIX
import com.tegonal.minimalist.utils.impl.checkIsPositive
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.appendText
import kotlin.io.path.readText
import kotlin.io.path.writeText

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistConfigViaPropertiesLoader {
	val config: MinimalistConfig by lazy {
		val parser = MinimalistPropertiesParser()
		val initialConfig = MinimalistConfig()
		val configFileSpecifics = ConfigFileSpecifics()
		initialConfig.toBuilder()
			.apply {
				setByPropertiesInResource("/minimalist.properties", configFileSpecifics, parser)
				check(seed == initialConfig.seed.value) {
					errorMessageNotAllowedToModify("seed")
				}
				check(skip == initialConfig.skip) {
					errorMessageNotAllowedToModify("skip")
				}
				check(requestedMinArgs == initialConfig.requestedMinArgs) {
					errorMessageNotAllowedToModify("requestedMinArgs")
				}
				check(maxArgs == initialConfig.maxArgs) {
					errorMessageNotAllowedToModify("maxArgs")
				}
			}
			.apply { setByEnv() }
			.apply { setByPropertiesInResource("/minimalist.local.properties", configFileSpecifics, parser) }
			.apply {
				val fixedSeed = seed != initialConfig.seed.value

				println("Minimalist${if (fixedSeed) " fixed" else ""} seed $seed ${if (skip != null) "skipping $skip " else ""} in env $activeEnv ")

				with(configFileSpecifics) {
					checkIsPositive(
						remindAboutFixedPropertiesAfterMinutes,
						"remindAboutFixedPropertiesAfterMinutes"
					)
					val projectRootDir = Paths.get("").toAbsolutePath().normalize()
					val localPropertiesPath =
						configFileSpecifics.minimalistPropertiesDir.resolve("minimalist.local.properties")
							.toAbsolutePath()
							.normalize()
					check(localPropertiesPath.startsWith(projectRootDir)) {
						"localPropertiesPath (l) must be within the projects root directory (p)\nl: $localPropertiesPath\np: $projectRootDir"
					}
					checkDeadline(localPropertiesPath, seed.takeIf { fixedSeed }, "seed")
					checkDeadline(localPropertiesPath, skip, "skip")
					checkDeadline(localPropertiesPath, maxArgs, "maxArgs")
					checkDeadline(localPropertiesPath, requestedMinArgs, "requestedMinArgs")
				}
			}.build()

	}

	private fun errorMessageNotAllowedToModify(what: String) =
		"You are not allowed to modify $what via minimalist.properties use minimalist.local.properties to fix a seed"


	private fun ConfigFileSpecifics.checkDeadline(
		localPropertiesPath: Path,
		propertyValue: Any?,
		propertyName: String,
	) {
		val definedDeadline = errorDeadlines[propertyName]
		val deadlinePropertyName = """${ERROR_DEADLINES_PREFIX}${propertyName}"""
		if (propertyValue == null) {
			if (definedDeadline != null) {
				localPropertiesPath.unsetErrorDeadlineFor(deadlinePropertyName)
			}
		} else {
			if (definedDeadline == null) {
				localPropertiesPath.setErrorDeadlineFor(
					propertyName,
					LocalDateTime.now().plusMinutes(remindAboutFixedPropertiesAfterMinutes.toLong())
				)
			} else if (definedDeadline.isBefore(LocalDateTime.now())) {
				throw MinimalistDeadlineException(
					"""
                        |$propertyName is still set (is $propertyValue) and $deadlinePropertyName (which is $definedDeadline) passed.
                        |Either:
						|a) remove/comment out the property `$propertyName`
						|b) remove $deadlinePropertyName (in which case a new deadline is set)
						|c) set $deadlinePropertyName manually to a later date/time
						|The adjustments need to be made in the following file:
                        |${localPropertiesPath}
                        |
                        """.trimMargin()
				)
			}
		}
	}

	private fun Path.setErrorDeadlineFor(
		propertyName: String,
		deadline: LocalDateTime
	) {
		appendText(
			"""
            |
            |# You have set `$propertyName` and this deadline will remind you to remove it again.
            |${ERROR_DEADLINES_PREFIX}$propertyName=${deadline.format(DateTimeFormatter.ISO_DATE_TIME)}
			|
            """.trimMargin()
		)
	}

	private fun Path.unsetErrorDeadlineFor(deadlinePropertyName: String) {
		replaceText {
			it.replace(Regex("\n(#.*\n)*${deadlinePropertyName}=.*"), "")
		}
	}

	private fun Path.replaceText(replace: (String) -> String) {
		val content = replace(readText())
		writeText(content)
	}


	private fun MinimalistConfigBuilder.setByPropertiesInResource(
		propertiesFile: String,
		configFileSpecifics: ConfigFileSpecifics,
		parser: MinimalistPropertiesParser
	) {
		this::class.java.getResourceAsStream(propertiesFile)?.also {
			it.use { input ->
				val props = Properties()
				props.load(input)
				parser.mergeWithProperties(this, configFileSpecifics, props)
			}
		}
	}


	private fun MinimalistConfigBuilder.setByEnv() {
		determineEnv()?.also { activeEnv = it }
	}

	private fun MinimalistConfigBuilder.determineEnv(): String? =
		System.getenv("MINIMALIST_ENV") ?: run {
			val envs = testProfiles[defaultProfile] ?: error("profile $defaultProfile does not exist")
			// only determine envs if at least one standard env is defined (as others we don't know how to map)
			takeIf(Env.entries.any { it.name in envs }) {
				determineEnvBasedOnGitHubActions()
					?: determineEnvBasedOnGitLab()
					?: determineEnvBasedOnBitBucket()
			}?.let { it.name.takeIf { env -> env in envs.keys } }
		}

	private fun determineEnvBasedOnGitHubActions(): Env? =
		System.getenv("GITHUB_EVENT_NAME")?.blankToNull()?.let { event ->
			when (event) {
				"pull_request" -> determinePrEnv(getGithubEnv("GITHUB_BASE_REF"))
				"push" -> determinePushEnv(getGithubEnv("GITHUB_REF_NAME"))
				else -> null
			}
		}

	private fun getGithubEnv(envName: String): String =
		System.getenv(envName) ?: error("$envName is not set but should in a github-action")

	private fun determineEnvBasedOnGitLab(): Env? =
		determineBasedOnMrAndPushEnv("CI_MERGE_REQUEST_TARGET_BRANCH_NAME", "CI_COMMIT_BRANCH")

	private fun determineEnvBasedOnBitBucket(): Env? =
		determineBasedOnMrAndPushEnv("BITBUCKET_PR_DESTINATION_BRANCH", "BITBUCKET_BRANCH")

	private fun determineBasedOnMrAndPushEnv(
		mergeRequestTargetBranchEnvName: String,
		pushBranchEnvName: String,
	): Env? =
		System.getenv(mergeRequestTargetBranchEnvName)?.blankToNull()?.let {
			determinePrEnv(it)
		} ?: System.getenv(pushBranchEnvName)?.blankToNull()?.let {
			determinePushEnv(it)
		}

	private fun determinePrEnv(targetBranch: String): Env =
		when {
			targetBranch.startsWith("hotfix/") -> Env.HotfixPR
			else -> Env.PR
		}

	private fun determinePushEnv(branch: String): Env =
		when {
			branch == "test" -> Env.DeployTest
			branch == "int" -> Env.DeployInt
			branch.startsWith("hotfix/") -> Env.Hotfix
			branch.startsWith("release/") -> Env.Release
			else -> Env.Main
		}
}

/**
 * Contains properties which are not exposed via [MinimalistConfig] and are used during parsing a [MinimalistConfig]
 * file.
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ConfigFileSpecifics(
	/**
	 * Defines in about how many minutes the reminder triggers when fixing a property (such as
	 * [MinimalistConfigBuilder.seed], [MinimalistConfigBuilder.skip],
	 * [MinimalistConfigBuilder.requestedMinArgs], [MinimalistConfigBuilder.maxArgs]).
	 */
	var remindAboutFixedPropertiesAfterMinutes: Int = 60,
	var minimalistPropertiesDir: Path = Paths.get("./src/test/resources"),
	var errorDeadlines: HashMap<String, LocalDateTime> = HashMap(),
)
