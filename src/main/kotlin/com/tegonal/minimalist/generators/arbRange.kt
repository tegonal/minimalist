package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.createClosedRangeArbGenerator
import com.tegonal.minimalist.generators.impl.createIntDomainBasedClosedRangeArbGenerator
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInIntDomain
import com.tegonal.minimalist.utils.toBigInt

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
): ArbArgsGenerator<CharRange> = if (minInclusive != Char.MIN_VALUE || maxInclusive != Char.MAX_VALUE) {
	// less than 65'535 elements, we can use the Int domain based implementation
	createIntDomainBasedClosedRangeArbGenerator(
		minInclusive = minInclusive.code,
		maxInclusive = maxInclusive.code,
		minSize = minSize,
		maxSize = maxSize
	) { from, toExclusive ->
		from.toChar()..toExclusive.toChar()
	}
} else {
	createClosedRangeArbGenerator(
		minInclusive = minInclusive.code.toLong(),
		maxInclusive = maxInclusive.code.toLong(),
		minSize = minSize.toLong(),
		maxSize = maxSize?.toBigInt()
	) { start, toInclusive ->
		start.toInt().toChar()..toInclusive.toInt().toChar()
	}
}

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
): ArbArgsGenerator<IntRange> {
	val possibleMaxSize = maxInclusive.toLong() - minInclusive + 1
	// it is beneficial if we can stay in the int domain (memory wise), hence this check here (tiny bit slower if not but we guess in
	// most cases the requirements don't require large ranges)
	return if (possibleMaxSize <= possibleMaxSizeSafeInIntDomain) {
		createIntDomainBasedClosedRangeArbGenerator(
			minInclusive = minInclusive,
			maxInclusive = maxInclusive,
			minSize = minSize,
			maxSize = maxSize,
			factory = ::IntRange
		)
	} else {
		createClosedRangeArbGenerator(
			minInclusive = minInclusive.toLong(),
			maxInclusive = maxInclusive.toLong(),
			minSize = minSize.toLong(),
			maxSize = maxSize?.toBigInt()
		) { start, toInclusive ->
			start.toInt()..toInclusive.toInt()
		}
	}
}

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
): ArbArgsGenerator<LongRange> = createClosedRangeArbGenerator(
	minInclusive = minInclusive,
	maxInclusive = maxInclusive,
	minSize = minSize,
	maxSize = maxSize?.toBigInt(),
	factory = ::LongRange
)
