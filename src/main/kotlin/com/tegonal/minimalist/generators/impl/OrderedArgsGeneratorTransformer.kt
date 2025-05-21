package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class OrderedArgsGeneratorTransformer<T, R>(
	private val baseGenerator: OrderedArgsGenerator<T>,
	private val transformation: (Sequence<T>) -> Sequence<R>
) : OrderedArgsGenerator<R> {
	override val size: Int get() = baseGenerator.size
	override fun generateOrdered(offset: Int): Sequence<R> =
		baseGenerator.generateOrdered(offset).let(transformation)
}
