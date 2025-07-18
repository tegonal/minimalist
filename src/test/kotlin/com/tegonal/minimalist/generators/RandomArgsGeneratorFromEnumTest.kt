package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class RandomArgsGeneratorFromEnumTest : AbstractRandomArgsGeneratorTest<RandomArgsGeneratorFromEnumTest.TestEnum>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromEnum", random.fromEnum<TestEnum>(), listOf(TestEnum.A, TestEnum.B, TestEnum.C, TestEnum.D))
	)

	enum class TestEnum {
		A, B, C, D
	}
}
