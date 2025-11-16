// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.variist.arguments.withArg

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import com.tegonal.variist.*
import com.tegonal.variist.testutils.atrium.*
import java.math.BigInteger
import java.time.LocalDate

class Args9WithArgTest {

	@Test
	fun withArg1() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg1(2, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(2)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual("new rep")
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg2() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg2(3L, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(3L)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual("new rep")
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg3() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg3(4F, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(4F)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual("new rep")
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg4() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg4(5.0, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(5.0)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual("new rep")
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg5() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg5('d', "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual('d')
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual("new rep")
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg6() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg6("another string", "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual("another string")
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual("new rep")
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg7() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg7(LocalDate.now().plusDays(2), "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(LocalDate.now().plusDays(2))
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual("new rep")
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg8() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg8(2.toShort(), "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(2.toShort())
			a9.toEqual(args.a9)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual("new rep")
			representation9.toEqual(args.representation9)
		}
	}

	@Test
	fun withArg9() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			1.toShort(),
			2.toByte(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9"
		)
		val argsResult = args.withArg9(3.toByte(), "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			a4.toEqual(4.0)
			a5.toEqual('c')
			a6.toEqual("string")
			a7.toEqual(LocalDate.now())
			a8.toEqual(1.toShort())
			a9.toEqual(2.toByte())
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(3.toByte())
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual("new rep")
		}
	}

}
