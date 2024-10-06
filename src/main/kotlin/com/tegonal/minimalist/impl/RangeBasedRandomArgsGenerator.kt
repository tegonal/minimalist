package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.RandomArgsGenerator
import kotlin.random.Random

abstract class RangeBasedRandomArgsGenerator<E : Comparable<E>, T : Args>(
	protected val from: E,
	protected val toExclusive: E,
	private val argsProvider: (E) -> T
) : RandomArgsGenerator<T> {
	init {
		require(from < toExclusive) {
			"from ($from) needs to be less than toExclusive ($toExclusive)"
		}
	}

	protected abstract fun nextRandom(): E

	override fun generate(): Sequence<T> = generateSequence {
		argsProvider(nextRandom())
	}
}

