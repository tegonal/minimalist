package com.tegonal.minimalist

import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.fromList
import com.tegonal.minimalist.generators.impl.SemiOrderedArgsGeneratorCombiner
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.utils.repeatForever
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@State(Scope.Benchmark)
open class CombinerBench {

	@Param("0")//, "10", "25", "50", "75", "99")//@Param("0", "25", "40", "50", "75", "90", "100")
	var offsetPercentage: Int = 0
	var offset = 0

	@Param("1", "25", "50", "75", "100")//@Param("1", "25", "40", "50", "75", "90", "100")
	var takePercentage: Int = 0
	var take = 0


	@Param("12", "30", "120", "300", "500")
	var numOfInts = 1
	lateinit var intGenerator: OrderedArgsGenerator<Int>
	lateinit var charGenerator: OrderedArgsGenerator<Char>
	lateinit var ints: List<Int>
	lateinit var chars: List<Char>

	@Setup
	fun setup() {
		val combinations = numOfInts * numOfInts / 3
		offset = combinations * offsetPercentage / 100
		take = (combinations * takePercentage / 100) * 10
		if (take <= 0) take = 1

		ints = (0..numOfInts).toList()
		chars = (0..numOfInts / 3).map { (it + 65).toChar() }
		intGenerator = ordered.fromList(ints)
		charGenerator = ordered.fromList(chars)
	}

	@Benchmark
	fun iterator() =
		SemiOrderedArgsGeneratorCombiner(intGenerator, charGenerator, ::Pair).generate(offset).take(take).count()


	@Benchmark
	fun flatMap() = repeatForever().flatMap {
		ints.asSequence().flatMap { a1 -> chars.asSequence().map { a2 -> a1 to a2 } }
	}.drop(offset).take(take).count()

}
