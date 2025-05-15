package com.tegonal.minimalist

import java.lang.reflect.Method

interface MaxArgsAndOffsetDeterminer {
	fun determineMaxArgsAndOffset(
        testMethod: Method,
        orderedArgsGenerators: List<Pair<Int, OrderedArgsGenerator<*>>>,
        randomArgsGenerators: List<Pair<Int, RandomArgsGenerator<*>>>
	): Pair<Int, Int>
}
