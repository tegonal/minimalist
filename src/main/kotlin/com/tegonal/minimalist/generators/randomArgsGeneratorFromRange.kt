package com.tegonal.minimalist.generators

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromRange(args: CharRange): RandomArgsGenerator<Char> =
	RandomArgsGenerator.fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromRange(args: IntRange): RandomArgsGenerator<Int> =
	RandomArgsGenerator.fromProgression(args)

/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [RandomArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromRange(args: LongRange): RandomArgsGenerator<Long> =
	RandomArgsGenerator.fromProgression(args)

