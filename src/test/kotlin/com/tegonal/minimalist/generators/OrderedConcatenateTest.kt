package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.anyToList
import com.tegonal.minimalist.testutils.getTestValue

@Suppress("UNCHECKED_CAST")
class OrderedConcatenateTest : AbstractOrderedConcatenateTest() {

	override fun createGenerators(): OrderedArgsTestFactoryResult<Any> {
		val g1Variants = variants(0)
		val g2Variants = variants(1)

		val concatenated = g1Variants.combine(g2Variants) { (name1, g1), (name2, g2) ->
			Tuple("$name1 + $name2", g1 + g2, anyToList(getTestValue(name1, 0)) + anyToList(getTestValue(name2, 1)))
		}
        return concatenated.generateAndTakeBasedOnDecider()
	}
}
