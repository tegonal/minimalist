package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.config._components

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
) : BaseSemiOrderedArgsGenerator<R>(orderedArgsGenerator._components, orderedArgsGenerator.size) {

	override fun generateAfterChecks(offset: Int): Sequence<R> =
		zipForever(orderedArgsGenerator.generate(offset), randomArgsGenerator.generate(), transform)
}
