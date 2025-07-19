package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.Tuple4
import ch.tutteli.kbox.Tuple4Like
import ch.tutteli.kbox.toList
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest
import kotlin.collections.map
import kotlin.toList

class OrderedArgsGeneratorTupleLikeCombinerTest : AbstractOrderedArgsGeneratorCombinerTest() {

	@ParameterizedTest
	@ArgsSource("numOfIntAndChars")
	fun combineAll_Pair(numOfInts: Int, numOfChars: Int) {
		val a1s = (1..numOfInts).toList()
		val a2s = (1..numOfChars).map { 'A' + it }
		val a1Generator = ordered.fromList(a1s)
		val a2Generator = ordered.fromList(a2s)
		val generator: OrderedArgsGenerator<Pair<Int, Char>> = Tuple(a1Generator, a2Generator).combineAll()

		validateGeneration(generator.map { pair -> pair.toList() }, listOf(a1s, a2s))
	}

	@ParameterizedTest
	@ArgsSource("numOfIntCharsAndStrings")
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
	@ArgsSource("numOfIntCharsAndStrings")
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

	private data class Tuple4LikeStructure<A1, A2, A3, A4>(
		val a1: OrderedArgsGenerator<A1>,
		val a2: OrderedArgsGenerator<A2>,
		val a3: OrderedArgsGenerator<A3>,
		val a4: OrderedArgsGenerator<A4>
	) : Tuple4Like<OrderedArgsGenerator<A1>, OrderedArgsGenerator<A2>, OrderedArgsGenerator<A3>, OrderedArgsGenerator<A4>>
}
