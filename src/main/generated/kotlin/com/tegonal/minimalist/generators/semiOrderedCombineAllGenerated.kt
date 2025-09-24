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
 * Combines the [component1] [SemiOrderedArgsGenerator] with the [component2] [ArgsGenerator]
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple2].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
 *
 * @since 2.0.0
 */
fun <A1, A2> Tuple2<
	SemiOrderedArgsGenerator<A1>,
	ArgsGenerator<A2>
>.combineAll(): SemiOrderedArgsGenerator<Tuple2<A1, A2>> =
	component1().combine(component2(), ::Tuple2)

/**
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple3].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple4].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple5].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple6].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple7].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple8].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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
 * Combines the [component1] [SemiOrderedArgsGenerator] with all other [ArgsGenerator] from left to right
 * resulting in a [SemiOrderedArgsGenerator] which generates [Tuple9].
 *
 * How the [ArgsGenerator]s are combined depends on their type:
 *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
 *   - [ArbArgsGenerator]s are combined using [zip]
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

