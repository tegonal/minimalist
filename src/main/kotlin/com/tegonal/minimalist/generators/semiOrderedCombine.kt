package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArgsGenerator] and [transform]s the generated
 * values pairwise, returning a [SemiOrderedArgsGenerator] which generates values of type [R].
 *
 * It is not statically known what [SemiOrderedArgsGenerator.size] the resulting [SemiOrderedArgsGenerator] will have.
 *
 * How the [other] [ArgsGenerator] is combined depends on its type:
 *   - a [SemiOrderedArgsGenerator] is combined using [cartesian]
 *   - an [ArbArgsGenerator] is combined using [zip]
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [R].
 *
 * @since 2.0.0
 */
fun <A1, A2, R> SemiOrderedArgsGenerator<A1>.combine(
	other: ArgsGenerator<A2>,
	transform: (A1, A2) -> R
): SemiOrderedArgsGenerator<R> = when (other) {
	is SemiOrderedArgsGenerator<A2> -> cartesian(other, transform)
	is ArbArgsGenerator<A2> -> zip(other, transform)
	else -> throwUnsupportedArgsGenerator(other)
}
