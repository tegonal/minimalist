package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayArbArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeBasedArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ByteArray): ArbArgsGenerator<Byte> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: CharArray): ArbArgsGenerator<Char> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }


/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ShortArray): ArbArgsGenerator<Short> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: IntArray): ArbArgsGenerator<Int> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: LongArray): ArbArgsGenerator<Long> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: FloatArray): ArbArgsGenerator<Float> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: DoubleArray): ArbArgsGenerator<Double> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: BooleanArray): ArbArgsGenerator<Boolean> =
	IntRangeBasedArbArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <A1> ArbExtensionPoint.fromArray(args: Array<out A1>): ArbArgsGenerator<A1> =
	ArrayArbArgsGenerator(_components, args)
