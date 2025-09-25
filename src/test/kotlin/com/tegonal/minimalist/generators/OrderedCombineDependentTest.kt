package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedCombineDependentTest : AbstractOrderedArgsGeneratorTest<Int>() {

	// see OrderedCombineTest for tests about combine

	override fun createGenerators() = listOf(1, 2, 3, 4).let { l ->
		val generator = modifiedOrdered.fromList(l)
		sequenceOf(
			Tuple(
				"combineDependentMaterialised",
				generator.combineDependentMaterialised({ a1 ->
					a1.toLong().let { ordered.intFromUntil(a1 - 1, a1) }
				}) { a1, a2 ->
					a1 + a2
				},
				//1+0, 2+1, 3+2, 4+3
				listOf(1, 3, 5, 7)
			),
		)
	}
}
