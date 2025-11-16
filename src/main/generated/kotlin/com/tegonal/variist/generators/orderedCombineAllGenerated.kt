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
	component1().cartesian(component2(), ::Tuple2)

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
	component1().cartesian(component2(), ::Tuple2).cartesian(component3()) { args, a3 -> args.append(a3) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }
		.cartesian(component5()) { args, a5 -> args.append(a5) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }
		.cartesian(component5()) { args, a5 -> args.append(a5) }
		.cartesian(component6()) { args, a6 -> args.append(a6) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }
		.cartesian(component5()) { args, a5 -> args.append(a5) }
		.cartesian(component6()) { args, a6 -> args.append(a6) }
		.cartesian(component7()) { args, a7 -> args.append(a7) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }
		.cartesian(component5()) { args, a5 -> args.append(a5) }
		.cartesian(component6()) { args, a6 -> args.append(a6) }
		.cartesian(component7()) { args, a7 -> args.append(a7) }
		.cartesian(component8()) { args, a8 -> args.append(a8) }

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
	component1().cartesian(component2(), ::Tuple2)
		.cartesian(component3()) { args, a3 -> args.append(a3) }
		.cartesian(component4()) { args, a4 -> args.append(a4) }
		.cartesian(component5()) { args, a5 -> args.append(a5) }
		.cartesian(component6()) { args, a6 -> args.append(a6) }
		.cartesian(component7()) { args, a7 -> args.append(a7) }
		.cartesian(component8()) { args, a8 -> args.append(a8) }
		.cartesian(component9()) { args, a9 -> args.append(a9) }

