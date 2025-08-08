package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.MinimalistConfigBuilder
import com.tegonal.minimalist.config.TestConfig
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import com.tegonal.minimalist.utils.impl.toIntOrErrorNotValid
import com.tegonal.minimalist.utils.impl.toPositiveIntOrErrorNotValid
import java.util.Properties

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistPropertiesParser {

	fun mergeWithProperties(config: MinimalistConfig, props: Properties) = config.copy {
		props.forEach { (keyAny, valueAny) ->
			val key = keyAny as String
			val value = valueAny as String

			when {
				key == "seed" -> seed = value.toIntOrErrorNotValid(key)
				key == "offsetToDecidedOffset" -> offsetToDecidedOffset = value.toIntOrErrorNotValid(key)

				key == "atMostArgs" -> atMostArgs = value.toIntOrErrorNotValid(key)
				key == "requestedMinArgs" -> requestedMinArgs = value.toIntOrErrorNotValid(key)
				key == "activeArgsRangeDecider" -> activeArgsRangeDecider = value
				key == "activeEnv" -> activeEnv = value
				key == "defaultProfile" -> defaultProfile = value

				key == PROFILES -> {
					if (value == "clear") testProfiles.clear()
					else error("don't know how to interpret $value for $key")
				}

				key.startsWith(PROFILES_PREFIX) -> parseTestProfile(key, value)


				else -> throwUnknownProperty(key, value)
			}
		}
	}


	private fun MinimalistConfigBuilder.parseTestProfile(
		key: String,
		value: String,
	) {
		//TODO 2.1.0 warn about duplicates profile Names / envs, i.e. copy to own maps first and merge in the end
		// (we don't want to error in case we override an existing profile name / env)
		val remainingAfterPrefix = key.substringAfter(PROFILES_PREFIX)
		val profile = remainingAfterPrefix.substringBefore(".")
		if (remainingAfterPrefix == profile) {
			if (value == "clear") testProfiles[profile]?.clear()
			else error("don't know how to interpret $value for $key")
		} else {
			val testConfigsPerEnv = testProfiles.computeIfAbsent(profile) { HashMap() }
			val remainingAfterProfile = remainingAfterPrefix.substringAfter("$profile.")
			val env = remainingAfterProfile.substringBefore(".")
			if (remainingAfterProfile == env) {
				if (value == "clear") testConfigsPerEnv.clear()
				else error("don't know how to interpret $value for $key")
			} else {
				val testConfig = when (remainingAfterProfile.substringAfter("$env.")) {
					"atMostArgs" -> TestConfig(atMostArgs = value.toPositiveIntOrErrorNotValid(key))
					else -> throwUnknownProperty(key, value)
				}
				testConfigsPerEnv.put(env, testConfig)
			}
		}
	}

	private fun throwUnknownProperty(key: String, value: String): Nothing {
		error("Unknown minimalist config property $key with value $value -- if you want to introduce custom config properties, then please open a feature request: $FEATURE_REQUEST_URL&title=custom%20config%20properties")
	}

	companion object {
		const val PROFILES = "profiles"
		const val PROFILES_PREFIX = "$PROFILES."
	}
}

