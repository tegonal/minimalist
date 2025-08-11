package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.ArbArgsGeneratorMerger
import com.tegonal.minimalist.generators.impl.MultiArbArgsGeneratorIndexOfMerger

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
//TODO 2.0.0 maybe better not introduce + for 2.0.0: arb.of(1) + arb.intFromUntil(1,10)
// makes it look like we first generate 1 and then a random int from 1 until 10. + resembles to much concatenation and
// of course concatenating two infinite streams doesn't make sense. Better just provide mergeWeighted
operator fun <T> ArbArgsGenerator<T>.plus(
	other: ArbArgsGenerator<T>,
): ArbArgsGenerator<T> = ArbArgsGeneratorMerger(50 to this, 50 to other)

/**
 * Merges [first] with the given [second] and optionally [others] [ArbArgsGenerator] resulting
 * in a [ArbArgsGenerator] which picks the next generator to contribute a value according to the defined weights.
 *
 * The weight represent rations. For instance, given the weights 2 to A and 8 to B can be interpreted as follows:
 * when generating 10 values, then generator A is picked 2 times and generator B 8 times in average. I.e. A has a chance
 * of 20% to be picked and B 80%. Some additional examples:
 * - 20 to A, 50 to B, 60 C => out of 130, A is picked 20, B 50 and C 60, i.e. A has a chance of roughly 15.4%,
 * B 38.5% and C 46.1% to be picked in average.
 *
 * @param first The first [ArbArgsGenerator] with an associated weight.
 * @param second The second [ArbArgsGenerator] with an associated weight
 * @param others optionally more [ArbArgsGenerator] with associated weights
 *
 * @throws IllegalArgumentException in case the weights are wrong (are less than 1)
 * @throws ArithmeticException in case the weights sum up to [Int.MAX_VALUE] or more.
 *
 * @return The resulting [ArbArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> mergeWeighted(
	//TODO 2.0.0 should we use Long for weights?
	first: Pair<Int, ArbArgsGenerator<T>>,
	second: Pair<Int, ArbArgsGenerator<T>>,
	vararg others: Pair<Int, ArbArgsGenerator<T>>,
): ArbArgsGenerator<T> {
	return if (others.isEmpty()) {
		ArbArgsGeneratorMerger(first, second)
	} else {
		// TODO 2.1.0 we could use a binary search instead of indexOf starting from ~20 elements (would need to
		//  be benchmarked). I think most of the time there are <= 10 weights and thus indexOf performs better
		MultiArbArgsGeneratorIndexOfMerger(first, second, others)
	}
}
