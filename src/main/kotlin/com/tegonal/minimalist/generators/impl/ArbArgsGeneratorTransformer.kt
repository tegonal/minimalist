package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.config._components

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbArgsGeneratorTransformer<T, R>(
	private val baseGenerator: ArbArgsGenerator<T>,
	private val transformation: (Sequence<T>) -> Sequence<R>
) : BaseArbArgsGenerator<R>(baseGenerator._components) {

	override fun generate(): Sequence<R> = baseGenerator.generate().let(transformation)
}
