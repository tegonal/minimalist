package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.MinimalistConfigBuilder
import com.tegonal.minimalist.config.TestConfig
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import com.tegonal.minimalist.utils.impl.toIntOrErrorNotValid
import com.tegonal.minimalist.utils.impl.toPositiveIntOrErrorNotValid
import java.util.*

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

			val supportedKeys = mutableListOf(PROFILES_PREFIX)

			fun isKey(supportedKey: String) = (key == supportedKey).also {
				supportedKeys.add(supportedKey)
			}

			when {
				isKey("seed") -> seed = value.toIntOrErrorNotValid(key)
				isKey("offsetToDecidedOffset") -> offsetToDecidedOffset = value.toIntOrErrorNotValid(key)

				isKey("maxArgs") -> maxArgs = value.toIntOrErrorNotValid(key)
				isKey("requestedMinArgs") -> requestedMinArgs = value.toIntOrErrorNotValid(key)
				isKey("activeArgsRangeDecider") -> activeArgsRangeDecider = value
				isKey("activeSuffixArgsGeneratorDecider") -> activeSuffixArgsGeneratorDecider = value
				isKey("activeEnv") -> activeEnv = value
				isKey("defaultProfile") -> defaultProfile = value

				isKey(PROFILES) -> {
					if (value == "clear") testProfiles.clear()
					else error("don't know how to interpret $value for $key")
				}

				key.startsWith(PROFILES_PREFIX) -> parseTestProfile(key, value)

				else -> throwUnknownProperty(key, value, supportedKeys)
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
					"maxArgs" -> TestConfig(maxArgs = value.toPositiveIntOrErrorNotValid(key))
					else -> throwUnknownProperty(
						key,
						value,
						supportedKeys = listOf("maxArgs"),
						within = "$PROFILES_PREFIX.$env."
					)
				}
				testConfigsPerEnv[env] = testConfig
			}
		}
	}

	private fun throwUnknownProperty(
		key: String,
		value: String,
		supportedKeys: List<String>,
		within: String? = null
	): Nothing {
		error(
			"Unknown minimalist config property $key with value $value -- if you want to introduce custom config properties, then please open a feature request: $FEATURE_REQUEST_URL&title=custom%20config%20properties\nSupported Keys${within?.let { " within $it" } ?: ""}: ${
				supportedKeys.sorted().joinToString(", ")
			}"
		)
	}

	companion object {
		const val PROFILES = "profiles"
		const val PROFILES_PREFIX = "$PROFILES."
	}
}

