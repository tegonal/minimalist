@file:JvmName("OrderedArgsGeneratorCombineKt")
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
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple2].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> OrderedArgsGenerator<A1>.combine(
	other: OrderedArgsGenerator<A2>
): OrderedArgsGenerator<Tuple2<A1, A2>> = this.combine(other, ::Tuple2)

/**
 * Combines the [component1] [OrderedArgsGenerator] with the [component2] [OrderedArgsGenerator]
 * resulting in a [OrderedArgsGenerator] which generates [Tuple2].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2> Tuple2<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>
>.combineAll(): OrderedArgsGenerator<Tuple2<A1, A2>> =
	component1().combine(component2(), ::Tuple2)
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple3].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> OrderedArgsGenerator<Tuple2<A1, A2>>.combine(
	other: OrderedArgsGenerator<A3>
): OrderedArgsGenerator<Tuple3<A1, A2, A3>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple3].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3> Tuple3<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>
>.combineAll(): OrderedArgsGenerator<Tuple3<A1, A2, A3>> =
	component1().combine(component2(), ::Tuple2).combine(component3()) { args, a3 -> args.append(a3) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple4].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> OrderedArgsGenerator<Tuple3<A1, A2, A3>>.combine(
	other: OrderedArgsGenerator<A4>
): OrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple4].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4> Tuple4<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>
>.combineAll(): OrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple5].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> OrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: OrderedArgsGenerator<A5>
): OrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple5].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5> Tuple5<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>,
	OrderedArgsGenerator<A5>
>.combineAll(): OrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple6].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> OrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: OrderedArgsGenerator<A6>
): OrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple6].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6> Tuple6<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>,
	OrderedArgsGenerator<A5>,
	OrderedArgsGenerator<A6>
>.combineAll(): OrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple7].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> OrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: OrderedArgsGenerator<A7>
): OrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple7].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7> Tuple7<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>,
	OrderedArgsGenerator<A5>,
	OrderedArgsGenerator<A6>,
	OrderedArgsGenerator<A7>
>.combineAll(): OrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple8].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> OrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: OrderedArgsGenerator<A8>
): OrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple8].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8> Tuple8<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>,
	OrderedArgsGenerator<A5>,
	OrderedArgsGenerator<A6>,
	OrderedArgsGenerator<A7>,
	OrderedArgsGenerator<A8>
>.combineAll(): OrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }
/**
 * Combines `this` [OrderedArgsGenerator] with the given [other] [OrderedArgsGenerator] transforming the values
 * into a [Tuple9].
 *
 * The resulting [OrderedArgsGenerator] generates
 * [this.size][OrderedArgsGenerator.size] * [other.size][OrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [OrderedArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> OrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: OrderedArgsGenerator<A9>
): OrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines the [component1] [OrderedArgsGenerator] with all other [OrderedArgsGenerator] from left to right
 * resulting in a [OrderedArgsGenerator] which generates [Tuple9].
 *
 * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
 *

 * @since 2.0.0
 */
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> Tuple9<
	OrderedArgsGenerator<A1>,
	OrderedArgsGenerator<A2>,
	OrderedArgsGenerator<A3>,
	OrderedArgsGenerator<A4>,
	OrderedArgsGenerator<A5>,
	OrderedArgsGenerator<A6>,
	OrderedArgsGenerator<A7>,
	OrderedArgsGenerator<A8>,
	OrderedArgsGenerator<A9>
>.combineAll(): OrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> =
	component1().combine(component2(), ::Tuple2)
		.combine(component3()) { args, a3 -> args.append(a3) }
		.combine(component4()) { args, a4 -> args.append(a4) }
		.combine(component5()) { args, a5 -> args.append(a5) }
		.combine(component6()) { args, a6 -> args.append(a6) }
		.combine(component7()) { args, a7 -> args.append(a7) }
		.combine(component8()) { args, a8 -> args.append(a8) }
		.combine(component9()) { args, a9 -> args.append(a9) }
