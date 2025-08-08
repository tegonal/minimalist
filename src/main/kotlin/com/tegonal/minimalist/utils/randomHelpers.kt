package com.tegonal.minimalist.utils

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.createMinimalistRandom
import com.tegonal.minimalist.utils.impl.checkIsPositive
import com.tegonal.minimalist.generators.ordered
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

/**
 * Picks randomly one element from `this` [Collection] based on the configured [MinimalistConfig.seed]
 * @since 2.0.0
 */
fun <T> Collection<T>.pickRandomly(): T =
	when (val size = size) {
		0 -> error("this Collection is empty, we cannot pick randomly an element from it")
		1 -> first()
		else -> {
			val index = createMinimalistRandom().nextInt(0, size)
			// basically the same as elementAt but more efficient as we don't have to check if index is in range
			when (this) {
				is List -> get(index)
				else -> {
					val iterator = this.iterator()
					repeat(index) { iterator.next() }
					iterator.next()
				}
			}
		}
	}

//TODO 2.1.0 we could provide overloads for specialised array types such as IntArray
/**
 * Picks randomly one element from `this` [Collection] based on the configured [MinimalistConfig.seed].
 * @since 2.0.0
 */
fun <T> Array<T>.pickRandomly(): T =
	when (val size = size) {
		0 -> error("this Array is empty, we cannot pick randomly an element from it")
		1 -> first()
		else -> {
			val index = createMinimalistRandom().nextInt(0, size)
			get(index)
		}
	}


/**
 * Takes the given [amount] of elements from `this` [Iterable] (likewise [Iterable.take]) but in a random way
 * based on the configured [MinimalistConfig.seed].
 *
 * @since 2.0.0
 */
fun <T> Iterable<T>.takeRandomly(amount: Int): List<T> {
	// TODO 2.1.0 we could implement an optimisation for big take in case of List -> use BitSet, see code in jmh dir
	// TODO 2.1.0 introduce `shuffled` as supplement to `random`
	return asSequence().takeRandomly(amount).toList()
}

/**
 * Takes the given [amount] of elements from `this` [Array] (likewise [Array.take]) but in a random way
 * based on the configured [MinimalistConfig.seed].
 *
 * @since 2.0.0
 */
fun <T> Array<T>.takeRandomly(amount: Int): List<T> {
	// TODO 2.1.0 we could implement an optimisation for big take -> use BitSet, see code in jmh dir
	return asSequence().takeRandomly(amount).toList()
}

/**
 * Takes the given [amount] of elements from `this` [Sequence] (likewise [Sequence.take]) but in a random way
 * based on the configured [MinimalistConfig.seed].
 *
 * The operation is intermediate and stateful.
 *
 * @throws OutOfMemoryError in case of an infinite Sequence
 *
 * @since 2.0.0
 */
fun <T> Sequence<T>.takeRandomly(amount: Int): Sequence<T> {
	// TODO 2.1.0 we could implement an optimisation for big take -> use BitSet, see code in jmh dir
	return this.shuffled(createMinimalistRandom()).take(amount)
}

/**
 * Creates a [Random] based on the [MinimalistConfig] behind [ordered].
 *
 * Note, in case you define your own [ordered] with an own [MinimalistConfig] where you override/redefine
 * [MinimalistConfig.seed] then your seed will not be taken into account. Please open a feature request in such a case
 * explaining your use case, thanks.
 *
 * Since the resulting [Random] is based on a static seed, the resulting number sequence will always be the same, making
 * it possible to re-test the same setup on failure. However, you should also be aware of that using
 * `createMinimalistRandom.next...` in a loop will produce always the same number. You should assign it to e.g. a
 * variable named `minimalistRandom` outside the loop.
 *
 * @since 2.0.0
 */
fun createMinimalistRandom(): Random = ordered._components.createMinimalistRandom(seedOffset = 0)

/**
 * @since 2.0.0
 */
typealias BigInt = BigInteger

/**
 * @since 2.0.0
 */
fun Random.nextBigInt(from: BigInt, toExclusive: BigInt): BigInt {
	require(from < toExclusive) { "from ($from) has to be less than toExclusive ($toExclusive)" }
	val range = toExclusive - from
	val result = nextBigInt(range)
	return result + from
}

/**
 * @since 2.0.0
 */
fun Random.nextBigInt(toExclusive: BigInt): BigInt {
	checkIsPositive(toExclusive, "toExclusive")
	val bitLength = toExclusive.bitLength()
	when {
		bitLength <= 31 -> return BigInt.valueOf(nextInt(toExclusive.toInt()).toLong())
		bitLength <= 63 -> return BigInt.valueOf(nextLong(toExclusive.toLong()))
		else -> {
			val javaRandom = this.asJavaRandom()
			var count = 0
			val maxTries = 50
			while (true) {
				val candidate = BigInteger(bitLength, javaRandom)
				// rejection sampling: as we generate 0..2^bitLength - 1 which might be greater than or equal to
				// toExclusive, we only accept a candidate which fits into the requested range and retry otherwise.
				// Chances to pick a wrong candidate is in the worst case (toExclusive = 2^x+1) at nearly 50%, i.e.
				// we should quickly get a candidate in most cases. To prevent that we run into a bug in case of a buggy
				// random we stop after 50 tries (in theory, 50 times a wrong candidate in a row could happen even for
				// a non-buggy random but the chances are (1/2)^50, so very unlikely and a buggy random more likely).
				if (candidate < toExclusive) return candidate
				else if (count >= maxTries) error("looks like we deal with a non uniform-random ($this), could not find a candidate after $count tries -- are you mocking Random? If so, adjust your mocking logic, if not, then try to re-run your test and report the buggy random in case this error re-appears")
				else ++count
			}
		}
	}
}
