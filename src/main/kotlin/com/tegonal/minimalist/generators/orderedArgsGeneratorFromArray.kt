package com.tegonal.minimalist.generators

import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: ByteArray): OrderedArgsGenerator<Byte> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: CharArray): OrderedArgsGenerator<Char> =
	ArrayOrderedArgsGenerator(args.toTypedArray())


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: ShortArray): OrderedArgsGenerator<Short> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: IntArray): OrderedArgsGenerator<Int> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: LongArray): OrderedArgsGenerator<Long> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: FloatArray): OrderedArgsGenerator<Float> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: DoubleArray): OrderedArgsGenerator<Double> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: BooleanArray): OrderedArgsGenerator<Boolean> =
	ArrayOrderedArgsGenerator(args.toTypedArray())

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun <A1> OrderedArgsGenerator.Companion.fromArray(args: Array<out A1>): OrderedArgsGenerator<A1> =
	ArrayOrderedArgsGenerator(args)
