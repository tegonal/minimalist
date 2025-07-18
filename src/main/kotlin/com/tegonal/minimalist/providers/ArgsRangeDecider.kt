package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator

interface ArgsRangeDecider {
	fun decideArgsRange(argsGenerator: ArgsGenerator<*>): ArgsRange
}

data class ArgsRange(val offset: Int, val take: Int)
