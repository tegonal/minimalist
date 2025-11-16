package com.tegonal.variist

import com.tegonal.variist.utils.repeatForever
import org.junit.jupiter.api.Test
import org.openjdk.jmh.annotations.*
import java.util.BitSet
import java.util.HashSet
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class RandomAccessOrderedArgsGeneratorBench {

	@Param("0", "25", "40", "50", "75", "90", "100")
	var offsetPercentage: Int = 0
	var offset = 0

	@Param("1", "25", "40", "50", "75", "90", "100")
	var takePercentage: Int = 0
	var take = 0


	@Param("3", "7", "12", "1000")
	var numOfValues = 1
	var list: List<Int> = emptyList()
	var array: IntArray = intArrayOf()

	@Setup
	fun setup() {
		offset = numOfValues * offsetPercentage / 100
		take = numOfValues * takePercentage / 100
		if (take <= 0) take = 1
		list = (0..numOfValues).toList()
		array = IntArray(numOfValues) { it }
		check(list.isNotEmpty()) { "values was empty" }
	}

	@Benchmark
	fun list_flatMap() {
		repeatForever().flatMap { list }.drop(offset).take(take).count()
	}

	@Benchmark
	fun list_sequence() {
		val actualOffset = offset % numOfValues
		sequence {
			// Yield first partial chunk
			for (i in actualOffset until numOfValues) {
				yield(list[i])
			}

			// Then yield whole chunks forever
			while (true) {
				for (element in list) {
					yield(element)
				}
			}
		}.take(take).count()
	}

	@Benchmark
	fun list_RepeatingSequence() {
		RepeatingSequence1(list, offset).take(take).count()
	}

	@Benchmark
	fun list_RepeatingSequence2() {
		RepeatingSequence2(list.size, offset, list::get).take(take).count()
	}

	@Benchmark
	fun list_RepeatingSequence3() {
		RepeatingListSequence3(list, offset).take(take).count()
	}


//	@Benchmark
//	fun array_RepeatingSequence2() {
//		RepeatingSequence2(array.size, offset) { array[it] }
//	}
//
//	@Benchmark
//	fun array_flatMap() {
//		repeatForever().flatMap { array.asList() }.drop(offset).take(take).count()
//	}


	@Test
	fun `needs to be turned into a proper Bench if we want to compare it`() {
		listOf(1, 2, 3, 4).shuffled()
		val max = 60_000
		val maxNumber = 1_000_000
		listOf(
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
}

class RepeatingSequence1<T>(
	private val values: List<T>,
	offset: Int
) : Sequence<T> {
	private val size = values.size
	private val startIndex = offset % size

	override fun iterator(): Iterator<T> = object : Iterator<T> {
		var index = startIndex

		override fun hasNext() = true

		override fun next(): T =
			values[index].also {
				++index
				if (index == size) {
					index = 0
				}
			}
	}
}

class RepeatingSequence2<T>(
	private val size: Int,
	offset: Int,
	private val elementAt: (Int) -> T,
) : Sequence<T> {
	private val startIndex = offset % size

	override fun iterator(): Iterator<T> = object : Iterator<T> {
		var index = startIndex

		override fun hasNext() = true

		override fun next(): T =
			elementAt(index).also {
				++index
				if (index == size) {
					index = 0
				}
			}
	}
}

class RepeatingListSequence3<T>(
	private val values: List<T>,
	offset: Int
) : Sequence<T> {
	private val startIndex = offset % values.size

	override fun iterator(): Iterator<T> = object : RepeatingIterator<T>(startIndex, values.size) {
		override fun getElementAt(index: Int): T = values[index]
	}
}

internal abstract class RepeatingIterator<T>(
	startIndex: Int,
	private val endIndexExclusive: Int
) : Iterator<T> {
	private var index = startIndex

	override fun hasNext() = true

	override fun next(): T =
		getElementAt(index).also {
			++index
			if (index >= endIndexExclusive) {
				index = 0
			}
		}

	abstract fun getElementAt(index: Int): T
}
