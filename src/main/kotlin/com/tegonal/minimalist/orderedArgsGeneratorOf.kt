package com.tegonal.minimalist

import com.tegonal.minimalist.impl.ArrayOrderedArgsGenerator

fun <T> OrderedArgsGenerator.Companion.of(vararg args: T): OrderedArgsGenerator<Args1<T>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

fun <T : Args> OrderedArgsGenerator.Companion.of(vararg args: T): OrderedArgsGenerator<T> = ArrayOrderedArgsGenerator(args)
