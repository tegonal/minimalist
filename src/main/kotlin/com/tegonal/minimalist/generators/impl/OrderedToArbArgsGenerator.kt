package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.config._components

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class OrderedToArbArgsGenerator<T>(private val orderedArgsGenerator: OrderedArgsGenerator<T>) :
	IntRangeBasedArbArgsGenerator<T>(orderedArgsGenerator._components, 0, orderedArgsGenerator.size, { offset ->
		orderedArgsGenerator.generate(offset).first()
	})
