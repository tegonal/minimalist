package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.config.impl.checkIsPositive
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

	constructor(componentFactoryContainer: ComponentFactoryContainer, size: Long) : this(
		componentFactoryContainer,
		Unit.run {
			checkIsPositive(size, "size")
			if (size < Int.MAX_VALUE) size.toInt()
			else error("Cannot convert the size $size to Int")
		}
	)

	init {
		checkIsPositive(size, "size")
	}

	final override fun generate(offset: Int): Sequence<T> {
		check(offset >= 0) {
			"minus offsets are not supported, given $offset"
		}
		return generateAfterChecks(offset)
	}

	abstract fun generateAfterChecks(offset: Int): Sequence<T>
}
