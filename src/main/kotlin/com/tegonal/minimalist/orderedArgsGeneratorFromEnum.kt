package com.tegonal.minimalist

import com.tegonal.minimalist.impl.ListOrderedArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] for the given Enum of type [E] where each value is transformed into an [Args1].
 *
 * @param E the Enum type which shall be transformed into an [OrderedArgsGenerator].
 * @return an [OrderedArgsGenerator] for the given Enum of type [E] where each value is transformed into an [Args1].
 *
 * @since 2.0.0
 */
inline fun <reified E : Enum<E>> OrderedArgsGenerator.Companion.fromEnum(): OrderedArgsGenerator<Args1<E>> =
	ListOrderedArgsGenerator(enumValues<E>().map { Args.of(it, representation1 = Representation(it.name)) })
