package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.RandomArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class RangeBasedRandomArgsGenerator<E : Comparable<E>, T>(
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

