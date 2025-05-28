package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.Tuple3Like
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorCombiner
import org.junit.jupiter.params.ParameterizedTest

class OrderedArgsGeneratorCombinerTest {

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combine2(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator = OrderedArgsGenerator.fromList(a1s)
		val a2Generator = OrderedArgsGenerator.fromList(a2s)
		val generator: OrderedArgsGenerator<Pair<Int, Char>> = a1Generator.combine(a2Generator)

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntCharsAndStrings")
	fun combine3(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a3s = (1..numOfStrings).map { ('a' + it).toString() }
		val a1Generator = OrderedArgsGenerator.fromList(a1s)
		val a2Generator = OrderedArgsGenerator.fromList(a2s)
		val a3Generator = OrderedArgsGenerator.fromList(a3s)
		val generator: OrderedArgsGenerator<Triple<Int, Char, String>> =
			a1Generator.combine(a2Generator).combine(a3Generator)

		validateGeneration(generator.map { triple -> triple.toList() }, listOf(a1s, a2s, a3s))
	}


	@ParameterizedTest
	@ArgsSource("dynamicNumOfGeneratorsAndValues")
	fun dynamic(numOfValuesPerGenerator: List<Int>) {
		val values: List<List<Any>> = numOfValuesPerGenerator.mapIndexed { index, numOfValues ->
			when (index % 3) {
				0 -> (0 until numOfValues).toList()
				1 -> (0 until numOfValues).map { 'A' + it }
				2 -> (0 until numOfValues).map { ('a' + it).toString() }
				else -> error("modulo 3 does not work?")
			}
		}
		val generators = values.map { OrderedArgsGenerator.fromList(it) }
		val generator = generators.drop(1).fold(generators.first().map { mutableListOf(it) }) { generator, other ->
			OrderedArgsGeneratorCombiner(generator, other) { l, a2 ->
				l.also { it.add(a2) }
			}
		}

		validateGeneration(generator, values)
	}

	@OptIn(ExperimentalWithOptions::class)
	private fun validateGeneration(
		generator: OrderedArgsGenerator<List<Any>>,
		values: List<List<Any>>
	) {
		val numberOfCombinations = values.fold(1) { acc, l -> acc * l.size }
		expect(generator) {
			feature { f(it::size) }.toEqual(numberOfCombinations)
		}

		val valuesCombinedNotOrdered =
			values.drop(1).fold(values.first().map { listOf(it) }) { acc, other ->
				acc.flatMap { args ->
					other.map { aX -> args + listOf(aX) }
				}
			}
		val allCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generateOrdered(numberOfCombinations, offset).toList()
		}
		val offsetAllCombinations = (0 until numberOfCombinations).map { offset ->
			generator.generateOrdered(numberOfCombinations, numberOfCombinations + offset).toList()
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
			RandomArgsGenerator.intFromUntil(1, 15),
			RandomArgsGenerator.intFromUntil(1, 7)
		)

		@JvmStatic
		fun numOfIntCharsAndStrings() = Tuple(
			RandomArgsGenerator.intFromUntil(1, 5),
			RandomArgsGenerator.intFromUntil(1, 7),
			RandomArgsGenerator.intFromUntil(1, 6)
		)

		@JvmStatic
		fun dynamicNumOfGeneratorsAndValues(): RandomArgsGenerator<List<Int>> =
			RandomArgsGenerator.intFromUntil(3, 7).map { numOfGenerators ->
				RandomArgsGenerator.intFromUntil(1, 7 - (numOfGenerators / 2)).generate().take(numOfGenerators).toList()
			}
	}
}
