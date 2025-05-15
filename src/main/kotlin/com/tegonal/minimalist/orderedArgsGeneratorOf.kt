package com.tegonal.minimalist

import com.tegonal.minimalist.impl.ArrayOrderedArgsGenerator

/**
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator.Companion.of(vararg args: T): OrderedArgsGenerator<Args1<T>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * @since 2.0.0
 */
fun <T : Args> OrderedArgsGenerator.Companion.of(vararg args: T): OrderedArgsGenerator<T> =
	ArrayOrderedArgsGenerator(args)
