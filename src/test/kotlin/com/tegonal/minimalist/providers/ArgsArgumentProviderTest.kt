package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.component1
import com.tegonal.minimalist.component2
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
	@ArgsSource("args")
	fun args(index: Int, value: Long) {
		val (expectedIndex, expectValue) = args()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("pairs")
	fun pairIsSplit(index: Int, value: Long) {
		val (expectedIndex, expectValue) = pairs()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("tupleLike")
	fun tupleLikeIsNotSplit(tupleLike: Tuple4LikeStructure<Int, Long, Double, Float>) {
		// TODO would be nicer if we take the index from ParameterizedTest, could be possible with junit 5.4/6
		val expectedTupleLike = tupleLike()[tupleLike.a1]
		expect(tupleLike).toEqual(expectedTupleLike)
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
		fun args() = rawValuesInRange().mapIndexed { index, it -> Args.of(index, it) }

		@JvmStatic
		fun pairs() = rawValuesInRange().mapIndexed { index, it -> index to it }

		@JvmStatic
		fun tupleLike() = listOf(Tuple4LikeStructure(0, 2L, 3.0, 4.0f), Tuple4LikeStructure(1, 20L, 30.0, 40.0f))
	}

}
