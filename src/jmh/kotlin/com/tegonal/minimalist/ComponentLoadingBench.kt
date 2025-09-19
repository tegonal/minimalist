package com.tegonal.minimalist

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.config.create
import com.tegonal.minimalist.config.impl.createSingletonVia
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.impl.ProfileBasedArgsRangeDecider
import com.tegonal.minimalist.utils.impl.loadService
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@State(Scope.Benchmark)
open class ComponentLoadingBench {

	// 10x slower on my machine but the difference is 5'000 nanoseconds vs. ~50'000 nanoseconds.
	// Not worth optimising for now
	@Benchmark
	fun viaLoadService() =
		ComponentFactoryContainer.create(
			mapOf(
				ArgsRangeDecider::class createSingletonVia { _ ->
					loadService<ArgsRangeDecider>(ProfileBasedArgsRangeDecider::class.qualifiedName!!)
				},
			),
			emptyMap()
		).build<ArgsRangeDecider>()

	@Benchmark
	fun hardCoded() =
		ComponentFactoryContainer.create(
			mapOf(
				ArgsRangeDecider::class createSingletonVia { _ ->
					ProfileBasedArgsRangeDecider()
				},
			),
			emptyMap()
		).build<ArgsRangeDecider>()
}
