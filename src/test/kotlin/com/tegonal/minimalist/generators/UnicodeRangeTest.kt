package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Test

class UnicodeRangeTest {

	@Test
	fun charsCorrectlyAssigned() {
		val tabToCr = 0x0009..0x000D
		val fileToUnitSeparator = 0x001C..0x001F

		expectGrouped {
			(0..UnicodeRange.MAX_CODE_POINT).forEach { codePoint ->
				val charType = Character.getType(codePoint)
				expect(codePoint) {
					feature(
						"${"0x%04X".format(codePoint)} (${String(Character.toChars(codePoint))}) with character type $charType",
						{ this }
					) {
						when {
							charType == Character.PRIVATE_USE.toInt() -> {
								toBeIn {
									it == UnicodeRanges.PRIVATE_USE ||
										it == UnicodeRanges.UTF_8 ||
										it == UnicodeRanges.ALL
								}
							}

							charType == Character.UNASSIGNED.toInt() -> {
								toBeIn {
									it == UnicodeRanges.UNASSIGNED ||
										it == UnicodeRanges.UTF_8 ||
										it == UnicodeRanges.ALL
								}
								feature("Char.isDefined") { Character.isDefined(this) }.toEqual(false)
							}

							charType == Character.CONTROL.toInt() -> {
								toBeIn {
									it == UnicodeRanges.CONTROL ||
										it == UnicodeRanges.UTF_8 ||
										it == UnicodeRanges.ALL ||
										codePoint <= UnicodeRange.ASCII_END && it == UnicodeRanges.ASCII ||
										codePoint <= UnicodeRange.ISO_8859_1_END && it == UnicodeRanges.ISO_8859_1 ||
										// Unicode considers TAB and co as control, same for file separator and co.
										// Kotlin includes them in char.isWhitespace
										(codePoint in tabToCr || codePoint in fileToUnitSeparator) && it == UnicodeRanges.WHITESPACES_KOTLIN
								}
							}

							charType == Character.SPACE_SEPARATOR.toInt() ||
								charType == Character.PARAGRAPH_SEPARATOR.toInt() ||
								Character.isWhitespace(codePoint) || Character.isSpaceChar(codePoint) -> toBeIn {
								it == UnicodeRanges.WHITESPACES_KOTLIN ||
									(codePoint <= UnicodeRange.ASCII_END && (it == UnicodeRanges.ASCII || it == UnicodeRanges.ASCII_PRINTABLE)) ||
									(codePoint <= UnicodeRange.ISO_8859_1_END && (it == UnicodeRanges.ISO_8859_1 || it == UnicodeRanges.ISO_8859_1_PRINTABLE)) ||
									it == UnicodeRanges.UTF_8 ||
									it == UnicodeRanges.UTF_8_PRINTABLE ||
									it == UnicodeRanges.ALL
							}

							charType == Character.SURROGATE.toInt() -> toBeIn {
								it == UnicodeRanges.SURROGATES ||
									it == UnicodeRanges.ALL
							}

							else -> {
								toBeIn {
									codePoint <= UnicodeRange.ASCII_END && (it == UnicodeRanges.ASCII || it == UnicodeRanges.ASCII_PRINTABLE) ||
										codePoint <= UnicodeRange.ISO_8859_1_END && (it == UnicodeRanges.ISO_8859_1 || it == UnicodeRanges.ISO_8859_1_PRINTABLE) ||
										it == UnicodeRanges.UTF_8 ||

										(
											// zulu and dragonwell jdk are not up-to-date and return charType 2 (lower letter) instead of unassigned
											codePoint == 0x0560 || codePoint == 0x0588 ||
												// Microsoft and SAP jdk are not up-to-date and return charType 5 (other letter) instead of unassigned
												codePoint == 0x05EF
											) && it == UnicodeRanges.UNASSIGNED ||
										(codePoint != 0x0560 && codePoint != 0x0588 && codePoint != 0x05EF && it == UnicodeRanges.UTF_8_PRINTABLE) ||
										it == UnicodeRanges.ALL
								}
							}
						}
					}
				}
			}
		}
	}

//	enum class UnicodeType(val text: String) {
//		UNASSIGNED("unassigned"),
//		CONTROL("control"),
//		PRIVATE_USE("private use"),
//		SURROGATE("surrogate"),
//		PRINTABLE("printable"),
//	}
//
//	@Test
//	fun generateLists() {
//		val utf8Printable = StringBuilder()
//		val unassigned = StringBuilder()
//		val control = StringBuilder()
//		val privateUse = StringBuilder()
//
//		var start = 0
//		var currentName = "<not-defined>"
//		var counter = 1
//		var currentType = UnicodeType.CONTROL
//		var currentComment = UnicodeType.CONTROL.text
//
//
//		fun unicodeRange(start: Int, end: Int) =
//			"UnicodeRange(0x" + "%04X".format(start) + ", 0x" + "%04X".format(end) + "), "
//
//		fun appendUtf8(codePoint: Int) {
//			if (currentType != UnicodeType.PRINTABLE) utf8Printable.append("//")
//			utf8Printable.append(unicodeRange(start, codePoint - 1))
//			if (currentType != UnicodeType.PRINTABLE) utf8Printable.append("//").append(currentComment)
//			utf8Printable.append("\n")
//		}
//
//		fun appendNonUtf8(codePoint: Int) {
//			when (currentType) {
//				UnicodeType.UNASSIGNED -> unassigned
//				UnicodeType.CONTROL -> control
//				UnicodeType.PRIVATE_USE -> privateUse
//				UnicodeType.SURROGATE,
//				UnicodeType.PRINTABLE -> null
//			}?.also {
//				it.append(unicodeRange(start, codePoint - 1))
//				if (currentType == UnicodeType.UNASSIGNED) it.append("// ").append(currentComment)
//				it.append("\n")
//			}
//		}
//
//		fun updateComment(codePoint: Int, type: UnicodeType) {
//			if (type != UnicodeType.PRINTABLE) {
//				currentComment = if (type == UnicodeType.UNASSIGNED) {
//					val name =
//						(com.ibm.icu.lang.UCharacter.getName(codePoint - 1)?.substringBefore(" ")?.lowercase()
//							?.let { "$it " }) ?: ""
//					if (currentName == name) {
//						++counter
//					} else {
//						currentName = name
//						counter = 1
//					}
//					"${currentName}${UnicodeType.UNASSIGNED.text} $counter"
//				} else {
//					type.text
//				}
//			}
//		}
//
//		fun determineUnicodeType(codePoint: Int): UnicodeType = when (Character.getType(codePoint)) {
//			Character.UNASSIGNED.toInt() -> UnicodeType.UNASSIGNED
//			Character.CONTROL.toInt() -> UnicodeType.CONTROL
//			Character.PRIVATE_USE.toInt() -> UnicodeType.PRIVATE_USE
//			Character.SURROGATE.toInt() -> UnicodeType.SURROGATE
//			else -> UnicodeType.PRINTABLE
//		}
//
//		(0..UnicodeRange.MAX_CODE_POINT).forEach { codePoint ->
//			val type = determineUnicodeType(codePoint)
//			if (type != currentType) {
//				appendNonUtf8(codePoint)
//				updateComment(codePoint, type)
//				appendUtf8(codePoint)
//
//				currentType = type
//				@Suppress("AssignedValueIsNeverRead") // intellij bug, start is used in the functions
//				start = codePoint
//			}
//		}
//		val type = determineUnicodeType(UnicodeRange.MAX_CODE_POINT)
//		appendNonUtf8(UnicodeRange.MAX_CODE_POINT)
//		updateComment(UnicodeRange.MAX_CODE_POINT, type)
//		appendUtf8(UnicodeRange.MAX_CODE_POINT)
//
//		println("control:\n$control\n\n private use:\n$privateUse\n\nunassigned:\n$unassigned\n\nutf8Printable:\n$utf8Printable")
//	}

	private fun Expect<Int>.toBeIn(predicate: (UnicodeRanges) -> Boolean) {
		UnicodeRanges.entries.partition(predicate).let { (rangesIn, rangesNotIn) ->
			rangesIn.forEach { toBeIn(it) }
			rangesNotIn.forEach { notToBeIn(it) }
		}
	}

	private fun Expect<Int>.toBeIn(unicodeRanges: UnicodeRanges) {
		feature("is in ${unicodeRanges.name}") { this in unicodeRanges }.toEqual(true)
	}

	private fun Expect<Int>.notToBeIn(unicodeRanges: UnicodeRanges) {
		feature("is in ${unicodeRanges.name}") { this in unicodeRanges }.toEqual(false)
	}
}
