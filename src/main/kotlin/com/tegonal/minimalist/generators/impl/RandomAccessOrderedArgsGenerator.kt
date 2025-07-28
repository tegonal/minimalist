package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.impl.RepeatingRandomAccessSequence

/**
 * Represents a class for [OrderedArgsGenerator] which provide fast random access.
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class RandomAccessOrderedArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	size: Int,
	private val elementAt: (index: Int) -> T
) : BaseSemiOrderedArgsGenerator<T>(componentFactoryContainer, size), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		RepeatingRandomAccessSequence(size, offset, elementAt)
}
