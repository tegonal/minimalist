@file:JvmName("SemiOrderedArgsGeneratorCombineKt")
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
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> SemiOrderedArgsGenerator<A1>.combine(
	other: ArbArgsGenerator<A2>
): SemiOrderedArgsGenerator<Tuple2<A1, A2>> = this.combine(other, ::Tuple2)

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> SemiOrderedArgsGenerator<Tuple2<A1, A2>>.combine(
	other: ArbArgsGenerator<A3>
): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>>.combine(
	other: ArbArgsGenerator<A4>
): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: ArbArgsGenerator<A5>
): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: ArbArgsGenerator<A6>
): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: ArbArgsGenerator<A7>
): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: ArbArgsGenerator<A8>
): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [SemiOrderedArgsGenerator].
 *
 * @param other The other [SemiOrderedArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: ArbArgsGenerator<A9>
): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = this.combine(other) { args, otherArg ->
	args.append(otherArg)
}

