package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.MinimalistConfigLoader
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.providers.ArgsArgumentProvider
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.providers.GenericToArgumentsTransformer
import com.tegonal.minimalist.providers.MaxArgsAndOffsetDeterminer
import com.tegonal.minimalist.utils.loadService
import org.junit.jupiter.params.provider.Arguments
import java.lang.reflect.Method

class DefaultGenericToArgumentsTransformer : GenericToArgumentsTransformer {

	override fun toArguments(
		testMethod: Method,
		annotation: ArgsSource,
		maybeGenerators: List<Any?>
	): List<Arguments> {
		check(maybeGenerators.isNotEmpty()) {
			"no generators defined"
		}

		val orderedArgsGenerators = mutableListOf<Pair<Int, OrderedArgsGenerator<*>>>()
		val randomArgsGenerators = mutableListOf<Pair<Int, RandomArgsGenerator<*>>>()
		maybeGenerators.forEachIndexed { index, maybeGenerator ->
			when (maybeGenerator) {
				is OrderedArgsGenerator<*> -> {
					check(maybeGenerator.size > 0) {
						"The OrderedArgsGenerator $maybeGenerator would yield 0 values, check your filters"
					}
					orderedArgsGenerators.add(index to maybeGenerator)

				}

				is RandomArgsGenerator<*> -> randomArgsGenerators.add(index to maybeGenerator)
				is ArgsGenerator<*> -> throw UnsupportedOperationException("found an ArgsGenerator ${maybeGenerator::class.qualifiedName} but we don't know how to treat it, please open a feature request: ${ArgsArgumentProvider.Companion.FEATURE_REQUEST_URL}&title=Allow%20custom%20ArgsGenerator")
				//TODO 2.1.0 support Arguments?
				else -> throw IllegalStateException("Found ${maybeGenerator?.let { it::class.qualifiedName } ?: "null"} which is not an ArgsGenerator")
			}
		}

		val (maxArgs, offset) = determineMaxArgsAndOffset(
			testMethod,
			annotation,
			orderedArgsGenerators,
			randomArgsGenerators
		)


		// in case we only have randomArgsGenerators, then we can just zip them
		return if (orderedArgsGenerators.isEmpty()) {
			randomArgsGenerators
				.asSequence()
				.map { (_, generator) -> generator }
				.combine()
				// no need to take the offset into account as they are random values anyway
				.take(maxArgs)
				.toList()
		} else if (randomArgsGenerators.isEmpty()) {

			// could be there are fewer combinations than maxArgs in which case we only want maxCombinations
			val numberOfArgs = minOf(maxArgs, orderedArgsGenerators.fold(1) { maxCombinations, (_, generator) ->
				maxCombinations * generator.size
			})
			orderedArgsGenerators
				.map { (_, generator) -> generator }
				.combine(offset)
				.take(numberOfArgs)
				.toList()
		} else {
			TODO()
		}
	}

	private fun determineMaxArgsAndOffset(
		testMethod: Method,
		annotation: ArgsSource,
		orderedArgsGenerators: List<Pair<Int, OrderedArgsGenerator<*>>>,
		randomArgsGenerators: List<Pair<Int, RandomArgsGenerator<*>>>
	): Pair<Int, Int> =
		if (annotation.fixedMaxNumberOfArgs > 0) {
			// value in annotation takes precedence over MaxArgProvider
			annotation.fixedMaxNumberOfArgs to annotation.fixedOffset
		} else {
			val config = MinimalistConfigLoader.config
			val determiner = loadService<MaxArgsAndOffsetDeterminer>(config.activeMaxArgsAndOffsetDeterminer)

			determiner.determineMaxArgsAndOffset(testMethod, orderedArgsGenerators, randomArgsGenerators)
		}


	//TODO 2.1.0 couldn't this also be implemented with generateSequence (would be faster I guess)?
	private fun Sequence<RandomArgsGenerator<*>>.combine(): Sequence<Arguments> = sequence {
		val iterators = this@combine.map { it.generate().iterator() }.toList()

		// random generators should always generate a next value, hence no need to check if the iterator has next
		// (and if not, then the call to next will fail)
		while (true) {
			yield(iterators.map { generator ->
				generator.next().let { it as? Args ?: Args.of(it) }
			})
		}
	}.toArguments()

	private fun List<OrderedArgsGenerator<*>>.combine(offset: Int): Sequence<Arguments> {
		val generators = this
		val numOfGenerators = generators.size
		var maxSize = 0
		val generatorSizes = generators.map { generator ->
			generator.size.also {
				if (maxSize < it) {
					maxSize = it
				}
			}
		}
		val chunkOffset = offset / maxSize

		val generatorsAsSequence = generators.asSequence()
		var carry = offset
		val initialArray = IntArray(numOfGenerators) { index ->
			val size = generatorSizes[index]
			if (carry < size) {
				carry.also {
					carry = 0
				}
			} else {
				carry % size.also {
					carry -= size - 1
				}
			}
		}
		val initialPair = initialArray to 0
		return generateSequence(initialPair) { (offsets, counterIndex) ->
			if (offsets[counterIndex] + 1 < generatorSizes[counterIndex]) {
				offsets[counterIndex] += 1
				offsets to counterIndex
			} else {
				val newIndex = counterIndex + 1
				if (newIndex < numOfGenerators - 1) {
					offsets[counterIndex] = 0
					offsets[newIndex] += 1
					offsets to newIndex
				} else {
					// traversed all combinations, start over again
					initialPair
				}
			}
		}.flatMap { (offsets, _) ->
			val chunkOfSequencesOfArgs = generatorsAsSequence.mapIndexed { index, generator ->
				val individualOffset = offsets[index]
				generator.generate(individualOffset).take(maxSize).map {
					it as? Args ?: Args.of(it)
				}
			}
			// every sequence in chunkOfSequencesOfArgs yields exactly maxSize Args, we want to zip each of them (i.e.
			// create a new sequence out of element 0 of Sequence A, B and C, a new sequence out of element 1 of
			// Sequence A, B, C etc. until element maxSize -1
			chunkOfSequencesOfArgs.zipAll(maxSize).toArguments()
		}
	}

	private fun Sequence<Sequence<Args>>.zipAll(uniformSize: Int): Sequence<List<Args>> = sequence {
		val iterators = this@zipAll.map { it.iterator() }.toList()
		// we know that our sequences are generating indefinitely, so we can just use while true
		repeat(uniformSize) {
			yield(iterators.map { it.next() })
		}
	}


	private fun Sequence<List<Args>>.toArguments(): Sequence<Arguments> = map { sequence ->
		// flattening the different args into one Arguments
		// TODO 2.1.0 construct an Array directly instead of going though a list?
		Arguments.of(*sequence.flatMap { it.get().asSequence() }.toList().toTypedArray())
	}

}
