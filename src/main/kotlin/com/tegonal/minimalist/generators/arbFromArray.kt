package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ConstantArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ByteArray): ArbArgsGenerator<Byte> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: CharArray): ArbArgsGenerator<Char> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())


/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: ShortArray): ArbArgsGenerator<Short> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: IntArray): ArbArgsGenerator<Int> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: LongArray): ArbArgsGenerator<Long> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: FloatArray): ArbArgsGenerator<Float> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: DoubleArray): ArbArgsGenerator<Double> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromArray(args: BooleanArray): ArbArgsGenerator<Boolean> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

/**
 * Returns an [ArbArgsGenerator] based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.fromArray(args: Array<out T>): ArbArgsGenerator<T> =
	checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(args.size)?.map(args::get)
		?: ConstantArbArgsGenerator(_components, seedBaseOffset, args.first())

fun ArbExtensionPoint.checkNotEmptyNullIfOneElementAndOtherwiseIntFromUntilSize(size: Int) =
	when (size) {
		0 -> error("You must define at least one element, 0 given")
		1 -> null
		else -> intFromUntil(0, size)
	}
