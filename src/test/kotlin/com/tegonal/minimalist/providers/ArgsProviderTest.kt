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

class ArgsProviderTest {

	@ParameterizedTest
	@ArgsSource("rawValuesInList")
	fun rawValues_inList(value: Int) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.start).toBeLessThanOrEqualTo(range.endInclusive)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInSet")
	fun rawValues_inSet(value: Int) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.start).toBeLessThanOrEqualTo(range.endInclusive)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInIterable")
	fun rawValues_inIterable(value: Int) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.start).toBeLessThanOrEqualTo(range.endInclusive)
	}

	@ParameterizedTest
	@ArgsSource("rawValuesInSequence")
	fun rawValues_inSequence(value: Int) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.start).toBeLessThanOrEqualTo(range.endInclusive)
	}


	@ParameterizedTest
	@ArgsSource("rawValuesInRange")
	fun rawValues_inRange(value: Int) {
		val range = rawValuesInRange()
		expect(value).toBeGreaterThanOrEqualTo(range.start).toBeLessThanOrEqualTo(range.endInclusive)
	}


	@ParameterizedTest
	@ArgsSource("args")
	fun args(value: Int, index: Int) {
		val (expectValue, expectedIndex) = args()[index]
		expect(value).toEqual(expectValue)
		expect(index).toEqual(expectedIndex)
	}

	@ParameterizedTest
	@ArgsSource("pairs")
	fun pairIsNotSplit(pair: Pair<Int, Int>) {
		val (expectValue, expectedIndex) = pairs()[pair.second]
		expect(pair.first).toEqual(expectValue)
		expect(pair.second).toEqual(expectedIndex)
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
		fun rawValuesInRange() = (20..22)

		@JvmStatic
		fun args() = rawValuesInRange().mapIndexed { index, it -> Args.of(it, index) }

		@JvmStatic
		fun pairs() = rawValuesInRange().mapIndexed { index, it -> it to index }

		@JvmStatic
		fun tupleLike() = listOf(Tuple4LikeStructure(0, 2L, 3.0, 4.0f), Tuple4LikeStructure(1, 20L, 30.0, 40.0f))
	}

}
