package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.utils.repeatForever
import org.junit.jupiter.params.ParameterizedTest

class OrderedFromProgressionTest : AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		Tuple("fromCharProgression", modifiedOrdered.fromProgression('a'..'d' step 2), listOf('a', 'c')),
		Tuple("fromIntProgression", modifiedOrdered.fromProgression(1..5 step 2), listOf(1, 3, 5)),
		Tuple("fromLongProgression", modifiedOrdered.fromProgression(1L..3L step 1), listOf(1L, 2L, 3L)),
	)

	@ParameterizedTest
	@ArgsSource("steps")
	fun charDifferentSteps(step: Int) {
		val argRange = ArgsRange(offset = 0, take = 4)
		val l1 = ordered.fromProgression('a'..'d' step step).generateAndTake(argRange).toList()
		expect(l1).toEqual(
			repeatForever().flatMap {
				listOf('a', 'b', 'c', 'd').withIndex().filter { (index, _) -> index % step == 0 }.map { (_, it) -> it }
			}.take(4).toList()
		)
		val l2 = ordered.fromProgression('d' downTo 'a' step step).generateAndTake(argRange).toList()
		expect(l2).toEqual(
			repeatForever().flatMap {
				listOf('d', 'c', 'b', 'a').withIndex().filter { (index, _) -> index % step == 0 }.map { (_, it) -> it }
			}.take(4).toList()
		)
	}

	//TODO 2.0.0 write test for intProgression and longProgression, especially for cases where the range size overflows Int/Long Domain

	companion object {
		@JvmStatic
		fun steps() = ordered.intFromTo(1, 5)
	}
}
