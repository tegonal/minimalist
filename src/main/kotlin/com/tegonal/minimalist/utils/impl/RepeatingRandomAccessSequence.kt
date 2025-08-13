package com.tegonal.minimalist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RepeatingRandomAccessSequence<T>(
	size: Int,
	offset: Int,
	elementAt: (Int) -> T
) : IntRepeatingSequence<T>(0, size, offset, elementAt)
