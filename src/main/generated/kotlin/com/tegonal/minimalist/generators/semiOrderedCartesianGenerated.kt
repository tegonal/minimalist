@file:JvmName("SemiOrderedCartesianKt")
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
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple2].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple2")
fun <A1, A2> SemiOrderedArgsGenerator<A1>.cartesian(
	other: SemiOrderedArgsGenerator<A2>
): SemiOrderedArgsGenerator<Tuple2<A1, A2>> = cartesian(other, ::Tuple2)

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple3].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple3")
fun <A1, A2, A3> SemiOrderedArgsGenerator<Tuple2<A1, A2>>.cartesian(
	other: SemiOrderedArgsGenerator<A3>
): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple4].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple4")
fun <A1, A2, A3, A4> SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>>.cartesian(
	other: SemiOrderedArgsGenerator<A4>
): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple5].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple5")
fun <A1, A2, A3, A4, A5> SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.cartesian(
	other: SemiOrderedArgsGenerator<A5>
): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple6].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple6")
fun <A1, A2, A3, A4, A5, A6> SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.cartesian(
	other: SemiOrderedArgsGenerator<A6>
): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple7].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.cartesian(
	other: SemiOrderedArgsGenerator<A7>
): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple8].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.cartesian(
	other: SemiOrderedArgsGenerator<A8>
): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other]&nbsp;[SemiOrderedArgsGenerator] resulting in their
 * cartesian product where the values are transformed into a [Tuple9].
 *
 * The resulting [SemiOrderedArgsGenerator] generates
 * [this.size][SemiOrderedArgsGenerator.size] * [other.size][SemiOrderedArgsGenerator.size] values before repeating.
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which represents the cartesian product and
 *   generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("cartesianToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.cartesian(
	other: SemiOrderedArgsGenerator<A9>
): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = cartesian(other) { args, otherArg ->
	args.append(otherArg)
}

