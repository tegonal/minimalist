package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.atrium.size

abstract class AbstractOrderedCombinerTest {

	@OptIn(ExperimentalWithOptions::class)
	protected fun validateGeneration(
		generator: SemiOrderedArgsGenerator<List<Any>>,
		values: List<List<Any>>
	) {
		val numberOfCombinations = values.fold(1) { acc, l -> acc * l.size }
		expect(generator) {
			size.toEqual(numberOfCombinations)
		}

		val valuesCombinedNotOrdered =
			values.drop(1).fold(values.first().map { listOf(it) }) { acc, other ->
				acc.flatMap { args ->
					other.map { aX -> args + listOf(aX) }
				}
			}
		val allCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generate(offset).take(numberOfCombinations).toList()
		}
		val offsetAllCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generate(numberOfCombinations + offset).take(numberOfCombinations).toList()
		}
		allCombinations.forEachIndexed { offset, combination ->
			expect(combination).withOptions { withVerb("I expected offset $offset") }
				.group("to contain all combinations") {
					toContain.inAnyOrder.only.elementsOf(valuesCombinedNotOrdered)
				}
				.group("to contain exactly the same as offset + 1 just shifted by 1") {
					val combinationToCompare = (offset + 1) % numberOfCombinations
					// an offset should be the same as offset + 1 where the first element of offset comes last
					val comboToCompare = listOf(allCombinations[combinationToCompare].last()) +
						allCombinations[combinationToCompare].take(numberOfCombinations - 1)
					toContainExactlyElementsOf(comboToCompare)
				}
				.group("to contain exactly the same as offset + numberOfCombinations") {
					toContainExactlyElementsOf(offsetAllCombinations[offset])
				}
		}
	}

	companion object {

		@JvmStatic
		fun numOfIntAndChars() = Tuple(
			arb.intFromUntil(1, 15),
			arb.intFromUntil(1, 7)
		)

		@JvmStatic
		fun numOfIntCharsAndStrings() = Tuple(
			arb.intFromUntil(1, 5),
			arb.intFromUntil(1, 7),
			arb.intFromUntil(1, 6)
		)

		@JvmStatic
		fun dynamicNumOfGeneratorsAndValues(): ArbArgsGenerator<List<Int>> =
			arb.intFromUntil(3, 7).combineDependent({ numOfGenerators ->
				arb.intFromUntil(1, 7 - (numOfGenerators / 2)).chunked(numOfGenerators)
			}) { _, list -> list }
	}
}
