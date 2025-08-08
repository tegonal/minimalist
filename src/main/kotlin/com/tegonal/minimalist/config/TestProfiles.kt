package com.tegonal.minimalist.config

import ch.tutteli.kbox.mapFirst
import com.tegonal.minimalist.utils.impl.checkIsNotBlank
import com.tegonal.minimalist.utils.impl.checkNoDuplicates

/**
 * @since 2.0.0
 */
class TestProfiles private constructor(profiles: Map<String, Map<String, TestConfig>>) {
	private val profiles: Map<String, Map<String, TestConfig>> = buildMap {
		check(profiles.isNotEmpty()) {
			"You need to define at least one test profile"
		}

		profiles.forEach { (profile, testConfigPerEnv) ->
			checkIsNotBlank(profile, "profile")
			check(testConfigPerEnv.isNotEmpty()) {
				"You need to define at least one environment in test profile $profile"
			}
			testConfigPerEnv.keys.forEach { it ->
				checkIsNotBlank(it, "env in profile $profile")
			}
			put(profile, testConfigPerEnv.toMap())
		}
	}

	operator fun contains(profileName: String) = profiles.contains(profileName)

	fun get(profileName: String, env: String): TestConfig =
		find(profileName, env) ?: throw IllegalArgumentException(
			"profile $profileName not defined, available profiles: ${
				profiles.keys.joinToString(", ")
			}"
		)

	fun find(profileName: String, env: String): TestConfig? = profiles[profileName]?.get(env)

	fun profileNames(): Set<String> = profiles.keys
	fun envs(profileName: String): Set<String> = run {
		profiles[profileName] ?: error("profile $profileName does not exist")
	}.keys

	fun toHashMap(): HashMap<String, HashMap<String, TestConfig>> =
		HashMap<String, HashMap<String, TestConfig>>(profiles.size)
			.also { it.putAll(profiles.entries.map { (k, v) -> k to HashMap(v) }) }

	companion object {
		/**
		 * Creates a [TestProfiles] based on the given [profiles]
		 * which use one of the predefined [TestType] as profile name and [Env]  category names.
		 */
		fun create(vararg profiles: Pair<TestType, List<Pair<Env, TestConfig>>>): TestProfiles {
			checkNoDuplicates(profiles.map { it.first }) { duplicates ->
				"Looks like you defined some profiles multiple times: ${duplicates.joinToString(", ")}"
			}
			return create(profiles.associate { (profile, testConfigPerEnv) ->
				checkNoDuplicates(testConfigPerEnv.map { it.first }) { duplicates ->
					"Looks like you defined some envs in profile $profile multiple times: ${duplicates.joinToString(", ")}"
				}
				profile.name to testConfigPerEnv.associate { it.mapFirst { env -> env.name } }
			})
		}

		/**
		 * Creates a [TestProfiles] based on the given [profiles] which allows to
		 * specify custom category names.
		 *
		 * Also take a look at the overload which expects one of the predefined [TestType] as category names.
		 */
		fun create(profiles: Map<String, Map<String, TestConfig>>): TestProfiles =
			TestProfiles(profiles)
	}
}
