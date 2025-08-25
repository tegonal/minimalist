package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.testfactories.TestFactory
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.impl.DefaultOrderedExtensionPoint
import com.tegonal.minimalist.utils.createMinimalistRandom
import kotlin.math.absoluteValue

typealias OrderedArgsTestFactoryResult<T> = ArgsTestFactoryResult<T, SemiOrderedArgsGenerator<T>>

abstract class AbstractOrderedArgsGeneratorWithoutAnnotationsTest : AbstractArgsGeneratorTest() {

	val modifiedOrdered: OrderedExtensionPoint = DefaultOrderedExtensionPoint(customComponentFactoryContainer)

	protected fun <T> canAlwaysTakeTheDesiredAmountTest(factory: () -> OrderedArgsTestFactoryResult<T>) =
		super.canAlwaysTakeTheDesiredAmountTest(factory) { it.generate(ordered._components.config.seed.absoluteValue) }

	protected fun <T> coversAllCasesTest(factory: () -> OrderedArgsTestFactoryResult<T>) =
		testFactory(factory) { generator, expectedValues, _ ->
			expectGrouped {
				expect(generator.size).toEqual(expectedValues.size)

				(1..expectedValues.size).forEach { take ->
					group("take $take") {
						val l = generator.generate().take(take).toList()
						@Suppress("UNCHECKED_CAST")
						(expect(l) as Expect<List<*>>).toContainExactlyElementsOf(expectedValues.take(take))
					}
				}
			}
		}

	protected fun <T> minusOffsetThrowsTest(factory: () -> OrderedArgsTestFactoryResult<T>) =
		createMinimalistRandom().let { minimalistRandom ->
			testFactory(factory) { generator, _, _ ->
				expect {
					generator.generate(minimalistRandom.nextInt(Int.MIN_VALUE, -1))
				}.toThrow<IllegalStateException>()
			}
		}


	protected fun <T> returnsDifferentValuesUntilReachingSizeAndThenRepeatsTest(
		factory: () -> OrderedArgsTestFactoryResult<T>
	) = testFactory(factory) { generator, _, _ ->
		val generatorSize = generator.size
		val doubleSize = generatorSize * 2
		val list = generator.generate().take(doubleSize).toList()
		expect(list) {
			size.toEqual(doubleSize)
			feature("toSet", { toSet() }) {
				size.toEqual(generatorSize)
			}
			(0 until generatorSize).forEach {
				get(it).toEqual(list[it + generatorSize])
			}
		}
	}

	protected fun <T> offsetPlusXReturnsTheSameAsOffsetXMinus1JustShiftedTest(
		factory: () -> OrderedArgsTestFactoryResult<T>
	) = testFactory(factory) { generator, expectedValues, _ ->
		val take = expectedValues.size
		val list = generator.generate().take(take).toList()
		expectGrouped {
			(1 until take).forEach { offset ->
				generator.generate(offset).take(take).forEachIndexed { index, value ->
					val listIndex = (index + offset) % take
					group("offset $offset / index $index same as offset ${offset - 1} index $listIndex") {
						expect(value).toEqual(list[listIndex])
					}
				}
			}
		}
	}

}

abstract class AbstractOrderedArgsGeneratorTest<T>() : AbstractOrderedArgsGeneratorWithoutAnnotationsTest() {

	abstract fun createGenerators(): OrderedArgsTestFactoryResult<T>

	@TestFactory
	fun usesGivenComponentContainerFactory() = usesGivenComponentContainerFactoryTest(::createGenerators)

	@TestFactory
	fun canAlwaysTakeTheDesiredAmount() = canAlwaysTakeTheDesiredAmountTest(::createGenerators)

	@TestFactory
	fun generateOneIsTheSameAsGenerateFirst() =
		generateOneIsTheSameAsGenerateFirstTest(
			factory = { createGenerators() },
			generateOne = { it.generateOne(customComponentFactoryContainer.config.seed.absoluteValue) },
			generate = { it.generate(customComponentFactoryContainer.config.seed.absoluteValue) }
		)

	@TestFactory
	fun coversAllCases() = coversAllCasesTest(::createGenerators)

	@TestFactory
	fun minusOffsetThrows() = minusOffsetThrowsTest(::createGenerators)

	@TestFactory
	fun returnsDifferentValuesUntilReachingSizeAndThenRepeats() =
		returnsDifferentValuesUntilReachingSizeAndThenRepeatsTest(::createGenerators)

	@TestFactory
	fun offsetPlusXReturnsTheSameAsOffsetXMinus1JustShifted() =
		offsetPlusXReturnsTheSameAsOffsetXMinus1JustShiftedTest(::createGenerators)
}
