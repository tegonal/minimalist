package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ByteArray): ArbArgsGenerator<Byte> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: CharArray): ArbArgsGenerator<Char> =
	intFromUntil(0, args.size).map(args::get)


/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ShortArray): ArbArgsGenerator<Short> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: IntArray): ArbArgsGenerator<Int> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: LongArray): ArbArgsGenerator<Long> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: FloatArray): ArbArgsGenerator<Float> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: DoubleArray): ArbArgsGenerator<Double> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: BooleanArray): ArbArgsGenerator<Boolean> =
	intFromUntil(0, args.size).map(args::get)

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <A1> ArbExtensionPoint.fromArray(args: Array<out A1>): ArbArgsGenerator<A1> =
	intFromUntil(0, args.size).map(args::get)
