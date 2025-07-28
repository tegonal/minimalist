// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.minimalist.arguments.mapArg

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import com.tegonal.minimalist.*
import com.tegonal.minimalist.testutils.atrium.*
import java.math.BigInteger
import java.time.LocalDate

class Args10MapArgTest {

	@Test
	fun mapArg1() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg1 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(null)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg1WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg1WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual("rep 1 modified")
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg2() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg2 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(null)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg2WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg2WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual("rep 2 modified")
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg3() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg3 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(null)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg3WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg3WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual("rep 3 modified")
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg4() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg4 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(4.0)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(null)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg4WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg4WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(4.0)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual("rep 4 modified")
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg5() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg5 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual('c')
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(null)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg5WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg5WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual('c')
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual("rep 5 modified")
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg6() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg6 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual("string")
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(null)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg6WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg6WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual("string")
			a7.toEqual(args.a7)
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual("rep 6 modified")
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg7() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg7 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(LocalDate.now())
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(null)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg7WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg7WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(LocalDate.now())
			a8.toEqual(args.a8)
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual("rep 7 modified")
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg8() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg8 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(1.toShort())
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(null)
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg8WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg8WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			a6.toEqual(args.a6)
			a7.toEqual(args.a7)
			a8.toEqual(1.toShort())
			a9.toEqual(args.a9)
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual("rep 8 modified")
			representation9.toEqual(args.representation9)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg9() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg9 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
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
			a9.toEqual(2.toByte())
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(null)
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg9WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg9WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
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
			a9.toEqual(2.toByte())
			a10.toEqual(args.a10)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual("rep 9 modified")
			representation10.toEqual(args.representation10)
		}
	}

	@Test
	fun mapArg10() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg10 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
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
			a9.toEqual(args.a9)
			a10.toEqual(3.toBigInteger())
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual(null)
		}
	}

	@Test
	fun mapArg10WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			listOf("string"),
			listOf(LocalDate.now()),
			listOf(1.toShort()),
			listOf(2.toByte()),
			listOf(3.toBigInteger()),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7",
			representation8 = "rep 8",
			representation9 = "rep 9",
			representation10 = "rep 10"
		)
		val argsResult = args.mapArg10WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			a6.toEqual(listOf("string"))
			a7.toEqual(listOf(LocalDate.now()))
			a8.toEqual(listOf(1.toShort()))
			a9.toEqual(listOf(2.toByte()))
			a10.toEqual(listOf(3.toBigInteger()))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
			representation6.toEqual("rep 6")
			representation7.toEqual("rep 7")
			representation8.toEqual("rep 8")
			representation9.toEqual("rep 9")
			representation10.toEqual("rep 10")
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
			a9.toEqual(args.a9)
			a10.toEqual(3.toBigInteger())
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
			representation6.toEqual(args.representation6)
			representation7.toEqual(args.representation7)
			representation8.toEqual(args.representation8)
			representation9.toEqual(args.representation9)
			representation10.toEqual("rep 10 modified")
		}
	}

}