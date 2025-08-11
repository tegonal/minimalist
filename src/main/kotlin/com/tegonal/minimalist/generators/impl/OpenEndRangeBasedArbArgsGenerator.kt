package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.impl.requireFromLessThanToExclusive
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class OpenEndRangeBasedArbArgsGenerator<E : Comparable<E>, T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	protected val from: E,
	protected val toExclusive: E,
	private val argsProvider: (E) -> T
) : RandomBasedArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {
	init {
		requireFromLessThanToExclusive(from, toExclusive)
	}

	final override fun Random.nextElement(): T =
		argsProvider(nextElementInRange(this))

	protected abstract fun nextElementInRange(random: Random): E

}

