package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.of(vararg args: T): ArbArgsGenerator<T> =
	fromArray(args)
