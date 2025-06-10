package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ListRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun <T> SemiOrderedArgsGenerator<T>.toRandomArgsGenerator(): RandomArgsGenerator<T> =
	ListRandomArgsGenerator(generate(0).take(size).toList())
