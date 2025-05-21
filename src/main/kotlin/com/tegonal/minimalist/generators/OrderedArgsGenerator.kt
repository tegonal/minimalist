package com.tegonal.minimalist.generators

/**
 * Represents a generator which generates [T]s.
 *
 * @param T the type which this generator generates.
 *
 * @since 2.0.0
 */
interface ArgsGenerator<T>

/**
 * Represents an [ArgsGenerator] which provides [generate] which generates [T]s an infinite number of times in a random
 * or not defined order.
 */
interface RandomArgsGenerator<T> : ArgsGenerator<T> {

	fun generate(): Sequence<T>

	/**
	 * Extension point for things like [RandomArgsGenerator.of][RandomArgsGenerator.Companion.of].
	 *
	 * @since 2.0.0
	 */
	companion object
}

/**
 * Represents an [ArgsGenerator] which provides the method [generateOrdered] which generates [T]s always in the same
 * order and a finite number before repeating.
 *
 * @since 2.0.0
 */
interface OrderedArgsGenerator<T> : ArgsGenerator<T> {

	/**
	 * Returns the maximum of values this generator is able to generate before it starts over again.
	 *
	 * @since 2.0.0
	 */
	val size: Int


	/**
	 * Pure function which returns the given amount of values starting at [offset] where always the same values
	 * are generated when called multiple times.
	 *
	 * @since 2.0.0
	 */
	fun generateOrdered(offset: Int, amount: Int,): Sequence<T> = generateOrdered(offset).take(amount)

	/**
	 * Pure function which returns an infinite stream of values starting at [offset] where always the same values
	 * are generated when called multiple times.
	 *
	 * @since 2.0.0
	 */
	fun generateOrdered(offset: Int): Sequence<T>

	/**
	 * Extension point for things like [OrderedArgsGenerator.of][OrderedArgsGenerator.Companion.of].
	 *
	 * @since 2.0.0
	 */
	companion object
}
