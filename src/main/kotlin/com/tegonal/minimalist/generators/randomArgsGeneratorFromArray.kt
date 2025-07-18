@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeBasedRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: ByteArray): RandomArgsGenerator<Byte> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: CharArray): RandomArgsGenerator<Char> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }


/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: ShortArray): RandomArgsGenerator<Short> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: IntArray): RandomArgsGenerator<Int> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: LongArray): RandomArgsGenerator<Long> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: FloatArray): RandomArgsGenerator<Float> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: DoubleArray): RandomArgsGenerator<Double> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromArray(args: BooleanArray): RandomArgsGenerator<Boolean> =
	IntRangeBasedRandomArgsGenerator(_components, 0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <A1> RandomExtensionPoint.fromArray(args: Array<out A1>): RandomArgsGenerator<A1> =
	ArrayRandomArgsGenerator(_components, args)
