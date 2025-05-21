package com.tegonal.minimalist.generators.impl

import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntRangeRandomArgsGenerator<T>(
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : IntRangeBasedRandomArgsGenerator<T>(from, toExclusive, argsProvider)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongRangeRandomArgsGenerator<T>(
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : RangeBasedRandomArgsGenerator<Long, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Long = Random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DoubletRangeRandomArgsGenerator<T>(
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : RangeBasedRandomArgsGenerator<Double, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Double = Random.nextDouble(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayRandomArgsGenerator<T>(
	arr: Array<out T>
) : IntRangeBasedRandomArgsGenerator<T>(0, arr.size, { arr[it] })


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListRandomArgsGenerator<T>(
	list: List<T>
) : IntRangeBasedRandomArgsGenerator<T>(0, list.size, { list[it] })

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class IntRangeBasedRandomArgsGenerator<T>(
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : RangeBasedRandomArgsGenerator<Int, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Int = Random.nextInt(from, toExclusive)
}
