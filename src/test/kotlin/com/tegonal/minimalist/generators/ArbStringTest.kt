package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class ArbStringTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		// we skip the other predefined UnicodeRanges because they are quite big and we don't prove more
		// ASCII has one UnicodeRange, ISO_8859_1_PRINTABLE has two
		UnicodeRange.ASCII.let {
			Tuple("string ASCII", modifiedArb.string(maxLength = 1, allowedRanges = it), listOf("") + it.toStrings())
		},
		UnicodeRange.ISO_8859_1_PRINTABLE.let {
			Tuple(
				"string ISO_8859_1_PRINTABLE",
				modifiedArb.string(maxLength = 1, allowedRanges = it),
				listOf("") + it.toStrings()
			)
		}
	)

	private fun List<UnicodeRange>.toStrings() = flatMap { range ->
		(range.start..range.endInclusive).map {
			StringBuilder().appendCodePoint(it).toString()
		}
	}
}
