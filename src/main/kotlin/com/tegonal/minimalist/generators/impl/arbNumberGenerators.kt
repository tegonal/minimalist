package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.map
import com.tegonal.minimalist.utils.BigInt
import com.tegonal.minimalist.utils.nextBigInt
import com.tegonal.minimalist.utils.toBigInt
import java.math.BigInteger
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbIntArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
) : RandomBasedArbArgsGenerator<Int>(componentFactoryContainer, seedBaseOffset) {

	override fun Random.nextElement(): Int = nextInt()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbLongArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
) : RandomBasedArbArgsGenerator<Long>(componentFactoryContainer, seedBaseOffset) {

	override fun Random.nextElement(): Long = nextLong()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbDoubleArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
) : RandomBasedArbArgsGenerator<Double>(componentFactoryContainer, seedBaseOffset) {

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
	seedBaseOffset: Int,
	from: Int,
	toExclusive: Int,
	//TODO 2.0.0 don't provide an argsProvider, one can use a map on the ArgsGenerator instead
	// (same same for other generators where we use ::identity for the default case)
	argsProvider: (Int) -> T
) : OpenEndRangeBasedArbArgsGenerator<Int, T>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	argsProvider
) {

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
	seedBaseOffset: Int,
	from: Int,
	toInclusive: Int,
	argsProvider: (Int) -> T
): ArbArgsGenerator<T> =
	if (toInclusive == Int.MAX_VALUE) {
		if (from == Int.MIN_VALUE) ArbIntArgsGenerator(componentFactoryContainer, seedBaseOffset).map(argsProvider)
		else {
			//TODO 2.1.0 bench what is better (speed vs. memory), this approach or if we would swift the range
			LongFromUntilArbArgsGenerator(
				componentFactoryContainer,
				seedBaseOffset,
				from.toLong(),
				toInclusive.toLong() + 1
			) { argsProvider(it.toInt()) }
		}
	} else IntFromUntilArbArgsGenerator(componentFactoryContainer, seedBaseOffset, from, toInclusive + 1, argsProvider)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> LongFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Long,
	toInclusive: Long,
	argsProvider: (Long) -> T
): ArbArgsGenerator<T> =
	if (toInclusive == Long.MAX_VALUE) {
		if (from == Long.MIN_VALUE) {
			ArbLongArgsGenerator(componentFactoryContainer, seedBaseOffset).map(argsProvider)
		} else {
			BigIntFromUntilArbArgsGenerator(
				componentFactoryContainer,
				seedBaseOffset,
				from.toBigInt(),
				toInclusive.toBigInt() + BigInt.ONE
			) {
				argsProvider(it.toLong())
			}
		}
	} else LongFromUntilArbArgsGenerator(componentFactoryContainer, seedBaseOffset, from, toInclusive + 1, argsProvider)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class LongFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Long,
	toExclusive: Long,
	argsProvider: (Long) -> T
) : OpenEndRangeBasedArbArgsGenerator<Long, T>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	argsProvider
) {
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
	seedBaseOffset: Int,
	from: Double,
	toExclusive: Double,
	argsProvider: (Double) -> T
) : OpenEndRangeBasedArbArgsGenerator<Double, T>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	argsProvider
) {
	final override fun nextElementInRange(random: Random): Double = random.nextDouble(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
open class BigIntFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: BigInteger,
	toExclusive: BigInteger,
	argsProvider: (BigInteger) -> T
) : OpenEndRangeBasedArbArgsGenerator<BigInteger, T>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	argsProvider
) {
	final override fun nextElementInRange(random: Random): BigInteger = random.nextBigInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> BigIntFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: BigInteger,
	toInclusive: BigInteger,
	argsProvider: (BigInteger) -> T
): ArbArgsGenerator<T> = BigIntFromUntilArbArgsGenerator(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toInclusive + BigInt.ONE,
	argsProvider
)
