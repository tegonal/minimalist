package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedArgsGeneratorFromRangeTest : AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharRange", modifiedOrdered.fromRange('a'..'d'), listOf('a', 'b', 'c', 'd')),
		Tuple("fromIntRange", modifiedOrdered.fromRange(1..5), listOf(1, 2, 3, 4, 5)),
		Tuple("fromLongRange", modifiedOrdered.fromRange(1L..3L), listOf(1L, 2L, 3L)),
	)
}
