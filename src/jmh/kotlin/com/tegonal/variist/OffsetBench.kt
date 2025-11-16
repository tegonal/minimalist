package com.tegonal.variist

import com.tegonal.variist.utils.impl.RepeatingListSequence
import com.tegonal.variist.utils.repeatForever
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class OffsetBench {

	var offset = 0

	@Param("1", "25", "40", "50", "75", "90", "100")
	var takePercentage: Int = 0
	var take = 0


	@Param("3", "7", "12", "1000")
	var numOfValues = 1
	var list: List<Int> = emptyList()

	@Setup
	fun setup() {
		take = numOfValues * takePercentage / 100
		if (take <= 0) take = 1
		list = (0..numOfValues).toList()
	}

	@Benchmark
	fun flatMap() {
		repeatForever().flatMap { list.asSequence() }.drop(0).take(take).count()
	}

	@Benchmark
	fun iterator() {
		RepeatingListSequence(list, 0).take(take).count()
	}
}
