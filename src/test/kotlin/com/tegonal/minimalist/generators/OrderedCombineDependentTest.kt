package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.inAnyOrder
import ch.tutteli.atrium.api.fluent.en_GB.only
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.values
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import kotlin.test.Test

class OrderedCombineDependentTest : AbstractOrderedArgsGeneratorTest<Int>() {

	// see OrderedCombineTest for tests about combine

	override fun createGenerators() = listOf(1, 2, 3, 4).let { l ->
		val generator = modifiedOrdered.fromList(l)
		sequenceOf(
			Tuple(
				"combineDependentMaterialised",
				generator.combineDependentMaterialised({ a1 ->
					a1.toLong().let { ordered.intFromUntil(a1 - 1, a1) }
				}) { a1, a2 ->
					a1 + a2
				},
				//1+0, 2+1, 3+2, 4+3
				listOf(1, 3, 5, 7)
			),
		)
	}

	@Test
	fun combineDependentMaterialised_Tuple2() {
		val l = ordered.of(1, 2, 3).combineDependentMaterialised {
			when (it) {
				1 -> ordered.of('A', 'B', 'C')
				2 -> ordered.of('B')
				else -> ordered.of('B', 'C')
			}
		}.toList()

		expect(l).toContain.inAnyOrder.only.values(
			1 to 'A', 1 to 'B', 1 to 'C',
			2 to 'B',
			3 to 'B', 3 to 'C'
		)
	}
}
