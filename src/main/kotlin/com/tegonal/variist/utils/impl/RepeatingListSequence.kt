package com.tegonal.variist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * We saw in benchmarks that using a specialised version which does not need to pass getElementAt (e.g. to
 * IntRepeatingSequence) is significantly faster, hence the specialised version for List and Array. On the other hand,
 * it was not worth to introduce a specialised Iterator, I guess JIT inlines getElementAt so there's no difference
 *
 * @since 2.0.0
 */
class RepeatingListSequence<T>(
	private val values: List<T>,
	private val offset: Int
) : Sequence<T> {
	override fun iterator(): Iterator<T> = object : BaseIntFromUntilRepeatingIterator<T>(0, values.size, offset) {
		override fun getElementAt(index: Int): T = values[index]
	}
}


