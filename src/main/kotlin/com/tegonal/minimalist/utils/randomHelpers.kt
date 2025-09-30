package com.tegonal.minimalist.utils

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.createMinimalistRandom
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.generators.toArbArgsGenerator
import kotlin.random.Random
import com.tegonal.minimalist.generators.ArbArgsGenerator
/**
 * Picks randomly one element from `this` [Collection] based on the configured [MinimalistConfig.seed]
 * @since 2.0.0
 */
fun <T> Collection<T>.pickOneRandomly(): T =
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
fun <T> Array<T>.pickOneRandomly(): T =
	when (val size = size) {
		0 -> error("this Array is empty, we cannot pick randomly an element from it")
		1 -> first()
		else -> {
			val index = createMinimalistRandom().nextInt(0, size)
			get(index)
		}
	}

/**
 * Picks randomly one element from `this` [SemiOrderedArgsGenerator] based on the configured [MinimalistConfig.seed].
 *
 * @since 2.0.0
 */
fun <T> SemiOrderedArgsGenerator<T>.pickOneRandomly(seedOffset: Int = 0): T =
	_components.createMinimalistRandom(seedOffset).nextInt(0, size).let { offset ->
		generateOne(offset)
	}

/**
 * Takes randomly the given [amount] of elements from `this` [SemiOrderedArgsGenerator].
 *
 * @since 2.0.0
 */
fun <T> SemiOrderedArgsGenerator<T>.takeRandomly(amount: Int, seedOffset: Int = 0): List<T> =
	toArbArgsGenerator().generate(seedOffset).take(amount).toList()


/**
 * Takes the given [amount] of elements from `this` [Iterable] (likewise [Iterable.take]) but in a random way
 * based on the configured [MinimalistConfig.seed].
 *
 * @since 2.0.0
 */
fun <T> Iterable<T>.takeRandomly(amount: Int): List<T> {
	// TODO 2.1.0 we could implement an optimisation for big take in case of List -> use BitSet, see code in jmh dir
	// TODO 2.1.0 introduce `shuffled` as supplement to `arb`?
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
 * Makes sure the given [seed] is positive, so that it can be used as offset.
 */
fun seedToOffset(seed: Int): Int = seed ushr 1
