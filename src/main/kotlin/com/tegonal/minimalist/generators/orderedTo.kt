package com.tegonal.minimalist.generators

/**
 * Generates [size][OrderedArgsGenerator.size] values and puts them into a [List].
 *
 * @return A [List] of all values `this` [OrderedArgsGenerator] generates.
 *
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.toList(): List<T> =
	generate().take(size).toList()

/**
 * Generates [size][OrderedArgsGenerator.size] values and puts them into a [Set].
 *
 * @return A [Set] of all values `this` [OrderedArgsGenerator] generates.
 *
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.toSet(): Set<T> =
	generate().take(size).toSet()

