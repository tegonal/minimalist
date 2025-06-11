package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorCombiner
import com.tegonal.minimalist.generators.impl.SemiOrderedArgsWithRandomArgsGeneratorCombiner

/**
 * @since 2.0.0
 */
fun <A1, A2, R> OrderedArgsGenerator<A1>.combine(
	other: OrderedArgsGenerator<A2>,
	transform: (A1, A2) -> R
): OrderedArgsGenerator<R> = OrderedArgsGeneratorCombiner(this, other, transform)

///**
// * @since 2.0.0
// */
//fun <A1, A2, R> OrderedArgsGenerator<A1>.combine(
//	other: RandomArgsGenerator<A2>,
//	transform: (A1, A2) -> R
//): SemiOrderedArgsGenerator<R> = SemiOrderedArgsWithRandomArgsGeneratorCombiner(this, other, transform)
//
