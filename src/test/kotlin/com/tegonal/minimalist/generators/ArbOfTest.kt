package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbOfTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<Any> = sequenceOf(
		Tuple(
			"of(1)",
			modifiedArb.of(1),
			listOf(1)
		),
		Tuple(
			"of(1, 5)",
			modifiedArb.of(1, 5),
			listOf(1, 5)
		),
	)
}
