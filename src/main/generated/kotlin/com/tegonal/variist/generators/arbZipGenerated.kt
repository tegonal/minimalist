@file:JvmName("ArbZipKt")
@file:JvmMultifileClass
// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist.generators

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
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple2")
fun <A1, A2> ArbArgsGenerator<A1>.zip(
	other: ArbArgsGenerator<A2>
): ArbArgsGenerator<Tuple2<A1, A2>> = zip(other, ::Tuple2)

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple3")
fun <A1, A2, A3> ArbArgsGenerator<Tuple2<A1, A2>>.zip(
	other: ArbArgsGenerator<A3>
): ArbArgsGenerator<Tuple3<A1, A2, A3>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple4")
fun <A1, A2, A3, A4> ArbArgsGenerator<Tuple3<A1, A2, A3>>.zip(
	other: ArbArgsGenerator<A4>
): ArbArgsGenerator<Tuple4<A1, A2, A3, A4>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple5")
fun <A1, A2, A3, A4, A5> ArbArgsGenerator<Tuple4<A1, A2, A3, A4>>.zip(
	other: ArbArgsGenerator<A5>
): ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple6")
fun <A1, A2, A3, A4, A5, A6> ArbArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.zip(
	other: ArbArgsGenerator<A6>
): ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> ArbArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.zip(
	other: ArbArgsGenerator<A7>
): ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> ArbArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.zip(
	other: ArbArgsGenerator<A8>
): ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Zips `this` [ArbArgsGenerator] with the given [other]&nbsp;[ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [ArbArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("zipToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> ArbArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.zip(
	other: ArbArgsGenerator<A9>
): ArbArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = zip(other) { args, otherArg ->
	args.append(otherArg)
}

