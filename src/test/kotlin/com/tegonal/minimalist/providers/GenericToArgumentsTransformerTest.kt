package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.append
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Representation
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.impl.DefaultGenericToArgumentsTransformer
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import kotlin.test.Test

class GenericToArgumentsTransformerTest {

	@Test
	fun emptyListThrows(testInfo: TestInfo) {
		expect {
			DefaultGenericToArgumentsTransformer().toArguments(
				testInfo.testMethod.get(),
				ArgsSource(methodName = "dummy"),
				emptyList()
			)
		}.toThrow<IllegalStateException> {
			messageToContain("no generators")
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [1, 2, 4, 9, 15])
	fun randomOnly(numOfGenerators: Int, testInfo: TestInfo) {
		val combinations = DefaultGenericToArgumentsTransformer().toArguments(
			testInfo.testMethod.get(),
			ArgsSource(methodName = "dummy", fixedMaxNumberOfArgs = 1000),
			(0 until numOfGenerators).map {
				RandomArgsGenerator.fromRange(it * 10 until it * 10 + 10)
			}
		)
		expect(combinations) {
			toHaveSize(1000)
			//TODO use the following once https://github.com/robstoll/atrium/issues/1359 is fixed
//			toHaveElementsAndAll {
//				feature("get", { get().asList() }) {
//					... (see below)
//				}
//			}
			combinations.forEachIndexed { index, arguments ->
				feature("index $index", { arguments.get().asList() }) {
					val entries = (0 until numOfGenerators).map {
						expectLambda<Any> {
							toBeAnInstanceOf<Int> {
								toBeGreaterThanOrEqualTo(it * 10)
								toBeLessThan(it * 10 + 10)
							}
						}
					}
					val (first, rest) = entries.toVararg()
					toContainExactly(first, *rest)
				}
			}
		}
	}

	@Test
	fun randomOnlyDependent(testInfo: TestInfo) {
		val now = LocalDate.now()
		val lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear())
		val startDates = RandomArgsGenerator.localDateFromUntil(now, lastDayOfYear)

		val startAndEndDates = startDates.combineDependent { startDate ->
			RandomArgsGenerator.localDateFromUntil(startDate, startDate.plusYears(1))
		}
		val combinations = DefaultGenericToArgumentsTransformer().toArguments(
			testInfo.testMethod.get(),
			ArgsSource(methodName = "dummy", fixedMaxNumberOfArgs = 1000),
			listOf(startAndEndDates)
		)
		val firstNameGenerator = RandomArgsGenerator.intFromUntil(1, 1000).map { "firstName $it" }
			.map { Args.of(it, representation1 = Representation("firstName")) }
		val lastNameGenerator = RandomArgsGenerator.intFromUntil(1, 1000).map { "lastName $it" }
		val ageGenerator = RandomArgsGenerator.intFromUntil(1, 90)

		val a = Tuple(firstNameGenerator, lastNameGenerator, ageGenerator).append(ageGenerator)

		val p = firstNameGenerator.combine(lastNameGenerator).combine(ageGenerator).map { (args1, firstName, age) ->
			Person(args1.a1, firstName, age)
		}
		OrderedArgsGenerator.fromEnum<A>()

		expect(combinations) {
			toHaveSize(1000)
			combinations.forEachIndexed { index, arguments ->
				feature("index $index", { arguments.get().asList() }) {
					toHaveSize(2)
					extractSubject { (a1, a2) ->
						feature("a1") { a1 }.toBeAnInstanceOf<LocalDate> {
							toBeAfterOrTheSamePointInTimeAs(now)
							toBeBefore(lastDayOfYear)
						}
						val start = a1 as LocalDate
						feature("a2") { a2 }.toBeAnInstanceOf<LocalDate> {
							toBeAfterOrTheSamePointInTimeAs(start)
							toBeBefore(start.plusYears(1))
						}
					}
				}
			}
		}
	}

	private data class Person(val firstName: String, val lastName: String, val age: Int)

	enum class A {
		A, B, C, D, E, F, G, H
	}

	companion object {
		@JvmStatic
		fun dummy() {
		}
	}

}



