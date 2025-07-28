package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class OpenEndRangeBasedArbArgsGenerator<E : Comparable<E>, T>(
	componentFactoryContainer: ComponentFactoryContainer,
	protected val from: E,
	protected val toExclusive: E,
	private val argsProvider: (E) -> T
) : RandomBasedArbArgsGenerator<T>(componentFactoryContainer) {
	init {
		require(from < toExclusive) {
			"from ($from) needs to be less than toExclusive ($toExclusive)"
		}
	}

	final override fun Random.nextElement(): T =
		argsProvider(nextElementInRange(this))

	protected abstract fun nextElementInRange(random: Random): E

}

