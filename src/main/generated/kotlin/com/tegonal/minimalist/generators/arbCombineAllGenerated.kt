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
 * Zips the [component1] [ArbArgsGenerator] with the [component2] [ArbArgsGenerator]
 * resulting in a [ArbArgsGenerator] which generates [Tuple2].
 *
 * @since 2.0.0
 */
fun <A1, A2> Tuple2<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>
>.combineAll(): ArbArgsGenerator<Tuple2<A1, A2>> =
	component1().zip(component2(), ::Tuple2)

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
 * resulting in a [ArbArgsGenerator] which generates [Tuple3].
 *
 * @since 2.0.0
 */
fun <A1, A2, A3> Tuple3<
	ArbArgsGenerator<A1>,
	ArbArgsGenerator<A2>,
	ArbArgsGenerator<A3>
>.combineAll(): ArbArgsGenerator<Tuple3<A1, A2, A3>> =
	component1().zip(component2(), ::Tuple2).zip(component3()) { args, a3 -> args.append(a3) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }
		.zip(component5()) { args, a5 -> args.append(a5) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }
		.zip(component5()) { args, a5 -> args.append(a5) }
		.zip(component6()) { args, a6 -> args.append(a6) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }
		.zip(component5()) { args, a5 -> args.append(a5) }
		.zip(component6()) { args, a6 -> args.append(a6) }
		.zip(component7()) { args, a7 -> args.append(a7) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }
		.zip(component5()) { args, a5 -> args.append(a5) }
		.zip(component6()) { args, a6 -> args.append(a6) }
		.zip(component7()) { args, a7 -> args.append(a7) }
		.zip(component8()) { args, a8 -> args.append(a8) }

/**
 * Zips the [component1] [ArbArgsGenerator] with all other [ArbArgsGenerator] from left to right
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
	component1().zip(component2(), ::Tuple2)
		.zip(component3()) { args, a3 -> args.append(a3) }
		.zip(component4()) { args, a4 -> args.append(a4) }
		.zip(component5()) { args, a5 -> args.append(a5) }
		.zip(component6()) { args, a6 -> args.append(a6) }
		.zip(component7()) { args, a7 -> args.append(a7) }
		.zip(component8()) { args, a8 -> args.append(a8) }
		.zip(component9()) { args, a9 -> args.append(a9) }

