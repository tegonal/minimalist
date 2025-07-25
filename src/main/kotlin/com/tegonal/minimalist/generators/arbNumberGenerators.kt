@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.DoubleRangeArbArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeArbArgsGenerator
import com.tegonal.minimalist.generators.impl.LongRangeArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator]  which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.intFromUntil(
	from: Int,
	toExclusive: Int,
): ArbArgsGenerator<Int> = IntRangeArbArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [ArbArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator]  which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.longFromUntil(
	from: Long,
	toExclusive: Long,
): ArbArgsGenerator<Long> = LongRangeArbArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [ArbArgsGenerator] which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [ArbArgsGenerator]  which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): ArbArgsGenerator<Double> = DoubleRangeArbArgsGenerator(_components, from, toExclusive, ::identity)
