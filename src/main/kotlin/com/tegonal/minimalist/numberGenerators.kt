package com.tegonal.minimalist

import com.tegonal.minimalist.impl.DoubletRangeRandomArgsGenerator
import com.tegonal.minimalist.impl.IntRangeRandomArgsGenerator
import com.tegonal.minimalist.impl.LongRangeRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.intFromUntil(
	from: Int,
	toExclusive: Int,
): RandomArgsGenerator<Args1<Int>> = IntRangeRandomArgsGenerator(from, toExclusive, Args::of)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.longFromUntil(
	from: Long,
	toExclusive: Long,
): RandomArgsGenerator<Args1<Long>> = LongRangeRandomArgsGenerator(from, toExclusive, Args::of)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): RandomArgsGenerator<Args1<Double>> = DoubletRangeRandomArgsGenerator(from, toExclusive, Args::of)
