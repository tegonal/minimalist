package com.tegonal.minimalist.generators

/**
 * Returns an [OrderedArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromProgression(progression: CharProgression): OrderedArgsGenerator<Char> =
	if (progression.step > 0)
		intFromTo(progression.first.code, progression.last.code, progression.step).map { it.toChar() }
	//TODO 2.1.0 no longer needed once we support minus steps in the iterator
	else fromList(progression.toList())

/**
 * Returns an [OrderedArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromProgression(progression: IntProgression): OrderedArgsGenerator<Int> =
	if (progression.step > 0) intFromTo(progression.first, progression.last, progression.step)
	//TODO 2.1.0 no longer needed once we support minus steps in the iterator
	else fromList(progression.toList())

/**
 * Returns an [OrderedArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [OrderedArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun OrderedExtensionPoint.fromProgression(progression: LongProgression): OrderedArgsGenerator<Long> =
	if (progression.step > 0) longFromTo(progression.first, progression.last, progression.step)
	//TODO 2.1.0 no longer needed once we support minus steps in the iterator
	else fromList(progression.toList())

