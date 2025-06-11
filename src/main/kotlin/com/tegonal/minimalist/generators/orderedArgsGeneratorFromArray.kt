package com.tegonal.minimalist.generators

import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.RandomAccessOrderedArgsGenerator

//TODO 2.0.0 adjust docs Args1 no longer true
/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: ByteArray): OrderedArgsGenerator<Byte> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: CharArray): OrderedArgsGenerator<Char> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: ShortArray): OrderedArgsGenerator<Short> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: IntArray): OrderedArgsGenerator<Int> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: LongArray): OrderedArgsGenerator<Long> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: FloatArray): OrderedArgsGenerator<Float> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: DoubleArray): OrderedArgsGenerator<Double> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun ordered.fromArray(args: BooleanArray): OrderedArgsGenerator<Boolean> =
	RandomAccessOrderedArgsGenerator(args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun <A1> ordered.fromArray(args: Array<out A1>): OrderedArgsGenerator<A1> =
	ArrayOrderedArgsGenerator(args)
