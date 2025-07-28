package com.tegonal.minimalist.generators


/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: CharProgression): ArbArgsGenerator<Char> =
	fromList(progression.toList())


// TODO 2.1.0 optimise, if step = 1 and endInclusive is not MAX_VALUE, then we could use
//  random.intFromUntil -- for other cases we could come up with something more efficient which does not
//  require to materialise the progression, I guess something like random.intFromUntil + modulo would be
//  more efficient
/**
 * Returns an [ArbArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: IntProgression): ArbArgsGenerator<Int> =
	fromList(progression.toList())

/**
 * Returns an [ArbArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: LongProgression): ArbArgsGenerator<Long> =
	fromList(progression.toList())

