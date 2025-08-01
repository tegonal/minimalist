package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbCharTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		// we skip testing char()

		Tuple("charFromTo", modifiedArb.charFromTo('a', 'd'), listOf('a', 'b', 'c', 'd')),
	)
}
