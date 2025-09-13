package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.impl.RepeatingConstantSequence

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ConstantArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	constant: T,
) : BaseArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {
	val sequence = RepeatingConstantSequence(constant)

	override fun generate(seedOffset: Int): Sequence<T> = sequence
}
