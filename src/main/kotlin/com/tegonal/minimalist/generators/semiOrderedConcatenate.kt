package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.SemiOrderedArgsGeneratorConcatenator

/**
 * Concatenates all given [SemiOrderedArgsGenerator] resulting in an [SemiOrderedArgsGenerator] which yields the values of
 * the first [SemiOrderedArgsGenerator], then the second generator ... up to the values of the last generator and then
 * starting over.
 *
 * I.e. the resulting [SemiOrderedArgsGenerator] generates the sum of [SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @return The resulting [SemiOrderedArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> Sequence<SemiOrderedArgsGenerator<T>>.concatAll(): SemiOrderedArgsGenerator<T> = concatAll(iterator())

/**
 * Concatenates all given [SemiOrderedArgsGenerator] resulting in an [SemiOrderedArgsGenerator] which yields the values of
 * the first [SemiOrderedArgsGenerator], then the second generator ... up to the values of the last generator and then
 * starting over.
 *
 * I.e. the resulting [SemiOrderedArgsGenerator] generates the sum of [SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @return The resulting [SemiOrderedArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> Iterable<SemiOrderedArgsGenerator<T>>.concatAll(): SemiOrderedArgsGenerator<T> = concatAll(iterator())

private fun <T> concatAll(iterator: Iterator<SemiOrderedArgsGenerator<T>>): SemiOrderedArgsGenerator<T> {
	check(iterator.hasNext()) { "cannot concatenate an empty Iterator of SemiOrderedArgsGenerator" }
	val first = iterator.next()
	var result = first
	while (iterator.hasNext()) {
		//TODO 2.1.0 would it be worth to introduce a Concatenator which takes n SemiOrderedArgsGenerator instead of just 2?
		result = result + iterator.next()
	}
	return result
}

/**
 * Merges `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] resulting in an
 * [OrderedArgsGenerator] which yields the values of `this` [OrderedArgsGenerator] and then of the [other] before
 * repeating.
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] + [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator].
 *
 * @return The resulting [OrderedArgsGenerator].
 *
 * @since 2.0.0
 */
operator fun <T> SemiOrderedArgsGenerator<T>.plus(
	other: SemiOrderedArgsGenerator<T>,
): SemiOrderedArgsGenerator<T> = SemiOrderedArgsGeneratorConcatenator(this, other)
