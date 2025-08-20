package com.tegonal.minimalist.testutils.atrium

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.creating.Expect
import com.tegonal.minimalist.generators.UnicodeRange
import com.tegonal.minimalist.generators.UnicodeRanges


fun Expect<Int>.toBeIn(unicodeRanges: UnicodeRanges) {
	feature("is in ${unicodeRanges.name}") { this in unicodeRanges }.toEqual(true)
}

fun Expect<Int>.notToBeIn(unicodeRanges: UnicodeRanges) {
	feature("is in ${unicodeRanges.name}") { this in unicodeRanges }.toEqual(false)
}

fun Expect<Int>.toBeIn(unicodeRange: UnicodeRange) {
	feature("is in $unicodeRange") { this in unicodeRange }.toEqual(true)
}

fun Expect<Int>.toBeInIf(predicate: (UnicodeRanges) -> Boolean) {
	UnicodeRanges.entries.partition(predicate).let { (rangesIn, rangesNotIn) ->
		rangesIn.forEach { toBeIn(it) }
		rangesNotIn.forEach { notToBeIn(it) }
	}
}
