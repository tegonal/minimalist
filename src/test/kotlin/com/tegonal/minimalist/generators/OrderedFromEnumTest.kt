package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.AbcdEnum

class OrderedFromEnumTest : AbstractOrderedArgsGeneratorTest<AbcdEnum>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromEnum", modifiedOrdered.fromEnum<AbcdEnum>(), AbcdEnum.entries)
	)
}
