package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntRangeArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : IntRangeBasedArbArgsGenerator<T>(componentFactoryContainer, from, toExclusive, argsProvider)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongRangeArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : RangeBasedArbArgsGenerator<Long, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Long = random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DoubleRangeArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : RangeBasedArbArgsGenerator<Double, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Double = random.nextDouble(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	arr: Array<out T>
) : IntRangeBasedArbArgsGenerator<T>(componentFactoryContainer, 0, arr.size, { arr[it] })


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	list: List<T>
) : IntRangeBasedArbArgsGenerator<T>(componentFactoryContainer, 0, list.size, { list[it] })

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class IntRangeBasedArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toExclusive: Int,
	argsProvider: (index: Int) -> T
) : RangeBasedArbArgsGenerator<Int, T>(componentFactoryContainer, from, toExclusive, argsProvider) {

	final override fun nextRandom(random: Random): Int = random.nextInt(from, toExclusive)
}
