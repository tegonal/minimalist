package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LevelBasedArgsRangeDecider() : BaseOptionsBasedArgsRangeDecider() {

	override fun decideArgsRange(
		category: String,
		level: String,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange = argsGenerator._components.config.let { config ->
		ArgsRange(offset = config.seed, take = config.categorizedMaxArgsLevels[category][level])
	}
}
