package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfigBuilder
import com.tegonal.minimalist.config.TestConfig
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import com.tegonal.minimalist.utils.impl.toIntOrErrorNotValid
import com.tegonal.minimalist.utils.impl.toPositiveIntOrErrorNotValid
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.*

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistPropertiesParser {

	fun mergeWithProperties(
		config: MinimalistConfigBuilder,
		configFileSpecifics: ConfigFileSpecifics,
		props: Properties
	) {
		props.forEach { (keyAny, valueAny) ->
			try {
				val key = keyAny as String
				val value = valueAny as String

				config.parseProperty(configFileSpecifics, key, value)
			} catch (m: MinimalistParseException) {
				throw m
			} catch (e: Exception) {
				throw MinimalistParseException("could not parse $keyAny=$valueAny", e)
			}
		}
	}


	private fun MinimalistConfigBuilder.parseProperty(
		configFileSpecifics: ConfigFileSpecifics,
		key: String,
		value: String
	) {
		val supportedKeys = mutableListOf(PROFILES_PREFIX)

		fun isKey(supportedKey: String) = (key == supportedKey).also {
			supportedKeys.add(supportedKey)
		}
		when {
			isKey("seed") -> seed = value.toIntOrErrorNotValid(key)
			isKey("skip") -> skip = value.toIntOrErrorNotValid(key)

			isKey("maxArgs") -> maxArgs = value.toIntOrErrorNotValid(key)
			isKey("requestedMinArgs") -> requestedMinArgs = value.toIntOrErrorNotValid(key)
			isKey("activeArgsRangeDecider") -> activeArgsRangeDecider = value
			isKey("activeSuffixArgsGeneratorDecider") -> activeSuffixArgsGeneratorDecider = value
			isKey("activeEnv") -> activeEnv = value
			isKey("defaultProfile") -> defaultProfile = value
			isKey("remindAboutFixedPropertiesAfterMinutes") -> configFileSpecifics.remindAboutFixedPropertiesAfterMinutes =
				value.toIntOrErrorNotValid(key)

			isKey(PROFILES) -> {
				if (value == "clear") testProfiles.clear()
				else parseError("don't know how to interpret $value for $key")
			}

			key.startsWith(PROFILES_PREFIX) -> parseTestProfile(key, value)

			isKey("minimalistPropertiesDir") -> configFileSpecifics.minimalistPropertiesDir = Paths.get(value)

			key.startsWith(ERROR_DEADLINES_PREFIX) -> configFileSpecifics.parseErrorDeadlines(key, value)

			else -> throwUnknownProperty(key, value, supportedKeys)
		}
	}

	private fun MinimalistConfigBuilder.parseTestProfile(key: String, value: String) {
		//TODO 2.1.0 warn about duplicates profile Names / envs, i.e. copy to own maps first and merge in the end
		// (we don't want to error in case we override an existing profile name / env)
		val remainingAfterPrefix = key.substringAfter(PROFILES_PREFIX)
		val profile = remainingAfterPrefix.substringBefore(".")
		if (remainingAfterPrefix == profile) {
			if (value == "clear") testProfiles[profile]?.clear()
			else parseError("don't know how to interpret $value for $key")
		} else {
			val testConfigsPerEnv = testProfiles.computeIfAbsent(profile) { HashMap() }
			val remainingAfterProfile = remainingAfterPrefix.substringAfter("$profile.")
			val env = remainingAfterProfile.substringBefore(".")
			if (remainingAfterProfile == env) {
				if (value == "clear") testConfigsPerEnv.clear()
				else parseError("don't know how to interpret $value for $key")
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

	private fun ConfigFileSpecifics.parseErrorDeadlines(key: String, value: String) {
		val remainingAfterPrefix = key.substringAfter(ERROR_DEADLINES_PREFIX)
		errorDeadlines[remainingAfterPrefix] = LocalDateTime.parse(value)
	}

	private fun throwUnknownProperty(
		key: String,
		value: String,
		supportedKeys: List<String>,
		within: String? = null
	): Nothing {
		throw MinimalistParseException(
			"Unknown minimalist config property $key with value $value -- if you want to introduce custom config properties, then please open a feature request: $FEATURE_REQUEST_URL&title=custom%20config%20properties\nSupported Keys${within?.let { " within $it" } ?: ""}: ${
				supportedKeys.sorted().joinToString(", ")
			}"
		)
	}

	private fun parseError(message: String): Nothing = throw MinimalistParseException(message)

	companion object {
		const val PROFILES = "profiles"
		const val PROFILES_PREFIX = "$PROFILES."
		const val ERROR_DEADLINES_PREFIX = "errorDeadlines."
	}
}

