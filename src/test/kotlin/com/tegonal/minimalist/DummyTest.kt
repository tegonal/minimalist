package com.tegonal.minimalist

import ch.tutteli.atrium.api.fluent.en_GB.toBeAfterOrTheSamePointInTimeAs
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.random.Random
import kotlin.system.measureNanoTime


enum class A {
	A, B, C, D, E, F, G, H
}


class DummyTest {

	@Test
	fun test() {

		OrderedArgsGenerator.fromEnum<A>()
		RandomArgsGenerator.localDateFromUntil(LocalDate.now(), LocalDate.now().plusYears(1), ChronoUnit.DAYS)
	}

	@Test
	fun `mainly here to see if setup works`() {
		listOf(1, 2, 3, 4).shuffled()
		val max = 60_000
		val maxNumber = 1_000_000
		bench(
			"int range" to {
				(0..maxNumber).shuffled().take(max).joinToString(", ")
			},
			"generateSequence " to {
				generateSequence(1) { it + 1 }.take(maxNumber).shuffled().take(max).joinToString(", ")
			},
			"sequence intrange" to {
				(0..maxNumber).asSequence().shuffled().take(max).joinToString(", ")
			},
			"bitset" to {
				val bs = BitSet(maxNumber)
				val numbers = Array(max) { 0 }
				var cardinality = 0
				while (cardinality < max) {
					val v: Int = Random.nextInt(maxNumber + 1)
					if (!bs[v]) {
						bs.set(v)
						numbers[cardinality] = v
						cardinality++
					}
				}
				numbers.joinToString(", ")
			},
			"hashset" to {
				val s = HashSet<Int>()
				while (s.size < max) s.add(Random.nextInt(maxNumber + 1))
				s.joinToString(", ")
			},
			"random" to {
				Random.nextInt(maxNumber + 1)
			}
		)
	}

	fun bench(vararg pairs: Pair<String, () -> Unit>) {
		val origin = pairs.clone()
		var map = hashMapOf(*pairs.map { it.first to 0L }.toTypedArray())
		repeat(100) {
			pairs.apply { shuffle() }.forEach {
				map[it.first] = map[it.first]!! + measureNanoTime { it.second() }
			}
		}
		origin.forEach {
			println("${it.first.padEnd(20)}: ${map[it.first]!!.toString().padStart(10)}")
		}
	}


	@ParameterizedTest
	@ArgsSource("randomOnly")
	fun foo(
		startDate: LocalDate,
		endDate: LocalDate,
		rank: Int
	) {
		val now = LocalDate.now()
		expect(startDate)
			.toBeAfterOrTheSamePointInTimeAs(now)
			.toBeLessThan(now.with(TemporalAdjusters.lastDayOfYear()))
	}

	@ParameterizedTest
	@ArgsSource("orderedOnly")
	fun testOrderedOnly(a1: A, a2: A) {
		println(a1.name + " / " + a2.name)
	}

	companion object {
		@JvmStatic
		fun randomOnly(): Pair<RandomArgsGenerator<Args2<LocalDate, LocalDate>>, RandomArgsGenerator<Args1<Int>>> {
			val now = LocalDate.now()
			val startDates = RandomArgsGenerator.localDateFromUntil(now, now.with(TemporalAdjusters.lastDayOfYear()))

			val startAndEndDates = startDates.appendDependent { (startDate) ->
				RandomArgsGenerator.localDateFromUntil(startDate, startDate.plusYears(1))
			}

			return startAndEndDates to RandomArgsGenerator.intFromUntil(2, 10)
		}

		@JvmStatic
		fun orderedOnly() = Tuple(
			OrderedArgsGenerator.fromEnum<A>(),
			OrderedArgsGenerator.fromEnum<A>()
		)
//
//		@JvmStatic
//		fun bla(): (Args2<LocalDate, LocalDate>) -> Boolean = { (a1) ->
//			// start date should not be first of year
//			a1.isEqual(a1.with(TemporalAdjusters.firstDayOfYear())).not()
//		}
	}

}


//TODO 2.1.0 introduce filtering, pinning (via config) and such, though, I think filtering via Annotation might be abit
// complicated as there could be a ArgsX with X > 1 behind the scene, i.e. filter just one argument might be not feasible.
// I suggest we start of with filtering in code only (not via annotation). Pinning via config might be more interesting
//@Target(AnnotationTarget.VALUE_PARAMETER)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ArgsFilter(val methodName: String)
