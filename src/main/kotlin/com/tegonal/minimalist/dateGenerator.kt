package com.tegonal.minimalist

import com.tegonal.minimalist.impl.ArrayArgsGenerator
import java.util.Date

fun <T> RandomArgsGenerator.Companion.dateFromUpTo(from: Date, toInclusive: Date): RandomArgsGenerator<Args1<T>> =
	(from .. toInclusive)
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

fun <T : Args> RandomArgsGenerator.Companion.of(vararg args: T): OrderedArgsGenerator<T> = ArrayArgsGenerator(args)


