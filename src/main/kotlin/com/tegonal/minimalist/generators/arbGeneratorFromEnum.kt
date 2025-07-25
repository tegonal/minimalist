@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayArbArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] for the given Enum of type [E].
 *
 * @param E the Enum type which shall be transformed into an [OrderedArgsGenerator].
 * @return an [OrderedArgsGenerator] for the given Enum of type [E].
 *
 * @since 2.0.0
 */
inline fun <reified E : Enum<E>> ArbExtensionPoint.fromEnum(): ArbArgsGenerator<E> =
	ArrayArbArgsGenerator(_components, enumValues<E>())
