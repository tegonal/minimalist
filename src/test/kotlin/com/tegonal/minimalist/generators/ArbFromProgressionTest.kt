package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbFromProgressionTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		Tuple("fromCharProgression", modifiedArb.fromProgression('a'..'d' step 2), listOf('a', 'c')),
		Tuple("fromIntProgression", modifiedArb.fromProgression(1..5 step 2), listOf(1, 3, 5)),
		Tuple("fromLongProgression", modifiedArb.fromProgression(1L..3L step 1), listOf(1L, 2L, 3L)),
	)
}
