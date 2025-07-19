package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.extractSubject
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBeAfterOrTheSamePointInTimeAs
import ch.tutteli.atrium.api.fluent.en_GB.toBeAnInstanceOf
import ch.tutteli.atrium.api.fluent.en_GB.toBeBefore
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.fluent.en_GB.toHaveSize
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.generators.combine
import com.tegonal.minimalist.generators.combineDependent
import com.tegonal.minimalist.generators.fromRange
import com.tegonal.minimalist.generators.localDateFromUntil
import com.tegonal.minimalist.generators.map
import com.tegonal.minimalist.generators.random
import com.tegonal.minimalist.providers.impl.DefaultArgsGeneratorToArgumentsConverter
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import kotlin.test.Test

class GenericToArgumentsTransformerTest {

	@ParameterizedTest
	@ValueSource(ints = [1, 2, 4, 9, 15])
	fun randomOnly(numOfGenerators: Int, testInfo: TestInfo) {
		val firstList = random.fromRange(0 until 10).map { mutableListOf(it) }

		val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
			testInfo.testMethod.get(),
			ArgsSource(methodName = "dummy", fixedNumberOfArgs = 1000),
			(1 until numOfGenerators).fold(firstList) { generator, from ->
				generator.combine(random.fromRange(from * 10 until from * 10 + 10)) { list, num ->
					list.also {
						it.add(num)
					}
				}
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
		val startDates = random.localDateFromUntil(now, lastDayOfYear)

		val startAndEndDates = startDates.combineDependent { startDate ->
			random.localDateFromUntil(startDate, startDate.plusYears(1))
		}.map { listOf(it) }

		val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
			testInfo.testMethod.get(),
			ArgsSource(methodName = "dummy", fixedNumberOfArgs = 1000),
			startAndEndDates
		)

		expect(combinations) {
			toHaveSize(1000)
			combinations.forEachIndexed { index, arguments ->
				feature("index $index", { arguments.get().asList() }) {
					toHaveSize(2)
					extractSubject { l ->
						if (l.size == 2) {
							val (a1, a2) = l
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
	}

	companion object {
		@JvmStatic
		fun dummy() {
		}
	}

}
