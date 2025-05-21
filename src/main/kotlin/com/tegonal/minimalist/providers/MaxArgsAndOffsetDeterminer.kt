package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.RandomArgsGenerator
import java.lang.reflect.Method

interface MaxArgsAndOffsetDeterminer {
	fun determineMaxArgsAndOffset(
        testMethod: Method,
        orderedArgsGenerators: List<Pair<Int, OrderedArgsGenerator<*>>>,
        randomArgsGenerators: List<Pair<Int, RandomArgsGenerator<*>>>
	): Pair<Int, Int>
}
