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

class Args3MapArgTest {

	@Test
	fun mapArg1() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg1 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			representation1.toEqual(null)
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun mapArg1WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg1WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(1)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			representation1.toEqual("rep 1 modified")
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun mapArg2() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg2 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			representation1.toEqual(args.representation1)
			representation2.toEqual(null)
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun mapArg2WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg2WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2L)
			a3.toEqual(args.a3)
			representation1.toEqual(args.representation1)
			representation2.toEqual("rep 2 modified")
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun mapArg3() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg3 { it.first() }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual(null)
		}
	}

	@Test
	fun mapArg3WithRepresentation() {
		val args = Args.of(
			listOf(1),
			listOf(2L),
			listOf(3F),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.mapArg3WithRepresentation { arg, repr -> arg.first() to "$repr modified" }

		// no changes to args
		expect(args) {
			a1.toEqual(listOf(1))
			a2.toEqual(listOf(2L))
			a3.toEqual(listOf(3F))
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(3F)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual("rep 3 modified")
		}
	}

}