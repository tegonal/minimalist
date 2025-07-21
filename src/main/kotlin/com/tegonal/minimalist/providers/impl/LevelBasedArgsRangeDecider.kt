package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.RequiresConfig
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LevelBasedArgsRangeDecider() : ArgsRangeDecider, RequiresConfig {
	private lateinit var config: MinimalistConfig
	override fun setConfig(config: MinimalistConfig) {
		this.config = config
	}

	override fun decideArgsRange(argsGenerator: ArgsGenerator<*>): ArgsRange {
		val maxInLevel = config.maxArgsLevels.getLevel(config.activeMaxArgsLevel)
		return ArgsRange(
			offset = config.seed,
			take =
				if (maxInLevel == 1) maxInLevel
				else if (argsGenerator is SemiOrderedArgsGenerator<*>) minOf(maxInLevel, argsGenerator.size)
				else maxInLevel

		)
	}

}
