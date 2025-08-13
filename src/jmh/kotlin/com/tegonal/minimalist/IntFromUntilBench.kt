package com.tegonal.minimalist

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.generateAndTake
import com.tegonal.minimalist.generators.impl.BaseSemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.utils.impl.BaseIntFromUntilRepeatingIterator
import com.tegonal.minimalist.utils.impl.checkRangeNumbers
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 300, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(3)
@State(Scope.Benchmark)
open class IntFromUntilBench {

	val argsRange = ArgsRange(offset = 0, take = 1000)

	// without is 2 - 4 times faster than withIdentity and uses 100x less memory, so it is beneficial to remove the
	// argsProvider. withIdentity is about 0.4 - 0.5 faster than withoutPlusMap (memory about the same).
	// That's not enough to keep the complexity, for now we use only an approach which does not require argsProvider
	@Benchmark
	fun withIdentity() = IntFromUntilWithIdentityArgsProvider(ordered._components, 0, 1000, 1,::identity)
			.generateAndTake(argsRange).count()

	@Benchmark
	fun without(): Int = IntFromUntilWithoutArgsProvider(ordered._components, 0, 1000, 1)
		.generateAndTake(argsRange).count()

	// no significant difference between without and withoutWithIntFromUntilIterator, so we are going to reuse the
	// iterator
	@Benchmark
	fun withoutWithIntFromUntilIterator(): Int =
		IntFromUntilWithoutArgsProviderWithIntFromUntilIterator(ordered._components, 0, 1000, 1)
			.generateAndTake(argsRange).count()

	@Benchmark
	fun withoutPlusMap(): Int = IntFromUntilWithoutArgsProvider(ordered._components, 0, 1000, 1)
		.generateAndTake(argsRange).map(::identity).count()
}

class IntFromUntilWithIdentityArgsProvider<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	private val from: Int,
	private val toExclusive: Int,
	private val step: Int,
	private val argsProvider: (Int) -> T
) : BaseSemiOrderedArgsGenerator<T>(
	componentFactoryContainer,
	run {
		// we first check the numbers before calculating the size as the size would be wrong
		// if the invariants are not given
		checkRangeNumbers(from, toExclusive, offset = 0, step = step)
		// range size could be bigger than Int.MAX_VALUE, hence we use toLong
		(toExclusive - from + step - 1) / step
	}
), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> = Sequence {
		object : BaseIntFromUntilRepeatingIterator<T>(from, toExclusive, offset, step) {
			override fun getElementAt(index: Int): T = argsProvider(index)
		}
	}
}

class IntFromUntilWithoutArgsProvider(
	componentFactoryContainer: ComponentFactoryContainer,
	private val from: Int,
	private val toExclusive: Int,
	private val step: Int,
) : BaseSemiOrderedArgsGenerator<Int>(
	componentFactoryContainer,
	run {
		// we first check the numbers before calculating the size as the size would be wrong
		// if the invariants are not given

		checkRangeNumbers(from, toExclusive, offset = 0, step = step)
		(toExclusive - from + step - 1) / step
	}
), OrderedArgsGenerator<Int> {

	override fun generateAfterChecks(offset: Int): Sequence<Int> = Sequence {
		object : Iterator<Int> {
			private var index: Int

			init {
				checkRangeNumbers(from, toExclusive, offset, step)

				val offsetMax = toExclusive - from
				val offsetInSteps = Math.multiplyExact(offset, step)
				index = from + if (offsetInSteps < offsetMax) offsetInSteps else offsetInSteps % offsetMax
			}

			override fun next(): Int =
				index.also {
					if (index < toExclusive - step) {
						index += step
					} else {
						index = from
					}
				}

			override fun hasNext(): Boolean = true
		}
	}
}


class IntFromUntilWithoutArgsProviderWithIntFromUntilIterator(
	componentFactoryContainer: ComponentFactoryContainer,
	private val from: Int,
	private val toExclusive: Int,
	private val step: Int,
) : BaseSemiOrderedArgsGenerator<Int>(
	componentFactoryContainer,
	run {
		// we first check the numbers before calculating the size as the size would be wrong
		// if the invariants are not given

		checkRangeNumbers(from, toExclusive, offset = 0, step = step)
		(toExclusive - from + step - 1) / step
	}
), OrderedArgsGenerator<Int> {

	override fun generateAfterChecks(offset: Int): Sequence<Int> = Sequence {
		object : BaseIntFromUntilRepeatingIterator<Int>(from, toExclusive, offset = 0, step = step) {
			override fun getElementAt(index: Int): Int = index
		}
	}
}

