package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.OrderedArgsGenerator
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntRangeRandomArgsGenerator<T : Args>(
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : RangeBasedRandomArgsGenerator<Int, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Int = Random.nextInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongRangeRandomArgsGenerator<T : Args>(
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
class DoubletRangeRandomArgsGenerator<T : Args>(
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : RangeBasedRandomArgsGenerator<Double, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Double = Random.nextDouble(from, toExclusive)
}
