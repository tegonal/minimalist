package com.tegonal.minimalist.config

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.SuffixArgsGeneratorDecider
import com.tegonal.minimalist.providers.impl.ProfileBasedArgsRangeDecider
import com.tegonal.minimalist.providers.impl.SuffixArgsGeneratorNeverDecider
import com.tegonal.minimalist.utils.impl.checkIsNotBlank
import com.tegonal.minimalist.utils.impl.checkIsPositive
import com.tegonal.minimalist.utils.impl.failIfNegative
import com.tegonal.minimalist.utils.seedToOffset
import kotlin.random.Random

/**
 * @since 2.0.0
 */
class MinimalistConfig(
	/**
	 * The seed which Minimalist uses internally for [Random].
	 *
	 * Minimalist outputs the used seed when the [MinimalistConfig] is loaded.
	 *
	 * 🔍 Fix it to a previously used seed if you want to re-run the same arguments.
	 *
	 * Note, you are not allowed to fix a seed via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val seed: Seed = Seed(Random.nextInt()),

	/**
	 * Influences an [ArgsRangeDecider]'s choice of [ArgsRange.offset], i.e. allows to skip certain test cases.
	 *
	 * Must be greater than 0 if set.
	 *
	 * 🔍 Typically used with a fixed [seed] (i.e. during debugging). In such cases you might want to set [maxArgs]
	 * as well to restrict [ArgsRange.take].
	 *
	 * Note, you are not allowed to set [skip] via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val skip: Int? = null,

	/**
	 * Influences an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should be at least
	 * the specified amount, unless the [ArgsGenerator] repeats values beforehand.
	 *
	 * Must be greater than 0 if set.
	 *
	 * 🔍 Typically used during local development to force more arguments without the need to change profiles.
	 *
	 * Note, you are not allowed to set [requestedMinArgs] via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val requestedMinArgs: Int? = null,

	/**
	 * Should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should not be greater
	 * than the specified amount.
	 *
	 * Must be greater than 0 if set.
	 *
	 * 🔍 Typically used with a fixed [seed] and [skip] (i.e. during debugging)
	 * to restrict [ArgsRange.take].
	 *
	 * Note, you are not allowed to set [maxArgs] via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val maxArgs: Int? = null,

	/**
	 * Defines which [ArgsRangeDecider] shall be used identified via fully qualified name.
	 * Per default, the corresponding class is loaded via [java.util.ServiceLoader].
	 */
	val activeArgsRangeDecider: String = ProfileBasedArgsRangeDecider::class.qualifiedName ?: error(
		"cannot determine qualified name of ProfileBasedArgsRangeDecider "
	),

	/**
	 * Defines which [SuffixArgsGeneratorDecider] shall be used identified via fully qualified name.
	 * Per default, the corresponding class is loaded via [java.util.ServiceLoader].
	 */
	val activeSuffixArgsGeneratorDecider: String = SuffixArgsGeneratorNeverDecider::class.qualifiedName ?: error(
		"cannot determine qualified name of EmptyArgsGeneratorSuffixProvider "
	),

	/**
	 * Defines which test profile is chosen in case none is specified via [ArgsRangeOptions.profile].
	 * The chosen name needs to be configured in [testProfiles].
	 */
	val defaultProfile: String = TestType.Integration.name,

	/**
	 * Defines on which environment you want to run tests.
	 * The chosen name needs to be configured in [testProfiles].
	 */
	val activeEnv: String = Env.Local.name,

	/**
	 * Allows to define different profiles with different associated [TestConfig] per env.
	 *
	 * You can use the predefined [TestType] as profile names and [Env] as environment name but
	 * [TestProfiles] works on [String]s in the end, you can also choose your own names.
	 *
	 */
	val testProfiles: TestProfiles = TestProfiles.create(
		TestType.Unit to
			listOf(
				Env.Local to TestConfig(maxArgs = 100),
				Env.PR to TestConfig(maxArgs = 300),
				Env.Main to TestConfig(maxArgs = 500),
				// we don't expect that Unit tests are run as part of a deployment
				// Environment.DeployTest to 1000,
				// Environment.DeployInt to 1500,
				Env.NightlyTest to TestConfig(maxArgs = 2000),
				Env.NightlyInt to TestConfig(maxArgs = 4000),
				Env.HotfixPR to TestConfig(maxArgs = 600),
				Env.Hotfix to TestConfig(maxArgs = 1000),
				Env.Release to TestConfig(maxArgs = 1000),
			),
		TestType.Integration to
			listOf(
				Env.Local to TestConfig(maxArgs = 5),
				Env.PR to TestConfig(maxArgs = 10),
				Env.Main to TestConfig(maxArgs = 30),
				Env.DeployTest to TestConfig(maxArgs = 60),
				Env.DeployInt to TestConfig(maxArgs = 80),
				Env.NightlyTest to TestConfig(maxArgs = 150),
				Env.NightlyInt to TestConfig(maxArgs = 200),
				Env.HotfixPR to TestConfig(maxArgs = 50),
				Env.Hotfix to TestConfig(maxArgs = 100),
				Env.Release to TestConfig(maxArgs = 100),
			),
		TestType.E2E to
			listOf(
				Env.Local to TestConfig(maxArgs = 3),
				Env.PR to TestConfig(maxArgs = 7),
				Env.Main to TestConfig(maxArgs = 10),
				Env.DeployTest to TestConfig(maxArgs = 20),
				Env.DeployInt to TestConfig(maxArgs = 30),
				Env.NightlyTest to TestConfig(maxArgs = 50),
				Env.NightlyInt to TestConfig(maxArgs = 60),
				Env.HotfixPR to TestConfig(maxArgs = 15),
				Env.Hotfix to TestConfig(maxArgs = 50),
				Env.Release to TestConfig(maxArgs = 50),
			),
		TestType.SystemIntegration to
			listOf(
				Env.Local to TestConfig(maxArgs = 3),
				Env.PR to TestConfig(maxArgs = 5),
				Env.Main to TestConfig(maxArgs = 7),
				Env.DeployTest to TestConfig(maxArgs = 10),
				Env.DeployInt to TestConfig(maxArgs = 15),
				Env.NightlyTest to TestConfig(maxArgs = 40),
				Env.NightlyInt to TestConfig(maxArgs = 50),
				Env.HotfixPR to TestConfig(maxArgs = 10),
				Env.Release to TestConfig(maxArgs = 20),
			),
	),
) {
	init {
		skip?.also { checkIsPositive(it, "skip") }
		requestedMinArgs?.also { checkIsPositive(it, "requestedMinArgs") }
		maxArgs?.also { checkIsPositive(it, "maxArgs") }

		checkIsNotBlank(activeArgsRangeDecider, "activeArgsRangeDecider")
		checkIsNotBlank(defaultProfile, "defaultProfile")
		checkIsNotBlank(activeEnv, "activeEnv")

		check(defaultProfile in testProfiles) {
			"Your specified defaultProfile ($defaultProfile) does not exists in testProfiles, existing names: ${
				testProfiles.profileNames().joinToString(",")
			}"
		}
		testProfiles.find(defaultProfile, activeEnv) ?: error(
			"Your specified activeEnv (${activeEnv}) is not defined in profile $defaultProfile, existing envs: ${
				testProfiles.envs(defaultProfile).joinToString(", ")
			})"
		)
	}

	fun copy(configure: MinimalistConfigBuilder.() -> Unit): MinimalistConfig =
		MinimalistConfigBuilder(
			seed = seed.value,
			skip = skip,
			maxArgs = maxArgs,
			requestedMinArgs = requestedMinArgs,
			activeArgsRangeDecider = activeArgsRangeDecider,
			activeSuffixArgsGeneratorDecider = activeSuffixArgsGeneratorDecider,
			activeEnv = activeEnv,
			defaultProfile = defaultProfile,
			testProfiles = testProfiles.toHashMap()
		).apply(configure).build()
}

