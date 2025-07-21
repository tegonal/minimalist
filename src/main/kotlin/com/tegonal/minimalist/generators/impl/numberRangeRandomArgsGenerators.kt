package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntRangeRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : IntRangeBasedRandomArgsGenerator<T>(componentFactoryContainer, from, toExclusive, argsProvider)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongRangeRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : RangeBasedRandomArgsGenerator<Long, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Long = random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DoubleRangeRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : RangeBasedRandomArgsGenerator<Double, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	override fun nextRandom(random: Random): Double = random.nextDouble(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	arr: Array<out T>
) : IntRangeBasedRandomArgsGenerator<T>(componentFactoryContainer, 0, arr.size, { arr[it] })


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	list: List<T>
) : IntRangeBasedRandomArgsGenerator<T>(componentFactoryContainer, 0, list.size, { list[it] })

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class IntRangeBasedRandomArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toExclusive: Int,
	argsProvider: (index: Int) -> T
) : RangeBasedRandomArgsGenerator<Int, T>(componentFactoryContainer, from, toExclusive, argsProvider) {

	final override fun nextRandom(random: Random): Int = random.nextInt(from, toExclusive)
}
