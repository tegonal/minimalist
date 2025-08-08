package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.AbcdEnum

class ArbFromEnumTest : AbstractArbArgsGeneratorTest<AbcdEnum>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		Tuple("fromEnum", modifiedArb.fromEnum<AbcdEnum>(), AbcdEnum.entries)
	)
}
