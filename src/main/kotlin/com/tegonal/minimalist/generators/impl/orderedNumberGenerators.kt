package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.BigInt
import com.tegonal.minimalist.utils.impl.BigIntRepeatingSequence
import com.tegonal.minimalist.utils.impl.IntRepeatingSequence
import com.tegonal.minimalist.utils.impl.LongRepeatingSequence
import com.tegonal.minimalist.utils.impl.checkRangeNumbers
import com.tegonal.minimalist.utils.toBigInt

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class IntFromUntilOrderedArgsGenerator<T>(
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
		calculatedRangeSizeToArgsGeneratorSize(from.toLong(), toExclusive.toLong(), step.toLong())
	}
), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		IntRepeatingSequence(
			from = from,
			toExclusive = toExclusive,
			offset = offset,
			step = step,
			argsProvider = argsProvider
		)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LongFromUntilOrderedArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	private val from: Long,
	private val toExclusive: Long,
	private val step: Long,
	private val argsProvider: (Long) -> T
) : BaseSemiOrderedArgsGenerator<T>(
	componentFactoryContainer,
	run {
		// we first check the numbers before calculating the size as the size would be wrong
		// if the invariants are not given
		checkRangeNumbers(from, toExclusive, offset = 0, step = step)
		// range size could be bigger than Int.MAX_VALUE, hence we use toBigInt
		calculatedRangeSizeToArgsGeneratorSize(from.toBigInt(), toExclusive.toBigInt(), step.toBigInt())
	}
), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		LongRepeatingSequence(
			from = from,
			toInclusive = toExclusive,
			offset = offset.toLong(),
			step = step,
			argsProvider = argsProvider
		)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class BigIntFromUntilOrderedArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	private val from: BigInt,
	private val toExclusive: BigInt,
	private val step: BigInt,
	private val argsProvider: (BigInt) -> T
) : BaseSemiOrderedArgsGenerator<T>(
	componentFactoryContainer,
	run {
		// we first check the numbers before calculating the size as the size would be wrong
		// if the invariants are not given
		checkRangeNumbers(from, toExclusive, offset = BigInt.ZERO, step = step)
		calculatedRangeSizeToArgsGeneratorSize(from, toExclusive, step)
	}
), OrderedArgsGenerator<T> {

	override fun generateAfterChecks(offset: Int): Sequence<T> =
		BigIntRepeatingSequence(
			from = from,
			toInclusive = toExclusive,
			offset = offset.toBigInt(),
			step = step,
			argsProvider = argsProvider
		)
}


private fun calculatedRangeSizeToArgsGeneratorSize(
	from: Long,
	toExclusive: Long,
	step: Long,
) = calculatedRangeSizeToArgsGeneratorSize(from, toExclusive, step, Long::minus, Long::plus, Long::div, one = 1)

private fun calculatedRangeSizeToArgsGeneratorSize(
	from: BigInt,
	toExclusive: BigInt,
	step: BigInt,
) = calculatedRangeSizeToArgsGeneratorSize(
	from,
	toExclusive,
	step,
	BigInt::minus,
	BigInt::plus,
	BigInt::div,
	one = BigInt.ONE
)


private inline fun <NumberT> calculatedRangeSizeToArgsGeneratorSize(
	from: NumberT,
	toExclusive: NumberT,
	step: NumberT,
	crossinline minus: (NumberT, NumberT) -> NumberT,
	crossinline plus: (NumberT, NumberT) -> NumberT,
	crossinline divide: (NumberT, NumberT) -> NumberT,
	one: NumberT,
): NumberT where NumberT : Number, NumberT : Comparable<NumberT> {
	val elementsInRange = minus(toExclusive, from)
	val roundUpTrick = plus(elementsInRange, minus(step, one))
	val numberOfSteps = divide(roundUpTrick, step)
	// if step is greater than the range size we get 0 as a result but the progression's size is 1 in such a case
	return maxOf(one, numberOfSteps)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> IntFromToOrderedArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Int,
	toInclusive: Int,
	step: Int,
	argsProvider: (Int) -> T
): OrderedArgsGenerator<T> =
	if (toInclusive == Int.MAX_VALUE) {
		//TODO 2.1.0 bench what is better (speed vs. memory), this approach or if we would swift the range if possible?
		LongFromUntilOrderedArgsGenerator(
			componentFactoryContainer,
			from.toLong(),
			toInclusive.toLong() + 1,
			step.toLong()
		) {
			argsProvider(it.toInt())
		}
	} else IntFromUntilOrderedArgsGenerator(componentFactoryContainer, from, toInclusive + 1, step, argsProvider)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
@Suppress("FunctionName")
fun <T> LongFromToOrderedArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: Long,
	toInclusive: Long,
	step: Long,
	argsProvider: (Long) -> T
): OrderedArgsGenerator<T> =
	if (toInclusive == Long.MAX_VALUE) {
		//TODO 2.1.0 bench what is better (speed vs. memory), this approach or if we would swift the range
		BigIntFromUntilOrderedArgsGenerator(
			componentFactoryContainer,
			from.toBigInt(),
			toInclusive.toBigInt() + BigInt.ONE,
			step.toBigInt()
		) {
			argsProvider(it.toLong())
		}
	} else LongFromUntilOrderedArgsGenerator(componentFactoryContainer, from, toInclusive + 1, step, argsProvider)
