package com.tegonal.minimalist.generators

import com.tegonal.minimalist.utils.impl.failIfNegative

/**
 * @since 2.0.0
 */
class UnicodeRange(override val start: Int, override val endInclusive: Int) : ClosedRange<Int> {
	init {
		failIfNegative(start, "start")
		require(endInclusive <= MAX_CODE_POINT) {
			"endInclusive ($endInclusive) must be less than or equal to 0x10FFFF"
		}
		require(start <= endInclusive) {
			"start ($start) must be less than or equal to endInclusive ($endInclusive) -- a ${UnicodeRange::class.simpleName} doesn't support to be empty"
		}
	}

	override fun toString(): String = "U+%04X..U+%04X".format(start, endInclusive)

	companion object {
		const val SURROGATES_START = 0xD800
		const val SURROGATES_END = 0xDFFF
		const val BEFORE_SURROGATES_START = 0xD7FF
		const val AFTER_SURROGATES_END = 0xE000

		private const val MAX_CODE_POINT = 0x10FFFF
		val ASCII = listOf(UnicodeRange(0x00, 0x7F))
		val ASCII_PRINTABLE = listOf(UnicodeRange(0x20, 0x7E))

		val ISO_8859_1 = listOf(UnicodeRange(0x00, 0xFF))
		val ISO_8859_1_PRINTABLE = ASCII_PRINTABLE + listOf(UnicodeRange(0xA0, 0xFF))

		val UTF_8 = listOf(
			// we only exclude surrogates as they are not valid codepoints in UTF-8 everything else are valid points
			// but maybe not suited, nothing we need to figure out
			UnicodeRange(0x0000, BEFORE_SURROGATES_START),
			UnicodeRange(AFTER_SURROGATES_END, MAX_CODE_POINT)
		)
		val UTF_8_PRINTABLE by lazy {
			ASCII_PRINTABLE + listOf(
				// basic multilingual plane BMP (plane 0)
				UnicodeRange(0xA0, BEFORE_SURROGATES_START), // includes Mongolian variation selectors (0x180B–0x180D)
				// excluding surrogates...
				//UnicodeRange(SURROGATES_START, SURROGATES_END),
				//... and private use area ...
				//UnicodeRange(0xE000, 0xF8FF),
				UnicodeRange(0xF900, 0xFDCF),
				//.. and noncharacters ..
				//UnicodeRange(0xFDD0, 0xFDEF),
				// (including variation selectors)
				UnicodeRange(0xFE00, 0xFE0F),
				UnicodeRange(0xFE10, 0xFFFD),
				// .. as well as BMP noncharacters
				// UnicodeRange(0xFFFE, 0xFFFF),
			) + run {
				// supplementary planes (plane 1–13) should be printable...
				(0..12).map {
					val offset = 0x10000 * it
					UnicodeRange(0x10000 + offset, 0x1FFFD + offset)
					// ... except for noncharacters at the end
					//UnicodeRange(0x1FFFE, 0x1FFFF)
					//UnicodeRange(0x2FFFE, 0x2FFFF) etc.
				}
			} + listOf(
				// we exclude language tags and future use codepoints
				//UnicodeRange(0xE0000, 0xE00FF)

				// we include variation selectors
				UnicodeRange(0xE0100, 0xE01EF),
				// we exclude the rest of plane 14 which are there for future use...
				//UnicodeRange(0xE01F0, 0xEFFFD),
				// ...as well as the noncharacters
				//UnicodeRange(0xEFFFE, 0xEFFFF),

				// and we exclude private use area A + B
				// UnicodeRange(0xF0000, 0xFFFFF),
				// UnicodeRange(0x100000, 0x10FFFF)
			)
		}

		val SURROGATES = listOf(UnicodeRange(SURROGATES_START, SURROGATES_END))
		val NONCHARACTERS by lazy {
			listOf(UnicodeRange(0xFDD0, 0xFDEF)) +
				(0..16).map {
					val offset = 0x10000 * it
					UnicodeRange(0xFFFE + offset, 0xFFFF + offset)
				}
		}
		val PRIVATE_USE by lazy {
			listOf(
				UnicodeRange(0xE000, 0xF8FF),
				// plane 15 + 16: private use A and B
				UnicodeRange(0xF0000, 0xFFFFD),
				UnicodeRange(0x100000, 0x10FFFD)
			)
		}

		/**
		 * Kotlins [kotlin.text.isWhitespace] combines Java's [Character.isWhitespace] and [Character.isSpaceChar]
		 * This list includes all as of JDK 11
		 */
		val WHITESPACES_KOTLIN by lazy {
			listOf(
				UnicodeRange(0x0009, 0x000D), // TAB, LF, VT, FF, CR
				UnicodeRange(0x001C, 0x001F), // file, group, record + unit separator
				UnicodeRange(0x0020, 0x0020), // space
				UnicodeRange(0x00A0, 0x00A0), // no-break space (nbsp)
				UnicodeRange(0x1680, 0x1680), // ogham space mark
				UnicodeRange(0x2000, 0x200A), // EN quad .. hair space
				UnicodeRange(0x2028, 0x2029), // line + paragraph separator
				UnicodeRange(0x202F, 0x202F), // narrow no-break space
				UnicodeRange(0x205F, 0x205F), // medium mathematical space
				UnicodeRange(0x3000, 0x3000), // ideographic space
				// Note, zero-width space is not a whitespace
			)
		}

		val ALL = listOf(UnicodeRange(0x0000, MAX_CODE_POINT))
	}
}

/**
 * @since 2.0.0
 */
operator fun List<UnicodeRange>.contains(codePoint: Int): Boolean =
	any { codePoint in it }
