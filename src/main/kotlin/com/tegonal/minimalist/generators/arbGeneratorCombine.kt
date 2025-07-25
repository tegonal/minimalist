@file:JvmName("RandomArgsGeneratorCombineKt")
@file:JvmMultifileClass
package com.tegonal.minimalist.generators

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator], [ArbArgsGenerator.transform]ing
 * the pairwise generated values of type [A1] and [A2] to type [R] with the help of the given [transform] function.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A2].
 * @param transform The transformation function which takes an [A1] and [A2] and produces an [R].
 *
 * @param A1 The type of values generated by `this` [ArbArgsGenerator].
 * @param A2 The type of values generated by the given [other] [ArbArgsGenerator].
 * @param R the type of values generated by the resulting [ArbArgsGenerator].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [R].
 *
 * @since 2.0.0
 */
fun <A1, A2, R> ArbArgsGenerator<A1>.combine(
	other: ArbArgsGenerator<A2>,
	transform: (A1, A2) -> R
): ArbArgsGenerator<R> = this.transform { it.zip(other.generate(), transform) }


/**
 * Creates for each generated value of type [A1] by `this` [ArbArgsGenerator] another [ArbArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A2] and then combines the value
 * of `this` [ArbArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds another [ArbArgsGenerator] based on a given value of type [A1].
 *
 * @param A1 The type of values generated by `this` [ArbArgsGenerator].
 * @param A2 The type of values generated by the given other [ArbArgsGenerator] (built by the given [otherFactory]).
 *
 * @return The resulting [ArbArgsGenerator] which generates [Pair]s of [A1] and [A2].
 *
 * @since 2.0.0
 */
//TODO 2.1.0 provide a way to take more than 1 value from otherFactory
fun <A1, A2> ArbArgsGenerator<A1>.combineDependent(
	otherFactory: (A1) -> ArbArgsGenerator<A2>
): ArbArgsGenerator<Pair<A1, A2>> = combineDependent(otherFactory, ::Pair)

/**
 * Creates for each generated value of type [A1] by `this` [ArbArgsGenerator] another [ArbArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A2] and then [transform]s the
 * value of `this` [ArbArgsGenerator] with one value of the other [ArbArgsGenerator] to type [R].
 *
 *
 * @param otherFactory Builds another [ArbArgsGenerator] based on a given value of type [A1].
 * @param transform The transformation function which takes an [A1] and [A2] and produces an [R].
 *
 * @param A1 The type of values generated by `this` [ArbArgsGenerator].
 * @param A2 The type of values generated by the given other [ArbArgsGenerator] (built by the given [otherFactory]).
 * @param R the type of values generated by the resulting [ArbArgsGenerator].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [R].
 *
 * @return The resulting [ArbArgsGenerator] which generates [Pair]s of [A1] and [A2].
 *
 * @since 2.0.0
 */
fun <A1, A2, R> ArbArgsGenerator<A1>.combineDependent(
	otherFactory: (A1) -> ArbArgsGenerator<A2>,
	transform: (A1, A2) -> R
): ArbArgsGenerator<R> = this.map { transform(it, otherFactory(it).generate().first()) }
