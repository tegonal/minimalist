package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> OrderedExtensionPoint.of(vararg args: T): OrderedArgsGenerator<T> =
	ArrayOrderedArgsGenerator(_components, args)
