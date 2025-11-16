// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.variist.arguments.components

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

class Args5ComponentsTest {

	@Test
	fun component1() {
		val args = Args.of(
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
		val (a1) = args
		expect(a1).toEqual(args.a1)
	}

	@Test
	fun component2() {
		val args = Args.of(
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
		val (_, a2) = args
		expect(a2).toEqual(args.a2)
	}

	@Test
	fun component3() {
		val args = Args.of(
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
		val (_, _, a3) = args
		expect(a3).toEqual(args.a3)
	}

	@Test
	fun component4() {
		val args = Args.of(
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
		val (_, _, _, a4) = args
		expect(a4).toEqual(args.a4)
	}

	@Test
	fun component5() {
		val args = Args.of(
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
		val (_, _, _, _, a5) = args
		expect(a5).toEqual(args.a5)
	}

}
