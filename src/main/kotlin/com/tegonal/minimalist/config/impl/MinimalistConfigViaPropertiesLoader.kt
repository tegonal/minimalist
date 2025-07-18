package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import java.util.Properties
import kotlin.check

class MinimalistConfigViaPropertiesLoader {
	val config by lazy {
		MinimalistConfig()
			.run { mergeWithPropertiesInResource("/minimalist.properties") }
			.run { mergeWithPropertiesInResource("/minimalist.local.properties") }
			.also {
				println("Loaded a minimalist config with seed: ${it.seed}")
			}
	}

	private fun MinimalistConfig.mergeWithPropertiesInResource(propertiesFile: String): MinimalistConfig =
		this::class.java.getResourceAsStream(propertiesFile)?.let {
			it.use { input ->
				val props = Properties()
				props.load(input)
				val builder = this.toBuilder()

				props.forEach { (keyAny, valueAny) ->
					val key = keyAny as String
					val value = valueAny as String

					when {
						key == "seed" -> builder.seed = value.toInt()
						key == "activeMaxArgsLevel" -> builder.activeMaxArgsLevel = value.toMaxArgsLevel()
						key == "activeArgsRangeDecider" -> builder.activeArgsRangeDecider = value
						key.startsWith("maxArgsLevel") -> {
							val level = key.substringAfter("maxArgsLevel").toMaxArgsLevel()
							builder.maxArgsLevels.put(level, value.toInt())
						}

						else -> error("Unknown minimalist config property $key with value $value -- if you want to introduce custom config properties, then please open a feature request: $FEATURE_REQUEST_URL&title=custom%20config%20properties")
					}
				}
				builder.build()
			}
		} ?: this

	private fun String.toMaxArgsLevel(): Int =
		(toIntOrNull() ?: error("$this is not a valid maxArgsLevel")).also {
			check(it >= 1) { "$it is not a valid maxArgsLevel, needs to be greater than 0" }
		}
}

