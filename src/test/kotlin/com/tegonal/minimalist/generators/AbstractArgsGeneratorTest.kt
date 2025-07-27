package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.config.createBasedOnConfig

typealias ArgsTestFactoryResult<T, ArgsGeneratorT> = Sequence<Triple<String, ArgsGeneratorT, List<T>>>

abstract class AbstractArgsGeneratorTest {
	protected val customComponentFactoryContainer =
		ComponentFactoryContainer.createBasedOnConfig(ordered._components.config.copy { seed = 1 })

	protected fun <T, ArgsGeneratorT : ArgsGenerator<T>, TestResultT : ArgsTestFactoryResult<T, ArgsGeneratorT>> usesGivenComponentContainerFactoryTest(
		factory: () -> TestResultT
	) = testFactory(factory) { generator, _, _ ->
		expect(generator._components).toBeTheInstance(customComponentFactoryContainer)
	}

	protected fun <T, ArgsGeneratorT : ArgsGenerator<T>, TestResultT : ArgsTestFactoryResult<T, ArgsGeneratorT>> canAlwaysTakeTheDesiredAmountTest(
		factory: () -> TestResultT,
		generate: (ArgsGeneratorT) -> Sequence<T>
	) = testFactory(factory, { testResult, givenTestFactory ->
		testResult.mapIndexed { index, (name, generator, expectedValues) ->
			{
				describe("[$index] $name") {
					arb.fromRange(1..500).generate().take(10).forEach { take ->
						it("take $take") {
							givenTestFactory(generator, expectedValues, take)
						}
					}
				}
			}
		}
	}) { generator, expectedValues, takeAsAny ->
		val take = takeAsAny as Int
		var count = 0
		generate(generator).take(take).forEach {
			expect(expectedValues).toContain(it)
			++count
		}
		expect(count).toEqual(take)
	}

	protected fun <T, ArgGeneratorT : ArgsGenerator<T>, TestResulT : ArgsTestFactoryResult<T, ArgGeneratorT>> testFactory(
		factory: () -> TestResulT,
		testFactoryDecorator: (TestResulT, TestFactoryBuilder.(generator: ArgGeneratorT, expectedValues: List<T>, decoratorValue: Any) -> Unit) -> Sequence<TestFactoryBuilder.() -> Unit> = { testResult, givenTestFactory ->
			testResult.mapIndexed { index, (name, generator, expectedValues) ->
				{
					it("[$index] $name") {
						givenTestFactory(generator, expectedValues, Unit)
					}
				}
			}
		},
		testFactory: TestFactoryBuilder.(generator: ArgGeneratorT, expectedValues: List<T>, decoratorValue: Any) -> Unit,
	) = testFactoryDecorator(factory(), testFactory).toVararg().let { (first, rest) ->
		ch.tutteli.atrium.api.verbs.testFactory(first, *rest)
	}
}
