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
open class RandomAccessOrderedArgsGenerator<T>(
	override val size: Int,
	private val elementAt: (index: Int) -> T
) : OrderedArgsGenerator<T> {
	init {
		check(size > 0) {
			"size ($size) needs to be greater than 0"
		}
	}

	final override fun generateOrdered(offset: Int): Sequence<T> =
		repeatForever(offset, size)
			.map { elementAt(it) }
}
