package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.BaseRandomArgsGenerator
import com.tegonal.minimalist.utils.repeatForever

/**
 * Assumes the given [sequence] can be consumed multiple times.
 */
class PseudoRandomArgsGenerator<T>(
	private val sequence: Sequence<T>
) : BaseRandomArgsGenerator<T>(random._components) {
	override fun generate(): Sequence<T> = repeatForever().flatMap { sequence }
}
