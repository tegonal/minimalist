package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class OrderedArgsGeneratorTransformer<T, R>(
	baseGenerator: OrderedArgsGenerator<T>,
	transformation: (Sequence<T>) -> Sequence<R>
) : BaseSemiOrderedArgsGeneratorTransformer<T, R>(baseGenerator, transformation), OrderedArgsGenerator<R>
