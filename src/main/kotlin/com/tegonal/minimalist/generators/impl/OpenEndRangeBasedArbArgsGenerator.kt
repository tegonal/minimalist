package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.repeatForever
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
) : BaseArbArgsGenerator<T>(componentFactoryContainer) {
	init {
		require(from < toExclusive) {
			"from ($from) needs to be less than toExclusive ($toExclusive)"
		}
	}

	protected abstract fun nextRandom(random: Random): E

	override fun generate(): Sequence<T> = createMinimalistRandom().let { random ->
		repeatForever().map {
			// Random is not thread safe, add synchronisation in case we run into issues
			argsProvider(nextRandom(random))
		}
	}
}

