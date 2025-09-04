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
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A2].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple2")
fun <A1, A2> SemiOrderedArgsGenerator<A1>.combine(
	other: ArbArgsGenerator<A2>
): SemiOrderedArgsGenerator<Tuple2<A1, A2>> = combine(other, ::Tuple2)

/**
 * Creates for each generated value of type [A1] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A2] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [A1].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple2].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple2")
fun <A1, A2> SemiOrderedArgsGenerator<A1>.combineDependent(
	otherFactory: ArbExtensionPoint.(A1) -> ArbArgsGenerator<A2>
): SemiOrderedArgsGenerator<Tuple2<A1, A2>> = combineDependent(otherFactory, ::Tuple2)

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A3].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple3")
fun <A1, A2, A3> SemiOrderedArgsGenerator<Tuple2<A1, A2>>.combine(
	other: ArbArgsGenerator<A3>
): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple2] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A3] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple2].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple3].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple3")
fun <A1, A2, A3> SemiOrderedArgsGenerator<Tuple2<A1, A2>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple2<A1, A2>) -> ArbArgsGenerator<A3>
): SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A4].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple4")
fun <A1, A2, A3, A4> SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>>.combine(
	other: ArbArgsGenerator<A4>
): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple3] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A4] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple3].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple4].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple4")
fun <A1, A2, A3, A4> SemiOrderedArgsGenerator<Tuple3<A1, A2, A3>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple3<A1, A2, A3>) -> ArbArgsGenerator<A4>
): SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A5].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple5")
fun <A1, A2, A3, A4, A5> SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.combine(
	other: ArbArgsGenerator<A5>
): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple4] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A5] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple4].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple5].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple5")
fun <A1, A2, A3, A4, A5> SemiOrderedArgsGenerator<Tuple4<A1, A2, A3, A4>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple4<A1, A2, A3, A4>) -> ArbArgsGenerator<A5>
): SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A6].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple6")
fun <A1, A2, A3, A4, A5, A6> SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combine(
	other: ArbArgsGenerator<A6>
): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple5] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A6] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple5].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple6].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple6")
fun <A1, A2, A3, A4, A5, A6> SemiOrderedArgsGenerator<Tuple5<A1, A2, A3, A4, A5>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple5<A1, A2, A3, A4, A5>) -> ArbArgsGenerator<A6>
): SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A7].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combine(
	other: ArbArgsGenerator<A7>
): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple6] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A7] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple6].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple7].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple7")
fun <A1, A2, A3, A4, A5, A6, A7> SemiOrderedArgsGenerator<Tuple6<A1, A2, A3, A4, A5, A6>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple6<A1, A2, A3, A4, A5, A6>) -> ArbArgsGenerator<A7>
): SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A8].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combine(
	other: ArbArgsGenerator<A8>
): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple7] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A8] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple7].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple8].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple8")
fun <A1, A2, A3, A4, A5, A6, A7, A8> SemiOrderedArgsGenerator<Tuple7<A1, A2, A3, A4, A5, A6, A7>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple7<A1, A2, A3, A4, A5, A6, A7>) -> ArbArgsGenerator<A8>
): SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Combines `this` [SemiOrderedArgsGenerator] with the given [other] [ArbArgsGenerator].
 *
 * @param other The other [ArbArgsGenerator] which generates values of type [A9].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combine(
	other: ArbArgsGenerator<A9>
): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = combine(other) { args, otherArg ->
	args.append(otherArg)
}

/**
 * Creates for each generated value of type [Tuple8] by `this` [SemiOrderedArgsGenerator] another [SemiOrderedArgsGenerator] with the
 * help of the given [otherFactory] where the other generator generates values of type [A9] and then combines the value
 * of `this` [SemiOrderedArgsGenerator] with one value of the other [ArbArgsGenerator].
 *
 * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [Tuple8].
 *
 * @return The resulting [SemiOrderedArgsGenerator] which generates values of type [Tuple9].
 *
 * @since 2.0.0
 */
@JvmName("combineDependentToTuple9")
fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> SemiOrderedArgsGenerator<Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>>.combineDependent(
	otherFactory: ArbExtensionPoint.(Tuple8<A1, A2, A3, A4, A5, A6, A7, A8>) -> ArbArgsGenerator<A9>
): SemiOrderedArgsGenerator<Tuple9<A1, A2, A3, A4, A5, A6, A7, A8, A9>> = combineDependent(otherFactory) { args, otherArg ->
	args.append(otherArg)
}

