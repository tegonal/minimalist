package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedArgsGeneratorFromProgressionTest :
	AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharProgression", ordered.fromProgression('a'..'d' step 2), listOf('a', 'c')),
		Tuple("fromIntRange", ordered.fromProgression(1..5 step 2), listOf(1, 3, 5)),
		Tuple("fromLongRange", ordered.fromProgression(1L..3L step 1), listOf(1L, 2L, 3L)),
	)
}
