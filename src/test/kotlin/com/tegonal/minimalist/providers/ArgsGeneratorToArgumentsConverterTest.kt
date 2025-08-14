package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.createBasedOnConfig
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.impl.DefaultArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.testutils.Tuple4LikeStructure
import com.tegonal.minimalist.testutils.orderedWithSeed0
import com.tegonal.minimalist.testutils.withMockedArgsRange
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import kotlin.test.Test

class ArgsGeneratorToArgumentsConverterTest {

	val testee = DefaultArgsGeneratorToArgumentsConverter()

	val requestedAtLeastArgs1000 = AnnotationData(
		argsSourceMethodName = "dummy",
		argsRangeOptions = ArgsRangeOptions(requestedMinArgs = 1000)
	)

	@Test
	fun `should not decide on ArgsRange`() {

		val orderedWithRange0To100 = ComponentFactoryContainer.createBasedOnConfig(
			// neither the config setting...
			MinimalistConfig().copy { maxArgs = 500 }
		).withMockedArgsRange(0, 1000).ordered

		// ... nor the AnnotationData
		val maxArgs100 = AnnotationData("dummy", argsRangeOptions = ArgsRangeOptions(maxArgs = 100))
		val combinations = testee.toArguments(
			maxArgs100, orderedWithRange0To100.of(1, 2, 3).map { listOf(it) }
		).map { it.get().asList() }.toList()

		expect(combinations).toHaveSize(1000)
	}

	@Test
	fun pairs_splitIntoArgs() {
		val combinations = testee.toArguments(
			requestedAtLeastArgs1000, orderedWithSeed0.of(1 to 2, 3 to 4).map { listOf(it) }
		).map { it.get().asList() }.toList()

		expect(combinations) {
			toHaveSize(2)
			get(0).toContainExactly(1, 2)
			get(1).toContainExactly(3, 4)
		}
	}

	@Test
	fun multiplePairs_splitIntoArgs() {
		val combinations = testee.toArguments(
			requestedAtLeastArgs1000, orderedWithSeed0.of(listOf(1 to 2, 3 to 4), listOf(5 to 6, 7 to 8))
		).map { it.get().asList() }.toList()
		expect(combinations) {
			toHaveSize(2)
			get(0).toContainExactly(1, 2, 3, 4)
			get(1).toContainExactly(5, 6, 7, 8)
		}
	}

	@Test
	fun tupleLike_notSplitIntoArgs() {
		val tupleLike = Tuple4LikeStructure(1, 2L, 3.0, 4f)
		val combinations = testee.toArguments(
			requestedAtLeastArgs1000, orderedWithSeed0.of(tupleLike).map { listOf(it) }
		).map { it.get().asList() }.toList()
		expect(combinations) {
			toHaveSize(1)
			get(0).toContainExactly(tupleLike)
		}
	}

	@Test
	fun oneArgs_keptAsIs() {
		val args1 = Args.of(1, 2)
		val args2 = Args.of(3, 4)
		val combinations = testee.toArguments(
			requestedAtLeastArgs1000, orderedWithSeed0.of(args1, args2).map { listOf(it) }
		).toList()
		expect(combinations).toContainExactly(args1, args2)
	}

	@Test
	fun multipleArgs_flattened() {
		val combinations = testee.toArguments(
			requestedAtLeastArgs1000, orderedWithSeed0.of(
				listOf(Args.of(1, 2), Args.of(3, 4)),
				listOf(Args.of(5, 6), Args.of(7, 8))
			)
		).map { it.get().asList() }.toList()
		expect(combinations) {
			toHaveSize(2)
			get(0).toContainExactly(1, 2, 3, 4)
			get(1).toContainExactly(5, 6, 7, 8)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [1, 2, 4, 9, 15])
	fun randomOnly(numOfGenerators: Int) {
		val firstList = arb.fromRange(0 until 10).map { mutableListOf(it) }

		val combinations = testee.toArguments(
			requestedAtLeastArgs1000,
			(1 until numOfGenerators).fold(firstList) { generator, from ->
				generator.combine(arb.fromRange(from * 10 until from * 10 + 10)) { list, num ->
					list.also { it.add(num) }
				}
			}
		).toList()
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
	fun randomOnlyDependent() {
		val now = LocalDate.now()
		val lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear())
		val startDates = arb.localDateFromUntil(now, lastDayOfYear)

		val startAndEndDates = startDates.combineDependent { startDate ->
			arb.localDateFromUntil(startDate, startDate.plusYears(1))
		}.map { listOf(it) }

		val combinations = testee.toArguments(requestedAtLeastArgs1000, startAndEndDates).toList()

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
}
