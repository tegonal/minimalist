package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.AbcdEnum

class RandomArgsGeneratorFromEnumTest : AbstractRandomArgsGeneratorTest<AbcdEnum>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromEnum", modifiedRandom.fromEnum<AbcdEnum>(), AbcdEnum.entries)
	)
}
