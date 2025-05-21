package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ListRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.toRandomArgsGenerator(): RandomArgsGenerator<T> =
	ListRandomArgsGenerator(generateOrdered(size, 0).toList())
