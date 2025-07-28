package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

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
