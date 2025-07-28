package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [range].
 *
 * @return an [ArbArgsGenerator] based on the given [range].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(range: CharRange): ArbArgsGenerator<Char> =
	charFromTo(range.start, range.endInclusive)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [range].
 *
 * @return an [ArbArgsGenerator] based on the given [range].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(range: IntRange): ArbArgsGenerator<Int> =
	intFromTo(range.start, range.endInclusive)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [ArbArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(args: LongRange): ArbArgsGenerator<Long> =
	fromProgression(args)

