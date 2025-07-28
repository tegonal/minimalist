package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class IntFromUntilArbArgsGenerator<T>(
    componentFactoryContainer: ComponentFactoryContainer,
    from: Int,
    toExclusive: Int,
    argsProvider: (index: Int) -> T
) : OpenEndRangeBasedArbArgsGenerator<Int, T>(componentFactoryContainer, from, toExclusive, argsProvider) {

    final override fun nextRandom(random: Random): Int = random.nextInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : OpenEndRangeBasedArbArgsGenerator<Long, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Long = random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DoubleFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : OpenEndRangeBasedArbArgsGenerator<Double, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Double = random.nextDouble(from, toExclusive)
}
