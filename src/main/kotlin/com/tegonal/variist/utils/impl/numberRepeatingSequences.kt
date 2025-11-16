package com.tegonal.variist.utils.impl

import com.tegonal.variist.utils.BigInt

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class IntRepeatingSequence<T>(
	private val from: Int,
	private val toExclusive: Int,
	private val offset: Int,
	private val argsProvider: (Int) -> T,
	private val step: Int = 1,
) : Sequence<T> {

	// we don't check the invariants here, they should be checked in the ArgsGenerator and is again checked in the
	// Iterator (as safety net), checking it a third time seems unnecessary

	override fun iterator(): Iterator<T> {
		return object : BaseIntFromUntilRepeatingIterator<T>(from, toExclusive, offset, step) {
			override fun getElementAt(index: Int): T = argsProvider(index)
		}
	}
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongRepeatingSequence<T>(
	private val from: Long,
	private val toInclusive: Long,
	private val offset: Long,
	private val step: Long = 1,
	private val argsProvider: (Long) -> T
) : Sequence<T> {

	// we don't check the invariants here, they should be checked in the ArgsGenerator and is again checked in the
	// Iterator (as safety net), checking it a third time seems unnecessary

	override fun iterator(): Iterator<T> =
		object : BaseLongFromUntilRepeatingIterator<T>(from, toInclusive, offset, step) {
			override fun getElementAt(index: Long): T = argsProvider(index)
		}
}


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class BigIntRepeatingSequence<T>(
	private val from: BigInt,
	private val toInclusive: BigInt,
	private val offset: BigInt,
	private val step: BigInt = BigInt.ONE,
	private val argsProvider: (BigInt) -> T
) : Sequence<T> {

	// we don't check the invariants here, they should be checked in the ArgsGenerator and is again checked in the
	// Iterator (as safety net), checking it a third time seems unnecessary

	override fun iterator(): Iterator<T> =
		object : BaseBigIntFromUntilRepeatingIterator<T>(from, toInclusive, offset, step) {
			override fun getElementAt(index: BigInt): T = argsProvider(index)
		}
}


