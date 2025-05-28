package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultSemiOrderedArgsGenerator<A1, A2>(
	private val orderedArgsGenerator: OrderedArgsGenerator<A1>,
	private val randomArgsGenerator: RandomArgsGenerator<A2>,
) : SemiOrderedArgsGenerator<A1, A2> {
	override val size: Int get() = orderedArgsGenerator.size
	override fun generateOrdered(offset: Int): Sequence<Pair<A1, A2>> =
		orderedArgsGenerator.generateOrdered(offset).zip(randomArgsGenerator.generate())
}
