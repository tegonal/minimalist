package com.tegonal.variist.generators

/**
 * Returns an [ArbArgsGenerator] which generates [IntRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges. Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.charRange(
	minInclusive: Char = Char.MIN_VALUE,
	maxInclusive: Char = Char.MAX_VALUE,
	minSize: Int = 0,
	maxSize: Int? = null,
): ArbArgsGenerator<CharRange> = charBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::CharRange)

/**
 * Returns an [ArbArgsGenerator] which generates [IntRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges. Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.intRange(
	minInclusive: Int = Int.MIN_VALUE,
	maxInclusive: Int = Int.MAX_VALUE,
	minSize: Int = 0,
	maxSize: Int? = null,
): ArbArgsGenerator<IntRange> = intBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::IntRange)

/**
 * Returns an [ArbArgsGenerator] which generates [LongRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges. Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.longRange(
	minInclusive: Long = Long.MIN_VALUE,
	maxInclusive: Long = Long.MAX_VALUE,
	minSize: Long = 0,
	maxSize: Long? = null,
): ArbArgsGenerator<LongRange> = longBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::LongRange)
