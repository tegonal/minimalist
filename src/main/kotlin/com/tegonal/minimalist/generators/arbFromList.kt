package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> ArbExtensionPoint.fromList(args: List<T>): ArbArgsGenerator<T> =
	intFromUntil(0, args.size).map(args::get)
