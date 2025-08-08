package com.tegonal.minimalist.providers

import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.impl.checkIsPositive
import com.tegonal.minimalist.config.impl.failIfNegative

/**
 * Responsible to decide what range of arguments should be generated for a given [ArgsGenerator].
 *
 * @since 2.0.0
 */
interface ArgsRangeDecider {
	/**
	 * Returns an [ArgsRange] based on the given [argsGenerator] (and most likely the underlying [MinimalistConfig])
	 * and [argsRangeOptions].
	 */
	fun decide(argsGenerator: ArgsGenerator<*>, argsRangeOptions: ArgsRangeOptions? = null): ArgsRange
}


/**
 * Represents a range which an [ArgsGenerator] should generate.
 *
 * @since 2.0.0
 */
data class ArgsRange(val offset: Int, val take: Int) {
	init {
		failIfNegative(offset, "offset")
		checkIsPositive(take, "take")
	}
}
