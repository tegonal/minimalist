package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.AbcdEnum

class ArbArgsGeneratorFromEnumTest : AbstractArbArgsGeneratorTest<AbcdEnum>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromEnum", modifiedArb.fromEnum<AbcdEnum>(), AbcdEnum.entries)
	)
}
