package com.tegonal.minimalist.config.impl

import ch.tutteli.kbox.blankToNull
import ch.tutteli.kbox.takeIf
import com.tegonal.minimalist.config.Env
import com.tegonal.minimalist.config.MinimalistConfig
import java.util.*

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistConfigViaPropertiesLoader {
	val config by lazy {
		val parser = MinimalistPropertiesParser()
		val initialConfig = MinimalistConfig()
		initialConfig
			.run {
				mergeWithPropertiesInResource("/minimalist.properties", parser).also {
					check(it.seed == initialConfig.seed) {
						errorMessageNotAllowedToModify("seed")
					}
					check(it.offsetToDecidedOffset == initialConfig.offsetToDecidedOffset) {
						errorMessageNotAllowedToModify("offsetToDecidedOffset")
					}
					check(it.requestedMinArgs == initialConfig.requestedMinArgs) {
						errorMessageNotAllowedToModify("requestedMinArgs")
					}
					check(it.atMostArgs == initialConfig.atMostArgs) {
						errorMessageNotAllowedToModify("atMostArgs")
					}
				}
			}
			.run { mergeWithEnv() }
			.run { mergeWithPropertiesInResource("/minimalist.local.properties", parser) }
			.also {
				val fixedSeed = it.seed != initialConfig.seed
				println("Minimalist${if (fixedSeed) " fixed" else ""} seed ${it.seed} in env ${it.activeEnv} ")
				//TODO 2.1.0 add seedFixedAt to MinimalistConfig and write it to minimalist.local.properties in
				// case the seed is fixed and error after x hours, minutes or whatever (could be configurable)
				// so that we warn a user if he forgot to remove it again - a user could set seedFixedAt manually to
				// current time, this way the user would be reminded again. Or we introduce a property
				// errorAboutFixedSeeedAt. Would be simpler for a user to define. Imagine the following, config property
				// errorAboutFixedSeedAfterDuration = PT30M is set and the user gets notified after 30min (because
				// they did not figure out in those 30min why the test failed. They estimate they need another 1h
				// instead of calculating what seedFixedAt they should set so that they are reminded after 1h, it
				// would be simpler if they could just set the DateTime in errorAboutFixedSeedAt
			}
	}

	private fun errorMessageNotAllowedToModify(what: String) =
		"You are not allowed to modify $what via minimalist.properties use minimalist.local.properties to fix a seed"

	private fun MinimalistConfig.mergeWithPropertiesInResource(
		propertiesFile: String,
		parser: MinimalistPropertiesParser
	): MinimalistConfig = this::class.java.getResourceAsStream(propertiesFile)?.let {
		it.use { input ->
			val props = Properties()
			props.load(input)
			parser.mergeWithProperties(this, props)
		}
	} ?: this

	private fun MinimalistConfig.mergeWithEnv(): MinimalistConfig =
		determineEnv()?.let { copy { activeEnv = it } } ?: this

	private fun MinimalistConfig.determineEnv(): String? =
		System.getenv("MINIMALIST_ENV") ?: run {
			val envs = testProfiles.envs(this.defaultProfile)
			takeIf(Env.entries.any { it.name in envs }) {
				determineEnvBasedOnGitHubActions()
					?: determineEnvBasedOnGitLab()
					?: determineEnvBasedOnBitBucket()
			}?.let { it.name.takeIf { env -> env in envs } }
		}

	private fun determineEnvBasedOnGitHubActions(): Env? =
		System.getenv("GITHUB_ENV")?.blankToNull()?.let { event ->
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
