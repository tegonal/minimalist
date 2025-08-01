package com.tegonal.minimalist.generators

import ch.tutteli.atrium.testfactories.TestFactory
import com.tegonal.minimalist.config.arb

typealias ArbArgsTestFactoryResult<T> = ArgsTestFactoryResult<T, ArbArgsGenerator<T>>

abstract class AbstractArbArgsGeneratorWithoutAnnotationsTest : AbstractArgsGeneratorTest() {

	val modifiedArb: ArbExtensionPoint = customComponentFactoryContainer.arb
}

abstract class AbstractArbArgsGeneratorTest<T>() : AbstractArbArgsGeneratorWithoutAnnotationsTest() {

	//TODO 2.0.0 pass modifiedArb via parameter and add a test case where we use a mocked Random so that we can be sure
	// it yields all values -- makes sense to introduce ArbSizeAware or the like now?
	abstract fun createGenerators(): ArbArgsTestFactoryResult<T>

	@TestFactory
	fun usesGivenComponentContainerFactory() = usesGivenComponentContainerFactoryTest(::createGenerators)

	@TestFactory
	fun canAlwaysTakeTheDesiredAmount() = canAlwaysTakeTheDesiredAmountTest(::createGenerators) { it.generate() }
}
