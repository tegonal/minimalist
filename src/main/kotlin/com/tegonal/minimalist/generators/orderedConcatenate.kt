package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorConcatenator

/**
 * Concatenates `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] resulting in an
 * [OrderedArgsGenerator] which yields the values of `this` [OrderedArgsGenerator] and then of the [other] before
 * repeating.
 *
 * I.e. the resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] + [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator].
 *
 * @return The resulting [OrderedArgsGenerator].
 *
 * @since 2.0.0
 */
operator fun <T> OrderedArgsGenerator<T>.plus(
	other: OrderedArgsGenerator<T>,
): OrderedArgsGenerator<T> = OrderedArgsGeneratorConcatenator(this, other)
