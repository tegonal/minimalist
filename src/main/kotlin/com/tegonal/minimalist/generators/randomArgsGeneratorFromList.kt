@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ListRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
@JvmName("fromValueList")
fun <T> RandomExtensionPoint.fromList(args: List<T>): RandomArgsGenerator<T> =
	ListRandomArgsGenerator(_components, args)
