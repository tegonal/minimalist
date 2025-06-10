package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.SemiOrderedArgsGeneratorCombiner
import com.tegonal.minimalist.generators.impl.SemiOrderedArgsWithRandomArgsGeneratorCombiner

/**
 * @since 2.0.0
 */
fun <A1, A2, R> SemiOrderedArgsGenerator<A1>.combine(
    other: SemiOrderedArgsGenerator<A2>,
    transform: (A1, A2) -> R
): SemiOrderedArgsGenerator<R> = SemiOrderedArgsGeneratorCombiner(this, other, transform)

/**
 * @since 2.0.0
 */
fun <A1, A2, R> SemiOrderedArgsGenerator<A1>.combine(
    other: RandomArgsGenerator<A2>,
    transform: (A1, A2) -> R
): SemiOrderedArgsGenerator<R> = SemiOrderedArgsWithRandomArgsGeneratorCombiner(this, other, transform)
