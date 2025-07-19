package com.tegonal.minimalist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RepeatingArraySequence<T>(
	private val values: Array<T>,
	private val offset: Int
) : Sequence<T> {

	override fun iterator(): Iterator<T> = object : BaseRepeatingIterator<T>(0, values.size, offset) {
		override fun getElementAt(index: Int): T = values[index]
	}
}
