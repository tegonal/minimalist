@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] for the given Enum of type [E].
 *
 * @param E the Enum type which shall be transformed into an [OrderedArgsGenerator].
 * @return an [OrderedArgsGenerator] for the given Enum of type [E].
 *
 * @since 2.0.0
 */
inline fun <reified E : Enum<E>> RandomExtensionPoint.fromEnum(): RandomArgsGenerator<E> =
	ArrayRandomArgsGenerator(_components, enumValues<E>())
