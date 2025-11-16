package com.tegonal.variist.generators.impl

import com.tegonal.variist.config._components
import com.tegonal.variist.generators.SemiOrderedArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseSemiOrderedArgsGeneratorTransformer<T, R>(
	private val baseGenerator: SemiOrderedArgsGenerator<T>,
	private val transform: (Sequence<T>) -> Sequence<R>
) : BaseSemiOrderedArgsGenerator<R>(baseGenerator._components, baseGenerator.size) {

	override fun generateAfterChecks(offset: Int): Sequence<R> = baseGenerator.generate(offset).let(transform)
}
