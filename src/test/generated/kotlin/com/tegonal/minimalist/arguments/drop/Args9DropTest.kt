// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.arguments.drop

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

class Args9DropTest {

	@Test
	fun dropArg1() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<Int, Long, Float, Double, Char, LocalDate, Short, Byte> = args.dropArg1()
		expect(argsResult) {
			a1.toEqual(args.a2)
			a2.toEqual(args.a3)
			a3.toEqual(args.a4)
			a4.toEqual(args.a5)
			a5.toEqual(args.a6)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation2)
			representation2.toEqual(args.representation3)
			representation3.toEqual(args.representation4)
			representation4.toEqual(args.representation5)
			representation5.toEqual(args.representation6)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg2() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Long, Float, Double, Char, LocalDate, Short, Byte> = args.dropArg2()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a3)
			a3.toEqual(args.a4)
			a4.toEqual(args.a5)
			a5.toEqual(args.a6)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation3)
			representation3.toEqual(args.representation4)
			representation4.toEqual(args.representation5)
			representation5.toEqual(args.representation6)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg3() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Float, Double, Char, LocalDate, Short, Byte> = args.dropArg3()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a4)
			a4.toEqual(args.a5)
			a5.toEqual(args.a6)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation4)
			representation4.toEqual(args.representation5)
			representation5.toEqual(args.representation6)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg4() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Double, Char, LocalDate, Short, Byte> = args.dropArg4()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a5)
			a5.toEqual(args.a6)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation5)
			representation5.toEqual(args.representation6)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg5() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Float, Char, LocalDate, Short, Byte> = args.dropArg5()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a6)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation6)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg6() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Float, Double, LocalDate, Short, Byte> = args.dropArg6()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a7)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation7)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg7() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Float, Double, Char, Short, Byte> = args.dropArg7()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a8)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation8)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg8() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Float, Double, Char, LocalDate, Byte> = args.dropArg8()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation9)
		}
	}

	@Test
	fun dropArg9() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			4.0,
			'c',
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult: Args8<String, Int, Long, Float, Double, Char, LocalDate, Short> = args.dropArg9()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
		}
	}

}