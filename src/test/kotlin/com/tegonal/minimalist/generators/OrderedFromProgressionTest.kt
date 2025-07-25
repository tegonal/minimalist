package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedFromProgressionTest : AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharProgression", modifiedOrdered.fromProgression('a'..'d' step 2), listOf('a', 'c')),
		Tuple("fromIntProgression", modifiedOrdered.fromProgression(1..5 step 2), listOf(1, 3, 5)),
		Tuple("fromLongProgression", modifiedOrdered.fromProgression(1L..3L step 1), listOf(1L, 2L, 3L)),
	)
}
