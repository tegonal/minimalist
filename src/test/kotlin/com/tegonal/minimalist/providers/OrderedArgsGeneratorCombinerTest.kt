package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import com.tegonal.minimalist.generators.*
import org.junit.jupiter.params.ParameterizedTest

class OrderedArgsGeneratorCombinerTest {

	@ParameterizedTest
	@ArgsSource("intAndChars")
	fun combine(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val numberOfCombinations = a1s.size * a2s.size
		val a1Generator = OrderedArgsGenerator.fromList(a1s)
		val a2Generator = OrderedArgsGenerator.fromList(a2s)
		val generator: OrderedArgsGenerator<Pair<Int, Char>> = a1Generator.append(a2Generator)

		expect(generator) {
			feature { f(it::size) }.toEqual(numberOfCombinations)
		}

		val pairsNotOrdered = a1s.flatMap { a1 -> a2s.map { a2 -> a1 to a2 } }
		val allCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generateOrdered(numberOfCombinations, offset).toList()
		}
		val offsetAllCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generateOrdered(numberOfCombinations, numberOfCombinations + offset).toList()
		}
		expect(allCombinations) {
			allCombinations.forEachIndexed { offset, combination ->
				feature("offset $offset", { combination }) {

					group("offset + 1 is the same just shifted by 1") {
						val combinationToCompare = (offset + 1) % numberOfCombinations
						// an offset should be the same as offset + 1 where the first element of offset comes last
						val comboToCompare = listOf(allCombinations[combinationToCompare].last()) +
							allCombinations[combinationToCompare].take(numberOfCombinations - 1)
						toContainExactlyElementsOf(comboToCompare)
					}

					group("offset + numberOfCombinations is the same") {
						toContainExactlyElementsOf(offsetAllCombinations[offset])
					}

					group("contains all combinations") {
						toContain.inAnyOrder.only.elementsOf(pairsNotOrdered)
					}
				}
			}
		}
	}
	companion object{
		@JvmStatic
//		fun intAndChars() = OrderedArgsGenerator.of(Args.of(7, 1))
				fun intAndChars() = RandomArgsGenerator.intFromUntil(1, 15).let { it to it }
	}
}

