package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.ArrayOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.RandomAccessOrderedArgsGenerator

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: ByteArray): OrderedArgsGenerator<Byte> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: CharArray): OrderedArgsGenerator<Char> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }


/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: ShortArray): OrderedArgsGenerator<Short> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: IntArray): OrderedArgsGenerator<Int> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: LongArray): OrderedArgsGenerator<Long> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: FloatArray): OrderedArgsGenerator<Float> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: DoubleArray): OrderedArgsGenerator<Double> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromArray(args: BooleanArray): OrderedArgsGenerator<Boolean> =
	RandomAccessOrderedArgsGenerator(_components, args.size) { args[it] }

/**
 * Returns an [OrderedArgsGenerator] based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun <T> OrderedExtensionPoint.fromArray(args: Array<out T>): OrderedArgsGenerator<T> =
	ArrayOrderedArgsGenerator(_components, args)
