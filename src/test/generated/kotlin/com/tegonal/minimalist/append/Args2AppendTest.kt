// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.append

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

class Args2AppendTest {

	@Test
	fun `append Arg1`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			representation1 = "rep 3"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			representation3.toEqual(secondArgs.representation1)
		}
	}

	@Test
	fun `append Arg2`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			representation1 = "rep 3",
			representation2 = "rep 4"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
		}
	}

	@Test
	fun `append Arg3`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
		}
	}

	@Test
	fun `append Arg4`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5",
			representation4 = "rep 6"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			a6.toEqual(secondArgs.a4)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
			representation6.toEqual(secondArgs.representation4)
		}
	}

	@Test
	fun `append Arg5`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5",
			representation4 = "rep 6",
			representation5 = "rep 7"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			a6.toEqual(secondArgs.a4)
			a7.toEqual(secondArgs.a5)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
			representation6.toEqual(secondArgs.representation4)
			representation7.toEqual(secondArgs.representation5)
		}
	}

	@Test
	fun `append Arg6`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5",
			representation4 = "rep 6",
			representation5 = "rep 7",
			representation6 = "rep 8"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			a6.toEqual(secondArgs.a4)
			a7.toEqual(secondArgs.a5)
			a8.toEqual(secondArgs.a6)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
			representation6.toEqual(secondArgs.representation4)
			representation7.toEqual(secondArgs.representation5)
			representation8.toEqual(secondArgs.representation6)
		}
	}

	@Test
	fun `append Arg7`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5",
			representation4 = "rep 6",
			representation5 = "rep 7",
			representation6 = "rep 8",
			representation7 = "rep 9"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			a6.toEqual(secondArgs.a4)
			a7.toEqual(secondArgs.a5)
			a8.toEqual(secondArgs.a6)
			a9.toEqual(secondArgs.a7)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
			representation6.toEqual(secondArgs.representation4)
			representation7.toEqual(secondArgs.representation5)
			representation8.toEqual(secondArgs.representation6)
			representation9.toEqual(secondArgs.representation7)
		}
	}

	@Test
	fun `append Arg8`() {
		val firstArgs = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val secondArgs = Args.of(
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			3.toBigInteger(),
			representation1 = "rep 3",
			representation2 = "rep 4",
			representation3 = "rep 5",
			representation4 = "rep 6",
			representation5 = "rep 7",
			representation6 = "rep 8",
			representation7 = "rep 9",
			representation8 = "rep 10"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			a3.toEqual(secondArgs.a1)
			a4.toEqual(secondArgs.a2)
			a5.toEqual(secondArgs.a3)
			a6.toEqual(secondArgs.a4)
			a7.toEqual(secondArgs.a5)
			a8.toEqual(secondArgs.a6)
			a9.toEqual(secondArgs.a7)
			a10.toEqual(secondArgs.a8)
			representation3.toEqual(secondArgs.representation1)
			representation4.toEqual(secondArgs.representation2)
			representation5.toEqual(secondArgs.representation3)
			representation6.toEqual(secondArgs.representation4)
			representation7.toEqual(secondArgs.representation5)
			representation8.toEqual(secondArgs.representation6)
			representation9.toEqual(secondArgs.representation7)
			representation10.toEqual(secondArgs.representation8)
		}
	}

}