package com.tegonal.minimalist.utils.impl

class RepeatingRandomAccessSequence<T>(
	private val size: Int,
	private val offset: Int,
	private val elementAt: (Int) -> T
) : Sequence<T> {

	override fun iterator(): Iterator<T> = object : BaseRepeatingIterator<T>(0, size, offset) {
		override fun getElementAt(index: Int): T = elementAt(index)
	}
}
