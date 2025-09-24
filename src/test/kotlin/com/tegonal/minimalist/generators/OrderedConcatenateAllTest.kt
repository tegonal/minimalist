package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.testutils.anyToList
import com.tegonal.minimalist.testutils.getTestValue
import kotlin.test.Test

@Suppress("UNCHECKED_CAST")
class OrderedConcatenateAllTest : AbstractOrderedConcatenateTest() {

	override fun createGenerators(): OrderedArgsTestFactoryResult<Any> {
		val g1Variants = variants(0)
		val g2Variants = variants(1)

		val concatenated = g1Variants.cartesian(g2Variants) { (name1, g1), (name2, g2) ->
			val l = listOf(888_888, 999_999)
			Tuple(
				"$name1, $name2",
				listOf(g1, ordered.fromList(l), g2).concatAll(),
				anyToList(getTestValue(name1, 0)) + l + anyToList(getTestValue(name2, 1))
			)
		}
		return concatenated.generateAndTakeBasedOnDecider()
	}

	@Test
	fun worksWithOneTimeConsumableSequence() {
		val seq = sequenceOf(ordered.intFromUntil(1, 5), ordered.intFromUntil(10, 12)).constrainOnce()
		val concatenated = seq.concatAll()
		expect {
			seq.iterator()
		}.toThrow<IllegalStateException> { messageToContain("can be consumed only once") }
		expect(concatenated.generate().take(11).toList()).toContainExactly(1, 2, 3, 4, 10, 11, 1, 2, 3, 4, 10)
	}
}
