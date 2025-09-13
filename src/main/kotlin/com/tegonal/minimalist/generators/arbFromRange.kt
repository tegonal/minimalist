package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [range].
 *
 * @return an [ArbArgsGenerator] based on the given [range].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(range: CharRange): ArbArgsGenerator<Char> =
	charFromTo(range.first, range.last)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [range].
 *
 * @return an [ArbArgsGenerator] based on the given [range].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(range: IntRange): ArbArgsGenerator<Int> =
	intFromTo(range.first, range.last)

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [range].
 *
 * @return an [ArbArgsGenerator] based on the given [range].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromRange(range: LongRange): ArbArgsGenerator<Long> =
	longFromTo(range.first, range.last)
