@file:JvmName("SemiOrderedCombineKt")
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
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple2].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> SemiOrderedArgsGenerator<A1>.combine(
	other: SemiOrderedArgsGenerator<A2>
): SemiOrderedArgsGenerator<Tuple2<A1, A2>> = this.combine(other, ::Tuple2)

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with the [component2] [ArgsGenerator]
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple2].
 *
 * @since 2.0.0
 */
fun <A1, A2> Tuple2<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>
>.combineAll(): SemiOrderedArgsGenerator<Tuple2<A1, A2>> =
	component1().combine(component2(), ::Tuple2)
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple3].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> SemiOrderedArgsGenerator<Tuple2<A1, A2>>.combine(
	other: SemiOrderedArgsGenerator<A3>
): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple3].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3> Tuple3<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>
>.combineAll(): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> =
	component1().combine(component2(), ::Tuple2).combine(component3()) { args, a3 -> args.append(a3) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple4].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>>.combine(
	other: SemiOrderedArgsGenerator<A4>
): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple4].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4> Tuple4<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>
>.combineAll(): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple5].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: SemiOrderedArgsGenerator<A5>
): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple5].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5> Tuple5<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>,
	ArgsGenerator<A5>
>.combineAll(): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple6].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: SemiOrderedArgsGenerator<A6>
): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple6].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6> Tuple6<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>,
	ArgsGenerator<A5>,
	ArgsGenerator<A6>
>.combineAll(): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple7].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: SemiOrderedArgsGenerator<A7>
): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple7].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7> Tuple7<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>,
	ArgsGenerator<A5>,
	ArgsGenerator<A6>,
	ArgsGenerator<A7>
>.combineAll(): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple8].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: SemiOrderedArgsGenerator<A8>
): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple8].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8> Tuple8<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>,
	ArgsGenerator<A5>,
	ArgsGenerator<A6>,
	ArgsGenerator<A7>,
	ArgsGenerator<A8>
>.combineAll(): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }
/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator] transforming the values
 * into a [Tuple9].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: SemiOrderedArgsGenerator<A9>
): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple9].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> Tuple9<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>,
	ArgsGenerator<A3>,
	ArgsGenerator<A4>,
	ArgsGenerator<A5>,
	ArgsGenerator<A6>,
	ArgsGenerator<A7>,
	ArgsGenerator<A8>,
	ArgsGenerator<A9>
>.combineAll(): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }
		.combine(component9()) { args, a9 -> args.append(a9) }
