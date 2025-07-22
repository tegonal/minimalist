package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.MultiRandomArgsGeneratorIndexOfMerger
import com.tegonal.minimalist.generators.impl.RandomArgsGeneratorMerger

/**
 * Merges `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator] resulting in a [RandomArgsGenerator]
 * where the chance to pick `this` [RandomArgsGenerator] to contribute the next value in the
 * [generate][RandomArgsGenerator.generate]d is 50% (i.e. also 50% chance for the [other]).
 *
 * Use [mergeWeighted] if you want to adjust the weights.

 * @param other The other [RandomArgsGenerator].
 *
 * @return The resulting [RandomArgsGenerator].
 *
 * @since 2.0.0
 */
operator fun <T> RandomArgsGenerator<T>.plus(
	other: RandomArgsGenerator<T>,
): RandomArgsGenerator<T> = RandomArgsGeneratorMerger(50 to this, 50 to other)

/**
 * Merges [first] with the given [second] and optionally [others] [RandomArgsGenerator] resulting
 * in a [RandomArgsGenerator] which picks generators to contribute a value according to the defined weights.
 *
 * The sum of the weights need to be 100 (i.e. representing 100%)
 *
 * @param first The first [RandomArgsGenerator] with an associated weight which indicates the chance (in percentage)
 *   of being picked to contribute a value.
 * @param second The second [RandomArgsGenerator] with an associated weight
 * @param others optionally more [RandomArgsGenerator] with associated weights
 *
 * @throws IllegalArgumentException in case the weights are wrong (are less than 1, don't sum up to 100 etc).
 *
 * @return The resulting [RandomArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> mergeWeighted(
	first: Pair<Int, RandomArgsGenerator<T>>,
	second: Pair<Int, RandomArgsGenerator<T>>,
	vararg others: Pair<Int, RandomArgsGenerator<T>>,
): RandomArgsGenerator<T> =
	if (others.isEmpty()) RandomArgsGeneratorMerger(first, second)
	else {
		// TODO 2.1.0 we could use a binary search instead of indexOf starting from ~20 elements (would need to
		//  be benchmarked). I think most of the time there are <= 10 weights and thus indexOf performs better
		MultiRandomArgsGeneratorIndexOfMerger(first, second, others)
	}
