package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.testutils.anyToList
import com.tegonal.minimalist.testutils.getTestValue

class RandomArgsGeneratorMergeWeightedThreeTest : AbstractRandomArgsGeneratorMergeTwoTest() {

	override fun createGenerators(): RandomArgsTestFactoryResult<Any> {
		val g1Variants = variants(0)
		val g2Variants = variants(1)

		val combined = g1Variants.combine(g2Variants) { (name1, g1), (name2, g2) ->
			val l = listOf(888_888, 999_999)
			Tuple(
				"$name1, $name2, fromList",
				mergeWeighted(40 to g1, 50 to g2, 10 to random.fromList(l)),
				anyToList(getTestValue(name1, 0)) + anyToList(getTestValue(name2, 1)) + l
			)
		}

		// we use a modifiedOrdered in AbstractOrderedArgsGeneratorWithoutAnnotationsTest because we want to test that
		// the ComponentContainer is passed correctly from generator to generator. But that also means that if we would
		// use the components from it, that seed is always 1. That's not what we want, hence we use the "normal"
		// MinimalistConfig and its defined seed and components
		val argsRange = ordered._components.build<ArgsRangeDecider>().decide(combined)
		return combined.generateAndTake(argsRange)
	}
}
