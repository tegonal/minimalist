package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorCombiner
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest

class OrderedCombinerTest : AbstractOrderedCombinerTest() {

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combine2(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' - 1 + it }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val generator: OrderedArgsGenerator<Pair<Int, Char>> = a1Generator.combine(a2Generator)

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntCharsAndStrings")
	fun combine3(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' - 1 + it }
		val a3s = (1..numOfStrings).map { ('a' - 1 + it).toString() }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val a3Generator = ordered.fromList(a3s)
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
				1 -> (0 until numOfValues).map { 'A' - 1 + it }
				2 -> (0 until numOfValues).map { ('a' - 1 + it).toString() }
				else -> error("modulo 3 does not work?")
			}
		}
		val generators = values.map { ordered.fromList(it) }
		val generator: OrderedArgsGenerator<List<Any>> =
			generators.drop(1).fold(generators.first().map { mutableListOf(it) }) { generator, other ->
				OrderedArgsGeneratorCombiner(generator, other) { l, a2 ->
					l.also { it.add(a2) }
				}
			}

		validateGeneration(generator, values)
	}
}
