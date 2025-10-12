package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.utils.BigInt
import com.tegonal.minimalist.utils.impl.checkIsPositive

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
		size.toInt().also {
			check(it.toLong() == size) {
				// toInt() overflowed
				"${OrderedArgsGenerator::class.simpleName}.${OrderedArgsGenerator<*>::size.name} only supports Int, the given size ($size) is bigger"
			}
		}
	)

	constructor(componentFactoryContainer: ComponentFactoryContainer, size: BigInt) : this(
		componentFactoryContainer,
		size.toInt().also {
			check(size.bitLength() <= 31) {
				"${OrderedArgsGenerator::class.simpleName}.${OrderedArgsGenerator<*>::size.name} only supports Int, the given size ($size) is bigger"
			}
		}
	)

	init {
		checkIsPositive(size, "size")
	}

	final override fun generateOne(offset: Int): T {
		checkOffset(offset)
		return generateOneAfterChecks(offset)
	}

	final override fun generate(offset: Int): Sequence<T> {
		checkOffset(offset)
		return generateAfterChecks(offset)
	}

	private fun checkOffset(offset: Int) {
		check(offset >= 0) {
			"negative offsets are not supported, given $offset"
		}
	}

	open fun generateOneAfterChecks(offset: Int): T =
		// we don't use first as it checks hasNext in addition and we know that it has to have one as the
		// Sequence needs to be infinite according to the ArgsGenerator contract
		generateAfterChecks(offset).iterator().next()

	abstract fun generateAfterChecks(offset: Int): Sequence<T>
}
