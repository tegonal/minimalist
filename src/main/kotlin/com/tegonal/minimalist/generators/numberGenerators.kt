package com.tegonal.minimalist.generators

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.generators.impl.DoubletRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.LongRangeRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.intFromUntil(
	from: Int,
	toExclusive: Int,
): RandomArgsGenerator<Int> = IntRangeRandomArgsGenerator(from, toExclusive, ::identity)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.longFromUntil(
	from: Long,
	toExclusive: Long,
): RandomArgsGenerator<Long> = LongRangeRandomArgsGenerator(from, toExclusive, ::identity)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.doubleFromUntil(
	from: Double,
	toExclusive: Double,
): RandomArgsGenerator<Double> = DoubletRangeRandomArgsGenerator(from, toExclusive, ::identity)
