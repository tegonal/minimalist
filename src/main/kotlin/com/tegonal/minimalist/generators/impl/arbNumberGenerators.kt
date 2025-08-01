package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.map
import com.tegonal.minimalist.generators.mergeWeighted
import com.tegonal.minimalist.generators.of
import java.math.BigInteger
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
	if (toInclusive == Int.MAX_VALUE) {
		if (from == Int.MIN_VALUE) ArbIntArgsGenerator(componentFactoryContainer).map(argsProvider)
		else {
			//TODO 2.1.0 bench what is faster, this approach or if we would use mergeWeighted or recalculate the range and use
			// a mapping function
			LongFromUntilArbArgsGenerator(
				componentFactoryContainer,
				from.toLong(),
				toInclusive.toLong() + 1
			) { argsProvider(it.toInt()) }
		}
	} else IntFromUntilArbArgsGenerator(componentFactoryContainer, from, toInclusive + 1, argsProvider)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> LongFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toInclusive: Long,
	argsProvider: (Long) -> T
): ArbArgsGenerator<T> =
	if (toInclusive == Long.MAX_VALUE) {
		if (from == Long.MIN_VALUE) {
			ArbLongArgsGenerator(componentFactoryContainer).map(argsProvider)
		} else {
			val diff: BigInteger = BigInteger.valueOf(toInclusive).subtract(BigInteger.valueOf(from))

			// mergeWeighted fails if sum of weight is Int.MAX_VALUE or more, since we use weight 1 for Long.MAX_VALUE
			// we cannot use more than intMaxMinus2, That's not very accurate in terms of uniform distribution but for
			// simplicity reasons and the fact that Int.MAX_VALUE is an edge case, it probably doesn't hurt if it
			// appears more often, than specified.
			// TODO 2.1.0 reconsider once we introduce the concept of edge cases
			val intMaxMinus2 = Int.MAX_VALUE - 2
			val weight = if (diff < BigInteger.valueOf(intMaxMinus2.toLong())) diff.toInt() else intMaxMinus2

			mergeWeighted(
				1 to componentFactoryContainer.arb.of(argsProvider(Long.MAX_VALUE)),
				weight - 2 to LongFromUntilArbArgsGenerator(
					componentFactoryContainer,
					from,
					toInclusive,
					argsProvider
				)
			)
		}
	} else LongFromUntilArbArgsGenerator(componentFactoryContainer, from, toInclusive + 1, argsProvider)


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
