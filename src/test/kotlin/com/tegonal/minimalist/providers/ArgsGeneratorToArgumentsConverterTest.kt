package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.impl.DefaultArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.testutils.Tuple4LikeStructure
import com.tegonal.minimalist.testutils.orderedWithSeed0
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import kotlin.test.Test

class ArgsGeneratorToArgumentsConverterTest {

    @Test
    fun pairs_splitIntoArgs() {
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy"), orderedWithSeed0.of(1 to 2, 3 to 4).map { listOf(it) }
        ).map { it.get().asList() }.toList()

        expect(combinations) {
            toHaveSize(2)
            get(0).toContainExactly(1, 2)
            get(1).toContainExactly(3, 4)
        }
    }

    @Test
    fun multiplePairs_splitIntoArgs() {
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy"),
            orderedWithSeed0.of(listOf(1 to 2, 3 to 4), listOf(5 to 6, 7 to 8))
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
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy"),
            orderedWithSeed0.of(tupleLike).map { listOf(it) }
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
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy"), orderedWithSeed0.of(args1, args2).map { listOf(it) }
        ).toList()
        expect(combinations).toContainExactly(args1, args2)
    }

    @Test
    fun multipleArgs_flattened() {
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy", fixedNumberOfArgs = 2), orderedWithSeed0.of(
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
        val firstList = random.fromRange(0 until 10).map { mutableListOf(it) }

        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            AnnotationData(argsSourceMethodName = "dummy", fixedNumberOfArgs = 1000),
            (1 until numOfGenerators).fold(firstList) { generator, from ->
                generator.combine(random.fromRange(from * 10 until from * 10 + 10)) { list, num ->
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
        val startDates = random.localDateFromUntil(now, lastDayOfYear)

        val startAndEndDates = startDates.combineDependent { startDate ->
            random.localDateFromUntil(startDate, startDate.plusYears(1))
        }.map { listOf(it) }

        val annotationData = AnnotationData(argsSourceMethodName = "dummy", fixedNumberOfArgs = 1000)
        val combinations = DefaultArgsGeneratorToArgumentsConverter().toArguments(
            annotationData,
            startAndEndDates
        ).toList()

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
