package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbTransformationTests : AbstractArbArgsGeneratorTest<Any>() {

	// see PseudoRandomArgsGeneratorTransformationTests for tests about combine

	override fun createGenerators(modifiedArb: ArbExtensionPoint) =
		listOf(1, 2, 3, 4).let { l ->
			val mapFun: (Int) -> Int = { it + 1 }
			val generator = modifiedArb.fromList(l)
			sequenceOf(
				Tuple("map", generator.map(mapFun), l.map(mapFun)),
				Tuple(
					"mapIndexed",
					generator.mapIndexed { index, it -> index to mapFun(it) },
					(0..maxTakeInCanAlwaysTakeTheDesiredAmountTest).flatMap { index ->
						l.map { index to mapFun(it) }
					}
				),
				Tuple(
					"filter", generator.filter { it % 2 == 0 },
					listOf(2, 4)
				),
				Tuple(
					"filterNot", generator.filterNot { it % 2 == 0 },
					listOf(1, 3)
				),
				Tuple(
					"chunked", generator.chunked(2),
					(1..4).flatMap { first -> (1..4).map { second -> listOf(first, second) } }
				),
				Tuple(
					"chunked with transform", generator.chunked(2) { it.toSet() },
					(1..4).flatMap { first -> (1..4).map { second -> setOf(first, second) } }
				),
				Tuple(
					"transformMaterialised - flatMap",
					generator.transform { s -> s.flatMap { sequenceOf(it, it + 10) } },
					listOf(1, 11, 2, 12, 3, 13, 4, 14)
				),
				Tuple(
					"transformMaterialised - zip",
					generator.transform { s -> s.zip(s) { a1, a2 -> a1 + a2 } },
					listOf(
						/* 1+1 = */ 2,
						/* 1+2 = */ 3,
						/* 1+3 = */ 4,
						/* 1+4 = */ 5,
						/* 2+4 = */ 6,
						/* 3+4 = */ 7,
						/* 4+4 = */ 8
					)
				),
			)
		}
}
