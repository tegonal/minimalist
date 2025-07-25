package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedFromListTest : AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		listOf(1, 2, 3).let { Tuple("fromList of Int", modifiedOrdered.fromList(it), it) },
		listOf('a', 'b', 'c').let { Tuple("fromList of Char", modifiedOrdered.fromList(it), it) },
		listOf<Any>(1, 2.0, 'a').let { Tuple("fromList of Any ", modifiedOrdered.fromList(it), it) },
	)
}
