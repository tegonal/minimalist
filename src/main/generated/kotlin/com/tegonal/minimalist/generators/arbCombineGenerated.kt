@file:JvmName("ArbCombineKt")
@file:JvmMultifileClass
// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.generators

import ch.tutteli.kbox.append
import ch.tutteli.kbox.Tuple2
import ch.tutteli.kbox.Tuple3
import ch.tutteli.kbox.Tuple4
import ch.tutteli.kbox.Tuple5
import ch.tutteli.kbox.Tuple6
import ch.tutteli.kbox.Tuple7
import ch.tutteli.kbox.Tuple8
import ch.tutteli.kbox.Tuple9

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple2].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> ArbArgsGenerator<A1>.combine(
	other: ArbArgsGenerator<A2>
): ArbArgsGenerator<Tuple2<A1, A2>> = combine(other, ::Tuple2)

/**
 * Combines the [component1] [ArbArgsGenerator] with the [component2] [ArbArgsGenerator]
 * resulting in a [ArbArgsGenerator] which generates [Tuple2].
 *
 * @since 2.0.0
 */
fun <A1, A2> Tuple2<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>
>.combineAll(): ArbArgsGenerator<Tuple2<A1, A2>> =
	component1().combine(component2(), ::Tuple2)

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple3].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> ArbArgsGenerator<Tuple2<A1, A2>>.combine(
	other: ArbArgsGenerator<A3>
): ArbArgsGenerator<Tuple3<A1, A2, A3>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple3].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3> Tuple3<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>
>.combineAll(): ArbArgsGenerator<Tuple3<A1, A2, A3>> =
	component1().combine(component2(), ::Tuple2).combine(component3()) { args, a3 -> args.append(a3) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple4].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> ArbArgsGenerator<Tuple3<A1, A2, A3>>.combine(
	other: ArbArgsGenerator<A4>
): ArbArgsGenerator<Tuple4<A1, A2, A3, A4>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple4].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4> Tuple4<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>
>.combineAll(): ArbArgsGenerator<Tuple4<A1, A2, A3, A4>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple5].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> ArbArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: ArbArgsGenerator<A5>
): ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple5].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5> Tuple5<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>,
	ArbArgsGenerator<A5>
>.combineAll(): ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple6].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: ArbArgsGenerator<A6>
): ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple6].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6> Tuple6<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>,
	ArbArgsGenerator<A5>,
	ArbArgsGenerator<A6>
>.combineAll(): ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple7].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: ArbArgsGenerator<A7>
): ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple7].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7> Tuple7<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>,
	ArbArgsGenerator<A5>,
	ArbArgsGenerator<A6>,
	ArbArgsGenerator<A7>
>.combineAll(): ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple8].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: ArbArgsGenerator<A8>
): ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple8].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8> Tuple8<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>,
	ArbArgsGenerator<A5>,
	ArbArgsGenerator<A6>,
	ArbArgsGenerator<A7>,
	ArbArgsGenerator<A8>
>.combineAll(): ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator] transforming the values
 * into a [Tuple9].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: ArbArgsGenerator<A9>
): ArbArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple9].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> Tuple9<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>,
	ArbArgsGenerator<A4>,
	ArbArgsGenerator<A5>,
	ArbArgsGenerator<A6>,
	ArbArgsGenerator<A7>,
	ArbArgsGenerator<A8>,
	ArbArgsGenerator<A9>
>.combineAll(): ArbArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }
		.combine(component9()) { args, a9 -> args.append(a9) }

