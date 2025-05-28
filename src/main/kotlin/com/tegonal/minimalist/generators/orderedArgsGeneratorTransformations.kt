package com.tegonal.minimalist.generators

import ch.tutteli.kbox.append
import com.tegonal.minimalist.generators.impl.DefaultSemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorCombiner
import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorTransformer

/**
 * @since 2.0.0
 */
fun <A1, A2> OrderedArgsGenerator<A1>.combine(
	other: OrderedArgsGenerator<A2>
): OrderedArgsGenerator<Pair<A1, A2>> = this.combine(other, ::Pair)

//TODO 2.0.0 required?
fun <A1, A2> OrderedArgsGenerator<A1>.combine(
	other: RandomArgsGenerator<A2>
): SemiOrderedArgsGenerator<A1, A2> = DefaultSemiOrderedArgsGenerator(this, other)

/**
 * @since 2.0.0
 */
@JvmName("appendToPair")
fun <A1, A2, A3> OrderedArgsGenerator<Pair<A1, A2>>.combine(
	other: OrderedArgsGenerator<A3>
): OrderedArgsGenerator<Triple<A1, A2, A3>> =
	OrderedArgsGeneratorCombiner(this, other) { args, a3 -> args.append(a3) }

///**
// * @since 2.0.0
// */
//fun <A1, A2> OrderedArgsGenerator<A1>.appendDependent(
//	map: (A1) -> OrderedArgsGenerator<A2>
//): OrderedArgsGenerator<Args2<A1, A2>> = this.map { Args.of(it, map(it).generate().first()) }

/**
 * @since 2.0.0
 */
fun <A1, A2> OrderedArgsGenerator<A1>.combineDependent(
	map: (A1) -> RandomArgsGenerator<A2>
): OrderedArgsGenerator<Pair<A1, A2>> = this.map { it to map(it).generate().first() }


/**
 * @since 2.0.0
 */
fun <A1, A2, R> OrderedArgsGenerator<A1>.combine(
	other: OrderedArgsGenerator<A2>,
	transform: (A1, A2) -> R
): OrderedArgsGenerator<R> = OrderedArgsGeneratorCombiner(this, other, transform)


/**
 * @since 2.0.0
 */
fun <T, R> OrderedArgsGenerator<T>.map(f: (T) -> R): OrderedArgsGenerator<R> =
	this.transform { it.map(f) }

/**
 * @since 2.0.0
 */
fun <A1, A2, A3, R> OrderedArgsGenerator<Triple<A1, A2, A3>>.map(f: (A1, A2, A3) -> R): OrderedArgsGenerator<R> =
	this.transform { it.map { (a1, a2, a3) -> f(a1, a2, a3) } }

/**
 * Filters the [Sequence] this [OrderedArgsGenerator] will generate which match the given [predicate],
 * should not filter out all values otherwise it will break the [ArgsGenerator] contract.
 *
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.filter(predicate: (T) -> Boolean): OrderedArgsGenerator<T> =
	this.transform { it.filter(predicate) }

/**
 * Filters the [Sequence] this [OrderedArgsGenerator] will generate which do not match the given [predicate],
 * should not filter out all values otherwise it will break the [ArgsGenerator] contract.
 *
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.filterNot(predicate: (T) -> Boolean): OrderedArgsGenerator<T> =
	this.transform { it.filterNot(predicate) }

/**
 * @since 2.0.0
 */
fun <T, R> OrderedArgsGenerator<T>.transform(transform: (Sequence<T>) -> Sequence<R>): OrderedArgsGenerator<R> =
	OrderedArgsGeneratorTransformer(this, transform)
