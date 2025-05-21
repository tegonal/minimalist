package com.tegonal.minimalist.generators

import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: ByteArray): RandomArgsGenerator<Byte> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: CharArray): RandomArgsGenerator<Char> =
	ArrayRandomArgsGenerator(args.toTypedArray())


/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: ShortArray): RandomArgsGenerator<Short> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: IntArray): RandomArgsGenerator<Int> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: LongArray): RandomArgsGenerator<Long> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: FloatArray): RandomArgsGenerator<Float> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: DoubleArray): RandomArgsGenerator<Double> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: BooleanArray): RandomArgsGenerator<Boolean> =
	ArrayRandomArgsGenerator(args.toTypedArray())

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun <A1> RandomArgsGenerator.Companion.fromArray(args: Array<out A1>): RandomArgsGenerator<A1> =
	ArrayRandomArgsGenerator(args)
