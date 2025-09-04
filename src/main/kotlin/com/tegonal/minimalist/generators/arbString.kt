package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.createMinimalistRandom
import com.tegonal.minimalist.utils.impl.failIfNegative
import kotlin.random.Random

/**
 * Returns an [ArbArgsGenerator] which generates [String]s with [minLength] and [maxLength] (inclusive) using
 * the given allowed [UnicodeRange]s.
 *
 * You can use one of the predefined [UnicodeRanges.ranges] such as [UnicodeRanges.ASCII_PRINTABLE]
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.string(
	maxLength: Int,
	minLength: Int = 0,
	allowedRanges: Array<out UnicodeRange> = UnicodeRanges.UTF_8_PRINTABLE.ranges
): ArbArgsGenerator<String> {
	failIfNegative(minLength, "minLength")
	require(minLength <= maxLength) {
		"minLength ($minLength) must be less than or equal to maxLength ($maxLength)"
	}
	require(allowedRanges.isNotEmpty()) {
		"you need to define at least one ${UnicodeRange::class.simpleName}"
	}
	val nextCodePoint: Random.() -> Int = when (allowedRanges.size) {
		1 -> allowedRanges.first().let { { nextInt(it.start, it.endInclusive + 1) } }
		else -> {
			{
				val sizes = allowedRanges.map {
					// Int is safe for Unicode  no need to convert toLong() (unless someone defined overlapping ranges,
					// we ignore this case here, if it overflows then it can be seen as consequential bug)
					it.endInclusive - it.start + 1
				}

				val cumulativeSizes = sizes.runningReduce(Int::plus)
				val totalSize = cumulativeSizes.last()
				val index = nextInt(totalSize)
				cumulativeSizes.binarySearch(index).let {
					if (it >= 0) {
						// the randomly chosen index was exactly the cumulative size of a range
						// i.e. we don't need to calculate the range offset
						allowedRanges[it].start
					} else {
						//  binarySearch returned the `inverted insertion point` and we invert it again to retrieve
						//  the rangeIndex where the cumulative size was greater than index
						val rangeIndex = -it - 1
						val range = allowedRanges[rangeIndex]
						// but that means that we need to calculate the offset in that range
						val cumulativeSizeOfPreviousRange = if (rangeIndex == 0) 0 else cumulativeSizes[rangeIndex - 1]
						val offsetInRange = index - cumulativeSizeOfPreviousRange
						range.start + offsetInRange
					}
				}
			}
		}
	}

	val componentFactoryContainer = _components
	return intFromTo(minLength, maxLength).mapIndexed { index, length, seedOffset ->
		componentFactoryContainer.createMinimalistRandom(seedBaseOffset + seedOffset + index).let { random ->
			val sb = StringBuilder(length * 2)
			repeat(length) {
				val codePoint = random.nextCodePoint()
				sb.appendCodePoint(codePoint)
			}
			sb.toString()
		}
	}
}


//TODO 2.1.0 combinations on case
//arb.randomCase(s: String)


