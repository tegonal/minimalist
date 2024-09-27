package com.tegonal.minimalist


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromProgression(args: CharProgression): OrderedArgsGenerator<Args1<Char>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })


/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromProgression(args: IntProgression): OrderedArgsGenerator<Args1<Int>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

/**
 * Returns an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 *
 * @return an [OrderedArgsGenerator] based on the given [args] where each element is transformed into an [Args1].
 * @since 2.0.0
 */
fun OrderedArgsGenerator.Companion.fromProgression(args: LongProgression): OrderedArgsGenerator<Args1<Long>> =
	OrderedArgsGenerator.fromList(args.map { Args.of(it) })

