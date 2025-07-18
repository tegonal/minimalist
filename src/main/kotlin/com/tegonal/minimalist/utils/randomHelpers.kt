package com.tegonal.minimalist.utils

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ordered
import kotlin.random.Random
import com.tegonal.minimalist.config.MinimalistConfig

/**
 * Picks randomly one element from `this` [Collection].
 * @since 2.0.0
 */
fun <T> Collection<T>.pickRandomly(): T =
	when (val size = size) {
		0 -> error("this Collection is empty, we cannot pick randomly an element from it")
		1 -> first()
		else -> {
			val index = createRandomBasedOnConfig().nextInt(0, size)
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
 * Picks randomly one element from `this` [Collection].
 * @since 2.0.0
 */
fun <T> Array<T>.pickRandomly(): T =
	when (val size = size) {
		0 -> error("this Array is empty, we cannot pick randomly an element from it")
		1 -> first()
		else -> {
			val index = createRandomBasedOnConfig().nextInt(0, size)
			get(index)
		}
	}


/**
 * Takes the given [amount] of elements from `this` [Iterable] (likewise [Iterable.take]) but in a random way.
 *
 * @since 2.0.0
 */
fun <T> Iterable<T>.takeRandomly(amount: Int): List<T> {
	// TODO 2.1.0 we could implement an optimisation for big take in case of List -> use BitSet, see code in jmh dir
	// TODO 2.1.0 introduce `shuffled` as supplement to `random`
	return asSequence().takeRandomly(amount).toList()
}

/**
 * Picks randomly the given [amount] of elements from `this` [Array] and returns them as [List].
 * @since 2.0.0
 */
fun <T> Array<T>.takeRandomly(amount: Int): List<T> {
	// TODO 2.1.0 we could implement an optimisation for big take -> use BitSet, see code in jmh dir
	return asSequence().takeRandomly(amount).toList()
}

/**
 * Picks randomly the given [amount] of elements from `this` [Array] and returns them as [List].
 * @since 2.0.0
 */
fun <T> Sequence<T>.takeRandomly(amount: Int): Sequence<T> {
	return this.shuffled(createRandomBasedOnConfig()).take(amount)
}

/**
 * Creates a [Random] based on the [MinimalistConfig] behind [ordered].
 *
 * Note, in case you define your own [ordered] with an own [MinimalistConfig] where you override/redefine
 * [MinimalistConfig.seed] then your seed will not be taken into account. Please open a feature request in such a case
 * explaining your use case, thanks.
 *
 * @since 2.0.0
 */
fun createRandomBasedOnConfig(): Random = Random(ordered._components.config.seed)

