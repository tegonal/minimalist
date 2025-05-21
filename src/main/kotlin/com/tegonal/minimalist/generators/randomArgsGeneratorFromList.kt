package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ListRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> RandomArgsGenerator.Companion.fromList(args: List<T>): RandomArgsGenerator<T> =
	ListRandomArgsGenerator(args)
