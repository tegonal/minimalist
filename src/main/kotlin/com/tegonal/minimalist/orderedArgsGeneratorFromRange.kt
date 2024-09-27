package com.tegonal.minimalist


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: CharRange): OrderedArgsGenerator<Args1<Char>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: IntRange): OrderedArgsGenerator<Args1<Int>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromRange(args: LongRange): OrderedArgsGenerator<Args1<Long>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

