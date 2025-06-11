package com.tegonal.minimalist.generators

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.generators.impl.DoubleRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.LongRangeRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun random.intFromUntil(
	from: Int,
	toExclusive: Int,
): RandomArgsGenerator<Int> = IntRangeRandomArgsGenerator(from, toExclusive, ::identity)

/**
 * @since 2.0.0
 */
fun random.longFromUntil(
	from: Long,
	toExclusive: Long,
): RandomArgsGenerator<Long> = LongRangeRandomArgsGenerator(from, toExclusive, ::identity)

/**
 * @since 2.0.0
 */
fun random.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): RandomArgsGenerator<Double> = DoubleRangeRandomArgsGenerator(from, toExclusive, ::identity)
