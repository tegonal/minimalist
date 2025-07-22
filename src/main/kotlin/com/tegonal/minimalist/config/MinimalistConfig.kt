package com.tegonal.minimalist.config

import com.tegonal.minimalist.providers.impl.LevelBasedArgsRangeDecider
import kotlin.random.Random

/**
 * @since 2.0.0
 */
data class MinimalistConfig(
	val seed: Int = Random.nextInt(0, Int.MAX_VALUE),

	val activeArgsRangeDecider: String = LevelBasedArgsRangeDecider::class.qualifiedName ?: error(
		"cannot determine qualified name of LevelBasedArgsRangeDecider "
	),

	val activeMaxArgsLevel: Int = 3,
	val maxArgsLevels: MaxArgLevels = 10_000.let { maxArgsInGeneral ->
		MaxArgLevels(
			maxArgsInGeneral,
			mapOf(
				// the following comments describe a possible way to define/use the levels
				1 to 3,  // local dev
				2 to 10, // in PR
				3 to 30, // on main
				4 to 50, // nightly
				5 to maxArgsInGeneral // on release branch
			)
		)
	},
) {
	init {
		val levels = maxArgsLevels.toHashMap()

		check(activeMaxArgsLevel in levels) {
			"Your specified activeMaxArgsLevel (${activeMaxArgsLevel}) is not defined in maxArgsLevels (existing levels: ${
				levels.keys.joinToString(", ")
			}"
		}
	}

	fun toBuilder(): MinimalistConfigBuilder = MinimalistConfigBuilder(
		seed,
		activeArgsRangeDecider,
		activeMaxArgsLevel,
		maxArgsLevels.maxArgsInGeneral,
		maxArgsLevels.toHashMap()
	)
}

/**
 * @since 2.0.0
 */
class MinimalistConfigBuilder(
	var seed: Int,
	var activeArgsRangeDecider: String,
	var activeMaxArgsLevel: Int = 3,
	var maxArgsInGeneral: Int,
	var maxArgsLevels: HashMap<Int, Int>
) {
	fun build(): MinimalistConfig = MinimalistConfig(
		seed,
		activeArgsRangeDecider,
		activeMaxArgsLevel,
		MaxArgLevels(maxArgsInGeneral, maxArgsLevels)
	)
}

/**
 * Our way to inject the config after ServiceLoader creation.
 *
 * @since 2.0.0
 */
interface RequiresConfig {
	fun setConfig(config: MinimalistConfig)
}
