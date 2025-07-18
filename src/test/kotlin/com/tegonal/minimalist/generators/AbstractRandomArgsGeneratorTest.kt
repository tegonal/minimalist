package com.tegonal.minimalist.generators

import ch.tutteli.atrium.testfactories.TestFactory
import com.tegonal.minimalist.config.random

typealias RandomArgsTestFactoryResult<T> = ArgsTestFactoryResult<T, RandomArgsGenerator<T>>

abstract class AbstractRandomArgsGeneratorWithoutAnnotationsTest : AbstractArgsGeneratorTest() {

	val random: RandomExtensionPoint = customComponentFactoryContainer.random
}

abstract class AbstractRandomArgsGeneratorTest<T>() : AbstractRandomArgsGeneratorWithoutAnnotationsTest() {

	abstract fun createGenerators(): RandomArgsTestFactoryResult<T>

	@TestFactory
	fun usesGivenComponentContainerFactory() = usesGivenComponentContainerFactoryTest(::createGenerators)

	@TestFactory
	fun canAlwaysTakeTheDesiredAmount() = canAlwaysTakeTheDesiredAmountTest(::createGenerators) { it.generate() }
}
