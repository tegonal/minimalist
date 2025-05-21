package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.repeatForever

/**
 * Represents a base class for [OrderedArgsGenerator] which provide fast random access.
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class RandomAccessOrderedArgsGenerator<T> : OrderedArgsGenerator<T> {

	final override fun generateOrdered(offset: Int): Sequence<T> =
		repeatForever(offset, size)
			.map { elementAt(it) }

	protected abstract fun elementAt(index: Int): T
}
