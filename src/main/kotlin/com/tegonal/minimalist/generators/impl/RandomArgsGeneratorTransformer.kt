package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.RandomArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RandomArgsGeneratorTransformer<T , R >(
    private val baseGenerator: RandomArgsGenerator<T>,
    private val transformation: (Sequence<T>) -> Sequence<R>
) : RandomArgsGenerator<R> {
	override fun generate(): Sequence<R> = baseGenerator.generate().let(transformation)
}
