package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator

/**
 * @since 2.0.0
 */
fun <T> RandomArgsGenerator.Companion.of(vararg args: T): RandomArgsGenerator<T> =
	ArrayRandomArgsGenerator(args)
