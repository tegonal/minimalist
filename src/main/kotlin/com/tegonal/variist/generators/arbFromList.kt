package com.tegonal.variist.generators

import com.tegonal.variist.config._components
import com.tegonal.variist.generators.impl.ConstantArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> ArbExtensionPoint.fromList(args: List<T>): ArbArgsGenerator<T> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())
