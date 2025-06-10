package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SemiOrderedArgsWithRandomArgsGeneratorCombiner<A1, A2, R>(
	private val orderedArgsGenerator: SemiOrderedArgsGenerator<A1>,
	private val randomArgsGenerator: RandomArgsGenerator<A2>,
	private val transform: (A1, A2) -> R
) : SemiOrderedArgsGenerator<R> {
	override val size: Int get() = orderedArgsGenerator.size
	override fun generate(offset: Int): Sequence<R> =
		zipDefinedSize(orderedArgsGenerator.generate(offset), randomArgsGenerator.generate(), size, transform)
}
