@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromRange(args: CharRange): RandomArgsGenerator<Char> =
	fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromRange(args: IntRange): RandomArgsGenerator<Int> =
	fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromRange(args: LongRange): RandomArgsGenerator<Long> =
	fromProgression(args)

