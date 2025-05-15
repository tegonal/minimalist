package com.tegonal.minimalist

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: ByteArray): OrderedArgsGenerator<Args1<Byte>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: CharArray): OrderedArgsGenerator<Args1<Char>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: ShortArray): OrderedArgsGenerator<Args1<Short>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: IntArray): OrderedArgsGenerator<Args1<Int>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: LongArray): OrderedArgsGenerator<Args1<Long>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: FloatArray): OrderedArgsGenerator<Args1<Float>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: DoubleArray): OrderedArgsGenerator<Args1<Double>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromArray(args: BooleanArray): OrderedArgsGenerator<Args1<Boolean>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun <A1> OrderedArgsGenerator.Companion.fromArray(args: Array<out A1>): OrderedArgsGenerator<Args1<A1>> =
	OrderedArgsGenerator.fromList(args.map(Args::of))
