package com.tegonal.minimalist.generators

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: CharRange): OrderedArgsGenerator<Char> =
	OrderedArgsGenerator.fromList(args.toList())

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: IntRange): OrderedArgsGenerator<Int> =
	OrderedArgsGenerator.fromList(args.toList())

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [args].
 *
 * @return an [OrderedArgsGenerator] based on the given [args].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: LongRange): OrderedArgsGenerator<Long> =
	OrderedArgsGenerator.fromList(args.toList())

