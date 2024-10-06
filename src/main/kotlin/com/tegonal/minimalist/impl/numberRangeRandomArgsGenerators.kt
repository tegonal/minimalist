package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import kotlin.random.Random

class IntRangeRandomArgsGenerator<T : Args>(
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : RangeBasedRandomArgsGenerator<Int, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Int = Random.nextInt(from, toExclusive)
}

class LongRangeRandomArgsGenerator<T : Args>(
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : RangeBasedRandomArgsGenerator<Long, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Long = Random.nextLong(from, toExclusive)
}

class DoubletRangeRandomArgsGenerator<T : Args>(
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : RangeBasedRandomArgsGenerator<Double, T>(from, toExclusive, argsProvider) {
	override fun nextRandom(): Double = Random.nextDouble(from, toExclusive)
}
