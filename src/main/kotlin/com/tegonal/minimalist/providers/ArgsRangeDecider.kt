package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator

/**
 * Responsible to decide what range of arguments should be generated for a given [ArgsGenerator].
 *
 * @since 2.0.0
 */
interface ArgsRangeDecider  {
	fun decideArgsRange(argsGenerator: ArgsGenerator<*>): ArgsRange
}

/**
 * Represents a range of arguments of an [ArgsGenerator].
 *
 * @since 2.0.0
 */
data class ArgsRange(val offset: Int, val take: Int)
