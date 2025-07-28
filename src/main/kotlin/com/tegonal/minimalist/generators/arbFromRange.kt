package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(args: CharRange): ArbArgsGenerator<Char> =
	fromProgression(args)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(args: IntRange): ArbArgsGenerator<Int> =
	fromProgression(args)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(args: LongRange): ArbArgsGenerator<Long> =
	fromProgression(args)

