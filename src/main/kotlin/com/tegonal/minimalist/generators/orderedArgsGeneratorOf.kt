package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator

/**
 * @since 2.0.0
 */
fun <T> ordered.of(vararg args: T): OrderedArgsGenerator<T> =
	ArrayOrderedArgsGenerator(args)
