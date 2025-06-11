package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.utils.repeatForever

/**
 * Assumes the given [sequence] can be consumed multiple times.
 */
class PseudoRandomArgsGenerator<T>(private val sequence: Sequence<T>) : RandomArgsGenerator<T> {
	override fun generate(): Sequence<T> = repeatForever().flatMap { sequence }
}
