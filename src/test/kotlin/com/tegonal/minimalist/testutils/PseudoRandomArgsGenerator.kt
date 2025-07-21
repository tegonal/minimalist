package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.BaseRandomArgsGenerator
import com.tegonal.minimalist.generators.random
import com.tegonal.minimalist.utils.repeatForever

/**
 * Assumes the given [sequence] can be consumed multiple times.
 */
class PseudoRandomArgsGenerator<T>(
	private val sequence: Sequence<T>,
	componentFactoryContainer: ComponentFactoryContainer = random._components
) : BaseRandomArgsGenerator<T>(componentFactoryContainer) {
	override fun generate(): Sequence<T> = repeatForever().flatMap { sequence }
}
