@file:JvmName("RandomArgsGeneratorCombineKt")
@file:JvmMultifileClass
// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.generators

import ch.tutteli.kbox.append
import ch.tutteli.kbox.Tuple4
import ch.tutteli.kbox.Tuple5
import ch.tutteli.kbox.Tuple6
import ch.tutteli.kbox.Tuple7
import ch.tutteli.kbox.Tuple8
import ch.tutteli.kbox.Tuple9

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Pair].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> RandomArgsGenerator<A1>.combine(
	other: RandomArgsGenerator<A2>
): RandomArgsGenerator<Pair<A1, A2>> = this.combine(other, ::Pair)

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Triple].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> RandomArgsGenerator<Pair<A1, A2>>.combine(
	other: RandomArgsGenerator<A3>
): RandomArgsGenerator<Triple<A1, A2, A3>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> RandomArgsGenerator<Triple<A1, A2, A3>>.combine(
	other: RandomArgsGenerator<A4>
): RandomArgsGenerator<Tuple4<A1, A2, A3, A4>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> RandomArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: RandomArgsGenerator<A5>
): RandomArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> RandomArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: RandomArgsGenerator<A6>
): RandomArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> RandomArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: RandomArgsGenerator<A7>
): RandomArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> RandomArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: RandomArgsGenerator<A8>
): RandomArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [RandomArgsGenerator] with the given [other] [RandomArgsGenerator].
 *
 * @param other The other [RandomArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [RandomArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> RandomArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: RandomArgsGenerator<A9>
): RandomArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

