package com.tegonal.minimalist.generators

import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.kbox.Tuple

class OrderedAsSemiOrderedTransformationTest : AbstractOrderedArgsGeneratorTest<Int>() {

	// see SemiOrderedCombineTest for tests about combine

	override fun createGenerators() =
		listOf(1, 2, 3, 4).let { l ->
			val mapFun: (Int) -> Int = { it + 1 }
			val generator = modifiedOrdered.fromList(l) as SemiOrderedArgsGenerator<Int>

			sequenceOf(
				Tuple("map", generator.map(mapFun), l.map(mapFun)),
			)
		}

	@TestFactory
	override fun offsetPlusXReturnsTheSameAsOffsetXMinus1JustShifted() =
		offsetPlusXReturnsTheSameAsOffsetXMinus1JustShiftedTest {
			// this "law" does not hold for mapIndexed
			createGenerators().filter { it.first != "mapIndexed" }
		}
}
