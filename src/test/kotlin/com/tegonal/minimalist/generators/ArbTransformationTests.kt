package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.createArbWithCustomConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

class ArbTransformationTests : AbstractArbArgsGeneratorTest<Int>() {

	// see PseudoRandomArgsGeneratorTransformationTests for tests about combine

	override fun createGenerators(modifiedArb: ArbExtensionPoint) =
		listOf(1, 2, 3, 4).let { l ->
			val mapFun: (Int) -> Int = { it + 1 }
			val generator = modifiedArb.fromList(l)
			sequenceOf(
				Tuple("map", generator.map(mapFun), l.map(mapFun)),
				Tuple("mapIndexed", generator.mapIndexed { _, it -> mapFun(it) }, l.map(mapFun)),
				Tuple(
					"filter", generator.filter { it % 2 == 0 },
					listOf(2, 4)
				),
				Tuple(
					"filterNot", generator.filterNot { it % 2 == 0 },
					listOf(1, 3)
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
