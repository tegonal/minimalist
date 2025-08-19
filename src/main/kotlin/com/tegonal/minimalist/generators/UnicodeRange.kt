package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.UnicodeRange.Companion.AFTER_SURROGATES_END
import com.tegonal.minimalist.generators.UnicodeRange.Companion.BEFORE_SURROGATES_START
import com.tegonal.minimalist.generators.UnicodeRange.Companion.ASCII_END
import com.tegonal.minimalist.generators.UnicodeRange.Companion.ASCII_PRINTABLE_END
import com.tegonal.minimalist.generators.UnicodeRange.Companion.ASCII_PRINTABLE_START
import com.tegonal.minimalist.generators.UnicodeRange.Companion.MAX_CODE_POINT
import com.tegonal.minimalist.generators.UnicodeRange.Companion.ISO_8859_1_END
import com.tegonal.minimalist.generators.UnicodeRange.Companion.ISO_8859_1_PRINTABLE_START
import com.tegonal.minimalist.generators.UnicodeRange.Companion.SURROGATES_END
import com.tegonal.minimalist.generators.UnicodeRange.Companion.SURROGATES_START
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
		const val BEFORE_SURROGATES_START = SURROGATES_START - 1
		const val AFTER_SURROGATES_END = SURROGATES_END + 1

		const val MAX_CODE_POINT = 0x10FFFF
		const val ASCII_END = 0x7F
		const val ASCII_PRINTABLE_START = 0x20
		const val ASCII_PRINTABLE_END = ASCII_END - 1
		const val ISO_8859_1_END = 0xFF
		const val ISO_8859_1_PRINTABLE_START = 0xA0
	}
}

/**
 * Don't depend on order, we might break it
 *
 * @since 2.0.0
 */
enum class UnicodeRanges(open vararg val ranges: UnicodeRange) {
	ASCII(UnicodeRange(0x00, ASCII_END)),
	ASCII_PRINTABLE(UnicodeRange(ASCII_PRINTABLE_START, ASCII_PRINTABLE_END)),
	ISO_8859_1(UnicodeRange(0x00, ISO_8859_1_END)),
	ISO_8859_1_PRINTABLE(
		ranges = arrayOf(UnicodeRange(ISO_8859_1_PRINTABLE_START, ISO_8859_1_END)) + ASCII_PRINTABLE.ranges
	),
	UTF_8(
		// we only exclude surrogates as they are not valid codepoints in UTF-8 everything else are valid points
		// but maybe not suited (like unassigned code points, private use, noncharacters etc.).
		UnicodeRange(0x0000, BEFORE_SURROGATES_START),
		UnicodeRange(AFTER_SURROGATES_END, MAX_CODE_POINT)
	),

	// UTF_8_PRINTABLE see further below as it is quite long it comes last
	SURROGATES(UnicodeRange(SURROGATES_START, SURROGATES_END)),
	ALL(UnicodeRange(0x0000, MAX_CODE_POINT)),
	CONTROL() {
		override val ranges: Array<out UnicodeRange> by lazy {
			arrayOf(
				UnicodeRange(0x0000, ASCII_PRINTABLE_START - 1),
				UnicodeRange(ASCII_PRINTABLE_END + 1, ISO_8859_1_PRINTABLE_START - 1)
			)
		}
	},
	PRIVATE_USE() {
		override val ranges: Array<out UnicodeRange> by lazy {
			arrayOf(
				UnicodeRange(0xE000, 0xF8FF),
				// plane 15 + 16: private use A and B
				UnicodeRange(0xF0000, 0xFFFFD),
				UnicodeRange(0x100000, 0x10FFFD)
			)
		}
	},

