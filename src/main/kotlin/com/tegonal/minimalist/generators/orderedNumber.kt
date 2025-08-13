package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.*
import com.tegonal.minimalist.utils.BigInt

/**
 * Returns an [OrderedArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toExclusive] using the given
 * [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.intFromUntil(
	from: Int,
	toExclusive: Int,
	step: Int = 1
): OrderedArgsGenerator<Int> = IntFromUntilOrderedArgsGenerator(_components, from, toExclusive, step)

/**
 * Returns an [OrderedArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toExclusive] using the
 * given [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.longFromUntil(
	from: Long,
	toExclusive: Long,
	step: Long = 1L
): OrderedArgsGenerator<Long> = LongFromUntilOrderedArgsGenerator(_components, from, toExclusive, step)

/**
 * Returns an [OrderedArgsGenerator] which generates [BigInt]s ranging [from] (inclusive) to [toExclusive] using the
 * given [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.bigIntFromUntil(
	from: BigInt,
	toExclusive: BigInt,
	step: BigInt = BigInt.ONE
): OrderedArgsGenerator<BigInt> = BigIntFromUntilOrderedArgsGenerator(_components, from, toExclusive, step)


/**
 * Returns an [OrderedArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toInclusive] using the given
 * [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.intFromTo(
	from: Int,
	toInclusive: Int,
	step: Int = 1
): OrderedArgsGenerator<Int> = IntFromToOrderedArgsGenerator(_components, from, toInclusive, step)

/**
 * Returns an [OrderedArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toInclusive] using the
 * given [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.longFromTo(
	from: Long,
	toInclusive: Long,
	step: Long = 1L
): OrderedArgsGenerator<Long> = LongFromToOrderedArgsGenerator(_components, from, toInclusive, step)

/**
 * Returns an [OrderedArgsGenerator] which generates [BigInt]s ranging [from] (inclusive) to [toInclusive] using the
 * given [step].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.bigIntFromTo(
	from: BigInt,
	toInclusive: BigInt,
	step: BigInt = BigInt.ONE
): OrderedArgsGenerator<BigInt> =
	BigIntFromUntilOrderedArgsGenerator(_components, from, toInclusive + BigInt.ONE, step)
