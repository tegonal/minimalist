@file:Suppress("UnusedReceiverParameter")

package com.tegonal.minimalist.generators


/**
 * Returns an [RandomArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromProgression(progression: CharProgression): RandomArgsGenerator<Char> =
	fromList(progression.toList())


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
fun RandomExtensionPoint.fromProgression(progression: IntProgression): RandomArgsGenerator<Int> =
	fromList(progression.toList())

/**
 * Returns an [RandomArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [RandomArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun RandomExtensionPoint.fromProgression(progression: LongProgression): RandomArgsGenerator<Long> =
	fromList(progression.toList())

