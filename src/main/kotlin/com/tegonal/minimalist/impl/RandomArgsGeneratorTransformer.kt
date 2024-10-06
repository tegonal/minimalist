package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.RandomArgsGenerator

class RandomArgsGeneratorTransformer<T : Args, R : Args>(
    private val baseGenerator: RandomArgsGenerator<T>,
    private val transformation: (Sequence<T>) -> Sequence<R>
) : RandomArgsGenerator<R> {
	override fun generate(): Sequence<R> = baseGenerator.generate().let(transformation)
}
