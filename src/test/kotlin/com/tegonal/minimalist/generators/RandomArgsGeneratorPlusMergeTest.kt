package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.testutils.PseudoRandomArgsGenerator
import com.tegonal.minimalist.testutils.anyToList
import com.tegonal.minimalist.testutils.getTestValue
import com.tegonal.minimalist.testutils.withMockedRandom
import com.tegonal.minimalist.utils.repeatForever
import kotlin.test.Test

class RandomArgsGeneratorPlusMergeTest : AbstractRandomArgsGeneratorMergeTwoTest() {

	override fun createGenerators(): RandomArgsTestFactoryResult<Any> {
		val g1Variants = variants(0)
		val g2Variants = variants(1)

		val combined = g1Variants.combine(g2Variants) { (name1, g1), (name2, g2) ->
			Tuple(
				"$name1 + $name2",
				g1 + g2,
				anyToList(getTestValue(name1, 0)) + anyToList(getTestValue(name2, 1))
			)
		}

		// we use a modifiedOrdered in AbstractOrderedArgsGeneratorWithoutAnnotationsTest because we want to test that
		// the ComponentContainer is passed correctly from generator to generator. But that also means that if we would
		// use the components from it, that seed is always 1. That's not what we want, hence we use the "normal"
		// MinimalistConfig and its defined seed and components
		val argsRange = ordered._components.build<ArgsRangeDecider>().decide(combined)
		return combined.generateAndTake(argsRange)
	}

	@Test
	fun `check 50 50 chance is correct`() {
		val s1 = sequenceOf(10, 11, 12, 13)
		val s2 = sequenceOf(20, 21, 22)

		val g1 = PseudoRandomArgsGenerator(s1, random._components.withMockedRandom(ints = (1..100).toList()))
		val g2 = PseudoRandomArgsGenerator(s2)

		val merged = g1 + g2

		// The following depends heavily on implementation details: we know that we use Random inside for a
		// uniform distribution and we know that weights keep the given order, i.e. if g1 has weight 10 then if
		// Random.next(1,100) yields a value between 1 and 10 g1 is picked to contribute a value
		val expected = run {
			repeatForever().flatMap { s1 }.take(50) + repeatForever().flatMap { s2 }.take(50)
		}.toList()
		val l = merged.generate().take(100).toList()
		expect(l).toContainExactlyElementsOf(expected)
	}
}
