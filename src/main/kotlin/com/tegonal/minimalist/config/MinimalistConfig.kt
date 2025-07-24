package com.tegonal.minimalist.config

import com.tegonal.minimalist.config.impl.failIfNegative
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.impl.LevelBasedArgsRangeDecider
import kotlin.random.Random

/**
 * @since 2.0.0
 */
data class MinimalistConfig(
	val seed: Int = Random.nextInt(0, Int.MAX_VALUE),

	/**
	 * Set to a value if you want to test a particular case (maybe together with [ArgsRangeOptions.atMostArgs] = 1)
	 */
	val offsetToDecidedOffset: Int? = null,

	/**
	 * Set to a value if you want to test a particular case (most likely together with [offsetToDecidedOffset]).
	 *
	 * This is meant to take precedence over an [ArgsRangeDecider] (but depends on the implementation of the [ArgsRangeDecider])
	 */
	val argsRangeOptions: ArgsRangeOptions = ArgsRangeOptions(),

	val activeArgsRangeDecider: String = LevelBasedArgsRangeDecider::class.qualifiedName ?: error(
		"cannot determine qualified name of LevelBasedArgsRangeDecider "
	),

	/**
	 * Defines which category (see [MaxArgsCategory] for predefined categories - you can also define own) is chosen
	 * in case none is specified via [ArgsRangeOptions.category] (either in [argsRangeOptions] or in
	 * [AnnotationData.argsRangeOptions])
	 */
	val defaultMaxArgsLevelCategory: String = MaxArgsCategory.Integration.name,

	/**
	 * Defines which level an [ArgsRangeDecider] shall consider.
	 */
	val activeMaxArgsLevel: String = MaxArgsLevel.Local.name,

	/**
	 * Allows to define different categories (of tests) with different associated [MaxArgsLevels]
	 */
	val categorizedMaxArgsLevels: CategorizedMaxArgsLevels = CategorizedMaxArgsLevels.create(
		MaxArgsCategory.Unit to
			MaxArgsLevels.create(
				MaxArgsLevel.Local to 500,
				MaxArgsLevel.PR to 1000,
				MaxArgsLevel.Main to 2000,
				MaxArgsLevel.Nightly to 5000,
				MaxArgsLevel.Release to 3000,
			),
		MaxArgsCategory.Integration to
			MaxArgsLevels.create(
				MaxArgsLevel.Local to 3,
				MaxArgsLevel.PR to 10,
				MaxArgsLevel.Main to 50,
				MaxArgsLevel.Nightly to 150,
				MaxArgsLevel.Release to 75,
			),
	),
) {
	init {
		failIfNegative(seed, "seed")
		offsetToDecidedOffset?.also { failIfNegative(it, "offsetToDecidedOffset") }

		val defaultMaxArgsLevels = categorizedMaxArgsLevels.find(defaultMaxArgsLevelCategory) ?: error(
			"Your specified defaultMaxArgsLevelCategory ($defaultMaxArgsLevelCategory) does not exists, existing categories: ${
				categorizedMaxArgsLevels.categories().joinToString(",")
			}"
		)
		check(activeMaxArgsLevel in defaultMaxArgsLevels) {
			"Your specified activeMaxArgsLevel (${activeMaxArgsLevel}) is not defined in MaxArgsLevels (existing levels: ${
				defaultMaxArgsLevels.levels().joinToString(", ")
			})"
		}
	}

	fun toBuilder(): MinimalistConfigBuilder = MinimalistConfigBuilder(
		seed = seed,
		offsetToDecidedOffset = offsetToDecidedOffset,
		atMostArgs = argsRangeOptions.atMostArgs,
		atLeastArgs = argsRangeOptions.requestedMinArgs,
		activeArgsRangeDecider = activeArgsRangeDecider,
		activeMaxArgsLevel = activeMaxArgsLevel,
		defaultMaxArgsLevelCategory = defaultMaxArgsLevelCategory,
		categoryToMaxArgsLevel = categorizedMaxArgsLevels.toHashMap()
	)
}

/**
 * @since 2.0.0
 */
class MinimalistConfigBuilder(
	var seed: Int,
	var offsetToDecidedOffset: Int?,
	var atMostArgs: Int?,
	var atLeastArgs: Int?,
	var activeArgsRangeDecider: String,
	var activeMaxArgsLevel: String,
	var defaultMaxArgsLevelCategory: String,
	var categoryToMaxArgsLevel: HashMap<String, HashMap<String, Int>>
) {
	fun build(): MinimalistConfig = MinimalistConfig(
		seed = seed,
		offsetToDecidedOffset = offsetToDecidedOffset,
		argsRangeOptions = ArgsRangeOptions(requestedMinArgs = atLeastArgs, atMostArgs = atMostArgs),
		activeArgsRangeDecider = activeArgsRangeDecider,
		activeMaxArgsLevel = activeMaxArgsLevel,
		defaultMaxArgsLevelCategory = defaultMaxArgsLevelCategory,
		categorizedMaxArgsLevels = CategorizedMaxArgsLevels.create(
			categoryToMaxArgsLevel.entries.associate { (category, levels) -> category to MaxArgsLevels.create(levels) }
		)
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

/**
 * Predefined category names for [MinimalistConfig.categorizedMaxArgsLevels]
 *
 * @since 2.0.0
 */
enum class MaxArgsCategory {
	Unit, Integration
}
