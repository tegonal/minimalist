package com.tegonal.minimalist.generators


/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromProgression(progression: CharProgression): RandomArgsGenerator<Char> =
	RandomArgsGenerator.fromList(progression.toList())


// TODO 2.1.0 optimise, if step = 1 and endInclusive is not MAX_VALUE, then we could use
//  RandomArgsGenerator.intFromUntil -- for other cases we could come up with something more efficient which does not
//  require to materialise the progression, I guess something like RandomArgsGenerator.intFromUntil + modulo would be
//  more efficient
/**
 * Returns an [RandomArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromProgression(progression: IntProgression): RandomArgsGenerator<Int> =
	RandomArgsGenerator.fromList(progression.toList())

/**
 * Returns an [RandomArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.fromProgression(progression: LongProgression): RandomArgsGenerator<Long> =
	RandomArgsGenerator.fromList(progression.toList())

