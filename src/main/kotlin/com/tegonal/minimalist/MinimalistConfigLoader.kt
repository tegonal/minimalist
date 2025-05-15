package com.tegonal.minimalist

import com.tegonal.minimalist.impl.LevelBasedMaxArgsAndOffsetDeterminer
import java.util.Properties

object MinimalistConfigLoader {
	val config by lazy {
		MinimalistConfig().let { config ->
			mergeWithPropertiesInResource(config, "/minimalist.properties")
		}.let { config ->
			mergeWithPropertiesInResource(config, "/minimalist.local.properties")
		}
	}

	private fun mergeWithPropertiesInResource(config: MinimalistConfig, propertiesFile: String): MinimalistConfig =
		this::class.java.getResourceAsStream(propertiesFile)?.let {
			it.use { input ->
				val props = Properties()
				props.load(input)
				val newLevels = HashMap(config.maxArgsLevels)
				props.entries.fold(config) { config, (keyAny, valueAny) ->
					val key = keyAny as String
					val value = valueAny as String
					when {
						key == "activeMaxArgsLevel" -> config.copy(activeMaxArgsLevel = value.toMaxArgsLevel())
						key == "activeMaxArgsAndOffsetDeterminer" -> config.copy(activeMaxArgsAndOffsetDeterminer = value)
						key.startsWith("maxArgsLevel") -> {
							val level = key.substringAfter("maxArgsLevel").toInt()
							check(level > 0) {
								"maxArgs levels start from 1, given $level"
							}
							newLevels.put(level, value.toInt())
							config
						}

						else -> error("Unknown minimalist config property $key with value $value")
					}
					config
				}
				config.copy(maxArgsLevels = newLevels)
			}
		} ?: config

	private fun String.toMaxArgsLevel() = (toIntOrNull() ?: error("$this is not a valid maxArgsLevel")).also {
		check(it <= 0){ "$it is not a valid maxArgsLevel, needs to be greater than 0"}
	}
}

data class MinimalistConfig(
	val activeMaxArgsAndOffsetDeterminer: String = LevelBasedMaxArgsAndOffsetDeterminer::class.qualifiedName ?: error(
		"cannot determine qualified name of LevelBasedMaxArgsAndOffsetDeterminer "
	),

	val activeMaxArgsLevel: Int = 3,
	val maxArgsLevels: Map<Int, Int> = mapOf(
		// as comments a possible way to define the levels
		1 to 1,  // local dev
		2 to 10, // in PR
		3 to 30, // on main
		4 to 50, // nightly
		5 to Int.MAX_VALUE //
	)
) {

}

