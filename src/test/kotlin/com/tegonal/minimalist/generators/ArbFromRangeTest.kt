package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbFromRangeTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharRange", modifiedArb.fromRange('a'..'d'), listOf('a', 'b', 'c', 'd')),
		Tuple("fromIntRange", modifiedArb.fromRange(1..5), listOf(1, 2, 3, 4, 5)),
		Tuple("fromLongRange", modifiedArb.fromRange(1L..3L), listOf(1L, 2L, 3L)),
	)
}
