package com.tegonal.minimalist.config

import com.tegonal.minimalist.config.impl.checkIsNotBlank
import com.tegonal.minimalist.config.impl.failIfNegative
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.impl.ProfileBasedArgsRangeDecider
import kotlin.random.Random

/**
 * @since 2.0.0
 */
class MinimalistConfig(
	/**
	 * The seed which Minimalist uses internally for [Random].
	 * Needs to be between `0` and [Int.MAX_VALUE].
	 *
	 * Minimalist outputs the used seed when the [MinimalistConfig] is loaded.
	 *
	 * 🔍 Fix it to a previously used seed if you want to re-run the same arguments.
	 *
	 * Note, you are not allowed to fix a seed via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val seed: Int = Random.nextInt(0, Int.MAX_VALUE),

	/**
	 * Influences an [ArgsRangeDecider]'s choice of [ArgsRange.offset], i.e. allows to skip certain test cases.
	 *
	 * 🔍 Typically used with a fixed [seed] (i.e. during debugging). In such cases you might want to set [atMostArgs]
	 * as well to restrict [ArgsRange.take].
	 *
	 * Note, you are not allowed to fix a seed via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val offsetToDecidedOffset: Int? = null,

	/**
	 * Influences an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should be at least
	 * the specified amount, unless the [ArgsGenerator] repeats values beforehand.
	 *
	 * 🔍 Typically used during local development to force more arguments without the need to change profiles.
	 *
	 * Note, you are not allowed to fix a seed via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val requestedMinArgs: Int? = null,

	/**
	 * Should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should not be greater
	 * than the specified amount.
	 *
	 * 🔍 Typically used with a fixed [seed] and [offsetToDecidedOffset] (i.e. during debugging)
	 * to restrict [ArgsRange.take].
	 *
	 * Note, you are not allowed to fix a seed via minimalist.properties (which is usually committed), use
	 * minimalist.local.properties instead (which is usually on the git ignore list).
	 */
	val atMostArgs: Int? = null,

	/**
	 * Defines which [ArgsRangeDecider] shall be used.
	 */
	val activeArgsRangeDecider: String = ProfileBasedArgsRangeDecider::class.qualifiedName ?: error(
		"cannot determine qualified name of ProfileBasedArgsRangeDecider "
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
				Env.Local to TestConfig(atMostArgs = 100),
				Env.PR to TestConfig(atMostArgs = 300),
				Env.Main to TestConfig(atMostArgs = 500),
				// we don't expect that Unit tests are run as part of a deployment
				// Environment.DeployTest to 1000,
				// Environment.DeployInt to 1500,
				Env.NightlyTest to TestConfig(atMostArgs = 2000),
				Env.NightlyInt to TestConfig(atMostArgs = 4000),
				Env.HotfixPR to TestConfig(atMostArgs = 600),
				Env.Hotfix to TestConfig(atMostArgs = 1000),
			),
		TestType.Integration to
			listOf(
				Env.Local to TestConfig(atMostArgs = 5),
				Env.PR to TestConfig(atMostArgs = 10),
				Env.Main to TestConfig(atMostArgs = 30),
				Env.DeployTest to TestConfig(atMostArgs = 60),
				Env.DeployInt to TestConfig(atMostArgs = 80),
				Env.NightlyTest to TestConfig(atMostArgs = 150),
				Env.NightlyInt to TestConfig(atMostArgs = 200),
				Env.HotfixPR to TestConfig(atMostArgs = 50),
				Env.Hotfix to TestConfig(atMostArgs = 100),
			),
		TestType.E2E to
			listOf(
				Env.Local to TestConfig(atMostArgs = 3),
				Env.PR to TestConfig(atMostArgs = 7),
				Env.Main to TestConfig(atMostArgs = 10),
				Env.DeployTest to TestConfig(atMostArgs = 20),
				Env.DeployInt to TestConfig(atMostArgs = 30),
				Env.NightlyTest to TestConfig(atMostArgs = 50),
				Env.NightlyInt to TestConfig(atMostArgs = 60),
				Env.HotfixPR to TestConfig(atMostArgs = 15),
				Env.Hotfix to TestConfig(atMostArgs = 50),
			),
		TestType.SystemIntegration to
			listOf(
				Env.Local to TestConfig(atMostArgs = 3),
				Env.PR to TestConfig(atMostArgs = 5),
				Env.Main to TestConfig(atMostArgs = 7),
				Env.DeployTest to TestConfig(atMostArgs = 10),
				Env.DeployInt to TestConfig(atMostArgs = 15),
				Env.NightlyTest to TestConfig(atMostArgs = 40),
				Env.NightlyInt to TestConfig(atMostArgs = 50),
				Env.HotfixPR to TestConfig(atMostArgs = 10),
				Env.Hotfix to TestConfig(atMostArgs = 20),
			),
	),
) {
	init {
		failIfNegative(seed, "seed")
		offsetToDecidedOffset?.also { failIfNegative(it, "offsetToDecidedOffset") }

		checkIsNotBlank(activeArgsRangeDecider, "activeArgsRangeDecider")
		checkIsNotBlank(defaultProfile, "defaultProfile")
		checkIsNotBlank(activeEnv, "activeEnv")

		check(defaultProfile in testProfiles) {
			"Your specified defaultProfile ($defaultProfile) does not exists in testProfiles, existing names: ${
				testProfiles.profileNames().joinToString(",")
			}"
		}
		testProfiles.find(defaultProfile, activeEnv) ?: error(
			"Your specified activeEnv (${activeEnv}) is not defined in profile $defaultProfile (existing envs): ${
				testProfiles.envs(defaultProfile).joinToString(", ")
			})"
		)
	}

	fun copy(configure: MinimalistConfigBuilder.() -> Unit): MinimalistConfig =
		MinimalistConfigBuilder(
			seed = seed,
			offsetToDecidedOffset = offsetToDecidedOffset,
			atMostArgs = atMostArgs,
			requestedMinArgs = requestedMinArgs,
			activeArgsRangeDecider = activeArgsRangeDecider,
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
	var offsetToDecidedOffset: Int?,
	var atMostArgs: Int?,
	var requestedMinArgs: Int?,
	var activeArgsRangeDecider: String,
	var activeEnv: String,
	var defaultProfile: String,
	var testProfiles: HashMap<String, HashMap<String, TestConfig>>
) {
	fun build(): MinimalistConfig = MinimalistConfig(
		seed = seed,
		offsetToDecidedOffset = offsetToDecidedOffset,
		requestedMinArgs = requestedMinArgs,
		atMostArgs = atMostArgs,
		activeArgsRangeDecider = activeArgsRangeDecider,
		activeEnv = activeEnv,
		defaultProfile = defaultProfile,
		testProfiles = TestProfiles.create(testProfiles)
	)
}

/**
 * Our way to inject the [MinimalistConfig] into an instance after it was created via [java.util.ServiceLoader].
 *
 * @since 2.0.0
 */
interface RequiresConfig {
	var config: MinimalistConfig
}
