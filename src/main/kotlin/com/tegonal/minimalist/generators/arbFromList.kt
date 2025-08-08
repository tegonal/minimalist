package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ListArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> ArbExtensionPoint.fromList(args: List<T>): ArbArgsGenerator<T> =
	ListArbArgsGenerator(_components, seedBaseOffset, args)
