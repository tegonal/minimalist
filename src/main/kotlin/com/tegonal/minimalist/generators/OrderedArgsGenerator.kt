package com.tegonal.minimalist.generators

/**
 * Represents an [ArgsGenerator] which provides the method [generate] which generates [T]s always in the same
 * order and a finite number ([size]) before repeating.
 *
 * @since 2.0.0
 */
interface OrderedArgsGenerator<out T> : SemiOrderedArgsGenerator<T> {

	/**
	 * Returns the maximum of values `this` generator is able to generate before it starts over again.
	 *
	 * @since 2.0.0
	 */
	override val size: Int

	/**
	 * Returns an infinite stream of values starting at [offset] and repeating after reaching [size] of values
	 * where always the same values are generated when called multiple times.
	 *
	 * @since 2.0.0
	 */
	override fun generate(offset: Int): Sequence<T>
}
