package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseSemiOrderedArgsGenerator<T>(
	override val componentFactoryContainer: ComponentFactoryContainer,
	override val size: Int
) : SemiOrderedArgsGenerator<T>, ComponentFactoryContainerProvider {

	init {
		check(size > 0) {
			"size needs to be greater than 0, given $size"
		}
	}

	final override fun generate(offset: Int): Sequence<T> {
		check(offset >= 0) {
			"minus offsets are not supported, given $offset"
		}
		return generateAfterChecks(offset)
	}

	abstract fun generateAfterChecks(offset: Int): Sequence<T>
}
