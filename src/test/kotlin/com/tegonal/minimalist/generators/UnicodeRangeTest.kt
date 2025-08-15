package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.and
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.withRepresentation
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Test

class UnicodeRangeTest {

	@Test
	fun whitespaceIsUpToDate() {
		(0..0x10FFFF).forEach {
			if (Character.isWhitespace(it) || Character.isSpaceChar(it)) {
				expect(it) {
					feature("in WHITESPACES_KOTLIN") { this in UnicodeRange.WHITESPACES_KOTLIN }.toEqual(true)
				}
			}
		}
	}

	@OptIn(ExperimentalWithOptions::class)
	@Test
	fun utf8PrintableDoesntIncludeSurrogatesNoncharactersAndPrivateUse() {
		UnicodeRange.UTF_8_PRINTABLE.asSequence().flatMap { (it.start..it.endInclusive).asSequence() }.forEach {
			expect(it).withRepresentation("U+%04X".format(it)).and {
				feature("in SURROGATES") { this in UnicodeRange.SURROGATES }.toEqual(false)
				feature("in NONCHARACTERS") { this in UnicodeRange.NONCHARACTERS }.toEqual(false)
				feature("in PRIVATE_USE") { this in UnicodeRange.PRIVATE_USE }.toEqual(false)
			}
		}
	}
}
