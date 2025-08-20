package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbStringTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		// we skip the other predefined UnicodeRanges because they are quite big and we don't prove more
		// ASCII has one UnicodeRange, ISO_8859_1_PRINTABLE has two
		UnicodeRanges.ASCII.let {
			Tuple(
				"string ASCII",
				modifiedArb.string(maxLength = 1, allowedRanges = it.ranges),
				listOf("") + it.toStrings()
			)
		},
		UnicodeRanges.ISO_8859_1_PRINTABLE.let {
			Tuple(
				"string ISO_8859_1_PRINTABLE",
				modifiedArb.string(maxLength = 1, allowedRanges = it.ranges),
				listOf("") + it.toStrings()
			)
		}
	)

	private fun UnicodeRanges.toStrings() = ranges.flatMap { range ->
		(range.start..range.endInclusive).map {
			StringBuilder().appendCodePoint(it).toString()
		}
	}
}
