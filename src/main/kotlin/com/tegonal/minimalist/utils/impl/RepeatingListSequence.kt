package com.tegonal.minimalist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RepeatingListSequence<T>(
	private val values: List<T>,
	private val offset: Int
) : Sequence<T> {

	override fun iterator(): Iterator<T> {
		return object : BaseIntFromUntilRepeatingIterator<T>(0, values.size, offset) {
			override fun getElementAt(index: Int): T = values[index]
		}
	}
}


