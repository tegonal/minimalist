package com.tegonal.minimalist

import ch.tutteli.atrium.api.fluent.en_GB.toBeAfterOrTheSamePointInTimeAs
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.utils.repeatForeverFromUntil
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
		ordered.fromEnum<A>()
		random.localDateFromUntil(LocalDate.now(), LocalDate.now().plusYears(1), ChronoUnit.DAYS)
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

	@Test
	fun benchCombine() {
		val a1Generator = ordered.fromRange(1..100)
		val a2Generator = ordered.fromRange(0 until 26).map { 'A' + it }
		val a1Size = a1Generator.size
		val a2Size = a2Generator.size
		val a1IsSmaller = a1Size < a2Size
		val maxSize = if (a1IsSmaller) a2Size else a1Size

		repeat(1) { r ->
			val offset = 10_000
			// we generate in chunks of maxSize thus we can already fast-forward to the correct chunk
			val chunkOffset = (offset / maxSize) % maxSize
			// within a chunk we might need to drop a few elements to reach the offset
			val inChunkOffset = offset % maxSize
			val transform = { i: Int, c: Char -> i to c }


			bench(
				"if" to { run ->
					// probably micro-optimisation, if this should become bigger, then we should refactor it
					val sequence = if (a1IsSmaller) {
						a1Generator.generate(chunkOffset).take(maxSize).drop(inChunkOffset)
							.zip(a2Generator.generate(0).take(maxSize).drop(inChunkOffset), transform) +
							repeatForeverFromUntil(0, a1Size, offset = chunkOffset + 1).flatMap { a1Offset ->
								a1Generator.generate(a1Offset).take(maxSize)
									.zip(a2Generator.generate(0).take(maxSize), transform)
							}
					} else {
						a1Generator.generate(0).take(maxSize).drop(inChunkOffset)
							.zip(a2Generator.generate(chunkOffset).take(maxSize).drop(inChunkOffset), transform) +
							repeatForeverFromUntil(0, a2Size, offset = chunkOffset + 1).flatMap { a2Offset ->
								a1Generator.generate(0).take(maxSize)
									.zip(a2Generator.generate(a2Offset).take(maxSize), transform)
							}
					}
					sequence.take(run * 10).count()
				},
				"var" to { run ->


					var a1OffsetFirstChunk = 0
					var a2OffsetFirstChunk = chunkOffset
					var sizeOfSmaller = a2Size
					if (a1IsSmaller) {
						a1OffsetFirstChunk = chunkOffset
						a2OffsetFirstChunk = 0
						sizeOfSmaller = a1Size
					}

					val sequence = run {
						a1Generator.generate(a1OffsetFirstChunk).take(maxSize).drop(inChunkOffset)
							.zip(
								a2Generator.generate(a2OffsetFirstChunk).take(maxSize).drop(inChunkOffset),
								transform
							)
					} + repeatForeverFromUntil(0, sizeOfSmaller, chunkOffset + 1).flatMap { offsetOfSmaller ->
						var a1Offset = 0
						var a2Offset = offsetOfSmaller
						if (a1IsSmaller) {
							a1Offset = offsetOfSmaller
							a2Offset = 0
						}

						a1Generator.generate(a1Offset).take(maxSize)
							.zip(a2Generator.generate(a2Offset).take(maxSize), transform)
					}
					sequence.take(run * 10).count()
				},
				"state" to { run ->

					val sizeOfSmaller = if (a1IsSmaller) a1Size else a2Size

					var firstTime = true
					val sequence = repeatForeverFromUntil(0, sizeOfSmaller, offset = chunkOffset).flatMap { offsetOfSmaller ->
						var a1Offset = 0
						var a2Offset = offsetOfSmaller
						var a1Drop = 0
						var a2Drop = 0
						if (firstTime) {
							firstTime = false
							a1Drop = if (a1IsSmaller) inChunkOffset else 0
							a2Drop = if (a1IsSmaller) 0 else inChunkOffset
						} else if (a1IsSmaller) {
							a1Offset = offsetOfSmaller
							a2Offset = 0
						}

						a1Generator.generate(a1Offset + a1Drop).take(maxSize).zip(
							a2Generator.generate(a2Offset + a2Drop).take(maxSize),
							transform
						)
//
//						zipWithGenerateSequence(
//							a1Generator.generateOrdered(maxSize, a1Offset + a1Drop),
//							a2Generator.generateOrdered(maxSize, a2Offset + a2Drop),
//							maxSize,
//							transform
//						)
					}
					sequence.take(run * 10).count()
				},
				"tuple" to { run ->


					val (a1OffsetFirstChunk, a2OffsetFirstChunk, sizeOfSmaller) =
						if (a1IsSmaller) Triple(chunkOffset, 0, a1Size)
						else Triple(0, chunkOffset, a2Size)

					val sequence = run {
						zipWithGenerateSequence(
							a1Generator.generate(a1OffsetFirstChunk).take(maxSize),
							a2Generator.generate(a2OffsetFirstChunk).take(maxSize),
							maxSize,
							transform
						).drop(inChunkOffset)
					} + repeatForeverFromUntil(0, sizeOfSmaller, offset = chunkOffset + 1).take(run * 10 / maxSize)
						.flatMap { offsetOfSmaller ->
							val (a1Offset, a2Offset) = if (a1IsSmaller) offsetOfSmaller to 0 else 0 to offsetOfSmaller

							zipWithGenerateSequence(
								a1Generator.generate(a1Offset).take(maxSize),
								a2Generator.generate(a2Offset).take(maxSize),
								maxSize,
								transform
							)
						}
					sequence.take(run * 10).count()
				},
				"tuple drop outside" to { run ->

					val (a1OffsetFirstChunk, a2OffsetFirstChunk, sizeOfSmaller) =
						if (a1IsSmaller) Triple(chunkOffset, 0, a1Size)
						else Triple(0, chunkOffset, a2Size)

					val sequence = run {
						a1Generator.generate(a1OffsetFirstChunk).take(maxSize)
							.zip(a2Generator.generate(a2OffsetFirstChunk).take(maxSize), transform)
							.drop(inChunkOffset)
					} + repeatForeverFromUntil(0, sizeOfSmaller, offset = chunkOffset + 1).flatMap { offsetOfSmaller ->
						val (a1Offset, a2Offset) = if (a1IsSmaller) offsetOfSmaller to 0 else 0 to offsetOfSmaller

						a1Generator.generate(a1Offset).take(maxSize)
							.zip(a2Generator.generate(a2Offset).take(maxSize), transform)
					}
					sequence.take(run * 10).count()
				},
				"flatMap" to { run ->
					val sequence = a1Generator.generate(0).take(run * 10 + offset).flatMap { a1 ->
						a2Generator.generate(0).take(run * 10 + offset).map { a2 -> a1 to a2 }
					}.drop(offset)
					sequence.take(run * 10).count()
				},
				"var optimised" to { run ->
					val sizeOfSmaller = if (a1IsSmaller) a1Size else a2Size
					val sequence =
						repeatForeverFromUntil(0, sizeOfSmaller, offset = chunkOffset).flatMapIndexed { index, offsetOfSmaller ->
							val a1Offset = if (a1IsSmaller) offsetOfSmaller else 0
							val a2Offset = if (a1IsSmaller) 0 else offsetOfSmaller

							val drop = if (index == 0) inChunkOffset else 0

							a1Generator.generate(a1Offset + drop).take(maxSize - drop).zip(
								a2Generator.generate(a2Offset + drop).take(maxSize - drop), transform
							)

//			zipWithGenerateSequence(
//				a1Generator.generateOrdered(maxSize, a1Offset + drop),
//				a2Generator.generateOrdered(maxSize, a2Offset + drop),
//				maxSize,
//				transform
//			)
						}
					sequence.take(run * 10).count()
				},
				"var optimised 2" to { run ->
					val sizeOfSmaller = if (a1IsSmaller) a1Size else a2Size
					val sequence =
						repeatForeverFromUntil(0, sizeOfSmaller, offset = chunkOffset).flatMapIndexed { index, offsetOfSmaller ->
							val a1Offset = if (a1IsSmaller) offsetOfSmaller else 0
							val a2Offset = if (a1IsSmaller) 0 else offsetOfSmaller

							val drop = if (index == 0) inChunkOffset else 0

							zipWithGenerateSequence(
								a1Generator.generate(a1Offset + drop).take(maxSize - drop),
								a2Generator.generate(a2Offset + drop).take(maxSize - drop),
								maxSize,
								transform
							)
						}
					sequence.take(run * 10).count()
				}
			)
			println("-----------------------------------------------------------------------------------")
		}
	}

	fun bench(vararg pairs: Pair<String, (Int) -> Unit>) {
		val origin = pairs.clone()
		val map = hashMapOf(*pairs.map { it.first to 0L }.toTypedArray())
		repeat(1000) { run ->
			pairs.apply { shuffle() }.forEach {
				map[it.first] = map[it.first]!! + measureNanoTime { it.second(run) }
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
	@ArgsSource("orderedOnly", fixedMaxNumberOfArgs = 70, fixedOffset = 1)
	fun testOrderedOnly(a1: A, a2: A) {
		println(a1.name + " / " + a2.name)
	}

	companion object {
		@JvmStatic
		fun randomOnly(): Pair<RandomArgsGenerator<Pair<LocalDate, LocalDate>>, RandomArgsGenerator<Int>> {
			val now = LocalDate.now()
			val startDates = random.localDateFromUntil(now, now.with(TemporalAdjusters.lastDayOfYear()))

			val startAndEndDates = startDates.combineDependent { startDate ->
				random.localDateFromUntil(startDate, startDate.plusYears(1))
			}

			val a = random.intFromUntil(2, 10).combine(random.intFromUntil(2, 10)) { a1, a2 ->

			}

			return startAndEndDates to random.intFromUntil(2, 10)
		}

		@JvmStatic
		fun orderedOnly() = Tuple(
			ordered.fromEnum<A>(),
			ordered.fromEnum<A>()
		)
//
//		@JvmStatic
//		fun bla(): (Args2<LocalDate, LocalDate>) -> Boolean = { (a1) ->
//			// start date should not be first of year
//			a1.isEqual(a1.with(TemporalAdjusters.firstDayOfYear())).not()
//		}
	}

}


//TODO 2.1.0 introduce filtering, pinning (via config) and such, though, I think filtering via Annotation might be a bit
// complicated as there could be a ArgsX with X > 1 behind the scene, i.e. filter just one argument might be not feasible.
// I suggest we start of with filtering in code only (not via annotation). Pinning via config might be more interesting
//@Target(AnnotationTarget.VALUE_PARAMETER)
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ArgsFilter(val methodName: String)

inline fun <A, B, R> zipWithGenerateSequence(
	seqA: Sequence<A>,
	seqB: Sequence<B>,
	size: Int,
	crossinline transform: (A, B) -> R
): Sequence<R> {
	val iterA = seqA.iterator()
	val iterB = seqB.iterator()
	var index = 0

	return generateSequence {
		if (index >= size) return@generateSequence null

		// assume iterators have enough elements
		val result = transform(iterA.next(), iterB.next())
		++index
		result
	}
}
