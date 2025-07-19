package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.config._components

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

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SemiOrderedArgsGeneratorTransformer<T, R>(
	baseGenerator: SemiOrderedArgsGenerator<T>,
	transformation: (Sequence<T>) -> Sequence<R>
) : BaseSemiOrderedArgsGeneratorTransformer<T, R>(baseGenerator, transformation)
