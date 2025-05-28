package com.tegonal.minimalist.generators

import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.generators.impl.ArrayRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.IntRangeBasedRandomArgsGenerator
//TODO 2.0.0 adjust docs Args1 no longer true
/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [com.tegonal.minimalist.Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: ByteArray): RandomArgsGenerator<Byte> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: CharArray): RandomArgsGenerator<Char> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }


/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: ShortArray): RandomArgsGenerator<Short> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: IntArray): RandomArgsGenerator<Int> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: LongArray): RandomArgsGenerator<Long> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: FloatArray): RandomArgsGenerator<Float> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: DoubleArray): RandomArgsGenerator<Double> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromArray(args: BooleanArray): RandomArgsGenerator<Boolean> =
	IntRangeBasedRandomArgsGenerator(0, args.size) { args[it] }

/**
 * Returns an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [RandomArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun <A1> RandomArgsGenerator.Companion.fromArray(args: Array<out A1>): RandomArgsGenerator<A1> =
	ArrayRandomArgsGenerator(args)
