package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.ArbArgsGenerator
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbIntArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer
) : RandomBasedArbArgsGenerator<Int>(componentFactoryContainer) {

	override fun Random.nextElement(): Int = nextInt()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbLongArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer
) : RandomBasedArbArgsGenerator<Long>(componentFactoryContainer) {

	override fun Random.nextElement(): Long = nextLong()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbDoubleArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer
) : RandomBasedArbArgsGenerator<Double>(componentFactoryContainer) {

	override fun Random.nextElement(): Double = nextDouble()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class IntFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toExclusive: Int,
	argsProvider: (Int) -> T
) : OpenEndRangeBasedArbArgsGenerator<Int, T>(componentFactoryContainer, from, toExclusive, argsProvider) {

	final override fun nextElementInRange(random: Random): Int = random.nextInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> IntFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toInclusive: Int,
	argsProvider: (Int) -> T
): ArbArgsGenerator<T> =
	//TODO 2.1.0 bench what is faster, this approach or if we would use mergeWeighted
	if (toInclusive == Int.MAX_VALUE) {
		LongFromUntilArbArgsGenerator(
			componentFactoryContainer,
			from.toLong(),
			toInclusive.toLong() + 1
		) { argsProvider(it.toInt()) }
	} else IntFromUntilArbArgsGenerator(componentFactoryContainer, from, toInclusive + 1, argsProvider)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class LongFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : OpenEndRangeBasedArbArgsGenerator<Long, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	final override fun nextElementInRange(random: Random): Long = random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class DoubleFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : OpenEndRangeBasedArbArgsGenerator<Double, T>(componentFactoryContainer, from, toExclusive, argsProvider) {
	final override fun nextElementInRange(random: Random): Double = random.nextDouble(from, toExclusive)
}
