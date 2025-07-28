package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseSemiOrderedArgsGeneratorTransformer<T, R>(
	private val baseGenerator: SemiOrderedArgsGenerator<T>,
	private val transformation: (Sequence<T>) -> Sequence<R>
) : BaseSemiOrderedArgsGenerator<R>(baseGenerator._components, baseGenerator.size) {

	override fun generateAfterChecks(offset: Int): Sequence<R> = baseGenerator.generate(offset).let(transformation)
}
