package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.config._components

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RandomArgsGeneratorTransformer<T, R>(
	private val baseGenerator: RandomArgsGenerator<T>,
	private val transformation: (Sequence<T>) -> Sequence<R>
) : BaseRandomArgsGenerator<R>(baseGenerator._components) {

	override fun generate(): Sequence<R> = baseGenerator.generate().let(transformation)
}
