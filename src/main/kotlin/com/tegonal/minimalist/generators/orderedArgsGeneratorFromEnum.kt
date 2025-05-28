package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator

//TODO 2.0.0 adjust docs Args1 no longer true
/**
 * Returns an [OrderedArgsGenerator] for the given Enum of type [E] where each value is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @param E the Enum type which shall be transformed into an [OrderedArgsGenerator].
 * @return an [OrderedArgsGenerator] for the given Enum of type [E] where each value is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @since 2.0.0
 */
inline fun <reified E : Enum<E>> OrderedArgsGenerator.Companion.fromEnum(): OrderedArgsGenerator<E> =
	ArrayOrderedArgsGenerator(enumValues<E>())
