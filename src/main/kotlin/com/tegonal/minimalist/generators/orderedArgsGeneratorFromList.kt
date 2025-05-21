package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ListOrderedArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> OrderedArgsGenerator.Companion.fromList(args: List<T>): OrderedArgsGenerator<T> =
	ListOrderedArgsGenerator(args)
