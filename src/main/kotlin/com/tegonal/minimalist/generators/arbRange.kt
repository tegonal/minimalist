package com.tegonal.minimalist.generators

/**
 * Returns an [ArbArgsGenerator] which generates [IntRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges (where [CharRange.start] will be
 * [maxInclusive] and [CharRange.last] will be [minInclusive]). Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.charRange(
	minInclusive: Char = Char.MIN_VALUE,
	maxInclusive: Char = Char.MAX_VALUE,
	minSize: Int = 0,
	maxSize: Int? = null,
): ArbArgsGenerator<CharRange> = boundsGeneratorTakingMinSize0IntoAccount(
	minInclusive, maxInclusive, minSize, maxSize, ::charBoundsBased, ::CharRange, zero = 0, one = 1
)

/**
 * Returns an [ArbArgsGenerator] which generates [IntRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges (where [IntRange.start] will be
 * [maxInclusive] and [IntRange.last] will be [minInclusive]). Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.intRange(
	minInclusive: Int = Int.MIN_VALUE,
	maxInclusive: Int = Int.MAX_VALUE,
	minSize: Int = 0,
	maxSize: Int? = null,
): ArbArgsGenerator<IntRange> = boundsGeneratorTakingMinSize0IntoAccount(
	minInclusive, maxInclusive, minSize, maxSize, ::intBoundsBased, ::IntRange, zero = 0, one = 1
)

/**
 * Returns an [ArbArgsGenerator] which generates [LongRange]s ranging from a lower bound [minInclusive] to
 * an upper bound [maxInclusive] respecting the given [minSize] as well as [maxSize] if defined.
 *
 * If [minSize] = 0 (which is the default), then it generates also empty ranges (where [LongRange.start] will be
 * [maxInclusive] and [LongRange.last] will be [minInclusive]). Set [minSize] to 1 or greater if
 * you do not want to include empty ranges.
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.longRange(
	minInclusive: Long = Long.MIN_VALUE,
	maxInclusive: Long = Long.MAX_VALUE,
	minSize: Long = 0,
	maxSize: Long? = null,
): ArbArgsGenerator<LongRange> = boundsGeneratorTakingMinSize0IntoAccount(
	minInclusive, maxInclusive, minSize, maxSize, ::longBoundsBased, ::LongRange, zero = 0, one = 1
)

private fun <T, E, NumberT> ArbExtensionPoint.boundsGeneratorTakingMinSize0IntoAccount(
	minInclusive: T,
	maxInclusive: T,
	minSize: NumberT,
	maxSize: NumberT?,
	boundGenerator: (T, T, NumberT, NumberT?, (T, T) -> E) -> ArbArgsGenerator<E>,
	factory: (T, T) -> E,
	zero: NumberT,
	one: NumberT
): ArbArgsGenerator<E> where NumberT : Number, NumberT : Comparable<NumberT> {
	val arbRange = boundGenerator(minInclusive, maxInclusive, if (minSize == zero) one else minSize, maxSize, factory)
	return includeEmptyRangeIfMinSizeIs0(minInclusive, maxInclusive, minSize, arbRange, factory, zero)
}

private fun <T, E, NumberT> ArbExtensionPoint.includeEmptyRangeIfMinSizeIs0(
	minInclusive: T,
	maxInclusive: T,
	minSize: NumberT,
	arbRange: ArbArgsGenerator<E>,
	factory: (T, T) -> E,
	zero: NumberT
): ArbArgsGenerator<E> = if (minSize == zero) {
	mergeWeighted(
		// TODO 2.1.0 make this configurable once we introduce the concept of edge cases, for now we generate an
		// empty range in 5% of the cases
		5 to arb.of(factory(maxInclusive, minInclusive)),
		95 to arbRange
	)
} else arbRange
