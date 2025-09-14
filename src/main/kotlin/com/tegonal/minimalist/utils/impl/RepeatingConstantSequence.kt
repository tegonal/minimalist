package com.tegonal.minimalist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RepeatingConstantSequence<T>(
	private val constant: T,
) : Sequence<T> {

	override fun iterator(): Iterator<T> = object : Iterator<T> {
		override fun hasNext(): Boolean = true
		override fun next(): T = constant
	}
}
