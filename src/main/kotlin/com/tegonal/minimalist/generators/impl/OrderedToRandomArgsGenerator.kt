package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.config._components

class OrderedToRandomArgsGenerator<T>(private val orderedArgsGenerator: OrderedArgsGenerator<T>) :
	IntRangeBasedRandomArgsGenerator<T>(orderedArgsGenerator._components, 0, orderedArgsGenerator.size, { offset ->
		orderedArgsGenerator.generate(offset).first()
	})
