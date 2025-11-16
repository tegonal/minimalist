package com.tegonal.variist.generators.impl

import com.tegonal.variist.config._components
import com.tegonal.variist.config.arb
import com.tegonal.variist.generators.ArbArgsGenerator
import com.tegonal.variist.generators.ArbExtensionPoint
import com.tegonal.variist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SemiOrderedFlatZipArbArgsGenerator<A1, A2, R>(
	private val semiOrderedArgsGenerator: SemiOrderedArgsGenerator<A1>,
	private val otherFactory: ArbExtensionPoint.(A1) -> ArbArgsGenerator<A2>,
	private val amount: Int,
	private val transform: (A1, A2) -> R
) : BaseSemiOrderedArgsGenerator<R>(semiOrderedArgsGenerator._components, semiOrderedArgsGenerator.size * amount) {

	//TODO 2.0.0 implement generateOne

	@OptIn(InternalDangerousApi::class)
	override fun generateAfterChecks(offset: Int): Sequence<R> {
		val orderedOffset = offset / amount
		// same as offset % amount but usually faster, as division is usually slower than
		// substraction and multiplication together
		val transformationOffset = offset - orderedOffset * amount
		return semiOrderedArgsGenerator.flatMapIndexedInternal { index, a1: A1 ->
			componentFactoryContainer.arb.otherFactory(a1).generate(seedOffset = index).take(amount).map { a2 ->
				transform(a1, a2)
			}
		}.generate(orderedOffset).drop(transformationOffset)
	}
}

