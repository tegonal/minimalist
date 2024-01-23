// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
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

class Args7AppendTest {

	@Test
	fun `append Arg1`() {
		val firstArgs = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7"
		)
		val secondArgs = Args.of(
			1.toShort(),
			representation1 = "rep 8"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			a6.toEqual(firstArgs.a6)
			a7.toEqual(firstArgs.a7)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			representation6.toEqual(firstArgs.representation6)
			representation7.toEqual(firstArgs.representation7)
			a8.toEqual(secondArgs.a1)
			representation8.toEqual(secondArgs.representation1)
		}
	}

	@Test
	fun `append Arg2`() {
		val firstArgs = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7"
		)
		val secondArgs = Args.of(
			1.toShort(),
			2.toByte(),
			representation1 = "rep 8",
			representation2 = "rep 9"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			a6.toEqual(firstArgs.a6)
			a7.toEqual(firstArgs.a7)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			representation6.toEqual(firstArgs.representation6)
			representation7.toEqual(firstArgs.representation7)
			a8.toEqual(secondArgs.a1)
			a9.toEqual(secondArgs.a2)
			representation8.toEqual(secondArgs.representation1)
			representation9.toEqual(secondArgs.representation2)
		}
	}

	@Test
	fun `append Arg3`() {
		val firstArgs = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7"
		)
		val secondArgs = Args.of(
			1.toShort(),
			2.toByte(),
			3.toBigInteger(),
			representation1 = "rep 8",
			representation2 = "rep 9",
			representation3 = "rep 10"
		)
		val argsResult = firstArgs.append(secondArgs)
		expect(argsResult) {
			a1.toEqual(firstArgs.a1)
			a2.toEqual(firstArgs.a2)
			a3.toEqual(firstArgs.a3)
			a4.toEqual(firstArgs.a4)
			a5.toEqual(firstArgs.a5)
			a6.toEqual(firstArgs.a6)
			a7.toEqual(firstArgs.a7)
			representation1.toEqual(firstArgs.representation1)
			representation2.toEqual(firstArgs.representation2)
			representation3.toEqual(firstArgs.representation3)
			representation4.toEqual(firstArgs.representation4)
			representation5.toEqual(firstArgs.representation5)
			representation6.toEqual(firstArgs.representation6)
			representation7.toEqual(firstArgs.representation7)
			a8.toEqual(secondArgs.a1)
			a9.toEqual(secondArgs.a2)
			a10.toEqual(secondArgs.a3)
			representation8.toEqual(secondArgs.representation1)
			representation9.toEqual(secondArgs.representation2)
			representation10.toEqual(secondArgs.representation3)
		}
	}

}