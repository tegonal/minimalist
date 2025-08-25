package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.impl.determineStartingIndex
import com.tegonal.minimalist.utils.repeatForever

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [List].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListOrderedArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	private val values: List<T>
) : BaseSemiOrderedArgsGenerator<T>(componentFactoryContainer, values.size), OrderedArgsGenerator<T> {

	override fun generateOneAfterChecks(offset: Int): T {
		val index = determineStartingIndex(0, size, offset, 1)
		return values[index]
	}

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		repeatForever(values, offset)
}
