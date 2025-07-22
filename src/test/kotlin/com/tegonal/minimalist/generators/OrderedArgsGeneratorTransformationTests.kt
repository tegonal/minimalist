package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedArgsGeneratorTransformationTests : AbstractOrderedArgsGeneratorTest<Int>() {

	// see OrderedArgsGeneratorCombinerTest for tests about combine

	override fun createGenerators() = listOf(1, 2, 3, 4).let { l ->
		val mapFun: (Int) -> Int = { it + 1 }
		val generator = modifiedOrdered.fromList(l)
		sequenceOf(
			Tuple("map", generator.map(mapFun), l.map(mapFun)),
			Tuple(
				"filterMaterialised", generator.filterMaterialised { it % 2 == 0 }, listOf(2, 4)
			),
			Tuple(
				"transformMaterialised - flatMap",
				generator.transformMaterialised { s -> s.flatMap { sequenceOf(it, it + 10) } },
				listOf(1, 11, 2, 12, 3, 13, 4, 14)
			),
			Tuple(
				"transformMaterialised - zip",
				generator.transformMaterialised { s -> s.zip(s.map { it + 1 }) { a1, a2 -> a1 + a2 } },
				listOf(3, 5, 7, 9)
			),
		)
	}
}
