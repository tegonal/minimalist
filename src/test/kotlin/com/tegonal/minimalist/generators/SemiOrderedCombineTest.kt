package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.PseudoArbArgsGenerator
import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.Test

class SemiOrderedCombineTest : AbstractOrderedCombinerTest() {

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combine2(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val a2Generator: SemiOrderedArgsGenerator<Char> = ordered.fromList(a2s)
		val generator: SemiOrderedArgsGenerator<Pair<Int, Char>> = a1Generator.cartesian(a2Generator)

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combineAll2(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val a2Generator: SemiOrderedArgsGenerator<Char> = ordered.fromList(a2s)
		val generator: SemiOrderedArgsGenerator<Pair<Int, Char>> = Tuple(a1Generator, a2Generator).combineAll()

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
			a1Generator.cartesian(a2Generator).cartesian(a3Generator)

		validateGeneration(generator.map { triple -> triple.toList() }, listOf(a1s, a2s, a3s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntCharsAndStrings")
	fun combineAll3(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a3s = (1..numOfStrings).map { ('a' + it).toString() }
		val a1Generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val a2Generator: SemiOrderedArgsGenerator<Char> = ordered.fromList(a2s)
		val a3Generator: SemiOrderedArgsGenerator<String> = ordered.fromList(a3s)
		val generator: SemiOrderedArgsGenerator<Triple<Int, Char, String>> =
			Tuple(a1Generator, a2Generator, a3Generator).combineAll()

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
				generator.cartesian(other) { l, a2 -> l.also { it.add(a2) } }
			}

		validateGeneration(generator, values)
	}

	@Test
	fun combineDependent() {
		val a1s = listOf(1, 2, 3, 4)
		val a2s = sequenceOf('a', 'b', 'c', 'd')
		val a1generator: SemiOrderedArgsGenerator<Int> = ordered.fromList(a1s)
		val generator = a1generator.combineDependent { int ->
			PseudoArbArgsGenerator(a2s.map { char -> char + int })
		}
		// note, our expectation is based on an implementation detail, we know that combineDependent just
		// picks the first generated value of the resulting RandomArgsGenerator and that PseudoArbArgsGenerator
		val a2sexpected = sequenceOf('b', 'd', 'f', 'h')
		val expected = a1s.asSequence().zip(a2sexpected)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	fun <T> SemiOrderedArgsGenerator<T>.generateToList(amount: Int): List<T> = generate().take(amount).toList()
}
