package com.tegonal.minimalist.generators

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ordered.fromProgression(progression: CharProgression): OrderedArgsGenerator<Char> =
	ordered.fromList(progression.toList())

// TODO 2.1.0 we could probably come up with a more efficient version which does not require to materialise the
//  progression (I guess will make a difference for big progressions)
/**
 * Returns an [OrderedArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ordered.fromProgression(progression: IntProgression): OrderedArgsGenerator<Int> =
	ordered.fromList(progression.toList())

/**
 * Returns an [OrderedArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ordered.fromProgression(progression: LongProgression): OrderedArgsGenerator<Long> =
	ordered.fromList(progression.toList())

