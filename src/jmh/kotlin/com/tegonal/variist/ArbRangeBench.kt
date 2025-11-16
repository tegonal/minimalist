package com.tegonal.variist

import com.tegonal.variist.generators.arb
import com.tegonal.variist.generators.impl.createBoundsArbGenerator
import com.tegonal.variist.generators.intRange
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@State(Scope.Benchmark)
open class ArbRangeBench {

	@Param(
		"10",
		"100",
		"1000",
		"65534",
		"65535",
		"100000",
		"1000000",
		"2147483648",
		"4294967296"
	)
	var size: Long = 0
	var minInclusive = Int.MIN_VALUE
	var maxInclusive = 0

	@Setup
	fun setup() {
		val t = (minInclusive.toLong() + size)
		if (t > Int.MAX_VALUE) error("overflow $t")
		maxInclusive = t.toInt()
	}

	@Benchmark
	fun intRangeWithIntDomainCheck() =
		// about 40% to 80% less memory usage than intRangeWithoutCheck
		// speedwise there is no difference (in the error range)
		arb.intRange(
			minInclusive = minInclusive,
			maxInclusive = maxInclusive,
			minSize = 1,
		).generate().take(1000).count()

	@Benchmark
	fun intRangeWithoutCheck() =
		arb.createBoundsArbGenerator(
			minInclusive = minInclusive.toLong(),
			maxInclusive = maxInclusive.toLong(),
			minSize = 1L,
			maxSize = null,
			{ s, e -> s.toInt()..e.toInt() }
		).generate().take(1000).count()

}
