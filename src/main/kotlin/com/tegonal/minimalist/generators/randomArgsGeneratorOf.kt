@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> RandomExtensionPoint.of(vararg args: T): RandomArgsGenerator<T> =
	ArrayRandomArgsGenerator(_components, args)
