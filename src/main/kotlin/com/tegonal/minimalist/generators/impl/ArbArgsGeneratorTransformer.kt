package com.tegonal.variist.generators.impl

import com.tegonal.variist.generators.ArbArgsGenerator
import com.tegonal.variist.generators._core

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbArgsGeneratorTransformer<T, R>(
	private val baseGenerator: ArbArgsGenerator<T>,
	private val transform: (Sequence<T>, seedOffset: Int) -> Sequence<R>
) : BaseArbArgsGenerator<R>(baseGenerator._core) {

	override fun generate(seedOffset: Int): Sequence<R> {
		val seq = baseGenerator.generate(seedOffset)
		return transform(seq, seedOffset)
	}
}
