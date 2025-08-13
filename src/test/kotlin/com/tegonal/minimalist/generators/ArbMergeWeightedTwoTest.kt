package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.testutils.anyToList
import com.tegonal.minimalist.testutils.getTestValue

class ArbMergeWeightedTwoTest : AbstractArbMergeTwoTest() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<Any> {
		val g1Variants = variants(modifiedArb, 0)
		val g2Variants = variants(modifiedArb, 1)

		val combined = g1Variants.combine(g2Variants) { (name1, g1), (name2, g2) ->
			Tuple(
				"$name1, $name2",
				mergeWeighted(40 to g1, 60 to g2),
				anyToList(getTestValue(name1, 0)) + anyToList(getTestValue(name2, 1))
			)
		}

		return combined.generateAndTakeBasedOnDecider()
	}
}

