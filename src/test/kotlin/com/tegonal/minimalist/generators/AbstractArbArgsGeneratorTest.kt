package com.tegonal.minimalist.generators

import ch.tutteli.atrium.testfactories.TestFactory
import com.tegonal.minimalist.config.random

typealias ArbArgsTestFactoryResult<T> = ArgsTestFactoryResult<T, ArbArgsGenerator<T>>

abstract class AbstractArbArgsGeneratorWithoutAnnotationsTest : AbstractArgsGeneratorTest() {

	val modifiedArb: ArbExtensionPoint = customComponentFactoryContainer.random
}

abstract class AbstractArbArgsGeneratorTest<T>() : AbstractArbArgsGeneratorWithoutAnnotationsTest() {

	abstract fun createGenerators(): ArbArgsTestFactoryResult<T>

	@TestFactory
	fun usesGivenComponentContainerFactory() = usesGivenComponentContainerFactoryTest(::createGenerators)

	@TestFactory
	fun canAlwaysTakeTheDesiredAmount() = canAlwaysTakeTheDesiredAmountTest(::createGenerators) { it.generate() }
}
