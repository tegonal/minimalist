package com.tegonal.minimalist

import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInIntDomainWithoutDivideTrick
import com.tegonal.minimalist.generators.impl.prefixSumFast
import com.tegonal.minimalist.generators.impl.prefixSumSlower
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@State(Scope.Benchmark)
open class PrefixSumBench {

	@Param(
		"10",
		"100",
		"1000",
		"3000",
		"7000",
		"20000",
		"$possibleMaxSizeSafeInIntDomainWithoutDivideTrick"
	)
	var size: Int = 0
	var firstSum = 0

	@Setup
	fun setup() {
		firstSum = size
	}

	@Benchmark
	fun prefixSumFastBench() {
		// around twice as fast as prefixSumSlowerBench but in the nanoseconds
		// we use an if to determine which one to take, taking this if into account we are only 20% faster for the first
		// number, i.e only "worth" it if we generate multiple ranges and even then, we only safe some nanoseconds
		// per range
		repeat(1000) {
			Long.MAX_VALUE
			prefixSumFast(
				size,
				possibleMaxSizeSafeInIntDomainWithoutDivideTrick,
				1,
				possibleMaxSizeSafeInIntDomainWithoutDivideTrick
			)
		}
	}

	@Benchmark
	fun prefixSumSlowerBench() =
		repeat(1000) {
			prefixSumSlower(
				size,
				possibleMaxSizeSafeInIntDomainWithoutDivideTrick,
				1,
				possibleMaxSizeSafeInIntDomainWithoutDivideTrick
			)
		}
}