/**
 * @since 2.0.0
 */
class MinimalistConfigBuilder(
	var seed: Int,
	var skip: Int?,
	var maxArgs: Int?,
	var requestedMinArgs: Int?,
	var activeArgsRangeDecider: String,
	var activeSuffixArgsGeneratorDecider: String,
	var activeEnv: String,
	var defaultProfile: String,
	var testProfiles: HashMap<String, HashMap<String, TestConfig>>
) {
	fun build(): MinimalistConfig = MinimalistConfig(
		seed = Seed(seed),
		skip = skip,
		requestedMinArgs = requestedMinArgs,
		maxArgs = maxArgs,
		activeArgsRangeDecider = activeArgsRangeDecider,
		activeSuffixArgsGeneratorDecider = activeSuffixArgsGeneratorDecider,
		activeEnv = activeEnv,
		defaultProfile = defaultProfile,
		testProfiles = TestProfiles.create(testProfiles)
	)
}

/**
 * Represents the Minimalist seed, typically used for [Random] and as offset in [SemiOrderedArgsGenerator.generate].
 *
 * Use [value] to retrieve the seed as such (e.g. for [Random]) and [toOffset] in case it shall be used as random offset
 * @since 2.0.0
 */
@JvmInline
value class Seed(val value: Int) {
	override fun toString(): String = value.toString()
}

/**
 * @since 2.0.0
 */
fun Seed.toOffset() = seedToOffset(value)
