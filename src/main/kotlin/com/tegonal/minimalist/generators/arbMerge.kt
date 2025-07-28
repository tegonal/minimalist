package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.MultiArbArgsGeneratorIndexOfMerger
import com.tegonal.minimalist.generators.impl.ArbArgsGeneratorMerger

/**
 * Merges `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] resulting in a [ArbArgsGenerator]
 * where the chance to pick `this` [ArbArgsGenerator] to contribute the next value in the
 * [generate][ArbArgsGenerator.generate]d is 50% (i.e. also 50% chance for the [other]).
 *
 * Use [mergeWeighted] if you want to adjust the weights.

 * @param other The other [ArbArgsGenerator].
 *
 * @return The resulting [ArbArgsGenerator].
 *
 * @since 2.0.0
 */
operator fun <T> ArbArgsGenerator<T>.plus(
    other: ArbArgsGenerator<T>,
): ArbArgsGenerator<T> = ArbArgsGeneratorMerger(50 to this, 50 to other)

/**
 * Merges [first] with the given [second] and optionally [others] [ArbArgsGenerator] resulting
 * in a [ArbArgsGenerator] which picks generators to contribute a value according to the defined weights.
 *
 * The sum of the weights need to be 100 (i.e. representing 100%)
 *
 * @param first The first [ArbArgsGenerator] with an associated weight which indicates the chance (in percentage)
 *   of being picked to contribute a value.
 * @param second The second [ArbArgsGenerator] with an associated weight
 * @param others optionally more [ArbArgsGenerator] with associated weights
 *
 * @throws IllegalArgumentException in case the weights are wrong (are less than 1, don't sum up to 100 etc).
 *
 * @return The resulting [ArbArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> mergeWeighted(
    first: Pair<Int, ArbArgsGenerator<T>>,
    second: Pair<Int, ArbArgsGenerator<T>>,
    vararg others: Pair<Int, ArbArgsGenerator<T>>,
): ArbArgsGenerator<T> =
	if (others.isEmpty()) ArbArgsGeneratorMerger(first, second)
	else {
		// TODO 2.1.0 we could use a binary search instead of indexOf starting from ~20 elements (would need to
		//  be benchmarked). I think most of the time there are <= 10 weights and thus indexOf performs better
		MultiArbArgsGeneratorIndexOfMerger(first, second, others)
	}
