package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class RandomArgsGeneratorFromProgressionTest : AbstractRandomArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharProgression", modifiedRandom.fromProgression('a'..'d' step 2), listOf('a', 'c')),
		Tuple("fromIntProgression", modifiedRandom.fromProgression(1..5 step 2), listOf(1, 3, 5)),
		Tuple("fromLongProgression", modifiedRandom.fromProgression(1L..3L step 1), listOf(1L, 2L, 3L)),
	)
}
