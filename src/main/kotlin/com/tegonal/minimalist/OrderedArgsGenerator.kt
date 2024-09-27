package com.tegonal.minimalist

/**
 * Represents a generator which generates [Args].
 *
 * @param T the type of [Args] (such as [Args1], [Args2] etc.) this generator generates.
 *
 * @since 2.0.0
 */
interface ArgsGenerator<T : Args> {

}

interface RandomArgsGenerator<T : Args> : ArgsGenerator<T> {

    fun generate(amount: Int): List<T>

    companion object
}


/**
 * Represents an [ArgsGenerator] which provides [generateOrdered] which generates [T]s always in the same order and
 * a finite number before repeating.
 *
 * @since 2.0.0
 */
interface OrderedArgsGenerator<T : Args> : ArgsGenerator<T> {

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
    fun generateOrdered(amount: Int, offset: Int): List<T>

    /**
     * Extension point for things like [OrderedArgsGenerator.of][OrderedArgsGenerator.Companion.of].
     */
    companion object
}
