package com.tegonal.minimalist.utils.impl

/**
 * Returns an [Iterator] which produces an infinite stream of [T]s based on an [Int] range from [startIndex] until
 * [endIndexExclusive] considering the given [step] and [offset].
 *
 * @param startIndex The inclusive lower bound of the range (in case [offset] = 0 also the starting number) --
 *   has to be less than [endIndexExclusive] and greater than or equal to 0.
 *
 * @param endIndexExclusive The non-inclusive upper bound of the range. Put differently. the number which is no longer
 *   included in the range, i.e. if the current number + [step] is greater than [endIndexExclusive] then the next number
 *   is [startIndex] again -- has to be greater than [startIndex].
 *
 * @param offset Influences from which number the range starts where it defines an offset to the [startIndex] of the
 *   range in terms of [step]s, taking [endIndexExclusive] and the infinite character of this iterator into account.
 *   Following a few examples:
 *   - startIndex=1, endIndexExclusive=4, step=1, offset=1 => range starts at 2
 *   - startIndex=1, endIndexExclusive=4, step=2, offset=1 => range starts at 3
 *   - startIndex=1, endIndexExclusive=4, step=2, offset=2 => range starts at 1 (since 5 is already greater than 4 it starts over at 1)
 *   - startIndex=1, endIndexExclusive=4, step=2, offset=3 => range starts at 3
 *
 * @param step Defines the step from the current number to the next - must be greater than 0.
 *
 * @since 2.0.0
 */
abstract class BaseRepeatingIterator<T>(
	private val startIndex: Int,
	private val endIndexExclusive: Int,
	offset: Int = 0,
	private val step: Int = 1,
) : Iterator<T> {

	private var index: Int

	init {
		check(startIndex < endIndexExclusive) {
			"RepeatingIterator only supports that startIndex ($startIndex) is less than endIndexExclusive ($endIndexExclusive) - feel free to open a feature request ${FEATURE_REQUEST_URL}&title=RepeatingIterator%20downwards"
		}
		check(startIndex >= 0) {
			"RepeatingIterator only supports a startIndex which is greater than or equal to 0 (given $startIndex) - feel free to open a feature request ${FEATURE_REQUEST_URL}&title=RepeatingIterator%20negative%20start"
		}
		check(step > 0) {
			"RepeatingIterator only supports a positive step (given $step) - feel free to open a feature request ${FEATURE_REQUEST_URL}&title=RepeatingIterator%20downwards"
		}
		check(offset >= 0) {
			"RepeatingIterator only supports an offset greater than or equal to 0 (given $offset)"
		}

		val offsetMax = (endIndexExclusive - startIndex)
		val offsetInSteps = offset * step
		index = startIndex + if (offsetInSteps < offsetMax) offsetInSteps else offsetInSteps % offsetMax
	}

	override fun hasNext() = true

	override fun next(): T =
		getElementAt(index).also {
			index += step
			if (index >= endIndexExclusive) {
				index = startIndex
			}
		}

	abstract fun getElementAt(index: Int): T
}
