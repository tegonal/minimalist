package com.tegonal.minimalist.generators

import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest

class SemiOrderedArgsGeneratorCombinerTest : AbstractOrderedArgsGeneratorCombinerTest() {

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combine2(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val a2Generator: SemiOrderedArgsGenerator<Char> = ordered.fromList(a2s)
		val generator: SemiOrderedArgsGenerator<Pair<Int, Char>> = a1Generator.combine(a2Generator)

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntCharsAndStrings")
	fun combine3(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a3s = (1..numOfStrings).map { ('a' + it).toString() }
		val a1Generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val a2Generator: SemiOrderedArgsGenerator<Char> = ordered.fromList(a2s)
		val a3Generator: SemiOrderedArgsGenerator<String> = ordered.fromList(a3s)
		val generator: SemiOrderedArgsGenerator<Triple<Int, Char, String>> =
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
		val generators = values.map { val g: SemiOrderedArgsGenerator<Any> = ordered.fromList(it); g }
		val generator: SemiOrderedArgsGenerator<List<Any>> =
			generators.drop(1).fold(generators.first().map { mutableListOf(it) }) { generator, other ->
				generator.combine(other) { l, a2 -> l.also { it.add(a2) } }
			}

		validateGeneration(generator, values)
	}
}
