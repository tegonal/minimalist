package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.Tuple4
import ch.tutteli.kbox.toList
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.Tuple4LikeStructure
import org.junit.jupiter.params.ParameterizedTest

class OrderedTupleLikeCombineTest : AbstractOrderedCombinerTest() {

	@ParameterizedTest
	@ArgsSource("arbNumOfIntAndChars")
	fun combineAll_Pair(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val generator: OrderedArgsGenerator<Pair<Int, Char>> = Tuple(a1Generator, a2Generator).combineAll()

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("arbNumOfIntCharsAndStrings")
	fun combineAll_Triple(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a3s = (1..numOfStrings).map { ('a' + it).toString() }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val a3Generator = ordered.fromList(a3s)
		val generator: OrderedArgsGenerator<Triple<Int, Char, String>> =
			Tuple(a1Generator, a2Generator, a3Generator).combineAll()

		validateGeneration(generator.map { triple -> triple.toList() }, listOf(a1s, a2s, a3s))
	}

	@ParameterizedTest
	@ArgsSource("arbNumOfIntCharsAndStrings")
	fun combineAll_Tuple4Like(numOfInts: Int, numOfChars: Int, numOfStrings: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a3s = (1..numOfStrings).map { ('a' + it).toString() }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val a3Generator = ordered.fromList(a3s)
		val generator: OrderedArgsGenerator<Tuple4<Int, Char, String, Int>> =
			Tuple4LikeStructure(a1Generator, a2Generator, a3Generator, a1Generator).toTuple().combineAll()

		validateGeneration(generator.map { tuple4 -> tuple4.toList() }, listOf(a1s, a2s, a3s, a1s))
	}

	// since combineAll is a composition of combine calls (currently, that could change but for now) we don't have to
	// test Tuple5 - Tuple9 (which could cause an OutOfMemoryException due to way we cache combinations in
	// validateGeneration)

}