	/**
	 * Kotlins [kotlin.text.isWhitespace] combines Java's [Character.isWhitespace] and [Character.isSpaceChar]
	 * This list includes all as of JDK 11
	 */
	WHITESPACES_KOTLIN() {
		override val ranges: Array<out UnicodeRange> by lazy {
			arrayOf(
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
	},
	UNASSIGNED() {
		override val ranges: Array<out UnicodeRange> by lazy {
			arrayOf(
				UnicodeRange(0x0378, 0x0379), // greek unassigned 1
				UnicodeRange(0x0380, 0x0383), // greek unassigned 2
				UnicodeRange(0x038B, 0x038B), // greek unassigned 3
				UnicodeRange(0x038D, 0x038D), // greek unassigned 4
				UnicodeRange(0x03A2, 0x03A2), // greek unassigned 5
				UnicodeRange(0x0530, 0x0530), // cyrillic unassigned 1
				UnicodeRange(0x0557, 0x0558), // armenian unassigned 1
				UnicodeRange(0x0560, 0x0560), // armenian unassigned 2
				UnicodeRange(0x0588, 0x0588), // armenian unassigned 3
				UnicodeRange(0x058B, 0x058C), // armenian unassigned 4
				UnicodeRange(0x0590, 0x0590), // armenian unassigned 5
				UnicodeRange(0x05C8, 0x05CF), // hebrew unassigned 1
				UnicodeRange(0x05EB, 0x05EF), // hebrew unassigned 2
				UnicodeRange(0x05F5, 0x05FF), // hebrew unassigned 3
				UnicodeRange(0x061D, 0x061D), // arabic unassigned 1
				UnicodeRange(0x070E, 0x070E), // syriac unassigned 1
				UnicodeRange(0x074B, 0x074C), // syriac unassigned 2
				UnicodeRange(0x07B2, 0x07BF), // thaana unassigned 1
				UnicodeRange(0x07FB, 0x07FF), // nko unassigned 1
				UnicodeRange(0x082E, 0x082F), // samaritan unassigned 1
				UnicodeRange(0x083F, 0x083F), // samaritan unassigned 2
				UnicodeRange(0x085C, 0x085D), // mandaic unassigned 1
				UnicodeRange(0x085F, 0x085F), // mandaic unassigned 2
				UnicodeRange(0x086B, 0x089F), // syriac unassigned 1
				UnicodeRange(0x08B5, 0x08B5), // arabic unassigned 1
				UnicodeRange(0x08BE, 0x08D3), // arabic unassigned 2
				UnicodeRange(0x0984, 0x0984), // bengali unassigned 1
				UnicodeRange(0x098D, 0x098E), // bengali unassigned 2
				UnicodeRange(0x0991, 0x0992), // bengali unassigned 3
				UnicodeRange(0x09A9, 0x09A9), // bengali unassigned 4
				UnicodeRange(0x09B1, 0x09B1), // bengali unassigned 5
				UnicodeRange(0x09B3, 0x09B5), // bengali unassigned 6
				UnicodeRange(0x09BA, 0x09BB), // bengali unassigned 7
				UnicodeRange(0x09C5, 0x09C6), // bengali unassigned 8
				UnicodeRange(0x09C9, 0x09CA), // bengali unassigned 9
				UnicodeRange(0x09CF, 0x09D6), // bengali unassigned 10
				UnicodeRange(0x09D8, 0x09DB), // bengali unassigned 11
				UnicodeRange(0x09DE, 0x09DE), // bengali unassigned 12
				UnicodeRange(0x09E4, 0x09E5), // bengali unassigned 13
				UnicodeRange(0x09FE, 0x0A00), // bengali unassigned 14
				UnicodeRange(0x0A04, 0x0A04), // gurmukhi unassigned 1
				UnicodeRange(0x0A0B, 0x0A0E), // gurmukhi unassigned 2
				UnicodeRange(0x0A11, 0x0A12), // gurmukhi unassigned 3
				UnicodeRange(0x0A29, 0x0A29), // gurmukhi unassigned 4
				UnicodeRange(0x0A31, 0x0A31), // gurmukhi unassigned 5
				UnicodeRange(0x0A34, 0x0A34), // gurmukhi unassigned 6
				UnicodeRange(0x0A37, 0x0A37), // gurmukhi unassigned 7
				UnicodeRange(0x0A3A, 0x0A3B), // gurmukhi unassigned 8
				UnicodeRange(0x0A3D, 0x0A3D), // gurmukhi unassigned 9
				UnicodeRange(0x0A43, 0x0A46), // gurmukhi unassigned 10
				UnicodeRange(0x0A49, 0x0A4A), // gurmukhi unassigned 11
				UnicodeRange(0x0A4E, 0x0A50), // gurmukhi unassigned 12
				UnicodeRange(0x0A52, 0x0A58), // gurmukhi unassigned 13
				UnicodeRange(0x0A5D, 0x0A5D), // gurmukhi unassigned 14
				UnicodeRange(0x0A5F, 0x0A65), // gurmukhi unassigned 15
				UnicodeRange(0x0A76, 0x0A80), // gurmukhi unassigned 16
				UnicodeRange(0x0A84, 0x0A84), // gujarati unassigned 1
				UnicodeRange(0x0A8E, 0x0A8E), // gujarati unassigned 2
				UnicodeRange(0x0A92, 0x0A92), // gujarati unassigned 3
				UnicodeRange(0x0AA9, 0x0AA9), // gujarati unassigned 4
				UnicodeRange(0x0AB1, 0x0AB1), // gujarati unassigned 5
				UnicodeRange(0x0AB4, 0x0AB4), // gujarati unassigned 6
				UnicodeRange(0x0ABA, 0x0ABB), // gujarati unassigned 7
				UnicodeRange(0x0AC6, 0x0AC6), // gujarati unassigned 8
				UnicodeRange(0x0ACA, 0x0ACA), // gujarati unassigned 9
				UnicodeRange(0x0ACE, 0x0ACF), // gujarati unassigned 10
				UnicodeRange(0x0AD1, 0x0ADF), // gujarati unassigned 11
				UnicodeRange(0x0AE4, 0x0AE5), // gujarati unassigned 12
				UnicodeRange(0x0AF2, 0x0AF8), // gujarati unassigned 13
				UnicodeRange(0x0B00, 0x0B00), // gujarati unassigned 14
				UnicodeRange(0x0B04, 0x0B04), // oriya unassigned 1
				UnicodeRange(0x0B0D, 0x0B0E), // oriya unassigned 2
				UnicodeRange(0x0B11, 0x0B12), // oriya unassigned 3
				UnicodeRange(0x0B29, 0x0B29), // oriya unassigned 4
				UnicodeRange(0x0B31, 0x0B31), // oriya unassigned 5
				UnicodeRange(0x0B34, 0x0B34), // oriya unassigned 6
				UnicodeRange(0x0B3A, 0x0B3B), // oriya unassigned 7
				UnicodeRange(0x0B45, 0x0B46), // oriya unassigned 8
				UnicodeRange(0x0B49, 0x0B4A), // oriya unassigned 9
				UnicodeRange(0x0B4E, 0x0B55), // oriya unassigned 10
				UnicodeRange(0x0B58, 0x0B5B), // oriya unassigned 11
				UnicodeRange(0x0B5E, 0x0B5E), // oriya unassigned 12
				UnicodeRange(0x0B64, 0x0B65), // oriya unassigned 13
				UnicodeRange(0x0B78, 0x0B81), // oriya unassigned 14
				UnicodeRange(0x0B84, 0x0B84), // tamil unassigned 1
				UnicodeRange(0x0B8B, 0x0B8D), // tamil unassigned 2
				UnicodeRange(0x0B91, 0x0B91), // tamil unassigned 3
				UnicodeRange(0x0B96, 0x0B98), // tamil unassigned 4
				UnicodeRange(0x0B9B, 0x0B9B), // tamil unassigned 5
				UnicodeRange(0x0B9D, 0x0B9D), // tamil unassigned 6
				UnicodeRange(0x0BA0, 0x0BA2), // tamil unassigned 7
				UnicodeRange(0x0BA5, 0x0BA7), // tamil unassigned 8
				UnicodeRange(0x0BAB, 0x0BAD), // tamil unassigned 9
				UnicodeRange(0x0BBA, 0x0BBD), // tamil unassigned 10
				UnicodeRange(0x0BC3, 0x0BC5), // tamil unassigned 11
				UnicodeRange(0x0BC9, 0x0BC9), // tamil unassigned 12
				UnicodeRange(0x0BCE, 0x0BCF), // tamil unassigned 13
				UnicodeRange(0x0BD1, 0x0BD6), // tamil unassigned 14
				UnicodeRange(0x0BD8, 0x0BE5), // tamil unassigned 15
				UnicodeRange(0x0BFB, 0x0BFF), // tamil unassigned 16
				UnicodeRange(0x0C04, 0x0C04), // telugu unassigned 1
				UnicodeRange(0x0C0D, 0x0C0D), // telugu unassigned 2
				UnicodeRange(0x0C11, 0x0C11), // telugu unassigned 3
				UnicodeRange(0x0C29, 0x0C29), // telugu unassigned 4
				UnicodeRange(0x0C3A, 0x0C3C), // telugu unassigned 5
				UnicodeRange(0x0C45, 0x0C45), // telugu unassigned 6
				UnicodeRange(0x0C49, 0x0C49), // telugu unassigned 7
				UnicodeRange(0x0C4E, 0x0C54), // telugu unassigned 8
				UnicodeRange(0x0C57, 0x0C57), // telugu unassigned 9
				UnicodeRange(0x0C5B, 0x0C5F), // telugu unassigned 10
				UnicodeRange(0x0C64, 0x0C65), // telugu unassigned 11
				UnicodeRange(0x0C70, 0x0C77), // telugu unassigned 12
				UnicodeRange(0x0C84, 0x0C84), // kannada unassigned 1
				UnicodeRange(0x0C8D, 0x0C8D), // kannada unassigned 2
				UnicodeRange(0x0C91, 0x0C91), // kannada unassigned 3
				UnicodeRange(0x0CA9, 0x0CA9), // kannada unassigned 4
				UnicodeRange(0x0CB4, 0x0CB4), // kannada unassigned 5
				UnicodeRange(0x0CBA, 0x0CBB), // kannada unassigned 6
				UnicodeRange(0x0CC5, 0x0CC5), // kannada unassigned 7
				UnicodeRange(0x0CC9, 0x0CC9), // kannada unassigned 8
				UnicodeRange(0x0CCE, 0x0CD4), // kannada unassigned 9
				UnicodeRange(0x0CD7, 0x0CDD), // kannada unassigned 10
				UnicodeRange(0x0CDF, 0x0CDF), // kannada unassigned 11
				UnicodeRange(0x0CE4, 0x0CE5), // kannada unassigned 12
				UnicodeRange(0x0CF0, 0x0CF0), // kannada unassigned 13
				UnicodeRange(0x0CF3, 0x0CFF), // kannada unassigned 14
				UnicodeRange(0x0D04, 0x0D04), // malayalam unassigned 1
				UnicodeRange(0x0D0D, 0x0D0D), // malayalam unassigned 2
				UnicodeRange(0x0D11, 0x0D11), // malayalam unassigned 3
				UnicodeRange(0x0D45, 0x0D45), // malayalam unassigned 4
				UnicodeRange(0x0D49, 0x0D49), // malayalam unassigned 5
				UnicodeRange(0x0D50, 0x0D53), // malayalam unassigned 6
				UnicodeRange(0x0D64, 0x0D65), // malayalam unassigned 7
				UnicodeRange(0x0D80, 0x0D81), // malayalam unassigned 8
				UnicodeRange(0x0D84, 0x0D84), // sinhala unassigned 1
				UnicodeRange(0x0D97, 0x0D99), // sinhala unassigned 2
				UnicodeRange(0x0DB2, 0x0DB2), // sinhala unassigned 3
				UnicodeRange(0x0DBC, 0x0DBC), // sinhala unassigned 4
				UnicodeRange(0x0DBE, 0x0DBF), // sinhala unassigned 5
				UnicodeRange(0x0DC7, 0x0DC9), // sinhala unassigned 6
				UnicodeRange(0x0DCB, 0x0DCE), // sinhala unassigned 7
				UnicodeRange(0x0DD5, 0x0DD5), // sinhala unassigned 8
				UnicodeRange(0x0DD7, 0x0DD7), // sinhala unassigned 9
				UnicodeRange(0x0DE0, 0x0DE5), // sinhala unassigned 10
				UnicodeRange(0x0DF0, 0x0DF1), // sinhala unassigned 11
				UnicodeRange(0x0DF5, 0x0E00), // sinhala unassigned 12
				UnicodeRange(0x0E3B, 0x0E3E), // thai unassigned 1
				UnicodeRange(0x0E5C, 0x0E80), // thai unassigned 2
				UnicodeRange(0x0E83, 0x0E83), // lao unassigned 1
				UnicodeRange(0x0E85, 0x0E86), // lao unassigned 2
				UnicodeRange(0x0E89, 0x0E89), // lao unassigned 3
				UnicodeRange(0x0E8B, 0x0E8C), // lao unassigned 4
				UnicodeRange(0x0E8E, 0x0E93), // lao unassigned 5
				UnicodeRange(0x0E98, 0x0E98), // lao unassigned 6
				UnicodeRange(0x0EA0, 0x0EA0), // lao unassigned 7
				UnicodeRange(0x0EA4, 0x0EA4), // lao unassigned 8
				UnicodeRange(0x0EA6, 0x0EA6), // lao unassigned 9
				UnicodeRange(0x0EA8, 0x0EA9), // lao unassigned 10
				UnicodeRange(0x0EAC, 0x0EAC), // lao unassigned 11
				UnicodeRange(0x0EBA, 0x0EBA), // lao unassigned 12
				UnicodeRange(0x0EBE, 0x0EBF), // lao unassigned 13
				UnicodeRange(0x0EC5, 0x0EC5), // lao unassigned 14
				UnicodeRange(0x0EC7, 0x0EC7), // lao unassigned 15
				UnicodeRange(0x0ECE, 0x0ECF), // lao unassigned 16
				UnicodeRange(0x0EDA, 0x0EDB), // lao unassigned 17
				UnicodeRange(0x0EE0, 0x0EFF), // lao unassigned 18
				UnicodeRange(0x0F48, 0x0F48), // tibetan unassigned 1
				UnicodeRange(0x0F6D, 0x0F70), // tibetan unassigned 2
				UnicodeRange(0x0F98, 0x0F98), // tibetan unassigned 3
				UnicodeRange(0x0FBD, 0x0FBD), // tibetan unassigned 4
				UnicodeRange(0x0FCD, 0x0FCD), // tibetan unassigned 5
				UnicodeRange(0x0FDB, 0x0FFF), // tibetan unassigned 6
				UnicodeRange(0x10C6, 0x10C6), // georgian unassigned 1
				UnicodeRange(0x10C8, 0x10CC), // georgian unassigned 2
				UnicodeRange(0x10CE, 0x10CF), // georgian unassigned 3
				UnicodeRange(0x1249, 0x1249), // ethiopic unassigned 1
				UnicodeRange(0x124E, 0x124F), // ethiopic unassigned 2
				UnicodeRange(0x1257, 0x1257), // ethiopic unassigned 3
				UnicodeRange(0x1259, 0x1259), // ethiopic unassigned 4
				UnicodeRange(0x125E, 0x125F), // ethiopic unassigned 5
				UnicodeRange(0x1289, 0x1289), // ethiopic unassigned 6
				UnicodeRange(0x128E, 0x128F), // ethiopic unassigned 7
				UnicodeRange(0x12B1, 0x12B1), // ethiopic unassigned 8
				UnicodeRange(0x12B6, 0x12B7), // ethiopic unassigned 9
				UnicodeRange(0x12BF, 0x12BF), // ethiopic unassigned 10
				UnicodeRange(0x12C1, 0x12C1), // ethiopic unassigned 11
				UnicodeRange(0x12C6, 0x12C7), // ethiopic unassigned 12
				UnicodeRange(0x12D7, 0x12D7), // ethiopic unassigned 13
				UnicodeRange(0x1311, 0x1311), // ethiopic unassigned 14
				UnicodeRange(0x1316, 0x1317), // ethiopic unassigned 15
				UnicodeRange(0x135B, 0x135C), // ethiopic unassigned 16
				UnicodeRange(0x137D, 0x137F), // ethiopic unassigned 17
				UnicodeRange(0x139A, 0x139F), // ethiopic unassigned 18
				UnicodeRange(0x13F6, 0x13F7), // cherokee unassigned 1
				UnicodeRange(0x13FE, 0x13FF), // cherokee unassigned 2
				UnicodeRange(0x169D, 0x169F), // ogham unassigned 1
				UnicodeRange(0x16F9, 0x16FF), // runic unassigned 1
				UnicodeRange(0x170D, 0x170D), // tagalog unassigned 1
				UnicodeRange(0x1715, 0x171F), // tagalog unassigned 2
				UnicodeRange(0x1737, 0x173F), // philippine unassigned 1
				UnicodeRange(0x1754, 0x175F), // buhid unassigned 1
				UnicodeRange(0x176D, 0x176D), // tagbanwa unassigned 1
				UnicodeRange(0x1771, 0x1771), // tagbanwa unassigned 2
				UnicodeRange(0x1774, 0x177F), // tagbanwa unassigned 3
				UnicodeRange(0x17DE, 0x17DF), // khmer unassigned 1
				UnicodeRange(0x17EA, 0x17EF), // khmer unassigned 2
				UnicodeRange(0x17FA, 0x17FF), // khmer unassigned 3
				UnicodeRange(0x180F, 0x180F), // mongolian unassigned 1
				UnicodeRange(0x181A, 0x181F), // mongolian unassigned 2
				UnicodeRange(0x1878, 0x187F), // mongolian unassigned 3
				UnicodeRange(0x18AB, 0x18AF), // mongolian unassigned 4
				UnicodeRange(0x18F6, 0x18FF), // canadian unassigned 1
				UnicodeRange(0x191F, 0x191F), // limbu unassigned 1
				UnicodeRange(0x192C, 0x192F), // limbu unassigned 2
				UnicodeRange(0x193C, 0x193F), // limbu unassigned 3
				UnicodeRange(0x1941, 0x1943), // limbu unassigned 4
				UnicodeRange(0x196E, 0x196F), // tai unassigned 1
				UnicodeRange(0x1975, 0x197F), // tai unassigned 2
				UnicodeRange(0x19AC, 0x19AF), // new unassigned 1
				UnicodeRange(0x19CA, 0x19CF), // new unassigned 2
				UnicodeRange(0x19DB, 0x19DD), // new unassigned 3
				UnicodeRange(0x1A1C, 0x1A1D), // buginese unassigned 1
				UnicodeRange(0x1A5F, 0x1A5F), // tai unassigned 1
				UnicodeRange(0x1A7D, 0x1A7E), // tai unassigned 2
				UnicodeRange(0x1A8A, 0x1A8F), // tai unassigned 3
				UnicodeRange(0x1A9A, 0x1A9F), // tai unassigned 4
				UnicodeRange(0x1AAE, 0x1AAF), // tai unassigned 5
				UnicodeRange(0x1ABF, 0x1AFF), // combining unassigned 1
				UnicodeRange(0x1B4C, 0x1B4F), // balinese unassigned 1
				UnicodeRange(0x1B7D, 0x1B7F), // balinese unassigned 2
				UnicodeRange(0x1BF4, 0x1BFB), // batak unassigned 1
				UnicodeRange(0x1C38, 0x1C3A), // lepcha unassigned 1
				UnicodeRange(0x1C4A, 0x1C4C), // lepcha unassigned 2
				UnicodeRange(0x1C89, 0x1CBF), // cyrillic unassigned 1
				UnicodeRange(0x1CC8, 0x1CCF), // sundanese unassigned 1
				UnicodeRange(0x1CFA, 0x1CFF), // vedic unassigned 1
				UnicodeRange(0x1DFA, 0x1DFA), // combining unassigned 1
				UnicodeRange(0x1F16, 0x1F17), // greek unassigned 1
				UnicodeRange(0x1F1E, 0x1F1F), // greek unassigned 2
				UnicodeRange(0x1F46, 0x1F47), // greek unassigned 3
				UnicodeRange(0x1F4E, 0x1F4F), // greek unassigned 4
				UnicodeRange(0x1F58, 0x1F58), // greek unassigned 5
				UnicodeRange(0x1F5A, 0x1F5A), // greek unassigned 6
				UnicodeRange(0x1F5C, 0x1F5C), // greek unassigned 7
				UnicodeRange(0x1F5E, 0x1F5E), // greek unassigned 8
				UnicodeRange(0x1F7E, 0x1F7F), // greek unassigned 9
				UnicodeRange(0x1FB5, 0x1FB5), // greek unassigned 10
				UnicodeRange(0x1FC5, 0x1FC5), // greek unassigned 11
				UnicodeRange(0x1FD4, 0x1FD5), // greek unassigned 12
				UnicodeRange(0x1FDC, 0x1FDC), // greek unassigned 13
				UnicodeRange(0x1FF0, 0x1FF1), // greek unassigned 14
				UnicodeRange(0x1FF5, 0x1FF5), // greek unassigned 15
				UnicodeRange(0x1FFF, 0x1FFF), // greek unassigned 16
				UnicodeRange(0x2065, 0x2065), // invisible unassigned 1
				UnicodeRange(0x2072, 0x2073), // superscript unassigned 1
				UnicodeRange(0x208F, 0x208F), // subscript unassigned 1
				UnicodeRange(0x209D, 0x209F), // latin unassigned 1
				UnicodeRange(0x20C0, 0x20CF), // bitcoin unassigned 1
				UnicodeRange(0x20F1, 0x20FF), // combining unassigned 1
				UnicodeRange(0x218C, 0x218F), // turned unassigned 1
				UnicodeRange(0x2427, 0x243F), // symbol unassigned 1
				UnicodeRange(0x244B, 0x245F), // ocr unassigned 1
				UnicodeRange(0x2B74, 0x2B75), // downwards unassigned 1
				UnicodeRange(0x2B96, 0x2B97), // rightwards unassigned 1
				UnicodeRange(0x2BBA, 0x2BBC), // up unassigned 1
				UnicodeRange(0x2BC9, 0x2BC9), // black unassigned 1
				UnicodeRange(0x2BD3, 0x2BEB), // group unassigned 1
				UnicodeRange(0x2BF0, 0x2BFF), // downwards unassigned 1
				UnicodeRange(0x2C2F, 0x2C2F), // glagolitic unassigned 1
				UnicodeRange(0x2C5F, 0x2C5F), // glagolitic unassigned 2
				UnicodeRange(0x2CF4, 0x2CF8), // coptic unassigned 1
				UnicodeRange(0x2D26, 0x2D26), // georgian unassigned 1
				UnicodeRange(0x2D28, 0x2D2C), // georgian unassigned 2
				UnicodeRange(0x2D2E, 0x2D2F), // georgian unassigned 3
				UnicodeRange(0x2D68, 0x2D6E), // tifinagh unassigned 1
				UnicodeRange(0x2D71, 0x2D7E), // tifinagh unassigned 2
				UnicodeRange(0x2D97, 0x2D9F), // ethiopic unassigned 1
				UnicodeRange(0x2DA7, 0x2DA7), // ethiopic unassigned 2
				UnicodeRange(0x2DAF, 0x2DAF), // ethiopic unassigned 3
				UnicodeRange(0x2DB7, 0x2DB7), // ethiopic unassigned 4
				UnicodeRange(0x2DBF, 0x2DBF), // ethiopic unassigned 5
				UnicodeRange(0x2DC7, 0x2DC7), // ethiopic unassigned 6
				UnicodeRange(0x2DCF, 0x2DCF), // ethiopic unassigned 7
				UnicodeRange(0x2DD7, 0x2DD7), // ethiopic unassigned 8
				UnicodeRange(0x2DDF, 0x2DDF), // ethiopic unassigned 9
				UnicodeRange(0x2E4A, 0x2E7F), // double unassigned 1
				UnicodeRange(0x2E9A, 0x2E9A), // cjk unassigned 1
				UnicodeRange(0x2EF4, 0x2EFF), // cjk unassigned 2
				UnicodeRange(0x2FD6, 0x2FEF), // kangxi unassigned 1
				UnicodeRange(0x2FFC, 0x2FFF), // ideographic unassigned 1
				UnicodeRange(0x3040, 0x3040), // ideographic unassigned 2
				UnicodeRange(0x3097, 0x3098), // hiragana unassigned 1
				UnicodeRange(0x3100, 0x3104), // katakana unassigned 1
				UnicodeRange(0x312F, 0x3130), // bopomofo unassigned 1
				UnicodeRange(0x318F, 0x318F), // hangul unassigned 1
				UnicodeRange(0x31BB, 0x31BF), // bopomofo unassigned 1
				UnicodeRange(0x31E4, 0x31EF), // cjk unassigned 1
				UnicodeRange(0x321F, 0x321F), // parenthesized unassigned 1
				UnicodeRange(0x4DB6, 0x4DBF), // cjk unassigned 1
				UnicodeRange(0x9FF0, 0x9FFF), // cjk unassigned 2
				UnicodeRange(0xA48D, 0xA48F), // yi unassigned 1
				UnicodeRange(0xA4C7, 0xA4CF), // yi unassigned 2
				UnicodeRange(0xA62C, 0xA63F), // vai unassigned 1
				UnicodeRange(0xA6F8, 0xA6FF), // bamum unassigned 1
				UnicodeRange(0xA7AF, 0xA7AF), // latin unassigned 1
				UnicodeRange(0xA7B8, 0xA7F6), // latin unassigned 2
				UnicodeRange(0xA82C, 0xA82F), // syloti unassigned 1
				UnicodeRange(0xA83A, 0xA83F), // north unassigned 1
				UnicodeRange(0xA878, 0xA87F), // phags-pa unassigned 1
				UnicodeRange(0xA8C6, 0xA8CD), // saurashtra unassigned 1
				UnicodeRange(0xA8DA, 0xA8DF), // saurashtra unassigned 2
				UnicodeRange(0xA8FE, 0xA8FF), // devanagari unassigned 1
				UnicodeRange(0xA954, 0xA95E), // rejang unassigned 1
				UnicodeRange(0xA97D, 0xA97F), // hangul unassigned 1
				UnicodeRange(0xA9CE, 0xA9CE), // javanese unassigned 1
				UnicodeRange(0xA9DA, 0xA9DD), // javanese unassigned 2
				UnicodeRange(0xA9FF, 0xA9FF), // myanmar unassigned 1
				UnicodeRange(0xAA37, 0xAA3F), // cham unassigned 1
				UnicodeRange(0xAA4E, 0xAA4F), // cham unassigned 2
				UnicodeRange(0xAA5A, 0xAA5B), // cham unassigned 3
				UnicodeRange(0xAAC3, 0xAADA), // tai unassigned 1
				UnicodeRange(0xAAF7, 0xAB00), // meetei unassigned 1
				UnicodeRange(0xAB07, 0xAB08), // ethiopic unassigned 1
				UnicodeRange(0xAB0F, 0xAB10), // ethiopic unassigned 2
				UnicodeRange(0xAB17, 0xAB1F), // ethiopic unassigned 3
				UnicodeRange(0xAB27, 0xAB27), // ethiopic unassigned 4
				UnicodeRange(0xAB2F, 0xAB2F), // ethiopic unassigned 5
				UnicodeRange(0xAB66, 0xAB6F), // greek unassigned 1
				UnicodeRange(0xABEE, 0xABEF), // meetei unassigned 1
				UnicodeRange(0xABFA, 0xABFF), // meetei unassigned 2
				UnicodeRange(0xD7A4, 0xD7AF), // hangul unassigned 1
				UnicodeRange(0xD7C7, 0xD7CA), // hangul unassigned 2
				UnicodeRange(0xD7FC, 0xD7FF), // hangul unassigned 3
				UnicodeRange(0xFA6E, 0xFA6F), // cjk unassigned 1
				UnicodeRange(0xFADA, 0xFAFF), // cjk unassigned 2
				UnicodeRange(0xFB07, 0xFB12), // latin unassigned 1
				UnicodeRange(0xFB18, 0xFB1C), // armenian unassigned 1
				UnicodeRange(0xFB37, 0xFB37), // hebrew unassigned 1
				UnicodeRange(0xFB3D, 0xFB3D), // hebrew unassigned 2
				UnicodeRange(0xFB3F, 0xFB3F), // hebrew unassigned 3
				UnicodeRange(0xFB42, 0xFB42), // hebrew unassigned 4
				UnicodeRange(0xFB45, 0xFB45), // hebrew unassigned 5
				UnicodeRange(0xFBC2, 0xFBD2), // arabic unassigned 1
				UnicodeRange(0xFD40, 0xFD4F), // ornate unassigned 1
				UnicodeRange(0xFD90, 0xFD91), // arabic unassigned 1
				UnicodeRange(0xFDC8, 0xFDEF), // arabic unassigned 2
				UnicodeRange(0xFDFE, 0xFDFF), // arabic unassigned 3
				UnicodeRange(0xFE1A, 0xFE1F), // presentation unassigned 1
				UnicodeRange(0xFE53, 0xFE53), // small unassigned 1
				UnicodeRange(0xFE67, 0xFE67), // small unassigned 2
				UnicodeRange(0xFE6C, 0xFE6F), // small unassigned 3
				UnicodeRange(0xFE75, 0xFE75), // arabic unassigned 1
				UnicodeRange(0xFEFD, 0xFEFE), // arabic unassigned 2
				UnicodeRange(0xFF00, 0xFF00), // zero unassigned 1
				UnicodeRange(0xFFBF, 0xFFC1), // halfwidth unassigned 1
				UnicodeRange(0xFFC8, 0xFFC9), // halfwidth unassigned 2
				UnicodeRange(0xFFD0, 0xFFD1), // halfwidth unassigned 3
				UnicodeRange(0xFFD8, 0xFFD9), // halfwidth unassigned 4
				UnicodeRange(0xFFDD, 0xFFDF), // halfwidth unassigned 5
				UnicodeRange(0xFFE7, 0xFFE7), // fullwidth unassigned 1
				UnicodeRange(0xFFEF, 0xFFF8), // halfwidth unassigned 1
				UnicodeRange(0xFFFE, 0xFFFF), // replacement unassigned 1
				UnicodeRange(0x1000C, 0x1000C), // linear unassigned 1
				UnicodeRange(0x10027, 0x10027), // linear unassigned 2
				UnicodeRange(0x1003B, 0x1003B), // linear unassigned 3
				UnicodeRange(0x1003E, 0x1003E), // linear unassigned 4
				UnicodeRange(0x1004E, 0x1004F), // linear unassigned 5
				UnicodeRange(0x1005E, 0x1007F), // linear unassigned 6
				UnicodeRange(0x100FB, 0x100FF), // linear unassigned 7
				UnicodeRange(0x10103, 0x10106), // aegean unassigned 1
				UnicodeRange(0x10134, 0x10136), // aegean unassigned 2
				UnicodeRange(0x1018F, 0x1018F), // nomisma unassigned 1
				UnicodeRange(0x1019C, 0x1019F), // roman unassigned 1
				UnicodeRange(0x101A1, 0x101CF), // greek unassigned 1
				UnicodeRange(0x101FE, 0x1027F), // phaistos unassigned 1
				UnicodeRange(0x1029D, 0x1029F), // lycian unassigned 1
				UnicodeRange(0x102D1, 0x102DF), // carian unassigned 1
				UnicodeRange(0x102FC, 0x102FF), // coptic unassigned 1
				UnicodeRange(0x10324, 0x1032C), // old unassigned 1
				UnicodeRange(0x1034B, 0x1034F), // gothic unassigned 1
				UnicodeRange(0x1037B, 0x1037F), // combining unassigned 1
				UnicodeRange(0x1039E, 0x1039E), // ugaritic unassigned 1
				UnicodeRange(0x103C4, 0x103C7), // old unassigned 1
				UnicodeRange(0x103D6, 0x103FF), // old unassigned 2
				UnicodeRange(0x1049E, 0x1049F), // osmanya unassigned 1
				UnicodeRange(0x104AA, 0x104AF), // osmanya unassigned 2
				UnicodeRange(0x104D4, 0x104D7), // osage unassigned 1
				UnicodeRange(0x104FC, 0x104FF), // osage unassigned 2
				UnicodeRange(0x10528, 0x1052F), // elbasan unassigned 1
				UnicodeRange(0x10564, 0x1056E), // caucasian unassigned 1
				UnicodeRange(0x10570, 0x105FF), // caucasian unassigned 2
				UnicodeRange(0x10737, 0x1073F), // linear unassigned 1
				UnicodeRange(0x10756, 0x1075F), // linear unassigned 2
				UnicodeRange(0x10768, 0x107FF), // linear unassigned 3
				UnicodeRange(0x10806, 0x10807), // cypriot unassigned 1
				UnicodeRange(0x10809, 0x10809), // cypriot unassigned 2
				UnicodeRange(0x10836, 0x10836), // cypriot unassigned 3
				UnicodeRange(0x10839, 0x1083B), // cypriot unassigned 4
				UnicodeRange(0x1083D, 0x1083E), // cypriot unassigned 5
				UnicodeRange(0x10856, 0x10856), // imperial unassigned 1
				UnicodeRange(0x1089F, 0x108A6), // nabataean unassigned 1
				UnicodeRange(0x108B0, 0x108DF), // nabataean unassigned 2
				UnicodeRange(0x108F3, 0x108F3), // hatran unassigned 1
				UnicodeRange(0x108F6, 0x108FA), // hatran unassigned 2
				UnicodeRange(0x1091C, 0x1091E), // phoenician unassigned 1
				UnicodeRange(0x1093A, 0x1093E), // lydian unassigned 1
				UnicodeRange(0x10940, 0x1097F), // lydian unassigned 2
				UnicodeRange(0x109B8, 0x109BB), // meroitic unassigned 1
				UnicodeRange(0x109D0, 0x109D1), // meroitic unassigned 2
				UnicodeRange(0x10A04, 0x10A04), // kharoshthi unassigned 1
				UnicodeRange(0x10A07, 0x10A0B), // kharoshthi unassigned 2
				UnicodeRange(0x10A14, 0x10A14), // kharoshthi unassigned 3
				UnicodeRange(0x10A18, 0x10A18), // kharoshthi unassigned 4
				UnicodeRange(0x10A34, 0x10A37), // kharoshthi unassigned 5
				UnicodeRange(0x10A3B, 0x10A3E), // kharoshthi unassigned 6
				UnicodeRange(0x10A48, 0x10A4F), // kharoshthi unassigned 7
				UnicodeRange(0x10A59, 0x10A5F), // kharoshthi unassigned 8
				UnicodeRange(0x10AA0, 0x10ABF), // old unassigned 1
				UnicodeRange(0x10AE7, 0x10AEA), // manichaean unassigned 1
				UnicodeRange(0x10AF7, 0x10AFF), // manichaean unassigned 2
				UnicodeRange(0x10B36, 0x10B38), // avestan unassigned 1
				UnicodeRange(0x10B56, 0x10B57), // inscriptional unassigned 1
				UnicodeRange(0x10B73, 0x10B77), // inscriptional unassigned 2
				UnicodeRange(0x10B92, 0x10B98), // psalter unassigned 1
				UnicodeRange(0x10B9D, 0x10BA8), // psalter unassigned 2
				UnicodeRange(0x10BB0, 0x10BFF), // psalter unassigned 3
				UnicodeRange(0x10C49, 0x10C7F), // old unassigned 1
				UnicodeRange(0x10CB3, 0x10CBF), // old unassigned 2
				UnicodeRange(0x10CF3, 0x10CF9), // old unassigned 3
				UnicodeRange(0x10D00, 0x10E5F), // old unassigned 4
				UnicodeRange(0x10E7F, 0x10FFF), // rumi unassigned 1
				UnicodeRange(0x1104E, 0x11051), // brahmi unassigned 1
				UnicodeRange(0x11070, 0x1107E), // brahmi unassigned 2
				UnicodeRange(0x110C2, 0x110CF), // kaithi unassigned 1
				UnicodeRange(0x110E9, 0x110EF), // sora unassigned 1
				UnicodeRange(0x110FA, 0x110FF), // sora unassigned 2
				UnicodeRange(0x11135, 0x11135), // chakma unassigned 1
				UnicodeRange(0x11144, 0x1114F), // chakma unassigned 2
				UnicodeRange(0x11177, 0x1117F), // mahajani unassigned 1
				UnicodeRange(0x111CE, 0x111CF), // sharada unassigned 1
				UnicodeRange(0x111E0, 0x111E0), // sharada unassigned 2
				UnicodeRange(0x111F5, 0x111FF), // sinhala unassigned 1
				UnicodeRange(0x11212, 0x11212), // khojki unassigned 1
				UnicodeRange(0x1123F, 0x1127F), // khojki unassigned 2
				UnicodeRange(0x11287, 0x11287), // multani unassigned 1
				UnicodeRange(0x11289, 0x11289), // multani unassigned 2
				UnicodeRange(0x1128E, 0x1128E), // multani unassigned 3
				UnicodeRange(0x1129E, 0x1129E), // multani unassigned 4
				UnicodeRange(0x112AA, 0x112AF), // multani unassigned 5
				UnicodeRange(0x112EB, 0x112EF), // khudawadi unassigned 1
				UnicodeRange(0x112FA, 0x112FF), // khudawadi unassigned 2
				UnicodeRange(0x11304, 0x11304), // grantha unassigned 1
				UnicodeRange(0x1130D, 0x1130E), // grantha unassigned 2
				UnicodeRange(0x11311, 0x11312), // grantha unassigned 3
				UnicodeRange(0x11329, 0x11329), // grantha unassigned 4
				UnicodeRange(0x11331, 0x11331), // grantha unassigned 5
				UnicodeRange(0x11334, 0x11334), // grantha unassigned 6
				UnicodeRange(0x1133A, 0x1133B), // grantha unassigned 7
				UnicodeRange(0x11345, 0x11346), // grantha unassigned 8
				UnicodeRange(0x11349, 0x1134A), // grantha unassigned 9
				UnicodeRange(0x1134E, 0x1134F), // grantha unassigned 10
				UnicodeRange(0x11351, 0x11356), // grantha unassigned 11
				UnicodeRange(0x11358, 0x1135C), // grantha unassigned 12
				UnicodeRange(0x11364, 0x11365), // grantha unassigned 13
				UnicodeRange(0x1136D, 0x1136F), // combining unassigned 1
				UnicodeRange(0x11375, 0x113FF), // combining unassigned 2
				UnicodeRange(0x1145A, 0x1145A), // newa unassigned 1
				UnicodeRange(0x1145C, 0x1145C), // newa unassigned 2
				UnicodeRange(0x1145E, 0x1147F), // newa unassigned 3
				UnicodeRange(0x114C8, 0x114CF), // tirhuta unassigned 1
				UnicodeRange(0x114DA, 0x1157F), // tirhuta unassigned 2
				UnicodeRange(0x115B6, 0x115B7), // siddham unassigned 1
				UnicodeRange(0x115DE, 0x115FF), // siddham unassigned 2
				UnicodeRange(0x11645, 0x1164F), // modi unassigned 1
				UnicodeRange(0x1165A, 0x1165F), // modi unassigned 2
				UnicodeRange(0x1166D, 0x1167F), // mongolian unassigned 1
				UnicodeRange(0x116B8, 0x116BF), // takri unassigned 1
				UnicodeRange(0x116CA, 0x116FF), // takri unassigned 2
				UnicodeRange(0x1171A, 0x1171C), // ahom unassigned 1
				UnicodeRange(0x1172C, 0x1172F), // ahom unassigned 2
				UnicodeRange(0x11740, 0x1189F), // ahom unassigned 3
				UnicodeRange(0x118F3, 0x118FE), // warang unassigned 1
				UnicodeRange(0x11900, 0x119FF), // warang unassigned 2
				UnicodeRange(0x11A48, 0x11A4F), // zanabazar unassigned 1
				UnicodeRange(0x11A84, 0x11A85), // soyombo unassigned 1
				UnicodeRange(0x11A9D, 0x11A9D), // soyombo unassigned 2
				UnicodeRange(0x11AA3, 0x11ABF), // soyombo unassigned 3
				UnicodeRange(0x11AF9, 0x11BFF), // pau unassigned 1
				UnicodeRange(0x11C09, 0x11C09), // bhaiksuki unassigned 1
				UnicodeRange(0x11C37, 0x11C37), // bhaiksuki unassigned 2
				UnicodeRange(0x11C46, 0x11C4F), // bhaiksuki unassigned 3
				UnicodeRange(0x11C6D, 0x11C6F), // bhaiksuki unassigned 4
				UnicodeRange(0x11C90, 0x11C91), // marchen unassigned 1
				UnicodeRange(0x11CA8, 0x11CA8), // marchen unassigned 2
				UnicodeRange(0x11CB7, 0x11CFF), // marchen unassigned 3
				UnicodeRange(0x11D07, 0x11D07), // masaram unassigned 1
				UnicodeRange(0x11D0A, 0x11D0A), // masaram unassigned 2
				UnicodeRange(0x11D37, 0x11D39), // masaram unassigned 3
				UnicodeRange(0x11D3B, 0x11D3B), // masaram unassigned 4
				UnicodeRange(0x11D3E, 0x11D3E), // masaram unassigned 5
				UnicodeRange(0x11D48, 0x11D4F), // masaram unassigned 6
				UnicodeRange(0x11D5A, 0x11FFF), // masaram unassigned 7
				UnicodeRange(0x1239A, 0x123FF), // cuneiform unassigned 1
				UnicodeRange(0x1246F, 0x1246F), // cuneiform unassigned 2
				UnicodeRange(0x12475, 0x1247F), // cuneiform unassigned 3
				UnicodeRange(0x12544, 0x12FFF), // cuneiform unassigned 4
				UnicodeRange(0x1342F, 0x143FF), // egyptian unassigned 1
				UnicodeRange(0x14647, 0x167FF), // anatolian unassigned 1
				UnicodeRange(0x16A39, 0x16A3F), // bamum unassigned 1
				UnicodeRange(0x16A5F, 0x16A5F), // mro unassigned 1
				UnicodeRange(0x16A6A, 0x16A6D), // mro unassigned 2
				UnicodeRange(0x16A70, 0x16ACF), // mro unassigned 3
				UnicodeRange(0x16AEE, 0x16AEF), // bassa unassigned 1
				UnicodeRange(0x16AF6, 0x16AFF), // bassa unassigned 2
				UnicodeRange(0x16B46, 0x16B4F), // pahawh unassigned 1
				UnicodeRange(0x16B5A, 0x16B5A), // pahawh unassigned 2
				UnicodeRange(0x16B62, 0x16B62), // pahawh unassigned 3
				UnicodeRange(0x16B78, 0x16B7C), // pahawh unassigned 4
				UnicodeRange(0x16B90, 0x16EFF), // pahawh unassigned 5
				UnicodeRange(0x16F45, 0x16F4F), // miao unassigned 1
				UnicodeRange(0x16F7F, 0x16F8E), // miao unassigned 2
				UnicodeRange(0x16FA0, 0x16FDF), // miao unassigned 3
				UnicodeRange(0x16FE2, 0x16FFF), // nushu unassigned 1
				UnicodeRange(0x187ED, 0x187FF), // tangut unassigned 1
				UnicodeRange(0x18AF3, 0x1AFFF), // tangut unassigned 2
				UnicodeRange(0x1B11F, 0x1B16F), // hentaigana unassigned 1
				UnicodeRange(0x1B2FC, 0x1BBFF), // nushu unassigned 1
				UnicodeRange(0x1BC6B, 0x1BC6F), // duployan unassigned 1
				UnicodeRange(0x1BC7D, 0x1BC7F), // duployan unassigned 2
				UnicodeRange(0x1BC89, 0x1BC8F), // duployan unassigned 3
				UnicodeRange(0x1BC9A, 0x1BC9B), // duployan unassigned 4
				UnicodeRange(0x1BCA4, 0x1CFFF), // shorthand unassigned 1
				UnicodeRange(0x1D0F6, 0x1D0FF), // byzantine unassigned 1
				UnicodeRange(0x1D127, 0x1D128), // musical unassigned 1
				UnicodeRange(0x1D1E9, 0x1D1FF), // musical unassigned 2
				UnicodeRange(0x1D246, 0x1D2FF), // greek unassigned 1
				UnicodeRange(0x1D357, 0x1D35F), // tetragram unassigned 1
				UnicodeRange(0x1D372, 0x1D3FF), // counting unassigned 1
				UnicodeRange(0x1D455, 0x1D455), // mathematical unassigned 1
				UnicodeRange(0x1D49D, 0x1D49D), // mathematical unassigned 2
				UnicodeRange(0x1D4A0, 0x1D4A1), // mathematical unassigned 3
				UnicodeRange(0x1D4A3, 0x1D4A4), // mathematical unassigned 4
				UnicodeRange(0x1D4A7, 0x1D4A8), // mathematical unassigned 5
				UnicodeRange(0x1D4AD, 0x1D4AD), // mathematical unassigned 6
				UnicodeRange(0x1D4BA, 0x1D4BA), // mathematical unassigned 7
				UnicodeRange(0x1D4BC, 0x1D4BC), // mathematical unassigned 8
				UnicodeRange(0x1D4C4, 0x1D4C4), // mathematical unassigned 9
				UnicodeRange(0x1D506, 0x1D506), // mathematical unassigned 10
				UnicodeRange(0x1D50B, 0x1D50C), // mathematical unassigned 11
				UnicodeRange(0x1D515, 0x1D515), // mathematical unassigned 12
				UnicodeRange(0x1D51D, 0x1D51D), // mathematical unassigned 13
				UnicodeRange(0x1D53A, 0x1D53A), // mathematical unassigned 14
				UnicodeRange(0x1D53F, 0x1D53F), // mathematical unassigned 15
				UnicodeRange(0x1D545, 0x1D545), // mathematical unassigned 16
				UnicodeRange(0x1D547, 0x1D549), // mathematical unassigned 17
				UnicodeRange(0x1D551, 0x1D551), // mathematical unassigned 18
				UnicodeRange(0x1D6A6, 0x1D6A7), // mathematical unassigned 19
				UnicodeRange(0x1D7CC, 0x1D7CD), // mathematical unassigned 20
				UnicodeRange(0x1DA8C, 0x1DA9A), // signwriting unassigned 1
				UnicodeRange(0x1DAA0, 0x1DAA0), // signwriting unassigned 2
				UnicodeRange(0x1DAB0, 0x1DFFF), // signwriting unassigned 3
				UnicodeRange(0x1E007, 0x1E007), // combining unassigned 1
				UnicodeRange(0x1E019, 0x1E01A), // combining unassigned 2
				UnicodeRange(0x1E022, 0x1E022), // combining unassigned 3
				UnicodeRange(0x1E025, 0x1E025), // combining unassigned 4
				UnicodeRange(0x1E02B, 0x1E7FF), // combining unassigned 5
				UnicodeRange(0x1E8C5, 0x1E8C6), // mende unassigned 1
				UnicodeRange(0x1E8D7, 0x1E8FF), // mende unassigned 2
				UnicodeRange(0x1E94B, 0x1E94F), // adlam unassigned 1
				UnicodeRange(0x1E95A, 0x1E95D), // adlam unassigned 2
				UnicodeRange(0x1E960, 0x1EDFF), // adlam unassigned 3
				UnicodeRange(0x1EE04, 0x1EE04), // arabic unassigned 1
				UnicodeRange(0x1EE20, 0x1EE20), // arabic unassigned 2
				UnicodeRange(0x1EE23, 0x1EE23), // arabic unassigned 3
				UnicodeRange(0x1EE25, 0x1EE26), // arabic unassigned 4
				UnicodeRange(0x1EE28, 0x1EE28), // arabic unassigned 5
				UnicodeRange(0x1EE33, 0x1EE33), // arabic unassigned 6
				UnicodeRange(0x1EE38, 0x1EE38), // arabic unassigned 7
				UnicodeRange(0x1EE3A, 0x1EE3A), // arabic unassigned 8
				UnicodeRange(0x1EE3C, 0x1EE41), // arabic unassigned 9
				UnicodeRange(0x1EE43, 0x1EE46), // arabic unassigned 10
				UnicodeRange(0x1EE48, 0x1EE48), // arabic unassigned 11
				UnicodeRange(0x1EE4A, 0x1EE4A), // arabic unassigned 12
				UnicodeRange(0x1EE4C, 0x1EE4C), // arabic unassigned 13
				UnicodeRange(0x1EE50, 0x1EE50), // arabic unassigned 14
				UnicodeRange(0x1EE53, 0x1EE53), // arabic unassigned 15
				UnicodeRange(0x1EE55, 0x1EE56), // arabic unassigned 16
				UnicodeRange(0x1EE58, 0x1EE58), // arabic unassigned 17
				UnicodeRange(0x1EE5A, 0x1EE5A), // arabic unassigned 18
				UnicodeRange(0x1EE5C, 0x1EE5C), // arabic unassigned 19
				UnicodeRange(0x1EE5E, 0x1EE5E), // arabic unassigned 20
				UnicodeRange(0x1EE60, 0x1EE60), // arabic unassigned 21
				UnicodeRange(0x1EE63, 0x1EE63), // arabic unassigned 22
				UnicodeRange(0x1EE65, 0x1EE66), // arabic unassigned 23
				UnicodeRange(0x1EE6B, 0x1EE6B), // arabic unassigned 24
				UnicodeRange(0x1EE73, 0x1EE73), // arabic unassigned 25
				UnicodeRange(0x1EE78, 0x1EE78), // arabic unassigned 26
				UnicodeRange(0x1EE7D, 0x1EE7D), // arabic unassigned 27
				UnicodeRange(0x1EE7F, 0x1EE7F), // arabic unassigned 28
				UnicodeRange(0x1EE8A, 0x1EE8A), // arabic unassigned 29
				UnicodeRange(0x1EE9C, 0x1EEA0), // arabic unassigned 30
				UnicodeRange(0x1EEA4, 0x1EEA4), // arabic unassigned 31
				UnicodeRange(0x1EEAA, 0x1EEAA), // arabic unassigned 32
				UnicodeRange(0x1EEBC, 0x1EEEF), // arabic unassigned 33
				UnicodeRange(0x1EEF2, 0x1EFFF), // arabic unassigned 34
				UnicodeRange(0x1F02C, 0x1F02F), // mahjong unassigned 1
				UnicodeRange(0x1F094, 0x1F09F), // domino unassigned 1
				UnicodeRange(0x1F0AF, 0x1F0B0), // playing unassigned 1
				UnicodeRange(0x1F0C0, 0x1F0C0), // playing unassigned 2
				UnicodeRange(0x1F0D0, 0x1F0D0), // playing unassigned 3
				UnicodeRange(0x1F0F6, 0x1F0FF), // playing unassigned 4
				UnicodeRange(0x1F10D, 0x1F10F), // dingbat unassigned 1
				UnicodeRange(0x1F12F, 0x1F12F), // circled unassigned 1
				UnicodeRange(0x1F16C, 0x1F16F), // raised unassigned 1
				UnicodeRange(0x1F1AD, 0x1F1E5), // squared unassigned 1
				UnicodeRange(0x1F203, 0x1F20F), // squared unassigned 2
				UnicodeRange(0x1F23C, 0x1F23F), // squared unassigned 3
				UnicodeRange(0x1F249, 0x1F24F), // tortoise unassigned 1
				UnicodeRange(0x1F252, 0x1F25F), // circled unassigned 1
				UnicodeRange(0x1F266, 0x1F2FF), // rounded unassigned 1
				UnicodeRange(0x1F6D5, 0x1F6DF), // pagoda unassigned 1
				UnicodeRange(0x1F6ED, 0x1F6EF), // airplane unassigned 1
				UnicodeRange(0x1F6F9, 0x1F6FF), // flying unassigned 1
				UnicodeRange(0x1F774, 0x1F77F), // alchemical unassigned 1
				UnicodeRange(0x1F7D5, 0x1F7FF), // heavy unassigned 1
				UnicodeRange(0x1F80C, 0x1F80F), // downwards unassigned 1
				UnicodeRange(0x1F848, 0x1F84F), // downwards unassigned 2
				UnicodeRange(0x1F85A, 0x1F85F), // up unassigned 1
				UnicodeRange(0x1F888, 0x1F88F), // wide-headed unassigned 1
				UnicodeRange(0x1F8AE, 0x1F8FF), // white unassigned 1
				UnicodeRange(0x1F90C, 0x1F90F), // downward unassigned 1
				UnicodeRange(0x1F93F, 0x1F93F), // handball unassigned 1
				UnicodeRange(0x1F94D, 0x1F94F), // curling unassigned 1
				UnicodeRange(0x1F96C, 0x1F97F), // canned unassigned 1
				UnicodeRange(0x1F998, 0x1F9BF), // cricket unassigned 1
				UnicodeRange(0x1F9C1, 0x1F9CF), // cheese unassigned 1
				UnicodeRange(0x1F9E7, 0x1FFFF), // socks unassigned 1
				UnicodeRange(0x2A6D7, 0x2A6FF), // cjk unassigned 1
				UnicodeRange(0x2B735, 0x2B73F), // cjk unassigned 2
				UnicodeRange(0x2B81E, 0x2B81F), // cjk unassigned 3
				UnicodeRange(0x2CEA2, 0x2CEAF), // cjk unassigned 4
				UnicodeRange(0x2EBE1, 0x2F7FF), // cjk unassigned 5
				UnicodeRange(0x2FA1E, 0xE0000), // cjk unassigned 6
				UnicodeRange(0xE0002, 0xE001F), // language unassigned 1
				UnicodeRange(0xE0080, 0xE00FF), // cancel unassigned 1
				UnicodeRange(0xE01F0, 0xEFFFF), // variation unassigned 1
				UnicodeRange(0xFFFFE, 0xFFFFF), // unassigned 1
				UnicodeRange(0x10FFFE, 0x10FFFF), // unassigned 2
			)
		}
	},
	UTF_8_PRINTABLE() {
		override val ranges: Array<out UnicodeRange> by lazy {
			arrayOf(
				//UnicodeRange(0x0000, 0x001F), //control
				UnicodeRange(0x0020, 0x007E),
				//UnicodeRange(0x007F, 0x009F), //control
				UnicodeRange(0x00A0, 0x0377),
				//UnicodeRange(0x0378, 0x0379), //greek unassigned 1
				UnicodeRange(0x037A, 0x037F),
				//UnicodeRange(0x0380, 0x0383), //greek unassigned 2
				UnicodeRange(0x0384, 0x038A),
				//UnicodeRange(0x038B, 0x038B), //greek unassigned 3
				UnicodeRange(0x038C, 0x038C),
				//UnicodeRange(0x038D, 0x038D), //greek unassigned 4
				UnicodeRange(0x038E, 0x03A1),
				//UnicodeRange(0x03A2, 0x03A2), //greek unassigned 5
				UnicodeRange(0x03A3, 0x052F),
				//UnicodeRange(0x0530, 0x0530), //cyrillic unassigned 1
				UnicodeRange(0x0531, 0x0556),
				//UnicodeRange(0x0557, 0x0558), //armenian unassigned 1
				UnicodeRange(0x0559, 0x055F),
				//UnicodeRange(0x0560, 0x0560), //armenian unassigned 2
				UnicodeRange(0x0561, 0x0587),
				//UnicodeRange(0x0588, 0x0588), //armenian unassigned 3
				UnicodeRange(0x0589, 0x058A),
				//UnicodeRange(0x058B, 0x058C), //armenian unassigned 4
				UnicodeRange(0x058D, 0x058F),
				//UnicodeRange(0x0590, 0x0590), //armenian unassigned 5
				UnicodeRange(0x0591, 0x05C7),
				//UnicodeRange(0x05C8, 0x05CF), //hebrew unassigned 1
				UnicodeRange(0x05D0, 0x05EA),
				//UnicodeRange(0x05EB, 0x05EF), //hebrew unassigned 2
				UnicodeRange(0x05F0, 0x05F4),
				//UnicodeRange(0x05F5, 0x05FF), //hebrew unassigned 3
				UnicodeRange(0x0600, 0x061C),
				//UnicodeRange(0x061D, 0x061D), //arabic unassigned 1
				UnicodeRange(0x061E, 0x070D),
				//UnicodeRange(0x070E, 0x070E), //syriac unassigned 1
				UnicodeRange(0x070F, 0x074A),
				//UnicodeRange(0x074B, 0x074C), //syriac unassigned 2
				UnicodeRange(0x074D, 0x07B1),
				//UnicodeRange(0x07B2, 0x07BF), //thaana unassigned 1
				UnicodeRange(0x07C0, 0x07FA),
				//UnicodeRange(0x07FB, 0x07FF), //nko unassigned 1
				UnicodeRange(0x0800, 0x082D),
				//UnicodeRange(0x082E, 0x082F), //samaritan unassigned 1
				UnicodeRange(0x0830, 0x083E),
				//UnicodeRange(0x083F, 0x083F), //samaritan unassigned 2
				UnicodeRange(0x0840, 0x085B),
				//UnicodeRange(0x085C, 0x085D), //mandaic unassigned 1
				UnicodeRange(0x085E, 0x085E),
				//UnicodeRange(0x085F, 0x085F), //mandaic unassigned 2
				UnicodeRange(0x0860, 0x086A),
				//UnicodeRange(0x086B, 0x089F), //syriac unassigned 1
				UnicodeRange(0x08A0, 0x08B4),
				//UnicodeRange(0x08B5, 0x08B5), //arabic unassigned 1
				UnicodeRange(0x08B6, 0x08BD),
				//UnicodeRange(0x08BE, 0x08D3), //arabic unassigned 2
				UnicodeRange(0x08D4, 0x0983),
				//UnicodeRange(0x0984, 0x0984), //bengali unassigned 1
				UnicodeRange(0x0985, 0x098C),
				//UnicodeRange(0x098D, 0x098E), //bengali unassigned 2
				UnicodeRange(0x098F, 0x0990),
				//UnicodeRange(0x0991, 0x0992), //bengali unassigned 3
				UnicodeRange(0x0993, 0x09A8),
				//UnicodeRange(0x09A9, 0x09A9), //bengali unassigned 4
				UnicodeRange(0x09AA, 0x09B0),
				//UnicodeRange(0x09B1, 0x09B1), //bengali unassigned 5
				UnicodeRange(0x09B2, 0x09B2),
				//UnicodeRange(0x09B3, 0x09B5), //bengali unassigned 6
				UnicodeRange(0x09B6, 0x09B9),
				//UnicodeRange(0x09BA, 0x09BB), //bengali unassigned 7
				UnicodeRange(0x09BC, 0x09C4),
				//UnicodeRange(0x09C5, 0x09C6), //bengali unassigned 8
				UnicodeRange(0x09C7, 0x09C8),
				//UnicodeRange(0x09C9, 0x09CA), //bengali unassigned 9
				UnicodeRange(0x09CB, 0x09CE),
				//UnicodeRange(0x09CF, 0x09D6), //bengali unassigned 10
				UnicodeRange(0x09D7, 0x09D7),
				//UnicodeRange(0x09D8, 0x09DB), //bengali unassigned 11
				UnicodeRange(0x09DC, 0x09DD),
				//UnicodeRange(0x09DE, 0x09DE), //bengali unassigned 12
				UnicodeRange(0x09DF, 0x09E3),
				//UnicodeRange(0x09E4, 0x09E5), //bengali unassigned 13
				UnicodeRange(0x09E6, 0x09FD),
				//UnicodeRange(0x09FE, 0x0A00), //bengali unassigned 14
				UnicodeRange(0x0A01, 0x0A03),
				//UnicodeRange(0x0A04, 0x0A04), //gurmukhi unassigned 1
				UnicodeRange(0x0A05, 0x0A0A),
				//UnicodeRange(0x0A0B, 0x0A0E), //gurmukhi unassigned 2
				UnicodeRange(0x0A0F, 0x0A10),
				//UnicodeRange(0x0A11, 0x0A12), //gurmukhi unassigned 3
				UnicodeRange(0x0A13, 0x0A28),
				//UnicodeRange(0x0A29, 0x0A29), //gurmukhi unassigned 4
				UnicodeRange(0x0A2A, 0x0A30),
				//UnicodeRange(0x0A31, 0x0A31), //gurmukhi unassigned 5
				UnicodeRange(0x0A32, 0x0A33),
				//UnicodeRange(0x0A34, 0x0A34), //gurmukhi unassigned 6
				UnicodeRange(0x0A35, 0x0A36),
				//UnicodeRange(0x0A37, 0x0A37), //gurmukhi unassigned 7
				UnicodeRange(0x0A38, 0x0A39),
				//UnicodeRange(0x0A3A, 0x0A3B), //gurmukhi unassigned 8
				UnicodeRange(0x0A3C, 0x0A3C),
				//UnicodeRange(0x0A3D, 0x0A3D), //gurmukhi unassigned 9
				UnicodeRange(0x0A3E, 0x0A42),
				//UnicodeRange(0x0A43, 0x0A46), //gurmukhi unassigned 10
				UnicodeRange(0x0A47, 0x0A48),
				//UnicodeRange(0x0A49, 0x0A4A), //gurmukhi unassigned 11
				UnicodeRange(0x0A4B, 0x0A4D),
				//UnicodeRange(0x0A4E, 0x0A50), //gurmukhi unassigned 12
				UnicodeRange(0x0A51, 0x0A51),
				//UnicodeRange(0x0A52, 0x0A58), //gurmukhi unassigned 13
				UnicodeRange(0x0A59, 0x0A5C),
				//UnicodeRange(0x0A5D, 0x0A5D), //gurmukhi unassigned 14
				UnicodeRange(0x0A5E, 0x0A5E),
				//UnicodeRange(0x0A5F, 0x0A65), //gurmukhi unassigned 15
				UnicodeRange(0x0A66, 0x0A75),
				//UnicodeRange(0x0A76, 0x0A80), //gurmukhi unassigned 16
				UnicodeRange(0x0A81, 0x0A83),
				//UnicodeRange(0x0A84, 0x0A84), //gujarati unassigned 1
				UnicodeRange(0x0A85, 0x0A8D),
				//UnicodeRange(0x0A8E, 0x0A8E), //gujarati unassigned 2
				UnicodeRange(0x0A8F, 0x0A91),
				//UnicodeRange(0x0A92, 0x0A92), //gujarati unassigned 3
				UnicodeRange(0x0A93, 0x0AA8),
				//UnicodeRange(0x0AA9, 0x0AA9), //gujarati unassigned 4
				UnicodeRange(0x0AAA, 0x0AB0),
				//UnicodeRange(0x0AB1, 0x0AB1), //gujarati unassigned 5
				UnicodeRange(0x0AB2, 0x0AB3),
				//UnicodeRange(0x0AB4, 0x0AB4), //gujarati unassigned 6
				UnicodeRange(0x0AB5, 0x0AB9),
				//UnicodeRange(0x0ABA, 0x0ABB), //gujarati unassigned 7
				UnicodeRange(0x0ABC, 0x0AC5),
				//UnicodeRange(0x0AC6, 0x0AC6), //gujarati unassigned 8
				UnicodeRange(0x0AC7, 0x0AC9),
				//UnicodeRange(0x0ACA, 0x0ACA), //gujarati unassigned 9
				UnicodeRange(0x0ACB, 0x0ACD),
				//UnicodeRange(0x0ACE, 0x0ACF), //gujarati unassigned 10
				UnicodeRange(0x0AD0, 0x0AD0),
				//UnicodeRange(0x0AD1, 0x0ADF), //gujarati unassigned 11
				UnicodeRange(0x0AE0, 0x0AE3),
				//UnicodeRange(0x0AE4, 0x0AE5), //gujarati unassigned 12
				UnicodeRange(0x0AE6, 0x0AF1),
				//UnicodeRange(0x0AF2, 0x0AF8), //gujarati unassigned 13
				UnicodeRange(0x0AF9, 0x0AFF),
				//UnicodeRange(0x0B00, 0x0B00), //gujarati unassigned 14
				UnicodeRange(0x0B01, 0x0B03),
				//UnicodeRange(0x0B04, 0x0B04), //oriya unassigned 1
				UnicodeRange(0x0B05, 0x0B0C),
				//UnicodeRange(0x0B0D, 0x0B0E), //oriya unassigned 2
				UnicodeRange(0x0B0F, 0x0B10),
				//UnicodeRange(0x0B11, 0x0B12), //oriya unassigned 3
				UnicodeRange(0x0B13, 0x0B28),
				//UnicodeRange(0x0B29, 0x0B29), //oriya unassigned 4
				UnicodeRange(0x0B2A, 0x0B30),
				//UnicodeRange(0x0B31, 0x0B31), //oriya unassigned 5
				UnicodeRange(0x0B32, 0x0B33),
				//UnicodeRange(0x0B34, 0x0B34), //oriya unassigned 6
				UnicodeRange(0x0B35, 0x0B39),
				//UnicodeRange(0x0B3A, 0x0B3B), //oriya unassigned 7
				UnicodeRange(0x0B3C, 0x0B44),
				//UnicodeRange(0x0B45, 0x0B46), //oriya unassigned 8
				UnicodeRange(0x0B47, 0x0B48),
				//UnicodeRange(0x0B49, 0x0B4A), //oriya unassigned 9
				UnicodeRange(0x0B4B, 0x0B4D),
				//UnicodeRange(0x0B4E, 0x0B55), //oriya unassigned 10
				UnicodeRange(0x0B56, 0x0B57),
				//UnicodeRange(0x0B58, 0x0B5B), //oriya unassigned 11
				UnicodeRange(0x0B5C, 0x0B5D),
				//UnicodeRange(0x0B5E, 0x0B5E), //oriya unassigned 12
				UnicodeRange(0x0B5F, 0x0B63),
				//UnicodeRange(0x0B64, 0x0B65), //oriya unassigned 13
				UnicodeRange(0x0B66, 0x0B77),
				//UnicodeRange(0x0B78, 0x0B81), //oriya unassigned 14
				UnicodeRange(0x0B82, 0x0B83),
				//UnicodeRange(0x0B84, 0x0B84), //tamil unassigned 1
				UnicodeRange(0x0B85, 0x0B8A),
				//UnicodeRange(0x0B8B, 0x0B8D), //tamil unassigned 2
				UnicodeRange(0x0B8E, 0x0B90),
				//UnicodeRange(0x0B91, 0x0B91), //tamil unassigned 3
				UnicodeRange(0x0B92, 0x0B95),
				//UnicodeRange(0x0B96, 0x0B98), //tamil unassigned 4
				UnicodeRange(0x0B99, 0x0B9A),
				//UnicodeRange(0x0B9B, 0x0B9B), //tamil unassigned 5
				UnicodeRange(0x0B9C, 0x0B9C),
				//UnicodeRange(0x0B9D, 0x0B9D), //tamil unassigned 6
				UnicodeRange(0x0B9E, 0x0B9F),
				//UnicodeRange(0x0BA0, 0x0BA2), //tamil unassigned 7
				UnicodeRange(0x0BA3, 0x0BA4),
				//UnicodeRange(0x0BA5, 0x0BA7), //tamil unassigned 8
				UnicodeRange(0x0BA8, 0x0BAA),
				//UnicodeRange(0x0BAB, 0x0BAD), //tamil unassigned 9
				UnicodeRange(0x0BAE, 0x0BB9),
				//UnicodeRange(0x0BBA, 0x0BBD), //tamil unassigned 10
				UnicodeRange(0x0BBE, 0x0BC2),
				//UnicodeRange(0x0BC3, 0x0BC5), //tamil unassigned 11
				UnicodeRange(0x0BC6, 0x0BC8),
				//UnicodeRange(0x0BC9, 0x0BC9), //tamil unassigned 12
				UnicodeRange(0x0BCA, 0x0BCD),
				//UnicodeRange(0x0BCE, 0x0BCF), //tamil unassigned 13
				UnicodeRange(0x0BD0, 0x0BD0),
				//UnicodeRange(0x0BD1, 0x0BD6), //tamil unassigned 14
				UnicodeRange(0x0BD7, 0x0BD7),
				//UnicodeRange(0x0BD8, 0x0BE5), //tamil unassigned 15
				UnicodeRange(0x0BE6, 0x0BFA),
				//UnicodeRange(0x0BFB, 0x0BFF), //tamil unassigned 16
				UnicodeRange(0x0C00, 0x0C03),
				//UnicodeRange(0x0C04, 0x0C04), //telugu unassigned 1
				UnicodeRange(0x0C05, 0x0C0C),
				//UnicodeRange(0x0C0D, 0x0C0D), //telugu unassigned 2
				UnicodeRange(0x0C0E, 0x0C10),
				//UnicodeRange(0x0C11, 0x0C11), //telugu unassigned 3
				UnicodeRange(0x0C12, 0x0C28),
				//UnicodeRange(0x0C29, 0x0C29), //telugu unassigned 4
				UnicodeRange(0x0C2A, 0x0C39),
				//UnicodeRange(0x0C3A, 0x0C3C), //telugu unassigned 5
				UnicodeRange(0x0C3D, 0x0C44),
				//UnicodeRange(0x0C45, 0x0C45), //telugu unassigned 6
				UnicodeRange(0x0C46, 0x0C48),
				//UnicodeRange(0x0C49, 0x0C49), //telugu unassigned 7
				UnicodeRange(0x0C4A, 0x0C4D),
				//UnicodeRange(0x0C4E, 0x0C54), //telugu unassigned 8
				UnicodeRange(0x0C55, 0x0C56),
				//UnicodeRange(0x0C57, 0x0C57), //telugu unassigned 9
				UnicodeRange(0x0C58, 0x0C5A),
				//UnicodeRange(0x0C5B, 0x0C5F), //telugu unassigned 10
				UnicodeRange(0x0C60, 0x0C63),
				//UnicodeRange(0x0C64, 0x0C65), //telugu unassigned 11
				UnicodeRange(0x0C66, 0x0C6F),
				//UnicodeRange(0x0C70, 0x0C77), //telugu unassigned 12
				UnicodeRange(0x0C78, 0x0C83),
				//UnicodeRange(0x0C84, 0x0C84), //kannada unassigned 1
				UnicodeRange(0x0C85, 0x0C8C),
				//UnicodeRange(0x0C8D, 0x0C8D), //kannada unassigned 2
				UnicodeRange(0x0C8E, 0x0C90),
				//UnicodeRange(0x0C91, 0x0C91), //kannada unassigned 3
				UnicodeRange(0x0C92, 0x0CA8),
				//UnicodeRange(0x0CA9, 0x0CA9), //kannada unassigned 4
				UnicodeRange(0x0CAA, 0x0CB3),
				//UnicodeRange(0x0CB4, 0x0CB4), //kannada unassigned 5
				UnicodeRange(0x0CB5, 0x0CB9),
				//UnicodeRange(0x0CBA, 0x0CBB), //kannada unassigned 6
				UnicodeRange(0x0CBC, 0x0CC4),
				//UnicodeRange(0x0CC5, 0x0CC5), //kannada unassigned 7
				UnicodeRange(0x0CC6, 0x0CC8),
				//UnicodeRange(0x0CC9, 0x0CC9), //kannada unassigned 8
				UnicodeRange(0x0CCA, 0x0CCD),
				//UnicodeRange(0x0CCE, 0x0CD4), //kannada unassigned 9
				UnicodeRange(0x0CD5, 0x0CD6),
				//UnicodeRange(0x0CD7, 0x0CDD), //kannada unassigned 10
				UnicodeRange(0x0CDE, 0x0CDE),
				//UnicodeRange(0x0CDF, 0x0CDF), //kannada unassigned 11
				UnicodeRange(0x0CE0, 0x0CE3),
				//UnicodeRange(0x0CE4, 0x0CE5), //kannada unassigned 12
				UnicodeRange(0x0CE6, 0x0CEF),
				//UnicodeRange(0x0CF0, 0x0CF0), //kannada unassigned 13
				UnicodeRange(0x0CF1, 0x0CF2),
				//UnicodeRange(0x0CF3, 0x0CFF), //kannada unassigned 14
				UnicodeRange(0x0D00, 0x0D03),
				//UnicodeRange(0x0D04, 0x0D04), //malayalam unassigned 1
				UnicodeRange(0x0D05, 0x0D0C),
				//UnicodeRange(0x0D0D, 0x0D0D), //malayalam unassigned 2
				UnicodeRange(0x0D0E, 0x0D10),
				//UnicodeRange(0x0D11, 0x0D11), //malayalam unassigned 3
				UnicodeRange(0x0D12, 0x0D44),
				//UnicodeRange(0x0D45, 0x0D45), //malayalam unassigned 4
				UnicodeRange(0x0D46, 0x0D48),
				//UnicodeRange(0x0D49, 0x0D49), //malayalam unassigned 5
				UnicodeRange(0x0D4A, 0x0D4F),
				//UnicodeRange(0x0D50, 0x0D53), //malayalam unassigned 6
				UnicodeRange(0x0D54, 0x0D63),
				//UnicodeRange(0x0D64, 0x0D65), //malayalam unassigned 7
				UnicodeRange(0x0D66, 0x0D7F),
				//UnicodeRange(0x0D80, 0x0D81), //malayalam unassigned 8
				UnicodeRange(0x0D82, 0x0D83),
				//UnicodeRange(0x0D84, 0x0D84), //sinhala unassigned 1
				UnicodeRange(0x0D85, 0x0D96),
				//UnicodeRange(0x0D97, 0x0D99), //sinhala unassigned 2
				UnicodeRange(0x0D9A, 0x0DB1),
				//UnicodeRange(0x0DB2, 0x0DB2), //sinhala unassigned 3
				UnicodeRange(0x0DB3, 0x0DBB),
				//UnicodeRange(0x0DBC, 0x0DBC), //sinhala unassigned 4
				UnicodeRange(0x0DBD, 0x0DBD),
				//UnicodeRange(0x0DBE, 0x0DBF), //sinhala unassigned 5
				UnicodeRange(0x0DC0, 0x0DC6),
				//UnicodeRange(0x0DC7, 0x0DC9), //sinhala unassigned 6
				UnicodeRange(0x0DCA, 0x0DCA),
				//UnicodeRange(0x0DCB, 0x0DCE), //sinhala unassigned 7
				UnicodeRange(0x0DCF, 0x0DD4),
				//UnicodeRange(0x0DD5, 0x0DD5), //sinhala unassigned 8
				UnicodeRange(0x0DD6, 0x0DD6),
				//UnicodeRange(0x0DD7, 0x0DD7), //sinhala unassigned 9
				UnicodeRange(0x0DD8, 0x0DDF),
				//UnicodeRange(0x0DE0, 0x0DE5), //sinhala unassigned 10
				UnicodeRange(0x0DE6, 0x0DEF),
				//UnicodeRange(0x0DF0, 0x0DF1), //sinhala unassigned 11
				UnicodeRange(0x0DF2, 0x0DF4),
				//UnicodeRange(0x0DF5, 0x0E00), //sinhala unassigned 12
				UnicodeRange(0x0E01, 0x0E3A),
				//UnicodeRange(0x0E3B, 0x0E3E), //thai unassigned 1
				UnicodeRange(0x0E3F, 0x0E5B),
				//UnicodeRange(0x0E5C, 0x0E80), //thai unassigned 2
				UnicodeRange(0x0E81, 0x0E82),
				//UnicodeRange(0x0E83, 0x0E83), //lao unassigned 1
				UnicodeRange(0x0E84, 0x0E84),
				//UnicodeRange(0x0E85, 0x0E86), //lao unassigned 2
				UnicodeRange(0x0E87, 0x0E88),
				//UnicodeRange(0x0E89, 0x0E89), //lao unassigned 3
				UnicodeRange(0x0E8A, 0x0E8A),
				//UnicodeRange(0x0E8B, 0x0E8C), //lao unassigned 4
				UnicodeRange(0x0E8D, 0x0E8D),
				//UnicodeRange(0x0E8E, 0x0E93), //lao unassigned 5
				UnicodeRange(0x0E94, 0x0E97),
				//UnicodeRange(0x0E98, 0x0E98), //lao unassigned 6
				UnicodeRange(0x0E99, 0x0E9F),
				//UnicodeRange(0x0EA0, 0x0EA0), //lao unassigned 7
				UnicodeRange(0x0EA1, 0x0EA3),
				//UnicodeRange(0x0EA4, 0x0EA4), //lao unassigned 8
				UnicodeRange(0x0EA5, 0x0EA5),
				//UnicodeRange(0x0EA6, 0x0EA6), //lao unassigned 9
				UnicodeRange(0x0EA7, 0x0EA7),
				//UnicodeRange(0x0EA8, 0x0EA9), //lao unassigned 10
				UnicodeRange(0x0EAA, 0x0EAB),
				//UnicodeRange(0x0EAC, 0x0EAC), //lao unassigned 11
				UnicodeRange(0x0EAD, 0x0EB9),
				//UnicodeRange(0x0EBA, 0x0EBA), //lao unassigned 12
				UnicodeRange(0x0EBB, 0x0EBD),
				//UnicodeRange(0x0EBE, 0x0EBF), //lao unassigned 13
				UnicodeRange(0x0EC0, 0x0EC4),
				//UnicodeRange(0x0EC5, 0x0EC5), //lao unassigned 14
				UnicodeRange(0x0EC6, 0x0EC6),
				//UnicodeRange(0x0EC7, 0x0EC7), //lao unassigned 15
				UnicodeRange(0x0EC8, 0x0ECD),
				//UnicodeRange(0x0ECE, 0x0ECF), //lao unassigned 16
				UnicodeRange(0x0ED0, 0x0ED9),
				//UnicodeRange(0x0EDA, 0x0EDB), //lao unassigned 17
				UnicodeRange(0x0EDC, 0x0EDF),
				//UnicodeRange(0x0EE0, 0x0EFF), //lao unassigned 18
				UnicodeRange(0x0F00, 0x0F47),
				//UnicodeRange(0x0F48, 0x0F48), //tibetan unassigned 1
				UnicodeRange(0x0F49, 0x0F6C),
				//UnicodeRange(0x0F6D, 0x0F70), //tibetan unassigned 2
				UnicodeRange(0x0F71, 0x0F97),
				//UnicodeRange(0x0F98, 0x0F98), //tibetan unassigned 3
				UnicodeRange(0x0F99, 0x0FBC),
				//UnicodeRange(0x0FBD, 0x0FBD), //tibetan unassigned 4
				UnicodeRange(0x0FBE, 0x0FCC),
				//UnicodeRange(0x0FCD, 0x0FCD), //tibetan unassigned 5
				UnicodeRange(0x0FCE, 0x0FDA),
				//UnicodeRange(0x0FDB, 0x0FFF), //tibetan unassigned 6
				UnicodeRange(0x1000, 0x10C5),
				//UnicodeRange(0x10C6, 0x10C6), //georgian unassigned 1
				UnicodeRange(0x10C7, 0x10C7),
				//UnicodeRange(0x10C8, 0x10CC), //georgian unassigned 2
				UnicodeRange(0x10CD, 0x10CD),
				//UnicodeRange(0x10CE, 0x10CF), //georgian unassigned 3
				UnicodeRange(0x10D0, 0x1248),
				//UnicodeRange(0x1249, 0x1249), //ethiopic unassigned 1
				UnicodeRange(0x124A, 0x124D),
				//UnicodeRange(0x124E, 0x124F), //ethiopic unassigned 2
				UnicodeRange(0x1250, 0x1256),
				//UnicodeRange(0x1257, 0x1257), //ethiopic unassigned 3
				UnicodeRange(0x1258, 0x1258),
				//UnicodeRange(0x1259, 0x1259), //ethiopic unassigned 4
				UnicodeRange(0x125A, 0x125D),
				//UnicodeRange(0x125E, 0x125F), //ethiopic unassigned 5
				UnicodeRange(0x1260, 0x1288),
				//UnicodeRange(0x1289, 0x1289), //ethiopic unassigned 6
				UnicodeRange(0x128A, 0x128D),
				//UnicodeRange(0x128E, 0x128F), //ethiopic unassigned 7
				UnicodeRange(0x1290, 0x12B0),
				//UnicodeRange(0x12B1, 0x12B1), //ethiopic unassigned 8
				UnicodeRange(0x12B2, 0x12B5),
				//UnicodeRange(0x12B6, 0x12B7), //ethiopic unassigned 9
				UnicodeRange(0x12B8, 0x12BE),
				//UnicodeRange(0x12BF, 0x12BF), //ethiopic unassigned 10
				UnicodeRange(0x12C0, 0x12C0),
				//UnicodeRange(0x12C1, 0x12C1), //ethiopic unassigned 11
				UnicodeRange(0x12C2, 0x12C5),
				//UnicodeRange(0x12C6, 0x12C7), //ethiopic unassigned 12
				UnicodeRange(0x12C8, 0x12D6),
				//UnicodeRange(0x12D7, 0x12D7), //ethiopic unassigned 13
				UnicodeRange(0x12D8, 0x1310),
				//UnicodeRange(0x1311, 0x1311), //ethiopic unassigned 14
				UnicodeRange(0x1312, 0x1315),
				//UnicodeRange(0x1316, 0x1317), //ethiopic unassigned 15
				UnicodeRange(0x1318, 0x135A),
				//UnicodeRange(0x135B, 0x135C), //ethiopic unassigned 16
				UnicodeRange(0x135D, 0x137C),
				//UnicodeRange(0x137D, 0x137F), //ethiopic unassigned 17
				UnicodeRange(0x1380, 0x1399),
				//UnicodeRange(0x139A, 0x139F), //ethiopic unassigned 18
				UnicodeRange(0x13A0, 0x13F5),
				//UnicodeRange(0x13F6, 0x13F7), //cherokee unassigned 1
				UnicodeRange(0x13F8, 0x13FD),
				//UnicodeRange(0x13FE, 0x13FF), //cherokee unassigned 2
				UnicodeRange(0x1400, 0x169C),
				//UnicodeRange(0x169D, 0x169F), //ogham unassigned 1
				UnicodeRange(0x16A0, 0x16F8),
				//UnicodeRange(0x16F9, 0x16FF), //runic unassigned 1
				UnicodeRange(0x1700, 0x170C),
				//UnicodeRange(0x170D, 0x170D), //tagalog unassigned 1
				UnicodeRange(0x170E, 0x1714),
				//UnicodeRange(0x1715, 0x171F), //tagalog unassigned 2
				UnicodeRange(0x1720, 0x1736),
				//UnicodeRange(0x1737, 0x173F), //philippine unassigned 1
				UnicodeRange(0x1740, 0x1753),
				//UnicodeRange(0x1754, 0x175F), //buhid unassigned 1
				UnicodeRange(0x1760, 0x176C),
				//UnicodeRange(0x176D, 0x176D), //tagbanwa unassigned 1
				UnicodeRange(0x176E, 0x1770),
				//UnicodeRange(0x1771, 0x1771), //tagbanwa unassigned 2
				UnicodeRange(0x1772, 0x1773),
				//UnicodeRange(0x1774, 0x177F), //tagbanwa unassigned 3
				UnicodeRange(0x1780, 0x17DD),
				//UnicodeRange(0x17DE, 0x17DF), //khmer unassigned 1
				UnicodeRange(0x17E0, 0x17E9),
				//UnicodeRange(0x17EA, 0x17EF), //khmer unassigned 2
				UnicodeRange(0x17F0, 0x17F9),
				//UnicodeRange(0x17FA, 0x17FF), //khmer unassigned 3
				UnicodeRange(0x1800, 0x180E),
				//UnicodeRange(0x180F, 0x180F), //mongolian unassigned 1
				UnicodeRange(0x1810, 0x1819),
				//UnicodeRange(0x181A, 0x181F), //mongolian unassigned 2
				UnicodeRange(0x1820, 0x1877),
				//UnicodeRange(0x1878, 0x187F), //mongolian unassigned 3
				UnicodeRange(0x1880, 0x18AA),
				//UnicodeRange(0x18AB, 0x18AF), //mongolian unassigned 4
				UnicodeRange(0x18B0, 0x18F5),
				//UnicodeRange(0x18F6, 0x18FF), //canadian unassigned 1
				UnicodeRange(0x1900, 0x191E),
				//UnicodeRange(0x191F, 0x191F), //limbu unassigned 1
				UnicodeRange(0x1920, 0x192B),
				//UnicodeRange(0x192C, 0x192F), //limbu unassigned 2
				UnicodeRange(0x1930, 0x193B),
				//UnicodeRange(0x193C, 0x193F), //limbu unassigned 3
				UnicodeRange(0x1940, 0x1940),
				//UnicodeRange(0x1941, 0x1943), //limbu unassigned 4
				UnicodeRange(0x1944, 0x196D),
				//UnicodeRange(0x196E, 0x196F), //tai unassigned 1
				UnicodeRange(0x1970, 0x1974),
				//UnicodeRange(0x1975, 0x197F), //tai unassigned 2
				UnicodeRange(0x1980, 0x19AB),
				//UnicodeRange(0x19AC, 0x19AF), //new unassigned 1
				UnicodeRange(0x19B0, 0x19C9),
				//UnicodeRange(0x19CA, 0x19CF), //new unassigned 2
				UnicodeRange(0x19D0, 0x19DA),
				//UnicodeRange(0x19DB, 0x19DD), //new unassigned 3
				UnicodeRange(0x19DE, 0x1A1B),
				//UnicodeRange(0x1A1C, 0x1A1D), //buginese unassigned 1
				UnicodeRange(0x1A1E, 0x1A5E),
				//UnicodeRange(0x1A5F, 0x1A5F), //tai unassigned 1
				UnicodeRange(0x1A60, 0x1A7C),
				//UnicodeRange(0x1A7D, 0x1A7E), //tai unassigned 2
				UnicodeRange(0x1A7F, 0x1A89),
				//UnicodeRange(0x1A8A, 0x1A8F), //tai unassigned 3
				UnicodeRange(0x1A90, 0x1A99),
				//UnicodeRange(0x1A9A, 0x1A9F), //tai unassigned 4
				UnicodeRange(0x1AA0, 0x1AAD),
				//UnicodeRange(0x1AAE, 0x1AAF), //tai unassigned 5
				UnicodeRange(0x1AB0, 0x1ABE),
				//UnicodeRange(0x1ABF, 0x1AFF), //combining unassigned 1
				UnicodeRange(0x1B00, 0x1B4B),
				//UnicodeRange(0x1B4C, 0x1B4F), //balinese unassigned 1
				UnicodeRange(0x1B50, 0x1B7C),
				//UnicodeRange(0x1B7D, 0x1B7F), //balinese unassigned 2
				UnicodeRange(0x1B80, 0x1BF3),
				//UnicodeRange(0x1BF4, 0x1BFB), //batak unassigned 1
				UnicodeRange(0x1BFC, 0x1C37),
				//UnicodeRange(0x1C38, 0x1C3A), //lepcha unassigned 1
				UnicodeRange(0x1C3B, 0x1C49),
				//UnicodeRange(0x1C4A, 0x1C4C), //lepcha unassigned 2
				UnicodeRange(0x1C4D, 0x1C88),
				//UnicodeRange(0x1C89, 0x1CBF), //cyrillic unassigned 1
				UnicodeRange(0x1CC0, 0x1CC7),
				//UnicodeRange(0x1CC8, 0x1CCF), //sundanese unassigned 1
				UnicodeRange(0x1CD0, 0x1CF9),
				//UnicodeRange(0x1CFA, 0x1CFF), //vedic unassigned 1
				UnicodeRange(0x1D00, 0x1DF9),
				//UnicodeRange(0x1DFA, 0x1DFA), //combining unassigned 1
				UnicodeRange(0x1DFB, 0x1F15),
				//UnicodeRange(0x1F16, 0x1F17), //greek unassigned 1
				UnicodeRange(0x1F18, 0x1F1D),
				//UnicodeRange(0x1F1E, 0x1F1F), //greek unassigned 2
				UnicodeRange(0x1F20, 0x1F45),
				//UnicodeRange(0x1F46, 0x1F47), //greek unassigned 3
				UnicodeRange(0x1F48, 0x1F4D),
				//UnicodeRange(0x1F4E, 0x1F4F), //greek unassigned 4
				UnicodeRange(0x1F50, 0x1F57),
				//UnicodeRange(0x1F58, 0x1F58), //greek unassigned 5
				UnicodeRange(0x1F59, 0x1F59),
				//UnicodeRange(0x1F5A, 0x1F5A), //greek unassigned 6
				UnicodeRange(0x1F5B, 0x1F5B),
				//UnicodeRange(0x1F5C, 0x1F5C), //greek unassigned 7
				UnicodeRange(0x1F5D, 0x1F5D),
				//UnicodeRange(0x1F5E, 0x1F5E), //greek unassigned 8
				UnicodeRange(0x1F5F, 0x1F7D),
				//UnicodeRange(0x1F7E, 0x1F7F), //greek unassigned 9
				UnicodeRange(0x1F80, 0x1FB4),
				//UnicodeRange(0x1FB5, 0x1FB5), //greek unassigned 10
				UnicodeRange(0x1FB6, 0x1FC4),
				//UnicodeRange(0x1FC5, 0x1FC5), //greek unassigned 11
				UnicodeRange(0x1FC6, 0x1FD3),
				//UnicodeRange(0x1FD4, 0x1FD5), //greek unassigned 12
				UnicodeRange(0x1FD6, 0x1FDB),
				//UnicodeRange(0x1FDC, 0x1FDC), //greek unassigned 13
				UnicodeRange(0x1FDD, 0x1FEF),
				//UnicodeRange(0x1FF0, 0x1FF1), //greek unassigned 14
				UnicodeRange(0x1FF2, 0x1FF4),
				//UnicodeRange(0x1FF5, 0x1FF5), //greek unassigned 15
				UnicodeRange(0x1FF6, 0x1FFE),
				//UnicodeRange(0x1FFF, 0x1FFF), //greek unassigned 16
				UnicodeRange(0x2000, 0x2064),
				//UnicodeRange(0x2065, 0x2065), //invisible unassigned 1
				UnicodeRange(0x2066, 0x2071),
				//UnicodeRange(0x2072, 0x2073), //superscript unassigned 1
				UnicodeRange(0x2074, 0x208E),
				//UnicodeRange(0x208F, 0x208F), //subscript unassigned 1
				UnicodeRange(0x2090, 0x209C),
				//UnicodeRange(0x209D, 0x209F), //latin unassigned 1
				UnicodeRange(0x20A0, 0x20BF),
				//UnicodeRange(0x20C0, 0x20CF), //bitcoin unassigned 1
				UnicodeRange(0x20D0, 0x20F0),
				//UnicodeRange(0x20F1, 0x20FF), //combining unassigned 1
				UnicodeRange(0x2100, 0x218B),
				//UnicodeRange(0x218C, 0x218F), //turned unassigned 1
				UnicodeRange(0x2190, 0x2426),
				//UnicodeRange(0x2427, 0x243F), //symbol unassigned 1
				UnicodeRange(0x2440, 0x244A),
				//UnicodeRange(0x244B, 0x245F), //ocr unassigned 1
				UnicodeRange(0x2460, 0x2B73),
				//UnicodeRange(0x2B74, 0x2B75), //downwards unassigned 1
				UnicodeRange(0x2B76, 0x2B95),
				//UnicodeRange(0x2B96, 0x2B97), //rightwards unassigned 1
				UnicodeRange(0x2B98, 0x2BB9),
				//UnicodeRange(0x2BBA, 0x2BBC), //up unassigned 1
				UnicodeRange(0x2BBD, 0x2BC8),
				//UnicodeRange(0x2BC9, 0x2BC9), //black unassigned 1
				UnicodeRange(0x2BCA, 0x2BD2),
				//UnicodeRange(0x2BD3, 0x2BEB), //group unassigned 1
				UnicodeRange(0x2BEC, 0x2BEF),
				//UnicodeRange(0x2BF0, 0x2BFF), //downwards unassigned 1
				UnicodeRange(0x2C00, 0x2C2E),
				//UnicodeRange(0x2C2F, 0x2C2F), //glagolitic unassigned 1
				UnicodeRange(0x2C30, 0x2C5E),
				//UnicodeRange(0x2C5F, 0x2C5F), //glagolitic unassigned 2
				UnicodeRange(0x2C60, 0x2CF3),
				//UnicodeRange(0x2CF4, 0x2CF8), //coptic unassigned 1
				UnicodeRange(0x2CF9, 0x2D25),
				//UnicodeRange(0x2D26, 0x2D26), //georgian unassigned 1
				UnicodeRange(0x2D27, 0x2D27),
				//UnicodeRange(0x2D28, 0x2D2C), //georgian unassigned 2
				UnicodeRange(0x2D2D, 0x2D2D),
				//UnicodeRange(0x2D2E, 0x2D2F), //georgian unassigned 3
				UnicodeRange(0x2D30, 0x2D67),
				//UnicodeRange(0x2D68, 0x2D6E), //tifinagh unassigned 1
				UnicodeRange(0x2D6F, 0x2D70),
				//UnicodeRange(0x2D71, 0x2D7E), //tifinagh unassigned 2
				UnicodeRange(0x2D7F, 0x2D96),
				//UnicodeRange(0x2D97, 0x2D9F), //ethiopic unassigned 1
				UnicodeRange(0x2DA0, 0x2DA6),
				//UnicodeRange(0x2DA7, 0x2DA7), //ethiopic unassigned 2
				UnicodeRange(0x2DA8, 0x2DAE),
				//UnicodeRange(0x2DAF, 0x2DAF), //ethiopic unassigned 3
				UnicodeRange(0x2DB0, 0x2DB6),
				//UnicodeRange(0x2DB7, 0x2DB7), //ethiopic unassigned 4
				UnicodeRange(0x2DB8, 0x2DBE),
				//UnicodeRange(0x2DBF, 0x2DBF), //ethiopic unassigned 5
				UnicodeRange(0x2DC0, 0x2DC6),
				//UnicodeRange(0x2DC7, 0x2DC7), //ethiopic unassigned 6
				UnicodeRange(0x2DC8, 0x2DCE),
				//UnicodeRange(0x2DCF, 0x2DCF), //ethiopic unassigned 7
				UnicodeRange(0x2DD0, 0x2DD6),
				//UnicodeRange(0x2DD7, 0x2DD7), //ethiopic unassigned 8
				UnicodeRange(0x2DD8, 0x2DDE),
				//UnicodeRange(0x2DDF, 0x2DDF), //ethiopic unassigned 9
				UnicodeRange(0x2DE0, 0x2E49),
				//UnicodeRange(0x2E4A, 0x2E7F), //double unassigned 1
				UnicodeRange(0x2E80, 0x2E99),
				//UnicodeRange(0x2E9A, 0x2E9A), //cjk unassigned 1
				UnicodeRange(0x2E9B, 0x2EF3),
				//UnicodeRange(0x2EF4, 0x2EFF), //cjk unassigned 2
				UnicodeRange(0x2F00, 0x2FD5),
				//UnicodeRange(0x2FD6, 0x2FEF), //kangxi unassigned 1
				UnicodeRange(0x2FF0, 0x2FFB),
				//UnicodeRange(0x2FFC, 0x2FFF), //ideographic unassigned 1
				UnicodeRange(0x3000, 0x303F),
				//UnicodeRange(0x3040, 0x3040), //ideographic unassigned 2
				UnicodeRange(0x3041, 0x3096),
				//UnicodeRange(0x3097, 0x3098), //hiragana unassigned 1
				UnicodeRange(0x3099, 0x30FF),
				//UnicodeRange(0x3100, 0x3104), //katakana unassigned 1
				UnicodeRange(0x3105, 0x312E),
				//UnicodeRange(0x312F, 0x3130), //bopomofo unassigned 1
				UnicodeRange(0x3131, 0x318E),
				//UnicodeRange(0x318F, 0x318F), //hangul unassigned 1
				UnicodeRange(0x3190, 0x31BA),
				//UnicodeRange(0x31BB, 0x31BF), //bopomofo unassigned 1
				UnicodeRange(0x31C0, 0x31E3),
				//UnicodeRange(0x31E4, 0x31EF), //cjk unassigned 1
				UnicodeRange(0x31F0, 0x321E),
				//UnicodeRange(0x321F, 0x321F), //parenthesized unassigned 1
				UnicodeRange(0x3220, 0x4DB5),
				//UnicodeRange(0x4DB6, 0x4DBF), //cjk unassigned 1
				UnicodeRange(0x4DC0, 0x9FEF),
				//UnicodeRange(0x9FF0, 0x9FFF), //cjk unassigned 2
				UnicodeRange(0xA000, 0xA48C),
				//UnicodeRange(0xA48D, 0xA48F), //yi unassigned 1
				UnicodeRange(0xA490, 0xA4C6),
				//UnicodeRange(0xA4C7, 0xA4CF), //yi unassigned 2
				UnicodeRange(0xA4D0, 0xA62B),
				//UnicodeRange(0xA62C, 0xA63F), //vai unassigned 1
				UnicodeRange(0xA640, 0xA6F7),
				//UnicodeRange(0xA6F8, 0xA6FF), //bamum unassigned 1
				UnicodeRange(0xA700, 0xA7AE),
				//UnicodeRange(0xA7AF, 0xA7AF), //latin unassigned 1
				UnicodeRange(0xA7B0, 0xA7B7),
				//UnicodeRange(0xA7B8, 0xA7F6), //latin unassigned 2
				UnicodeRange(0xA7F7, 0xA82B),
				//UnicodeRange(0xA82C, 0xA82F), //syloti unassigned 1
				UnicodeRange(0xA830, 0xA839),
				//UnicodeRange(0xA83A, 0xA83F), //north unassigned 1
				UnicodeRange(0xA840, 0xA877),
				//UnicodeRange(0xA878, 0xA87F), //phags-pa unassigned 1
				UnicodeRange(0xA880, 0xA8C5),
				//UnicodeRange(0xA8C6, 0xA8CD), //saurashtra unassigned 1
				UnicodeRange(0xA8CE, 0xA8D9),
				//UnicodeRange(0xA8DA, 0xA8DF), //saurashtra unassigned 2
				UnicodeRange(0xA8E0, 0xA8FD),
				//UnicodeRange(0xA8FE, 0xA8FF), //devanagari unassigned 1
				UnicodeRange(0xA900, 0xA953),
				//UnicodeRange(0xA954, 0xA95E), //rejang unassigned 1
				UnicodeRange(0xA95F, 0xA97C),
				//UnicodeRange(0xA97D, 0xA97F), //hangul unassigned 1
				UnicodeRange(0xA980, 0xA9CD),
				//UnicodeRange(0xA9CE, 0xA9CE), //javanese unassigned 1
				UnicodeRange(0xA9CF, 0xA9D9),
				//UnicodeRange(0xA9DA, 0xA9DD), //javanese unassigned 2
				UnicodeRange(0xA9DE, 0xA9FE),
				//UnicodeRange(0xA9FF, 0xA9FF), //myanmar unassigned 1
				UnicodeRange(0xAA00, 0xAA36),
				//UnicodeRange(0xAA37, 0xAA3F), //cham unassigned 1
				UnicodeRange(0xAA40, 0xAA4D),
				//UnicodeRange(0xAA4E, 0xAA4F), //cham unassigned 2
				UnicodeRange(0xAA50, 0xAA59),
				//UnicodeRange(0xAA5A, 0xAA5B), //cham unassigned 3
				UnicodeRange(0xAA5C, 0xAAC2),
				//UnicodeRange(0xAAC3, 0xAADA), //tai unassigned 1
				UnicodeRange(0xAADB, 0xAAF6),
				//UnicodeRange(0xAAF7, 0xAB00), //meetei unassigned 1
				UnicodeRange(0xAB01, 0xAB06),
				//UnicodeRange(0xAB07, 0xAB08), //ethiopic unassigned 1
				UnicodeRange(0xAB09, 0xAB0E),
				//UnicodeRange(0xAB0F, 0xAB10), //ethiopic unassigned 2
				UnicodeRange(0xAB11, 0xAB16),
				//UnicodeRange(0xAB17, 0xAB1F), //ethiopic unassigned 3
				UnicodeRange(0xAB20, 0xAB26),
				//UnicodeRange(0xAB27, 0xAB27), //ethiopic unassigned 4
				UnicodeRange(0xAB28, 0xAB2E),
				//UnicodeRange(0xAB2F, 0xAB2F), //ethiopic unassigned 5
				UnicodeRange(0xAB30, 0xAB65),
				//UnicodeRange(0xAB66, 0xAB6F), //greek unassigned 1
				UnicodeRange(0xAB70, 0xABED),
				//UnicodeRange(0xABEE, 0xABEF), //meetei unassigned 1
				UnicodeRange(0xABF0, 0xABF9),
				//UnicodeRange(0xABFA, 0xABFF), //meetei unassigned 2
				UnicodeRange(0xAC00, 0xD7A3),
				//UnicodeRange(0xD7A4, 0xD7AF), //hangul unassigned 1
				UnicodeRange(0xD7B0, 0xD7C6),
				//UnicodeRange(0xD7C7, 0xD7CA), //hangul unassigned 2
				UnicodeRange(0xD7CB, 0xD7FB),
				//UnicodeRange(0xD7FC, 0xD7FF), //surrogate
				//UnicodeRange(0xD800, 0xDFFF), //private use
				//UnicodeRange(0xE000, 0xF8FF), //private use
				UnicodeRange(0xF900, 0xFA6D),
				//UnicodeRange(0xFA6E, 0xFA6F), //cjk unassigned 1
				UnicodeRange(0xFA70, 0xFAD9),
				//UnicodeRange(0xFADA, 0xFAFF), //cjk unassigned 2
				UnicodeRange(0xFB00, 0xFB06),
				//UnicodeRange(0xFB07, 0xFB12), //latin unassigned 1
				UnicodeRange(0xFB13, 0xFB17),
				//UnicodeRange(0xFB18, 0xFB1C), //armenian unassigned 1
				UnicodeRange(0xFB1D, 0xFB36),
				//UnicodeRange(0xFB37, 0xFB37), //hebrew unassigned 1
				UnicodeRange(0xFB38, 0xFB3C),
				//UnicodeRange(0xFB3D, 0xFB3D), //hebrew unassigned 2
				UnicodeRange(0xFB3E, 0xFB3E),
				//UnicodeRange(0xFB3F, 0xFB3F), //hebrew unassigned 3
				UnicodeRange(0xFB40, 0xFB41),
				//UnicodeRange(0xFB42, 0xFB42), //hebrew unassigned 4
				UnicodeRange(0xFB43, 0xFB44),
				//UnicodeRange(0xFB45, 0xFB45), //hebrew unassigned 5
				UnicodeRange(0xFB46, 0xFBC1),
				//UnicodeRange(0xFBC2, 0xFBD2), //arabic unassigned 1
				UnicodeRange(0xFBD3, 0xFD3F),
				//UnicodeRange(0xFD40, 0xFD4F), //ornate unassigned 1
				UnicodeRange(0xFD50, 0xFD8F),
				//UnicodeRange(0xFD90, 0xFD91), //arabic unassigned 1
				UnicodeRange(0xFD92, 0xFDC7),
				//UnicodeRange(0xFDC8, 0xFDEF), //arabic unassigned 2
				UnicodeRange(0xFDF0, 0xFDFD),
				//UnicodeRange(0xFDFE, 0xFDFF), //arabic unassigned 3
				UnicodeRange(0xFE00, 0xFE19),
				//UnicodeRange(0xFE1A, 0xFE1F), //presentation unassigned 1
				UnicodeRange(0xFE20, 0xFE52),
				//UnicodeRange(0xFE53, 0xFE53), //small unassigned 1
				UnicodeRange(0xFE54, 0xFE66),
				//UnicodeRange(0xFE67, 0xFE67), //small unassigned 2
				UnicodeRange(0xFE68, 0xFE6B),
				//UnicodeRange(0xFE6C, 0xFE6F), //small unassigned 3
				UnicodeRange(0xFE70, 0xFE74),
				//UnicodeRange(0xFE75, 0xFE75), //arabic unassigned 1
				UnicodeRange(0xFE76, 0xFEFC),
				//UnicodeRange(0xFEFD, 0xFEFE), //arabic unassigned 2
				UnicodeRange(0xFEFF, 0xFEFF),
				//UnicodeRange(0xFF00, 0xFF00), //zero unassigned 1
				UnicodeRange(0xFF01, 0xFFBE),
				//UnicodeRange(0xFFBF, 0xFFC1), //halfwidth unassigned 1
				UnicodeRange(0xFFC2, 0xFFC7),
				//UnicodeRange(0xFFC8, 0xFFC9), //halfwidth unassigned 2
				UnicodeRange(0xFFCA, 0xFFCF),
				//UnicodeRange(0xFFD0, 0xFFD1), //halfwidth unassigned 3
				UnicodeRange(0xFFD2, 0xFFD7),
				//UnicodeRange(0xFFD8, 0xFFD9), //halfwidth unassigned 4
				UnicodeRange(0xFFDA, 0xFFDC),
				//UnicodeRange(0xFFDD, 0xFFDF), //halfwidth unassigned 5
				UnicodeRange(0xFFE0, 0xFFE6),
				//UnicodeRange(0xFFE7, 0xFFE7), //fullwidth unassigned 1
				UnicodeRange(0xFFE8, 0xFFEE),
				//UnicodeRange(0xFFEF, 0xFFF8), //halfwidth unassigned 1
				UnicodeRange(0xFFF9, 0xFFFD),
				//UnicodeRange(0xFFFE, 0xFFFF), //replacement unassigned 1
				UnicodeRange(0x10000, 0x1000B),
				//UnicodeRange(0x1000C, 0x1000C), //linear unassigned 1
				UnicodeRange(0x1000D, 0x10026),
				//UnicodeRange(0x10027, 0x10027), //linear unassigned 2
				UnicodeRange(0x10028, 0x1003A),
				//UnicodeRange(0x1003B, 0x1003B), //linear unassigned 3
				UnicodeRange(0x1003C, 0x1003D),
				//UnicodeRange(0x1003E, 0x1003E), //linear unassigned 4
				UnicodeRange(0x1003F, 0x1004D),
				//UnicodeRange(0x1004E, 0x1004F), //linear unassigned 5
				UnicodeRange(0x10050, 0x1005D),
				//UnicodeRange(0x1005E, 0x1007F), //linear unassigned 6
				UnicodeRange(0x10080, 0x100FA),
				//UnicodeRange(0x100FB, 0x100FF), //linear unassigned 7
				UnicodeRange(0x10100, 0x10102),
				//UnicodeRange(0x10103, 0x10106), //aegean unassigned 1
				UnicodeRange(0x10107, 0x10133),
				//UnicodeRange(0x10134, 0x10136), //aegean unassigned 2
				UnicodeRange(0x10137, 0x1018E),
				//UnicodeRange(0x1018F, 0x1018F), //nomisma unassigned 1
				UnicodeRange(0x10190, 0x1019B),
				//UnicodeRange(0x1019C, 0x1019F), //roman unassigned 1
				UnicodeRange(0x101A0, 0x101A0),
				//UnicodeRange(0x101A1, 0x101CF), //greek unassigned 1
				UnicodeRange(0x101D0, 0x101FD),
				//UnicodeRange(0x101FE, 0x1027F), //phaistos unassigned 1
				UnicodeRange(0x10280, 0x1029C),
				//UnicodeRange(0x1029D, 0x1029F), //lycian unassigned 1
				UnicodeRange(0x102A0, 0x102D0),
				//UnicodeRange(0x102D1, 0x102DF), //carian unassigned 1
				UnicodeRange(0x102E0, 0x102FB),
				//UnicodeRange(0x102FC, 0x102FF), //coptic unassigned 1
				UnicodeRange(0x10300, 0x10323),
				//UnicodeRange(0x10324, 0x1032C), //old unassigned 1
				UnicodeRange(0x1032D, 0x1034A),
				//UnicodeRange(0x1034B, 0x1034F), //gothic unassigned 1
				UnicodeRange(0x10350, 0x1037A),
				//UnicodeRange(0x1037B, 0x1037F), //combining unassigned 1
				UnicodeRange(0x10380, 0x1039D),
				//UnicodeRange(0x1039E, 0x1039E), //ugaritic unassigned 1
				UnicodeRange(0x1039F, 0x103C3),
				//UnicodeRange(0x103C4, 0x103C7), //old unassigned 1
				UnicodeRange(0x103C8, 0x103D5),
				//UnicodeRange(0x103D6, 0x103FF), //old unassigned 2
				UnicodeRange(0x10400, 0x1049D),
				//UnicodeRange(0x1049E, 0x1049F), //osmanya unassigned 1
				UnicodeRange(0x104A0, 0x104A9),
				//UnicodeRange(0x104AA, 0x104AF), //osmanya unassigned 2
				UnicodeRange(0x104B0, 0x104D3),
				//UnicodeRange(0x104D4, 0x104D7), //osage unassigned 1
				UnicodeRange(0x104D8, 0x104FB),
				//UnicodeRange(0x104FC, 0x104FF), //osage unassigned 2
				UnicodeRange(0x10500, 0x10527),
				//UnicodeRange(0x10528, 0x1052F), //elbasan unassigned 1
				UnicodeRange(0x10530, 0x10563),
				//UnicodeRange(0x10564, 0x1056E), //caucasian unassigned 1
				UnicodeRange(0x1056F, 0x1056F),
				//UnicodeRange(0x10570, 0x105FF), //caucasian unassigned 2
				UnicodeRange(0x10600, 0x10736),
				//UnicodeRange(0x10737, 0x1073F), //linear unassigned 1
				UnicodeRange(0x10740, 0x10755),
				//UnicodeRange(0x10756, 0x1075F), //linear unassigned 2
				UnicodeRange(0x10760, 0x10767),
				//UnicodeRange(0x10768, 0x107FF), //linear unassigned 3
				UnicodeRange(0x10800, 0x10805),
				//UnicodeRange(0x10806, 0x10807), //cypriot unassigned 1
				UnicodeRange(0x10808, 0x10808),
				//UnicodeRange(0x10809, 0x10809), //cypriot unassigned 2
				UnicodeRange(0x1080A, 0x10835),
				//UnicodeRange(0x10836, 0x10836), //cypriot unassigned 3
				UnicodeRange(0x10837, 0x10838),
				//UnicodeRange(0x10839, 0x1083B), //cypriot unassigned 4
				UnicodeRange(0x1083C, 0x1083C),
				//UnicodeRange(0x1083D, 0x1083E), //cypriot unassigned 5
				UnicodeRange(0x1083F, 0x10855),
				//UnicodeRange(0x10856, 0x10856), //imperial unassigned 1
				UnicodeRange(0x10857, 0x1089E),
				//UnicodeRange(0x1089F, 0x108A6), //nabataean unassigned 1
				UnicodeRange(0x108A7, 0x108AF),
				//UnicodeRange(0x108B0, 0x108DF), //nabataean unassigned 2
				UnicodeRange(0x108E0, 0x108F2),
				//UnicodeRange(0x108F3, 0x108F3), //hatran unassigned 1
				UnicodeRange(0x108F4, 0x108F5),
				//UnicodeRange(0x108F6, 0x108FA), //hatran unassigned 2
				UnicodeRange(0x108FB, 0x1091B),
				//UnicodeRange(0x1091C, 0x1091E), //phoenician unassigned 1
				UnicodeRange(0x1091F, 0x10939),
				//UnicodeRange(0x1093A, 0x1093E), //lydian unassigned 1
				UnicodeRange(0x1093F, 0x1093F),
				//UnicodeRange(0x10940, 0x1097F), //lydian unassigned 2
				UnicodeRange(0x10980, 0x109B7),
				//UnicodeRange(0x109B8, 0x109BB), //meroitic unassigned 1
				UnicodeRange(0x109BC, 0x109CF),
				//UnicodeRange(0x109D0, 0x109D1), //meroitic unassigned 2
				UnicodeRange(0x109D2, 0x10A03),
				//UnicodeRange(0x10A04, 0x10A04), //kharoshthi unassigned 1
				UnicodeRange(0x10A05, 0x10A06),
				//UnicodeRange(0x10A07, 0x10A0B), //kharoshthi unassigned 2
				UnicodeRange(0x10A0C, 0x10A13),
				//UnicodeRange(0x10A14, 0x10A14), //kharoshthi unassigned 3
				UnicodeRange(0x10A15, 0x10A17),
				//UnicodeRange(0x10A18, 0x10A18), //kharoshthi unassigned 4
				UnicodeRange(0x10A19, 0x10A33),
				//UnicodeRange(0x10A34, 0x10A37), //kharoshthi unassigned 5
				UnicodeRange(0x10A38, 0x10A3A),
				//UnicodeRange(0x10A3B, 0x10A3E), //kharoshthi unassigned 6
				UnicodeRange(0x10A3F, 0x10A47),
				//UnicodeRange(0x10A48, 0x10A4F), //kharoshthi unassigned 7
				UnicodeRange(0x10A50, 0x10A58),
				//UnicodeRange(0x10A59, 0x10A5F), //kharoshthi unassigned 8
				UnicodeRange(0x10A60, 0x10A9F),
				//UnicodeRange(0x10AA0, 0x10ABF), //old unassigned 1
				UnicodeRange(0x10AC0, 0x10AE6),
				//UnicodeRange(0x10AE7, 0x10AEA), //manichaean unassigned 1
				UnicodeRange(0x10AEB, 0x10AF6),
				//UnicodeRange(0x10AF7, 0x10AFF), //manichaean unassigned 2
				UnicodeRange(0x10B00, 0x10B35),
				//UnicodeRange(0x10B36, 0x10B38), //avestan unassigned 1
				UnicodeRange(0x10B39, 0x10B55),
				//UnicodeRange(0x10B56, 0x10B57), //inscriptional unassigned 1
				UnicodeRange(0x10B58, 0x10B72),
				//UnicodeRange(0x10B73, 0x10B77), //inscriptional unassigned 2
				UnicodeRange(0x10B78, 0x10B91),
				//UnicodeRange(0x10B92, 0x10B98), //psalter unassigned 1
				UnicodeRange(0x10B99, 0x10B9C),
				//UnicodeRange(0x10B9D, 0x10BA8), //psalter unassigned 2
				UnicodeRange(0x10BA9, 0x10BAF),
				//UnicodeRange(0x10BB0, 0x10BFF), //psalter unassigned 3
				UnicodeRange(0x10C00, 0x10C48),
				//UnicodeRange(0x10C49, 0x10C7F), //old unassigned 1
				UnicodeRange(0x10C80, 0x10CB2),
				//UnicodeRange(0x10CB3, 0x10CBF), //old unassigned 2
				UnicodeRange(0x10CC0, 0x10CF2),
				//UnicodeRange(0x10CF3, 0x10CF9), //old unassigned 3
				UnicodeRange(0x10CFA, 0x10CFF),
				//UnicodeRange(0x10D00, 0x10E5F), //old unassigned 4
				UnicodeRange(0x10E60, 0x10E7E),
				//UnicodeRange(0x10E7F, 0x10FFF), //rumi unassigned 1
				UnicodeRange(0x11000, 0x1104D),
				//UnicodeRange(0x1104E, 0x11051), //brahmi unassigned 1
				UnicodeRange(0x11052, 0x1106F),
				//UnicodeRange(0x11070, 0x1107E), //brahmi unassigned 2
				UnicodeRange(0x1107F, 0x110C1),
				//UnicodeRange(0x110C2, 0x110CF), //kaithi unassigned 1
				UnicodeRange(0x110D0, 0x110E8),
				//UnicodeRange(0x110E9, 0x110EF), //sora unassigned 1
				UnicodeRange(0x110F0, 0x110F9),
				//UnicodeRange(0x110FA, 0x110FF), //sora unassigned 2
				UnicodeRange(0x11100, 0x11134),
				//UnicodeRange(0x11135, 0x11135), //chakma unassigned 1
				UnicodeRange(0x11136, 0x11143),
				//UnicodeRange(0x11144, 0x1114F), //chakma unassigned 2
				UnicodeRange(0x11150, 0x11176),
				//UnicodeRange(0x11177, 0x1117F), //mahajani unassigned 1
				UnicodeRange(0x11180, 0x111CD),
				//UnicodeRange(0x111CE, 0x111CF), //sharada unassigned 1
				UnicodeRange(0x111D0, 0x111DF),
				//UnicodeRange(0x111E0, 0x111E0), //sharada unassigned 2
				UnicodeRange(0x111E1, 0x111F4),
				//UnicodeRange(0x111F5, 0x111FF), //sinhala unassigned 1
				UnicodeRange(0x11200, 0x11211),
				//UnicodeRange(0x11212, 0x11212), //khojki unassigned 1
				UnicodeRange(0x11213, 0x1123E),
				//UnicodeRange(0x1123F, 0x1127F), //khojki unassigned 2
				UnicodeRange(0x11280, 0x11286),
				//UnicodeRange(0x11287, 0x11287), //multani unassigned 1
				UnicodeRange(0x11288, 0x11288),
				//UnicodeRange(0x11289, 0x11289), //multani unassigned 2
				UnicodeRange(0x1128A, 0x1128D),
				//UnicodeRange(0x1128E, 0x1128E), //multani unassigned 3
				UnicodeRange(0x1128F, 0x1129D),
				//UnicodeRange(0x1129E, 0x1129E), //multani unassigned 4
				UnicodeRange(0x1129F, 0x112A9),
				//UnicodeRange(0x112AA, 0x112AF), //multani unassigned 5
				UnicodeRange(0x112B0, 0x112EA),
				//UnicodeRange(0x112EB, 0x112EF), //khudawadi unassigned 1
				UnicodeRange(0x112F0, 0x112F9),
				//UnicodeRange(0x112FA, 0x112FF), //khudawadi unassigned 2
				UnicodeRange(0x11300, 0x11303),
				//UnicodeRange(0x11304, 0x11304), //grantha unassigned 1
				UnicodeRange(0x11305, 0x1130C),
				//UnicodeRange(0x1130D, 0x1130E), //grantha unassigned 2
				UnicodeRange(0x1130F, 0x11310),
				//UnicodeRange(0x11311, 0x11312), //grantha unassigned 3
				UnicodeRange(0x11313, 0x11328),
				//UnicodeRange(0x11329, 0x11329), //grantha unassigned 4
				UnicodeRange(0x1132A, 0x11330),
				//UnicodeRange(0x11331, 0x11331), //grantha unassigned 5
				UnicodeRange(0x11332, 0x11333),
				//UnicodeRange(0x11334, 0x11334), //grantha unassigned 6
				UnicodeRange(0x11335, 0x11339),
				//UnicodeRange(0x1133A, 0x1133B), //grantha unassigned 7
				UnicodeRange(0x1133C, 0x11344),
				//UnicodeRange(0x11345, 0x11346), //grantha unassigned 8
				UnicodeRange(0x11347, 0x11348),
				//UnicodeRange(0x11349, 0x1134A), //grantha unassigned 9
				UnicodeRange(0x1134B, 0x1134D),
				//UnicodeRange(0x1134E, 0x1134F), //grantha unassigned 10
				UnicodeRange(0x11350, 0x11350),
				//UnicodeRange(0x11351, 0x11356), //grantha unassigned 11
				UnicodeRange(0x11357, 0x11357),
				//UnicodeRange(0x11358, 0x1135C), //grantha unassigned 12
				UnicodeRange(0x1135D, 0x11363),
				//UnicodeRange(0x11364, 0x11365), //grantha unassigned 13
				UnicodeRange(0x11366, 0x1136C),
				//UnicodeRange(0x1136D, 0x1136F), //combining unassigned 1
				UnicodeRange(0x11370, 0x11374),
				//UnicodeRange(0x11375, 0x113FF), //combining unassigned 2
				UnicodeRange(0x11400, 0x11459),
				//UnicodeRange(0x1145A, 0x1145A), //newa unassigned 1
				UnicodeRange(0x1145B, 0x1145B),
				//UnicodeRange(0x1145C, 0x1145C), //newa unassigned 2
				UnicodeRange(0x1145D, 0x1145D),
				//UnicodeRange(0x1145E, 0x1147F), //newa unassigned 3
				UnicodeRange(0x11480, 0x114C7),
				//UnicodeRange(0x114C8, 0x114CF), //tirhuta unassigned 1
				UnicodeRange(0x114D0, 0x114D9),
				//UnicodeRange(0x114DA, 0x1157F), //tirhuta unassigned 2
				UnicodeRange(0x11580, 0x115B5),
				//UnicodeRange(0x115B6, 0x115B7), //siddham unassigned 1
				UnicodeRange(0x115B8, 0x115DD),
				//UnicodeRange(0x115DE, 0x115FF), //siddham unassigned 2
				UnicodeRange(0x11600, 0x11644),
				//UnicodeRange(0x11645, 0x1164F), //modi unassigned 1
				UnicodeRange(0x11650, 0x11659),
				//UnicodeRange(0x1165A, 0x1165F), //modi unassigned 2
				UnicodeRange(0x11660, 0x1166C),
				//UnicodeRange(0x1166D, 0x1167F), //mongolian unassigned 1
				UnicodeRange(0x11680, 0x116B7),
				//UnicodeRange(0x116B8, 0x116BF), //takri unassigned 1
				UnicodeRange(0x116C0, 0x116C9),
				//UnicodeRange(0x116CA, 0x116FF), //takri unassigned 2
				UnicodeRange(0x11700, 0x11719),
				//UnicodeRange(0x1171A, 0x1171C), //ahom unassigned 1
				UnicodeRange(0x1171D, 0x1172B),
				//UnicodeRange(0x1172C, 0x1172F), //ahom unassigned 2
				UnicodeRange(0x11730, 0x1173F),
				//UnicodeRange(0x11740, 0x1189F), //ahom unassigned 3
				UnicodeRange(0x118A0, 0x118F2),
				//UnicodeRange(0x118F3, 0x118FE), //warang unassigned 1
				UnicodeRange(0x118FF, 0x118FF),
				//UnicodeRange(0x11900, 0x119FF), //warang unassigned 2
				UnicodeRange(0x11A00, 0x11A47),
				//UnicodeRange(0x11A48, 0x11A4F), //zanabazar unassigned 1
				UnicodeRange(0x11A50, 0x11A83),
				//UnicodeRange(0x11A84, 0x11A85), //soyombo unassigned 1
				UnicodeRange(0x11A86, 0x11A9C),
				//UnicodeRange(0x11A9D, 0x11A9D), //soyombo unassigned 2
				UnicodeRange(0x11A9E, 0x11AA2),
				//UnicodeRange(0x11AA3, 0x11ABF), //soyombo unassigned 3
				UnicodeRange(0x11AC0, 0x11AF8),
				//UnicodeRange(0x11AF9, 0x11BFF), //pau unassigned 1
				UnicodeRange(0x11C00, 0x11C08),
				//UnicodeRange(0x11C09, 0x11C09), //bhaiksuki unassigned 1
				UnicodeRange(0x11C0A, 0x11C36),
				//UnicodeRange(0x11C37, 0x11C37), //bhaiksuki unassigned 2
				UnicodeRange(0x11C38, 0x11C45),
				//UnicodeRange(0x11C46, 0x11C4F), //bhaiksuki unassigned 3
				UnicodeRange(0x11C50, 0x11C6C),
				//UnicodeRange(0x11C6D, 0x11C6F), //bhaiksuki unassigned 4
				UnicodeRange(0x11C70, 0x11C8F),
				//UnicodeRange(0x11C90, 0x11C91), //marchen unassigned 1
				UnicodeRange(0x11C92, 0x11CA7),
				//UnicodeRange(0x11CA8, 0x11CA8), //marchen unassigned 2
				UnicodeRange(0x11CA9, 0x11CB6),
				//UnicodeRange(0x11CB7, 0x11CFF), //marchen unassigned 3
				UnicodeRange(0x11D00, 0x11D06),
				//UnicodeRange(0x11D07, 0x11D07), //masaram unassigned 1
				UnicodeRange(0x11D08, 0x11D09),
				//UnicodeRange(0x11D0A, 0x11D0A), //masaram unassigned 2
				UnicodeRange(0x11D0B, 0x11D36),
				//UnicodeRange(0x11D37, 0x11D39), //masaram unassigned 3
				UnicodeRange(0x11D3A, 0x11D3A),
				//UnicodeRange(0x11D3B, 0x11D3B), //masaram unassigned 4
				UnicodeRange(0x11D3C, 0x11D3D),
				//UnicodeRange(0x11D3E, 0x11D3E), //masaram unassigned 5
				UnicodeRange(0x11D3F, 0x11D47),
				//UnicodeRange(0x11D48, 0x11D4F), //masaram unassigned 6
				UnicodeRange(0x11D50, 0x11D59),
				//UnicodeRange(0x11D5A, 0x11FFF), //masaram unassigned 7
				UnicodeRange(0x12000, 0x12399),
				//UnicodeRange(0x1239A, 0x123FF), //cuneiform unassigned 1
				UnicodeRange(0x12400, 0x1246E),
				//UnicodeRange(0x1246F, 0x1246F), //cuneiform unassigned 2
				UnicodeRange(0x12470, 0x12474),
				//UnicodeRange(0x12475, 0x1247F), //cuneiform unassigned 3
				UnicodeRange(0x12480, 0x12543),
				//UnicodeRange(0x12544, 0x12FFF), //cuneiform unassigned 4
				UnicodeRange(0x13000, 0x1342E),
				//UnicodeRange(0x1342F, 0x143FF), //egyptian unassigned 1
				UnicodeRange(0x14400, 0x14646),
				//UnicodeRange(0x14647, 0x167FF), //anatolian unassigned 1
				UnicodeRange(0x16800, 0x16A38),
				//UnicodeRange(0x16A39, 0x16A3F), //bamum unassigned 1
				UnicodeRange(0x16A40, 0x16A5E),
				//UnicodeRange(0x16A5F, 0x16A5F), //mro unassigned 1
				UnicodeRange(0x16A60, 0x16A69),
				//UnicodeRange(0x16A6A, 0x16A6D), //mro unassigned 2
				UnicodeRange(0x16A6E, 0x16A6F),
				//UnicodeRange(0x16A70, 0x16ACF), //mro unassigned 3
				UnicodeRange(0x16AD0, 0x16AED),
				//UnicodeRange(0x16AEE, 0x16AEF), //bassa unassigned 1
				UnicodeRange(0x16AF0, 0x16AF5),
				//UnicodeRange(0x16AF6, 0x16AFF), //bassa unassigned 2
				UnicodeRange(0x16B00, 0x16B45),
				//UnicodeRange(0x16B46, 0x16B4F), //pahawh unassigned 1
				UnicodeRange(0x16B50, 0x16B59),
				//UnicodeRange(0x16B5A, 0x16B5A), //pahawh unassigned 2
				UnicodeRange(0x16B5B, 0x16B61),
				//UnicodeRange(0x16B62, 0x16B62), //pahawh unassigned 3
				UnicodeRange(0x16B63, 0x16B77),
				//UnicodeRange(0x16B78, 0x16B7C), //pahawh unassigned 4
				UnicodeRange(0x16B7D, 0x16B8F),
				//UnicodeRange(0x16B90, 0x16EFF), //pahawh unassigned 5
				UnicodeRange(0x16F00, 0x16F44),
				//UnicodeRange(0x16F45, 0x16F4F), //miao unassigned 1
				UnicodeRange(0x16F50, 0x16F7E),
				//UnicodeRange(0x16F7F, 0x16F8E), //miao unassigned 2
				UnicodeRange(0x16F8F, 0x16F9F),
				//UnicodeRange(0x16FA0, 0x16FDF), //miao unassigned 3
				UnicodeRange(0x16FE0, 0x16FE1),
				//UnicodeRange(0x16FE2, 0x16FFF), //nushu unassigned 1
				UnicodeRange(0x17000, 0x187EC),
				//UnicodeRange(0x187ED, 0x187FF), //tangut unassigned 1
				UnicodeRange(0x18800, 0x18AF2),
				//UnicodeRange(0x18AF3, 0x1AFFF), //tangut unassigned 2
				UnicodeRange(0x1B000, 0x1B11E),
				//UnicodeRange(0x1B11F, 0x1B16F), //hentaigana unassigned 1
				UnicodeRange(0x1B170, 0x1B2FB),
				//UnicodeRange(0x1B2FC, 0x1BBFF), //nushu unassigned 1
				UnicodeRange(0x1BC00, 0x1BC6A),
				//UnicodeRange(0x1BC6B, 0x1BC6F), //duployan unassigned 1
				UnicodeRange(0x1BC70, 0x1BC7C),
				//UnicodeRange(0x1BC7D, 0x1BC7F), //duployan unassigned 2
				UnicodeRange(0x1BC80, 0x1BC88),
				//UnicodeRange(0x1BC89, 0x1BC8F), //duployan unassigned 3
				UnicodeRange(0x1BC90, 0x1BC99),
				//UnicodeRange(0x1BC9A, 0x1BC9B), //duployan unassigned 4
				UnicodeRange(0x1BC9C, 0x1BCA3),
				//UnicodeRange(0x1BCA4, 0x1CFFF), //shorthand unassigned 1
				UnicodeRange(0x1D000, 0x1D0F5),
				//UnicodeRange(0x1D0F6, 0x1D0FF), //byzantine unassigned 1
				UnicodeRange(0x1D100, 0x1D126),
				//UnicodeRange(0x1D127, 0x1D128), //musical unassigned 1
				UnicodeRange(0x1D129, 0x1D1E8),
				//UnicodeRange(0x1D1E9, 0x1D1FF), //musical unassigned 2
				UnicodeRange(0x1D200, 0x1D245),
				//UnicodeRange(0x1D246, 0x1D2FF), //greek unassigned 1
				UnicodeRange(0x1D300, 0x1D356),
				//UnicodeRange(0x1D357, 0x1D35F), //tetragram unassigned 1
				UnicodeRange(0x1D360, 0x1D371),
				//UnicodeRange(0x1D372, 0x1D3FF), //counting unassigned 1
				UnicodeRange(0x1D400, 0x1D454),
				//UnicodeRange(0x1D455, 0x1D455), //mathematical unassigned 1
				UnicodeRange(0x1D456, 0x1D49C),
				//UnicodeRange(0x1D49D, 0x1D49D), //mathematical unassigned 2
				UnicodeRange(0x1D49E, 0x1D49F),
				//UnicodeRange(0x1D4A0, 0x1D4A1), //mathematical unassigned 3
				UnicodeRange(0x1D4A2, 0x1D4A2),
				//UnicodeRange(0x1D4A3, 0x1D4A4), //mathematical unassigned 4
				UnicodeRange(0x1D4A5, 0x1D4A6),
				//UnicodeRange(0x1D4A7, 0x1D4A8), //mathematical unassigned 5
				UnicodeRange(0x1D4A9, 0x1D4AC),
				//UnicodeRange(0x1D4AD, 0x1D4AD), //mathematical unassigned 6
				UnicodeRange(0x1D4AE, 0x1D4B9),
				//UnicodeRange(0x1D4BA, 0x1D4BA), //mathematical unassigned 7
				UnicodeRange(0x1D4BB, 0x1D4BB),
				//UnicodeRange(0x1D4BC, 0x1D4BC), //mathematical unassigned 8
				UnicodeRange(0x1D4BD, 0x1D4C3),
				//UnicodeRange(0x1D4C4, 0x1D4C4), //mathematical unassigned 9
				UnicodeRange(0x1D4C5, 0x1D505),
				//UnicodeRange(0x1D506, 0x1D506), //mathematical unassigned 10
				UnicodeRange(0x1D507, 0x1D50A),
				//UnicodeRange(0x1D50B, 0x1D50C), //mathematical unassigned 11
				UnicodeRange(0x1D50D, 0x1D514),
				//UnicodeRange(0x1D515, 0x1D515), //mathematical unassigned 12
				UnicodeRange(0x1D516, 0x1D51C),
				//UnicodeRange(0x1D51D, 0x1D51D), //mathematical unassigned 13
				UnicodeRange(0x1D51E, 0x1D539),
				//UnicodeRange(0x1D53A, 0x1D53A), //mathematical unassigned 14
				UnicodeRange(0x1D53B, 0x1D53E),
				//UnicodeRange(0x1D53F, 0x1D53F), //mathematical unassigned 15
				UnicodeRange(0x1D540, 0x1D544),
				//UnicodeRange(0x1D545, 0x1D545), //mathematical unassigned 16
				UnicodeRange(0x1D546, 0x1D546),
				//UnicodeRange(0x1D547, 0x1D549), //mathematical unassigned 17
				UnicodeRange(0x1D54A, 0x1D550),
				//UnicodeRange(0x1D551, 0x1D551), //mathematical unassigned 18
				UnicodeRange(0x1D552, 0x1D6A5),
				//UnicodeRange(0x1D6A6, 0x1D6A7), //mathematical unassigned 19
				UnicodeRange(0x1D6A8, 0x1D7CB),
				//UnicodeRange(0x1D7CC, 0x1D7CD), //mathematical unassigned 20
				UnicodeRange(0x1D7CE, 0x1DA8B),
				//UnicodeRange(0x1DA8C, 0x1DA9A), //signwriting unassigned 1
				UnicodeRange(0x1DA9B, 0x1DA9F),
				//UnicodeRange(0x1DAA0, 0x1DAA0), //signwriting unassigned 2
				UnicodeRange(0x1DAA1, 0x1DAAF),
				//UnicodeRange(0x1DAB0, 0x1DFFF), //signwriting unassigned 3
				UnicodeRange(0x1E000, 0x1E006),
				//UnicodeRange(0x1E007, 0x1E007), //combining unassigned 1
				UnicodeRange(0x1E008, 0x1E018),
				//UnicodeRange(0x1E019, 0x1E01A), //combining unassigned 2
				UnicodeRange(0x1E01B, 0x1E021),
				//UnicodeRange(0x1E022, 0x1E022), //combining unassigned 3
				UnicodeRange(0x1E023, 0x1E024),
				//UnicodeRange(0x1E025, 0x1E025), //combining unassigned 4
				UnicodeRange(0x1E026, 0x1E02A),
				//UnicodeRange(0x1E02B, 0x1E7FF), //combining unassigned 5
				UnicodeRange(0x1E800, 0x1E8C4),
				//UnicodeRange(0x1E8C5, 0x1E8C6), //mende unassigned 1
				UnicodeRange(0x1E8C7, 0x1E8D6),
				//UnicodeRange(0x1E8D7, 0x1E8FF), //mende unassigned 2
				UnicodeRange(0x1E900, 0x1E94A),
				//UnicodeRange(0x1E94B, 0x1E94F), //adlam unassigned 1
				UnicodeRange(0x1E950, 0x1E959),
				//UnicodeRange(0x1E95A, 0x1E95D), //adlam unassigned 2
				UnicodeRange(0x1E95E, 0x1E95F),
				//UnicodeRange(0x1E960, 0x1EDFF), //adlam unassigned 3
				UnicodeRange(0x1EE00, 0x1EE03),
				//UnicodeRange(0x1EE04, 0x1EE04), //arabic unassigned 1
				UnicodeRange(0x1EE05, 0x1EE1F),
				//UnicodeRange(0x1EE20, 0x1EE20), //arabic unassigned 2
				UnicodeRange(0x1EE21, 0x1EE22),
				//UnicodeRange(0x1EE23, 0x1EE23), //arabic unassigned 3
				UnicodeRange(0x1EE24, 0x1EE24),
				//UnicodeRange(0x1EE25, 0x1EE26), //arabic unassigned 4
				UnicodeRange(0x1EE27, 0x1EE27),
				//UnicodeRange(0x1EE28, 0x1EE28), //arabic unassigned 5
				UnicodeRange(0x1EE29, 0x1EE32),
				//UnicodeRange(0x1EE33, 0x1EE33), //arabic unassigned 6
				UnicodeRange(0x1EE34, 0x1EE37),
				//UnicodeRange(0x1EE38, 0x1EE38), //arabic unassigned 7
				UnicodeRange(0x1EE39, 0x1EE39),
				//UnicodeRange(0x1EE3A, 0x1EE3A), //arabic unassigned 8
				UnicodeRange(0x1EE3B, 0x1EE3B),
				//UnicodeRange(0x1EE3C, 0x1EE41), //arabic unassigned 9
				UnicodeRange(0x1EE42, 0x1EE42),
				//UnicodeRange(0x1EE43, 0x1EE46), //arabic unassigned 10
				UnicodeRange(0x1EE47, 0x1EE47),
				//UnicodeRange(0x1EE48, 0x1EE48), //arabic unassigned 11
				UnicodeRange(0x1EE49, 0x1EE49),
				//UnicodeRange(0x1EE4A, 0x1EE4A), //arabic unassigned 12
				UnicodeRange(0x1EE4B, 0x1EE4B),
				//UnicodeRange(0x1EE4C, 0x1EE4C), //arabic unassigned 13
				UnicodeRange(0x1EE4D, 0x1EE4F),
				//UnicodeRange(0x1EE50, 0x1EE50), //arabic unassigned 14
				UnicodeRange(0x1EE51, 0x1EE52),
				//UnicodeRange(0x1EE53, 0x1EE53), //arabic unassigned 15
				UnicodeRange(0x1EE54, 0x1EE54),
				//UnicodeRange(0x1EE55, 0x1EE56), //arabic unassigned 16
				UnicodeRange(0x1EE57, 0x1EE57),
				//UnicodeRange(0x1EE58, 0x1EE58), //arabic unassigned 17
				UnicodeRange(0x1EE59, 0x1EE59),
				//UnicodeRange(0x1EE5A, 0x1EE5A), //arabic unassigned 18
				UnicodeRange(0x1EE5B, 0x1EE5B),
				//UnicodeRange(0x1EE5C, 0x1EE5C), //arabic unassigned 19
				UnicodeRange(0x1EE5D, 0x1EE5D),
				//UnicodeRange(0x1EE5E, 0x1EE5E), //arabic unassigned 20
				UnicodeRange(0x1EE5F, 0x1EE5F),
				//UnicodeRange(0x1EE60, 0x1EE60), //arabic unassigned 21
				UnicodeRange(0x1EE61, 0x1EE62),
				//UnicodeRange(0x1EE63, 0x1EE63), //arabic unassigned 22
				UnicodeRange(0x1EE64, 0x1EE64),
				//UnicodeRange(0x1EE65, 0x1EE66), //arabic unassigned 23
				UnicodeRange(0x1EE67, 0x1EE6A),
				//UnicodeRange(0x1EE6B, 0x1EE6B), //arabic unassigned 24
				UnicodeRange(0x1EE6C, 0x1EE72),
				//UnicodeRange(0x1EE73, 0x1EE73), //arabic unassigned 25
				UnicodeRange(0x1EE74, 0x1EE77),
				//UnicodeRange(0x1EE78, 0x1EE78), //arabic unassigned 26
				UnicodeRange(0x1EE79, 0x1EE7C),
				//UnicodeRange(0x1EE7D, 0x1EE7D), //arabic unassigned 27
				UnicodeRange(0x1EE7E, 0x1EE7E),
				//UnicodeRange(0x1EE7F, 0x1EE7F), //arabic unassigned 28
				UnicodeRange(0x1EE80, 0x1EE89),
				//UnicodeRange(0x1EE8A, 0x1EE8A), //arabic unassigned 29
				UnicodeRange(0x1EE8B, 0x1EE9B),
				//UnicodeRange(0x1EE9C, 0x1EEA0), //arabic unassigned 30
				UnicodeRange(0x1EEA1, 0x1EEA3),
				//UnicodeRange(0x1EEA4, 0x1EEA4), //arabic unassigned 31
				UnicodeRange(0x1EEA5, 0x1EEA9),
				//UnicodeRange(0x1EEAA, 0x1EEAA), //arabic unassigned 32
				UnicodeRange(0x1EEAB, 0x1EEBB),
				//UnicodeRange(0x1EEBC, 0x1EEEF), //arabic unassigned 33
				UnicodeRange(0x1EEF0, 0x1EEF1),
				//UnicodeRange(0x1EEF2, 0x1EFFF), //arabic unassigned 34
				UnicodeRange(0x1F000, 0x1F02B),
				//UnicodeRange(0x1F02C, 0x1F02F), //mahjong unassigned 1
				UnicodeRange(0x1F030, 0x1F093),
				//UnicodeRange(0x1F094, 0x1F09F), //domino unassigned 1
				UnicodeRange(0x1F0A0, 0x1F0AE),
				//UnicodeRange(0x1F0AF, 0x1F0B0), //playing unassigned 1
				UnicodeRange(0x1F0B1, 0x1F0BF),
				//UnicodeRange(0x1F0C0, 0x1F0C0), //playing unassigned 2
				UnicodeRange(0x1F0C1, 0x1F0CF),
				//UnicodeRange(0x1F0D0, 0x1F0D0), //playing unassigned 3
				UnicodeRange(0x1F0D1, 0x1F0F5),
				//UnicodeRange(0x1F0F6, 0x1F0FF), //playing unassigned 4
				UnicodeRange(0x1F100, 0x1F10C),
				//UnicodeRange(0x1F10D, 0x1F10F), //dingbat unassigned 1
				UnicodeRange(0x1F110, 0x1F12E),
				//UnicodeRange(0x1F12F, 0x1F12F), //circled unassigned 1
				UnicodeRange(0x1F130, 0x1F16B),
				//UnicodeRange(0x1F16C, 0x1F16F), //raised unassigned 1
				UnicodeRange(0x1F170, 0x1F1AC),
				//UnicodeRange(0x1F1AD, 0x1F1E5), //squared unassigned 1
				UnicodeRange(0x1F1E6, 0x1F202),
				//UnicodeRange(0x1F203, 0x1F20F), //squared unassigned 2
				UnicodeRange(0x1F210, 0x1F23B),
				//UnicodeRange(0x1F23C, 0x1F23F), //squared unassigned 3
				UnicodeRange(0x1F240, 0x1F248),
				//UnicodeRange(0x1F249, 0x1F24F), //tortoise unassigned 1
				UnicodeRange(0x1F250, 0x1F251),
				//UnicodeRange(0x1F252, 0x1F25F), //circled unassigned 1
				UnicodeRange(0x1F260, 0x1F265),
				//UnicodeRange(0x1F266, 0x1F2FF), //rounded unassigned 1
				UnicodeRange(0x1F300, 0x1F6D4),
				//UnicodeRange(0x1F6D5, 0x1F6DF), //pagoda unassigned 1
				UnicodeRange(0x1F6E0, 0x1F6EC),
				//UnicodeRange(0x1F6ED, 0x1F6EF), //airplane unassigned 1
				UnicodeRange(0x1F6F0, 0x1F6F8),
				//UnicodeRange(0x1F6F9, 0x1F6FF), //flying unassigned 1
				UnicodeRange(0x1F700, 0x1F773),
				//UnicodeRange(0x1F774, 0x1F77F), //alchemical unassigned 1
				UnicodeRange(0x1F780, 0x1F7D4),
				//UnicodeRange(0x1F7D5, 0x1F7FF), //heavy unassigned 1
				UnicodeRange(0x1F800, 0x1F80B),
				//UnicodeRange(0x1F80C, 0x1F80F), //downwards unassigned 1
				UnicodeRange(0x1F810, 0x1F847),
				//UnicodeRange(0x1F848, 0x1F84F), //downwards unassigned 2
				UnicodeRange(0x1F850, 0x1F859),
				//UnicodeRange(0x1F85A, 0x1F85F), //up unassigned 1
				UnicodeRange(0x1F860, 0x1F887),
				//UnicodeRange(0x1F888, 0x1F88F), //wide-headed unassigned 1
				UnicodeRange(0x1F890, 0x1F8AD),
				//UnicodeRange(0x1F8AE, 0x1F8FF), //white unassigned 1
				UnicodeRange(0x1F900, 0x1F90B),
				//UnicodeRange(0x1F90C, 0x1F90F), //downward unassigned 1
				UnicodeRange(0x1F910, 0x1F93E),
				//UnicodeRange(0x1F93F, 0x1F93F), //handball unassigned 1
				UnicodeRange(0x1F940, 0x1F94C),
				//UnicodeRange(0x1F94D, 0x1F94F), //curling unassigned 1
				UnicodeRange(0x1F950, 0x1F96B),
				//UnicodeRange(0x1F96C, 0x1F97F), //canned unassigned 1
				UnicodeRange(0x1F980, 0x1F997),
				//UnicodeRange(0x1F998, 0x1F9BF), //cricket unassigned 1
				UnicodeRange(0x1F9C0, 0x1F9C0),
				//UnicodeRange(0x1F9C1, 0x1F9CF), //cheese unassigned 1
				UnicodeRange(0x1F9D0, 0x1F9E6),
				//UnicodeRange(0x1F9E7, 0x1FFFF), //socks unassigned 1
				UnicodeRange(0x20000, 0x2A6D6),
				//UnicodeRange(0x2A6D7, 0x2A6FF), //cjk unassigned 1
				UnicodeRange(0x2A700, 0x2B734),
				//UnicodeRange(0x2B735, 0x2B73F), //cjk unassigned 2
				UnicodeRange(0x2B740, 0x2B81D),
				//UnicodeRange(0x2B81E, 0x2B81F), //cjk unassigned 3
				UnicodeRange(0x2B820, 0x2CEA1),
				//UnicodeRange(0x2CEA2, 0x2CEAF), //cjk unassigned 4
				UnicodeRange(0x2CEB0, 0x2EBE0),
				//UnicodeRange(0x2EBE1, 0x2F7FF), //cjk unassigned 5
				UnicodeRange(0x2F800, 0x2FA1D),
				//UnicodeRange(0x2FA1E, 0xE0000), //cjk unassigned 6
				UnicodeRange(0xE0001, 0xE0001),
				//UnicodeRange(0xE0002, 0xE001F), //language unassigned 1
				UnicodeRange(0xE0020, 0xE007F),
				//UnicodeRange(0xE0080, 0xE00FF), //cancel unassigned 1
				UnicodeRange(0xE0100, 0xE01EF),
				//UnicodeRange(0xE01F0, 0xEFFFF), //private use
				//UnicodeRange(0xF0000, 0xFFFFD), //unassigned 1
				//UnicodeRange(0xFFFFE, 0xFFFFF), //private use
				//UnicodeRange(0x100000, 0x10FFFD), //unassigned 2
				//UnicodeRange(0x10FFFE, 0x10FFFF), //unassigned 3
			)
		}
	},


	;

	operator fun contains(codePoint: Int): Boolean =
		ranges.any { codePoint in it }
}
