package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.repeatForever

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ConstantArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	private val constant: T,
) : BaseArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {
	val sequence = repeatForever(constant)

	override fun generateOne(seedOffset: Int): T = constant
	override fun generate(seedOffset: Int): Sequence<T> = sequence
}
