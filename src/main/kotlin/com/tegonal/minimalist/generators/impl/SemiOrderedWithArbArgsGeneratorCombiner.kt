package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.config._components

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SemiOrderedWithArbArgsGeneratorCombiner<A1, A2, R>(
	private val semiOrderedArgsGenerator: SemiOrderedArgsGenerator<A1>,
	private val arbArgsGenerator: ArbArgsGenerator<A2>,
	private val transform: (A1, A2) -> R
) : BaseSemiOrderedArgsGenerator<R>(semiOrderedArgsGenerator._components, semiOrderedArgsGenerator.size) {

	override fun generateAfterChecks(offset: Int): Sequence<R> =
		zipForever(semiOrderedArgsGenerator.generate(offset), arbArgsGenerator.generate(), transform)
}
