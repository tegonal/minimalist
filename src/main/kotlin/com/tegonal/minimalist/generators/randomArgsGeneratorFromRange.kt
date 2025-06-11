package com.tegonal.minimalist.generators

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun random.fromRange(args: CharRange): RandomArgsGenerator<Char> =
	random.fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun random.fromRange(args: IntRange): RandomArgsGenerator<Int> =
	random.fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun random.fromRange(args: LongRange): RandomArgsGenerator<Long> =
	random.fromProgression(args)

