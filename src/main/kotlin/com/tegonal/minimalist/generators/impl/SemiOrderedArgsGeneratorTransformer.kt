package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SemiOrderedArgsGeneratorTransformer<T, R>(
	private val baseGenerator: SemiOrderedArgsGenerator<T>,
	private val transformation: (Sequence<T>) -> Sequence<R>
) : SemiOrderedArgsGenerator<R> {
	override val size: Int get() = baseGenerator.size
	override fun generate(offset: Int): Sequence<R> =
		baseGenerator.generate(offset).let(transformation)
}
