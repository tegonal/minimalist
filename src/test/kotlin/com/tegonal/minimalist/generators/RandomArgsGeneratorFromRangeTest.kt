package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class RandomArgsGeneratorFromRangeTest : AbstractRandomArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharRange", modifiedRandom.fromRange('a'..'d'), listOf('a', 'b', 'c', 'd')),
		Tuple("fromIntRange", modifiedRandom.fromRange(1..5), listOf(1, 2, 3, 4, 5)),
		Tuple("fromLongRange", modifiedRandom.fromRange(1L..3L), listOf(1L, 2L, 3L)),
	)
}
