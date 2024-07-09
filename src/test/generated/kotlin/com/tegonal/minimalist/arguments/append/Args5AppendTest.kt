// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.arguments.append

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import com.tegonal.minimalist.*
import com.tegonal.minimalist.atrium.*
import java.math.BigInteger
import java.time.LocalDate

class Args5AppendTest {

	@Test
	fun `append Args1`() {
		val firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val secondArgs = Args.of(
			"string",
			representation1 = Representation("rep 6")
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			a6.toEqual(secondArgs.a1)
			representation6.toEqual(secondArgs.representation1)
		}
	}

	@Test
	fun `append Args2`() {
		val firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val secondArgs = Args.of(
			"string",
			LocalDate.now(),
			representation1 = Representation("rep 6"),
			representation2 = "rep 7"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			a6.toEqual(secondArgs.a1)
			a7.toEqual(secondArgs.a2)
			representation6.toEqual(secondArgs.representation1)
			representation7.toEqual(secondArgs.representation2)
		}
	}

	@Test
	fun `append Args3`() {
		val firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val secondArgs = Args.of(
			"string",
			LocalDate.now(),
			1.toShort(),
			representation1 = Representation("rep 6"),
			representation2 = "rep 7",
			representation3 = "rep 8"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			a6.toEqual(secondArgs.a1)
			a7.toEqual(secondArgs.a2)
			a8.toEqual(secondArgs.a3)
			representation6.toEqual(secondArgs.representation1)
			representation7.toEqual(secondArgs.representation2)
			representation8.toEqual(secondArgs.representation3)
		}
	}

	@Test
	fun `append Args4`() {
		val firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val secondArgs = Args.of(
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 6"),
			representation2 = "rep 7",
			representation3 = "rep 8",
			representation4 = "rep 9"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			a6.toEqual(secondArgs.a1)
			a7.toEqual(secondArgs.a2)
			a8.toEqual(secondArgs.a3)
			a9.toEqual(secondArgs.a4)
			representation6.toEqual(secondArgs.representation1)
			representation7.toEqual(secondArgs.representation2)
			representation8.toEqual(secondArgs.representation3)
			representation9.toEqual(secondArgs.representation4)
		}
	}

	@Test
	fun `append Args5`() {
		val firstArgs = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val secondArgs = Args.of(
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			3.toBigInteger(),
			representation1 = Representation("rep 6"),
			representation2 = "rep 7",
			representation3 = "rep 8",
			representation4 = "rep 9",
			representation5 = "rep 10"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			a6.toEqual(secondArgs.a1)
			a7.toEqual(secondArgs.a2)
			a8.toEqual(secondArgs.a3)
			a9.toEqual(secondArgs.a4)
			a10.toEqual(secondArgs.a5)
			representation6.toEqual(secondArgs.representation1)
			representation7.toEqual(secondArgs.representation2)
			representation8.toEqual(secondArgs.representation3)
			representation9.toEqual(secondArgs.representation4)
			representation10.toEqual(secondArgs.representation5)
		}
	}

}