package com.tegonal.minimalist.generators.impl

import ch.tutteli.kbox.takeIf
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.utils.repeatForever

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class OrderedArgsGeneratorCombiner<A1, A2, R>(
	private val a1Generator: OrderedArgsGenerator<A1>,
	private val a2Generator: OrderedArgsGenerator<A2>,
	private val transform: (A1, A2) -> R
) : OrderedArgsGenerator<R> {
	override val size: Int get() = a1Generator.size * a2Generator.size

	override fun generateOrdered(offset: Int): Sequence<R> {
		// Some notes about performance and why we use multiple if(a1IsSmaller)
		// - we think performance matters here, this approach is 2 - 4 times faster than a simple flatMap + drop
		//   (depends on how expensive the discarded values are to allocate/build)
		// - we don't use tuple + destructuring because we would allocate memory unnecessarily
		// - we don't use var and a single if because SSA (static single assignment) can usually better be
		//   optimised by a compiler/JIT
		// - a1IsSmaller is immutable, most likely JIT will inline all ifs anyway
		// - using an if outside repeatForever and use 2 repeatForever performed worse
		// - a minimalistic benchmark confirmed that this version is faster than the above-mentioned alternatives

		val a1Size = a1Generator.size
		val a2Size = a2Generator.size
		val a1IsSmaller = a1Size < a2Size
		val maxSize = if (a1IsSmaller) a2Size else a1Size

		// we generate in chunks of maxSize thus we can already fast-forward to the correct chunk
		val chunkOffset = offset / maxSize

		// within a chunk we might need to fast-forward elements to reach the desired offset
		val inChunkOffset = offset % maxSize

		val sizeOfSmaller = if (a1IsSmaller) a1Size else a2Size
		return repeatForever(chunkOffset, sizeOfSmaller).flatMapIndexed { index, offsetOfSmaller ->
			val a1Offset = if (a1IsSmaller) offsetOfSmaller else 0
			val a2Offset = if (a1IsSmaller) 0 else offsetOfSmaller

			// in case of the first generation, we need to take inChunkOffset into account, otherwise it doesn't matter
			val drop = if (index == 0) inChunkOffset else 0
			// we don't really drop (as this would generate values unnecessarily) instead we forward within the
			// chunk by the drop and create drop values less, resulting in the same as .drop(drop) but without
			// generating the values
			val numToGenerate = maxSize - drop

			// we use a specialised zip function which doesn't require range checks since we know (by contract)
			// that an OrderedArgsGenerator generates exactly the desired amount of elements

			zipDefinedSize(
				a1Generator.generateOrdered(numToGenerate, a1Offset + drop),
				a2Generator.generateOrdered(numToGenerate, a2Offset + drop),
				numToGenerate,
				transform
			)
		}
	}


	private inline fun <A, B, R> zipDefinedSize(
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
}
