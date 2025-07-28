package com.tegonal.minimalist.generators

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArbDoubleArgsGenerator
import com.tegonal.minimalist.generators.impl.ArbIntArgsGenerator
import com.tegonal.minimalist.generators.impl.ArbLongArgsGenerator
import com.tegonal.minimalist.generators.impl.DoubleFromUntilArbArgsGenerator
import com.tegonal.minimalist.generators.impl.IntFromToArbArgsGenerator
import com.tegonal.minimalist.generators.impl.IntFromUntilArbArgsGenerator
import com.tegonal.minimalist.generators.impl.LongFromUntilArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging from
 * [Int.MIN_VALUE] (inclusive) to [Int.MAX_VALUE] (inclusive).
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.int(): ArbArgsGenerator<Int> =
	ArbIntArgsGenerator(_components)

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging from
 * [Long.MIN_VALUE] (inclusive) to [Long.MAX_VALUE] (inclusive).
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.long(): ArbArgsGenerator<Long> =
	ArbLongArgsGenerator(_components)

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging from
 * [Double.MIN_VALUE] (inclusive) to [Double.MAX_VALUE] (inclusive).
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.double(): ArbArgsGenerator<Double> =
	ArbDoubleArgsGenerator(_components)

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.intFromUntil(
	from: Int,
	toExclusive: Int,
): ArbArgsGenerator<Int> = IntFromUntilArbArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [ArbArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.longFromUntil(
	from: Long,
	toExclusive: Long,
): ArbArgsGenerator<Long> = LongFromUntilArbArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [ArbArgsGenerator] which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator] which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): ArbArgsGenerator<Double> = DoubleFromUntilArbArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toInclusive].
 *
 * @return an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toInclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.intFromTo(
	from: Int,
	toInclusive: Int,
): ArbArgsGenerator<Int> = IntFromToArbArgsGenerator(_components, from, toInclusive, ::identity)

//TODO 2..0.0 add longFromTo which is based on mergeWeighted
