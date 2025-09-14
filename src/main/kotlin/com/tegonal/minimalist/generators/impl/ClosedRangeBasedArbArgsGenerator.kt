package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.impl.requireFromLessThanOrEqualToExclusive
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class ClosedRangeBasedArbArgsGenerator<T : Comparable<T>>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	protected val from: T,
	protected val toInclusive: T,
) : RandomBasedArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {
	init {
		requireFromLessThanOrEqualToExclusive(from, toInclusive)
	}

	final override fun Random.nextElement(): T = nextElementInRange(this)

	protected abstract fun nextElementInRange(random: Random): T
}

