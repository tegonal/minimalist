@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.DoubleRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.LongRangeRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [RandomArgsGenerator]  which generates [Int]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.intFromUntil(
	from: Int,
	toExclusive: Int,
): RandomArgsGenerator<Int> = IntRangeRandomArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [RandomArgsGenerator] which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [RandomArgsGenerator]  which generates [Long]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.longFromUntil(
	from: Long,
	toExclusive: Long,
): RandomArgsGenerator<Long> = LongRangeRandomArgsGenerator(_components, from, toExclusive, ::identity)

/**
 * Returns an [RandomArgsGenerator] which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 *
 * @return an [RandomArgsGenerator]  which generates [Double]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): RandomArgsGenerator<Double> = DoubleRangeRandomArgsGenerator(_components, from, toExclusive, ::identity)
