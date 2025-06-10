package com.tegonal.minimalist.utils

/**
 * Returns a [Sequence] which produces an infinite stream of [Int]s in a range from [start] until [endNotInclusive]
 * where [step] and [offset] define what numbers and from which number the sequence starts.
 *
 * @param start The inclusive lower bound of the range (in case [offset] = 0 also the starting number in the
 *   resulting [Sequence]) -- has to be less than [endNotInclusive] and greater than 0.
 *
 * @param endNotInclusive The non-inclusive upper bound of the range. Put differently. the number which is no longer
 *   included in the range, i.e. if the current number + [step] is greater than [endNotInclusive] then the next number
 *   is [start] again -- has to be greater than [start].
 *
 * @param offset Influences from which number this sequences starts where it defines an offset to the [start] of the
 *   range in terms of [step]s, taking [endNotInclusive] and the infinite character of this sequence into account.
 *   Following a few examples:
 *   - start=1, endNotInclusive=4, step=1, offset=1 => sequence starts at 2
 *   - start=1, endNotInclusive=4, step=2, offset=1 => sequence starts at 3
 *   - start=1, endNotInclusive=4, step=2, offset=2 => sequence starts at 1 (since 5 is already greater than 4 it starts over at 1)
 *   - start=1, endNotInclusive=4, step=2, offset=3 => sequence starts at 3
 *
 * @param step Defines the step from the current number to the next - must be greater than 0.
 *
 * @since 2.0.0
 */
fun repeatForeverFromUntil(start: Int, endNotInclusive: Int, offset: Int = 0, step: Int = 1): Sequence<Int> {
	check(start < endNotInclusive) {
		"repeatForeverFromUntil only supports that start ($start) is less than endNotInclusive ($endNotInclusive) - open a feature request"
	}
	check(start >= 0) {
		"repeatForeverFromUntil only support positive start ($start) - open a feature request"
	}
	check(step > 0) {
		"repeatForeverFromUntil only support positive step ($step) - open a feature request"
	}

	val offsetMax = (endNotInclusive - start)
	val offsetInSteps = offset * step
	return generateSequence(start + if (offsetInSteps < offsetMax) offset else offsetInSteps % offsetMax) {
		if (it + step < endNotInclusive) it + step else start
	}
}

/**
 * Returns an infinite [Sequence] of [Unit].
 * @since 2.0.0
 */
fun repeatForever(): Sequence<Unit> = generateSequence(Unit) { it }
