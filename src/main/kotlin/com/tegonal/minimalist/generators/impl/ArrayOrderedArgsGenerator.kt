package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.impl.RepeatingArraySequence
import com.tegonal.minimalist.utils.repeatForever

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [Array].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayOrderedArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	private val values: Array<T>
) : BaseSemiOrderedArgsGenerator<T>(componentFactoryContainer, values.size), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		repeatForever(values, offset)
}
