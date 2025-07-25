package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.BaseArbArgsGenerator
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.utils.repeatForever

/**
 * Assumes the given [sequence] can be consumed multiple times.
 */
class PseudoArbArgsGenerator<T>(
	private val sequence: Sequence<T>,
	componentFactoryContainer: ComponentFactoryContainer = arb._components
) : BaseArbArgsGenerator<T>(componentFactoryContainer) {
	override fun generate(): Sequence<T> = repeatForever().flatMap { sequence }
}
