package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedArgsGeneratorFromEnumTest : AbstractOrderedArgsGeneratorTest<OrderedArgsGeneratorFromEnumTest.TestEnum>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromEnum", ordered.fromEnum<TestEnum>(), listOf(TestEnum.A, TestEnum.B, TestEnum.C, TestEnum.D))
	)

	enum class TestEnum {
		A, B, C, D
	}
}
