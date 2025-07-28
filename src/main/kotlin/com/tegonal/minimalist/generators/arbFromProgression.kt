package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.IntFromToArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] generating [Char]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: CharProgression): ArbArgsGenerator<Char> =
	fromList(progression.toList())

/**
 * Returns an [ArbArgsGenerator] generating [Int]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: IntProgression): ArbArgsGenerator<Int> =
	when (val step = progression.step) {
		1 -> intFromTo(progression.first, progression.last)
		-1 -> intFromTo(progression.last, progression.first)
		else -> {
			val (start, endInclusive) =
				if (step < 0) Tuple(progression.first, progression.last)
				else Tuple(progression.last, progression.first)
			val size = (endInclusive - start) / step + 1

			//TODO 2.1.0 bench at what point it makes sense to calculate it, I just guess that for small progressions
			// storing it in a list is more efficient and memory is neglectable. But maybe this can be increased to x
			// or should be decreased to y
			if (size < 50) fromList(progression.toList())
			else IntFromToArbArgsGenerator(_components, 0, size) { start + it * step }
		}
	}

/**
 * Returns an [ArbArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: LongProgression): ArbArgsGenerator<Long> =
	fromList(progression.toList())

