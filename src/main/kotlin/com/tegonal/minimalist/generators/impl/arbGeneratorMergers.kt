package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.ArbArgsGenerator

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArbArgsGeneratorMerger<T>(
	a1GeneratorWithWeight: Pair<Int, ArbArgsGenerator<T>>,
	a2GeneratorWithWeight: Pair<Int, ArbArgsGenerator<T>>,
) : BaseArbArgsGenerator<T>(
	// note, we don't (and cannot) check that a1Generator and a2Generator use the same ComponentContainer,
	// should you run into weird behaviour (such as one generator uses seed X and the other seed Y) then most likely
	// someone used to different initial factories
	a1GeneratorWithWeight.second._components,
), ArbArgsGenerator<T> {

	private val a1Generator = a1GeneratorWithWeight.second
	private val a2Generator = a2GeneratorWithWeight.second
	private val a1Weight = a1GeneratorWithWeight.first

	init {
		val a2Weight = a2GeneratorWithWeight.first
		validateWeight(a1Weight)
		validateWeight(a2Weight)
		validateTotalWeights(a1Weight, a2Weight)
	}

	override fun generate(): Sequence<T> = createMinimalistRandom().let { minimalistRandom ->
		object : Sequence<T> {
			override fun iterator(): Iterator<T> = object : Iterator<T> {
				private val a1Iterator = a1Generator.generate().iterator()
				private val a2Iterator = a2Generator.generate().iterator()

				override fun hasNext(): Boolean = true
				override fun next(): T {
					val r = minimalistRandom.nextInt(1, 101)
					return if (r <= a1Weight) a1Iterator.next()
					else a2Iterator.next()
				}
			}
		}
	}
}

private fun validateWeight(weight: Int) = require(weight >= 1 && weight <= 99) {
	"weight is from 1 to 99 (percentage), given $weight"
}

private fun validateTotalWeights(vararg weights: Int) =
	weights.sum().let { totalWeight ->
		require(totalWeight == 100) {
			"weights must add up to 100, given $totalWeight (${weights.joinToString(", ")})"
		}
	}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MultiArbArgsGeneratorIndexOfMerger<T>(
	firstGeneratorWithWeight: Pair<Int, ArbArgsGenerator<T>>,
	secondGeneratorWithWeight: Pair<Int, ArbArgsGenerator<T>>,
	otherGeneratorsWithWeight: Array<out Pair<Int, ArbArgsGenerator<T>>>,
) : BaseArbArgsGenerator<T>(
	// note, we don't (and cannot) check that a1Generator and a2Generator use the same ComponentContainer,
	// should you run into weird behaviour (such as one generator uses seed X and the other seed Y) then most likely
	// someone used to different initial factories
	firstGeneratorWithWeight.second._components,
), ArbArgsGenerator<T> {
	private val generators: Array<ArbArgsGenerator<T>>
	private val cumulativeWeights: Array<Int>

	init {
		val firstWeight = firstGeneratorWithWeight.first
		val secondWeight = secondGeneratorWithWeight.first
		validateWeight(firstWeight)
		validateWeight(secondWeight)
		otherGeneratorsWithWeight.forEach { validateWeight(it.first) }
		validateTotalWeights(firstWeight, secondWeight, *otherGeneratorsWithWeight.map { it.first }.toIntArray())

		val totalGenerators = otherGeneratorsWithWeight.size + 2
		generators = Array(totalGenerators) { index ->
			when (index) {
				0 -> firstGeneratorWithWeight.second
				1 -> secondGeneratorWithWeight.second
				else -> otherGeneratorsWithWeight[index - 2].second
			}
		}

		var acc = 0
		cumulativeWeights = Array(totalGenerators) { index ->
			run {
				acc + when (index) {
					0 -> firstWeight
					1 -> secondWeight
					else -> otherGeneratorsWithWeight[index - 2].first
				}
			}.also { acc = it }
		}

	}

	override fun generate(): Sequence<T> = createMinimalistRandom().let { minimalistRandom ->
		object : Sequence<T> {
			override fun iterator(): Iterator<T> = object : Iterator<T> {
				private val iterators = Array(generators.size) { generators[it].generate().iterator() }

				override fun hasNext(): Boolean = true
				override fun next(): T {
					val r = minimalistRandom.nextInt(1, 101)
					val index = cumulativeWeights.indexOfFirst { it >= r }
					return iterators[index].next()
				}
			}
		}
	}
}
