package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.IntFromToArbArgsGenerator
import com.tegonal.minimalist.generators.impl.LongFromToArbArgsGenerator
import com.tegonal.minimalist.generators.impl.LongFromUntilArbArgsGenerator
import java.math.BigInteger
import kotlin.math.abs

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
				if (step > 0) Tuple(progression.first, progression.last)
				else Tuple(progression.last, progression.first)
			val stepAbs = abs(step)
			val numberOfSteps = (endInclusive.toLong() - start) / stepAbs + 1

			//TODO 2.1.0 bench at what point it makes sense to calculate it, I just guess that for small progressions
			// storing it in a list is more efficient and memory is neglectable. But maybe this can be increased to x
			// or should be decreased to y
			if (numberOfSteps < 50) fromList(progression.toList())
			else if (numberOfSteps >= Int.MAX_VALUE) {
				LongFromToArbArgsGenerator(_components, 0, numberOfSteps) { (start + it * stepAbs).toInt() }
			} else IntFromToArbArgsGenerator(_components, 0, numberOfSteps.toInt()) { start + it * stepAbs }
		}
	}

/**
 * Returns an [ArbArgsGenerator] generating [Long]s based on the given [progression].
 *
 * @return an [ArbArgsGenerator] based on the given [progression].
 * @since 2.0.0
 */
fun ArbExtensionPoint.fromProgression(progression: LongProgression): ArbArgsGenerator<Long> =
	when (val step = progression.step) {
		1L -> longFromTo(progression.first, progression.last)
		-1L -> longFromTo(progression.last, progression.first)
		else -> {
			val (start, endInclusive) =
				if (step > 0) Tuple(progression.first, progression.last)
				else Tuple(progression.last, progression.first)
			val stepAbs = abs(step)
			val diff = BigInteger.valueOf(endInclusive).subtract(BigInteger.valueOf(start))
			val numberOfSteps = diff.divide(BigInteger.valueOf(stepAbs)).add(BigInteger.ONE)

			//TODO 2.1.0 bench at what point it makes sense to calculate it, I just guess that for small progressions,
			// storing it in a list is more efficient and memory is neglectable. But maybe this can be increased to x
			// or should be decreased to y
			if (numberOfSteps < BigInteger.valueOf(50)) fromList(progression.toList())
			else {
				val longMaxAsBigInt = BigInteger.valueOf(Long.MAX_VALUE)
				if (numberOfSteps >= longMaxAsBigInt) {
					val minus = longMaxAsBigInt.subtract(numberOfSteps).add(BigInteger.ONE).toLong()
					LongFromUntilArbArgsGenerator(_components, minus, Long.MAX_VALUE) { start + (it - minus) * stepAbs }
				} else LongFromToArbArgsGenerator(_components, 0, numberOfSteps.toLong()) { start + it * stepAbs }
			}
		}
	}

