package com.tegonal.minimalist.generators

import ch.tutteli.kbox.append
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Args2
import com.tegonal.minimalist.generators.impl.RandomArgsGeneratorTransformer

//TODO 2.0.0 append sounds more like we append the other generator the generation for a1
/**
 * @since 2.0.0
 */
fun <A1, A2> RandomArgsGenerator<A1>.combine(
	other: RandomArgsGenerator<A2>
): RandomArgsGenerator<Pair<A1, A2>> = this.zip(other) { a1, a2 -> a1 to a2 }

/**
 * @since 2.0.0
 */
@JvmName("appendToPair")
fun <A1, A2, A3> RandomArgsGenerator<Pair<A1, A2>>.combine(
	other: RandomArgsGenerator<A3>
): RandomArgsGenerator<Triple<A1, A2, A3>> = this.zip(other) { args2, a3 -> args2.append(a3) }


/**
 * @since 2.0.0
 */
fun <A1, A2> RandomArgsGenerator<A1>.combineDependent(
	map: (A1) -> RandomArgsGenerator<A2>
): RandomArgsGenerator<Args2<A1, A2>> = this.map { Args.of(it, map(it).generate().first()) }

/**
 * @since 2.0.0
 */
fun <T, T2, R> RandomArgsGenerator<T>.zip(
	other: RandomArgsGenerator<T2>,
	transform: (T, T2) -> R
): RandomArgsGenerator<R> = this.transform { it.zip(other.generate(), transform) }


/**
 * @since 2.0.0
 */
fun <T, R> RandomArgsGenerator<T>.map(f: (T) -> R): RandomArgsGenerator<R> =
	this.transform { it.map(f) }

/**
 * @since 2.0.0
 */
fun <A1, A2, A3, R> RandomArgsGenerator<Triple<A1, A2, A3>>.map(f: (A1, A2, A3) -> R): RandomArgsGenerator<R> =
	this.transform { it.map { (a1, a2, a3) -> f(a1, a2, a3) } }

/**
 * Filters the [Sequence] this [RandomArgsGenerator] will generate which match the given [predicate],
 * should not filter out all values otherwise it will break the [ArgsGenerator] contract.
 *
 * @since 2.0.0
 */
fun <T> RandomArgsGenerator<T>.filter(predicate: (T) -> Boolean): RandomArgsGenerator<T> =
	this.transform { it.filter(predicate) }

/**
 * Filters the [Sequence] this [RandomArgsGenerator] will generate which do not match the given [predicate],
 * should not filter out all values otherwise it will break the [ArgsGenerator] contract.
 *
 * @since 2.0.0
 */
fun <T> RandomArgsGenerator<T>.filterNot(predicate: (T) -> Boolean): RandomArgsGenerator<T> =
	this.transform { it.filterNot(predicate) }

/**
 * Transforms the [Sequence] this [RandomArgsGenerator] will generate into a [Sequence] of type [R] and thus creating a
 * [RandomArgsGenerator] of type [R].
 *
 * Note that the resulting sequence should still be an infinite stream of values
 *
 * @since 2.0.0
 */
fun <T, R> RandomArgsGenerator<T>.transform(transform: (Sequence<T>) -> Sequence<R>): RandomArgsGenerator<R> =
	RandomArgsGeneratorTransformer(this, transform)

