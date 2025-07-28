package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.of(vararg args: T): ArbArgsGenerator<T> =
	ArrayArbArgsGenerator(_components, args)
