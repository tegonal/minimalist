package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbRangeTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<Any> {
		val minSize2MaxSize2 = listOf('a'..'b', 'b'..'c', 'c'..'d')
		val minSize1MaxSize2 = listOf('a'..'a', 'b'..'b', 'c'..'c', 'd'..'d') + minSize2MaxSize2
		val minSize0MaxSize2 = listOf('d'..'a') + minSize1MaxSize2
		return sequenceOf(
			Tuple(
				"charRange minSize=0, maxSize=2",
				modifiedArb.charRange('a', 'd', maxSize = 2),
				minSize0MaxSize2
			),
			Tuple(
				"charRange minSize=1, maxSize=2",
				modifiedArb.charRange('a', 'd', minSize = 1, maxSize = 2),
				minSize1MaxSize2
			),
			Tuple(
				"intRange minSize=0, maxSize=2",
				modifiedArb.intRange(1, 4, maxSize = 2),
				minSize0MaxSize2.map { it.first.code - 'a'.code + 1..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"intRange minSize=1, maxSize=2",
				modifiedArb.intRange(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { it.first.code - 'a'.code + 1..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"longRange minSize=0, maxSize=2",
				modifiedArb.longRange(1, 4, maxSize = 2),
				minSize0MaxSize2.map { (it.first.code - 'a'.code + 1).toLong()..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"longRange minSize=1, maxSize=2",
				modifiedArb.longRange(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { (it.first.code - 'a'.code + 1).toLong()..it.last.code - 'a'.code + 1 }
			),
		)
	}

	//TODO 2.0.0 tests for zero
}
