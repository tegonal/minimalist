package com.tegonal.minimalist.generators

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ordered.fromRange(args: CharRange): OrderedArgsGenerator<Char> =
	ordered.fromProgression(args)

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ordered.fromRange(args: IntRange): OrderedArgsGenerator<Int> =
	ordered.fromProgression(args)

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun ordered.fromRange(args: LongRange): OrderedArgsGenerator<Long> =
	ordered.fromProgression(args)

