// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.arguments.mapArg

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

class Args5MapArgTest {

	@Test
	fun mapArg1() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			listOf(4.0),
			listOf('c'),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg1 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual(null)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg1WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual("rep 1 modified")
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg2 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual(null)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg2WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual("rep 2 modified")
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg3 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(null)
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg3WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			a4.toEqual(args.a4)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual("rep 3 modified")
			representation4.toEqual(args.representation4)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg4 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(4.0)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(null)
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg4WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(4.0)
			a5.toEqual(args.a5)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual("rep 4 modified")
			representation5.toEqual(args.representation5)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg5 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual('c')
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual(null)
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
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5"
		)
		val argsResult = args.mapArg5WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			a4.toEqual(listOf(4.0))
			a5.toEqual(listOf('c'))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
			representation4.toEqual("rep 4")
			representation5.toEqual("rep 5")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			a4.toEqual(args.a4)
			a5.toEqual('c')
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
			representation4.toEqual(args.representation4)
			representation5.toEqual("rep 5 modified")
		}
	}

}