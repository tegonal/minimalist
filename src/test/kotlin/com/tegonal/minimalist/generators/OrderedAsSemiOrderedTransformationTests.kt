package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedAsSemiOrderedTransformationTests : AbstractOrderedArgsGeneratorTest<Int>() {

	// see SemiOrderedArgsGeneratorCombinerTest for tests about combine

	override fun createGenerators() =
		listOf(1, 2, 3, 4).let { l ->
			val mapFun: (Int) -> Int = { it + 1 }
			val generator = modifiedOrdered.fromList(l) as SemiOrderedArgsGenerator<Int>
			sequenceOf(
				Tuple("map", generator.map(mapFun), l.map(mapFun)),
			)
		}
}
