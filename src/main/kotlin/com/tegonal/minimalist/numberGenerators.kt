package com.tegonal.minimalist

import com.tegonal.minimalist.impl.IntRangeRandomArgsGenerator
import com.tegonal.minimalist.impl.LongRangeRandomArgsGenerator

fun RandomArgsGenerator.Companion.intFromUntil(
	from: Int,
	toExclusive: Int,
): RandomArgsGenerator<Args1<Int>> = IntRangeRandomArgsGenerator(from, toExclusive) { Args.of(it) }

fun RandomArgsGenerator.Companion.longFromUntil(
	from: Long,
	toExclusive: Long,
): RandomArgsGenerator<Args1<Long>> = LongRangeRandomArgsGenerator(from, toExclusive) { Args.of(it) }
