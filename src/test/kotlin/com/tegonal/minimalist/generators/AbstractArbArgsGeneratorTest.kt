package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.notToThrow
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.kbox.a2
import ch.tutteli.kbox.mapA3
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.generators.impl.DefaultArbExtensionPoint

typealias ArbArgsTestFactoryResult<T> = ArgsTestFactoryResult<T, ArbArgsGenerator<T>>

abstract class AbstractArbArgsGeneratorWithoutAnnotationsTest : AbstractArgsGeneratorTest() {

	private val modifiedArb: ArbExtensionPoint = customComponentFactoryContainer.arb

	fun <T> canBeCastToCoreArbArgsGeneratorTest(factory: (ArbExtensionPoint) -> ArbArgsTestFactoryResult<T>) =
		testFactory({ factory(modifiedArb) }) { generator, _, _ ->
			expect { generator._core }.notToThrow()
		}

	fun <T> seedOffsetInGeneratorIsTheSameAsUsingItInGenerate(factory: (ArbExtensionPoint) -> ArbArgsTestFactoryResult<T>) =
		arb.intPositive().generate().first().let { offset ->
			testFactory({
				factory(DefaultArbExtensionPoint(customComponentFactoryContainer, seedBaseOffset = 0))
					.zip(
						factory(DefaultArbExtensionPoint(customComponentFactoryContainer, seedBaseOffset = offset))
					) { a, b -> a.mapA3 { listOf(b.a2) } }
			}) { generator, generatorSeedOffset1InList, _ ->
				@Suppress("UNCHECKED_CAST")
				val generatorSeedOffset1 = generatorSeedOffset1InList.first() as ArbArgsGenerator<T>

				expect(generator.generate(seedOffset = offset).take(10).toList()).toEqual(
					generatorSeedOffset1.generate(seedOffset = 0).take(10).toList()
				)
			}
		}
}

abstract class AbstractArbArgsGeneratorTest<T>() : AbstractArbArgsGeneratorWithoutAnnotationsTest() {

	//TODO 2.0.0 pass modifiedArb via parameter and add a test case where we use a mocked Random so that we can be sure
	// it yields all values -- makes sense to introduce ArbSizeAware or the like now?
	abstract fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<T>

	@TestFactory
	fun canBeCastToCoreArbArgsGenerator() = canBeCastToCoreArbArgsGeneratorTest(::createGenerators)

	@TestFactory
	fun checkSeedOffset() = seedOffsetInGeneratorIsTheSameAsUsingItInGenerate(::createGenerators)


	@TestFactory
	fun usesGivenComponentContainerFactory() =
		usesGivenComponentContainerFactoryTest { createGenerators(customComponentFactoryContainer.arb) }

	@TestFactory
	fun canAlwaysTakeTheDesiredAmount() =
		canAlwaysTakeTheDesiredAmountTest({ createGenerators(customComponentFactoryContainer.arb) }) { it.generate() }
}
