package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.component1
import com.tegonal.minimalist.component2
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.fromList
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.testutils.Tuple4LikeStructure
import org.junit.jupiter.params.ParameterizedTest

class ArgsArgumentProviderTest {

	@ParameterizedTest
	@ArgsSource("rawValuesInList")
	fun rawValues_inList(value: Long) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.first).toBeLessThanOrEqualTo(range.last)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInSet")
	fun rawValues_inSet(value: Long) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.first).toBeLessThanOrEqualTo(range.last)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInIterable")
	fun rawValues_inIterable(value: Long) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.first).toBeLessThanOrEqualTo(range.last)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInSequence")
	fun rawValues_inSequence(value: Long) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.first).toBeLessThanOrEqualTo(range.last)
	}


	@ParameterizedTest
	@ArgsSource("rawValuesInRange")
	fun rawValues_inRange(value: Long) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.first).toBeLessThanOrEqualTo(range.last)
	}

	@ParameterizedTest
	@ArgsSource("rawArgs")
	fun rawArgsIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawArgs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("orderedArgs")
	fun orderedArgsIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawArgs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("arbArgs")
	fun arbArgsIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawArgs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}


	@ParameterizedTest
	@ArgsSource("rawPairs")
	fun rawPairIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawPairs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("orderedPairs")
	fun orderedPairIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawPairs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("arbPairs")
	fun arbPairIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = rawPairs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}


	@ParameterizedTest
	@ArgsSource("rawTupleLike")
	fun rawTupleLikeIsNotSplit(tupleLike: Tuple4LikeStructure<Int, Long, Double, Float>) {
		// TODO would be nicer if we take the index from ParameterizedTest, could be possible with junit 5.4/6
		val expectedTupleLike = rawTupleLike()[tupleLike.a1]
		expect(tupleLike).toEqual(expectedTupleLike)
	}

	@ParameterizedTest
	@ArgsSource("orderedTupleLike")
	fun orderedTupleLikeIsNotSplit(tupleLike: Tuple4LikeStructure<Int, Long, Double, Float>) {
		// TODO would be nicer if we take the index from ParameterizedTest, could be possible with junit 5.4/6
		val expectedTupleLike = rawTupleLike()[tupleLike.a1]
		expect(tupleLike).toEqual(expectedTupleLike)
	}

	@ParameterizedTest
	@ArgsSource("arbTupleLike")
	fun arbTupleLikeIsNotSplit(tupleLike: Tuple4LikeStructure<Int, Long, Double, Float>) {
		// TODO would be nicer if we take the index from ParameterizedTest, could be possible with junit 5.4/6
		val expectedTupleLike = rawTupleLike()[tupleLike.a1]
		expect(tupleLike).toEqual(expectedTupleLike)
	}


	@ParameterizedTest
	@ArgsSource("rawNestedTuples")
	fun rawNestedTuplesAreFlattenedAndSplit(i: Int, l: Long, d: Double) {
		expect(i.toDouble() + l.toDouble()).toEqual(d)
	}

	@ParameterizedTest
	@ArgsSource("orderedNestedTuples")
	fun orderedNestedTuplesAreFlattenedAndSplit(i: Int, l: Long, d: Double) {
		expect(i.toDouble() + l.toDouble()).toEqual(d)
	}

	@ParameterizedTest
	@ArgsSource("arbNestedTuples")
	fun arbNestedTuplesAreFlattenedAndSplit(i: Int, l: Long, d: Double) {
		expect(i.toDouble() + l.toDouble()).toEqual(d)
	}


	companion object {
		@JvmStatic
		fun rawValuesInList() = rawValuesInRange().toList()

		@JvmStatic
		fun rawValuesInSet() = rawValuesInRange().toSet()

		@JvmStatic
		fun rawValuesInIterable() = rawValuesInSequence().asIterable()

		@JvmStatic
		fun rawValuesInSequence() = rawValuesInRange().asSequence()

		@JvmStatic
		fun rawValuesInRange() = (20L..22L)

		@JvmStatic
		fun rawArgs() = rawValuesInRange().mapIndexed { index, it -> Args.of(index, it) }

		@JvmStatic
		fun orderedArgs() = ordered.fromList(rawPairs())

		@JvmStatic
		fun arbArgs() = arb.fromList(rawPairs())


		@JvmStatic
		fun rawPairs() = rawValuesInRange().mapIndexed { index, it -> index to it }

		@JvmStatic
		fun orderedPairs() = ordered.fromList(rawArgs())

		@JvmStatic
		fun arbPairs() = arb.fromList(rawArgs())


		@JvmStatic
		fun rawTupleLike() = listOf(Tuple4LikeStructure(0, 2L, 3.0, 4.0f), Tuple4LikeStructure(1, 20L, 30.0, 40.0f))

		@JvmStatic
		fun orderedTupleLike() = ordered.fromList(rawTupleLike())

		@JvmStatic
		fun arbTupleLike() = arb.fromList(rawTupleLike())

		@JvmStatic
		fun rawNestedTuples() = listOf(Tuple(Tuple(1, 2L), 3.0), Tuple(Tuple(2, 1L), 3.0))

		@JvmStatic
		fun orderedNestedTuples() = ordered.fromList(rawNestedTuples())

		@JvmStatic
		fun arbNestedTuples() = arb.fromList(rawNestedTuples())
	}
}
