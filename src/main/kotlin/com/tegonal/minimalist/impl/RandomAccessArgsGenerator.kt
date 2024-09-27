package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.OrderedArgsGenerator

/**
 * Represents a base class for [OrderedArgsGenerator] which provide fast random access.
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 2.0.0
 */
abstract class RandomAccessArgsGenerator<T : Args> : OrderedArgsGenerator<T> {
	final override fun generateOrdered(amount: Int, offset: Int): List<T> {
		val result = ArrayList<T>(amount)
		var count = 0
		var i = offset % size
		while (true) {
			result.add(elementAt(i))
			if (++count == amount) {
				break
			}
			if (++i == size) {
				i = 0
			}
		}
		return result
	}

	protected abstract fun elementAt(index: Int): T
}
