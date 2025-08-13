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
class IntFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Int,
	toExclusive: Int
) : OpenEndRangeBasedArbArgsGenerator<Int>(componentFactoryContainer, seedBaseOffset, from, toExclusive) {

	override fun nextElementInRange(random: Random): Int = random.nextInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun IntFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Int,
	toInclusive: Int,
): ArbArgsGenerator<Int> =
	if (toInclusive == Int.MAX_VALUE) {
		if (from == Int.MIN_VALUE) ArbIntArgsGenerator(componentFactoryContainer, seedBaseOffset)
		else {
			//TODO 2.1.0 bench what is better (speed vs. memory), this approach or if we would swift the range
			LongFromUntilArbArgsGenerator(
				componentFactoryContainer,
				seedBaseOffset,
				from.toLong(),
				toInclusive.toLong() + 1
			).map { it.toInt() }
		}
	} else IntFromUntilArbArgsGenerator(componentFactoryContainer, seedBaseOffset, from, toInclusive + 1)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun LongFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Long,
	toInclusive: Long,
): ArbArgsGenerator<Long> =
	if (toInclusive == Long.MAX_VALUE) {
		if (from == Long.MIN_VALUE) {
			ArbLongArgsGenerator(componentFactoryContainer, seedBaseOffset)
		} else {
			BigIntFromUntilArbArgsGenerator(
				componentFactoryContainer,
				seedBaseOffset,
				from.toBigInt(),
				toInclusive.toBigInt() + BigInt.ONE
			).map { it.toLong() }
		}
	} else LongFromUntilArbArgsGenerator(componentFactoryContainer, seedBaseOffset, from, toInclusive + 1)


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Long,
	toExclusive: Long,
) : OpenEndRangeBasedArbArgsGenerator<Long>(componentFactoryContainer, seedBaseOffset, from, toExclusive) {
	override fun nextElementInRange(random: Random): Long = random.nextLong(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DoubleFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: Double,
	toExclusive: Double,
) : OpenEndRangeBasedArbArgsGenerator<Double>(componentFactoryContainer, seedBaseOffset, from, toExclusive) {
	override fun nextElementInRange(random: Random): Double = random.nextDouble(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class BigIntFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: BigInt,
	toExclusive: BigInt,
) : OpenEndRangeBasedArbArgsGenerator<BigInt>(componentFactoryContainer, seedBaseOffset, from, toExclusive) {
	override fun nextElementInRange(random: Random): BigInt = random.nextBigInt(from, toExclusive)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun BigIntFromToArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: BigInt,
	toInclusive: BigInt,
): ArbArgsGenerator<BigInt> = BigIntFromUntilArbArgsGenerator(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toInclusive + BigInt.ONE,
)
