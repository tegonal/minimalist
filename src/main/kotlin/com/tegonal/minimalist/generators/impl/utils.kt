package com.tegonal.minimalist.generators.impl

import ch.tutteli.kbox.takeIf

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <A, B, R> zipDefinedSize(
	seqA: Sequence<A>,
	seqB: Sequence<B>,
	size: Int,
	crossinline transform: (A, B) -> R
): Sequence<R> {
	val iterA = seqA.iterator()
	val iterB = seqB.iterator()
	var index = 0

	return generateSequence {
		takeIf(index < size) {
			transform(iterA.next(), iterB.next()).also {
				++index
			}
		}
	}
}
