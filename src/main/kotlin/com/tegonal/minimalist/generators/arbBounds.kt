package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple2
import com.tegonal.minimalist.generators.impl.createBoundsArbGenerator
import com.tegonal.minimalist.generators.impl.createIntDomainBasedBoundsArbGenerator
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInIntDomain
import com.tegonal.minimalist.utils.toBigInt

/**
 * Returns an [ArbArgsGenerator] which generates [Tuple2] representing a lower and upper bound where the bounds
 * range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.charBounds(
	minInclusive: Char = Char.MIN_VALUE,
	maxInclusive: Char = Char.MAX_VALUE,
	minSize: Int = 1,
	maxSize: Int? = null,
): ArbArgsGenerator<Tuple2<Char, Char>> = charBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::Tuple2)

/**
 * Returns an [ArbArgsGenerator] which generates a [T] based on generated lower and upper bounds and the given [factory]
 * where the bounds range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.charBoundsBased(
	minInclusive: Char = Char.MIN_VALUE,
	maxInclusive: Char = Char.MAX_VALUE,
	minSize: Int = 1,
	maxSize: Int? = null,
	factory: (lowerBound: Char, upperBound: Char) -> T
): ArbArgsGenerator<T> = if (minInclusive != Char.MIN_VALUE || maxInclusive != Char.MAX_VALUE) {
	// less than 65'535 elements, we can use the Int domain based implementation
	createIntDomainBasedBoundsArbGenerator(
		minInclusive = minInclusive.code,
		maxInclusive = maxInclusive.code,
		minSize = minSize,
		maxSize = maxSize
	) { lowerBound, upperBound ->
		factory(lowerBound.toChar(), upperBound.toChar())
	}
} else {
	createBoundsArbGenerator(
		minInclusive = minInclusive.code.toLong(),
		maxInclusive = maxInclusive.code.toLong(),
		minSize = minSize.toLong(),
		maxSize = maxSize?.toBigInt()
	) { lowerBound, upperBound ->
		factory(lowerBound.toInt().toChar(), upperBound.toInt().toChar())
	}
}

/**
 * Returns an [ArbArgsGenerator] which generates [Tuple2] representing a lower and upper bound where the bounds
 * range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.intBounds(
	minInclusive: Int = Int.MIN_VALUE,
	maxInclusive: Int = Int.MAX_VALUE,
	minSize: Int = 1,
	maxSize: Int? = null,
): ArbArgsGenerator<Tuple2<Int, Int>> = intBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::Tuple2)

/**
 * Returns an [ArbArgsGenerator] which generates a [T] based on generated lower and upper bounds and the given [factory]
 * where the bounds range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.intBoundsBased(
	minInclusive: Int = Int.MIN_VALUE,
	maxInclusive: Int = Int.MAX_VALUE,
	minSize: Int = 1,
	maxSize: Int? = null,
	factory: (lowerBound: Int, upperBound: Int) -> T
): ArbArgsGenerator<T> {
	val possibleMaxSize = maxInclusive.toLong() - minInclusive + 1
	// it is beneficial if we can stay in the int domain (memory wise), hence this check here (tiny bit slower if not but we guess in
	// most cases the requirements don't require large ranges)
	return if (possibleMaxSize <= possibleMaxSizeSafeInIntDomain) {
		createIntDomainBasedBoundsArbGenerator(
			minInclusive = minInclusive,
			maxInclusive = maxInclusive,
			minSize = minSize,
			maxSize = maxSize,
			factory = factory
		)
	} else {
		createBoundsArbGenerator(
			minInclusive = minInclusive.toLong(),
			maxInclusive = maxInclusive.toLong(),
			minSize = minSize.toLong(),
			maxSize = maxSize?.toBigInt()
		) { lowerBound, upperBound ->
			factory(lowerBound.toInt(), upperBound.toInt())
		}
	}
}

/**
 * Returns an [ArbArgsGenerator] which generates [Tuple2] representing a lower and upper bound where the bounds
 * range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.longBounds(
	minInclusive: Long = Long.MIN_VALUE,
	maxInclusive: Long = Long.MAX_VALUE,
	minSize: Long = 1,
	maxSize: Long? = null,
) = longBoundsBased(minInclusive, maxInclusive, minSize, maxSize, ::Tuple2)

/**
 * Returns an [ArbArgsGenerator] which generates a [T] based on generated lower and upper bounds and the given [factory]
 * where the bounds range from [minInclusive] to [maxInclusive] respecting the given [minSize] as well as
 * [maxSize] if defined.
 *
 * @param minSize must be greater than or equal to 1
 * @param maxSize must be greater than or equal to [minSize] and less than the possible max size given
 *   by [minInclusive], [maxInclusive]
 *
 * @since 2.0.0
 */
fun <T> ArbExtensionPoint.longBoundsBased(
	minInclusive: Long = Long.MIN_VALUE,
	maxInclusive: Long = Long.MAX_VALUE,
	minSize: Long = 1,
	maxSize: Long? = null,
	factory: (lowerBound: Long, upperBound: Long) -> T
): ArbArgsGenerator<T> = createBoundsArbGenerator(
	minInclusive = minInclusive,
	maxInclusive = maxInclusive,
	minSize = minSize,
	maxSize = maxSize?.toBigInt(),
	factory = factory
)
