package com.tegonal.variist.utils.impl

import com.tegonal.variist.utils.BigInt

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntFromUntilRepeatingIterator(
	from: Int,
	toExclusive: Int,
	offset: Int = 0,
	step: Int = 1,
) : BaseIntFromUntilRepeatingIterator<Int>(from, toExclusive, offset, step) {
	override fun getElementAt(index: Int): Int = index
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongFromUntilRepeatingIterator(
	from: Long,
	toExclusive: Long,
	offset: Long = 0,
	step: Long = 1,
) : BaseLongFromUntilRepeatingIterator<Long>(from, toExclusive, offset, step) {
	override fun getElementAt(index: Long): Long = index
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class BigIntFromUntilRepeatingIterator(
	from: BigInt,
	toExclusive: BigInt,
	offset: BigInt = BigInt.ZERO,
	step: BigInt = BigInt.ONE,
) : BaseBigIntFromUntilRepeatingIterator<BigInt>(from, toExclusive, offset, step) {
	override fun getElementAt(index: BigInt): BigInt = index
}
