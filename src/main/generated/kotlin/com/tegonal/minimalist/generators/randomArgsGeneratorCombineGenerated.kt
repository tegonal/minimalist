@file:JvmName("RandomArgsGeneratorCombineKt")
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
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple2<A1, A2>> = this.combine(other, ::Tuple2)

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple3<A1, A2, A3>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple4<A1, A2, A3, A4>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [ArbArgsGenerator] with the given [other] [ArbArgsGenerator].
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
): ArbArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

