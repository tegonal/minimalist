package com.tegonal.minimalist.generators


/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun random.fromProgression(progression: CharProgression): RandomArgsGenerator<Char> =
	random.fromList(progression.toList())


// TODO 2.1.0 optimise, if step = 1 and endInclusive is not MAX_VALUE, then we could use
//  random.intFromUntil -- for other cases we could come up with something more efficient which does not
//  require to materialise the progression, I guess something like random.intFromUntil + modulo would be
//  more efficient
/**
 * Returns an [RandomArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun random.fromProgression(progression: IntProgression): RandomArgsGenerator<Int> =
	random.fromList(progression.toList())

/**
 * Returns an [RandomArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun random.fromProgression(progression: LongProgression): RandomArgsGenerator<Long> =
	random.fromList(progression.toList())

