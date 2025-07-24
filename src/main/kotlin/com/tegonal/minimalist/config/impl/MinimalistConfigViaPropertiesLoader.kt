package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import java.util.Properties

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistConfigViaPropertiesLoader {
	val config by lazy {
		MinimalistConfig()
			.run { mergeWithPropertiesInResource("/minimalist.properties") }
			.run { mergeWithPropertiesInResource("/minimalist.local.properties") }
			.also {
				println("Loaded a minimalist config with seed: ${it.seed}")
				//TODO 2.1.0 add seedCreated to MinimalistConfig and error after x hours, minutes or whatever (could be configurable)
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
						key == "seed" -> builder.seed = value.toIntOrErrorNotValid(key)
						key == "offsetToDecidedOffset" -> builder.offsetToDecidedOffset =
							value.toIntOrErrorNotValid(key)

						key == "atMostArgs" -> builder.atMostArgs = value.toIntOrErrorNotValid(key)
						key == "atLeastArgs" -> builder.atLeastArgs = value.toIntOrErrorNotValid(key)
						key == "activeArgsRangeDecider" -> builder.activeArgsRangeDecider = value
						key == "activeMaxArgsLevel" -> builder.activeMaxArgsLevel = value
						key == "defaultMaxArgsLevelCategory" -> builder.defaultMaxArgsLevelCategory = value

						key == MAX_ARGS_LEVEL -> {
							if (value == "clear") builder.categoryToMaxArgsLevel.clear()
							else error("don't know how to interpret $value for $key")
						}

						key.startsWith(MAX_ARGS_LEVEL_PREFIX) -> {
							val remaining = key.substringAfter(MAX_ARGS_LEVEL_PREFIX)
							val category = remaining.substringBefore(".")
							if (remaining == category) {
								if (value == "clear") builder.categoryToMaxArgsLevel[category]?.clear()
								else error("don't know how to interpret $value for $key")
							} else {
								val levels = builder.categoryToMaxArgsLevel.computeIfAbsent(category) { HashMap() }
								val level = remaining.substringAfter("$category.")
								levels.put(level, value.toIntOrErrorNotValid(key))
							}
						}

						else -> error("Unknown minimalist config property $key with value $value -- if you want to introduce custom config properties, then please open a feature request: $FEATURE_REQUEST_URL&title=custom%20config%20properties")
					}
				}
				builder.build()
			}
		} ?: this

	companion object {
		const val MAX_ARGS_LEVEL = "maxArgsLevels"
		const val MAX_ARGS_LEVEL_PREFIX = "$MAX_ARGS_LEVEL."
	}
}

