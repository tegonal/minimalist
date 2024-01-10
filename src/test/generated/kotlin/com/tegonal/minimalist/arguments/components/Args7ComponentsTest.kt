// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.arguments.components

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

class Args7ComponentsTest {

	@Test
	fun component1() {
		val args = Args.of(
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
		val (a1) = args
		expect(a1).toEqual(args.a1)
	}

	@Test
	fun component2() {
		val args = Args.of(
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
		val (_, a2) = args
		expect(a2).toEqual(args.a2)
	}

	@Test
	fun component3() {
		val args = Args.of(
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
		val (_, _, a3) = args
		expect(a3).toEqual(args.a3)
	}

	@Test
	fun component4() {
		val args = Args.of(
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
		val (_, _, _, a4) = args
		expect(a4).toEqual(args.a4)
	}

	@Test
	fun component5() {
		val args = Args.of(
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
		val (_, _, _, _, a5) = args
		expect(a5).toEqual(args.a5)
	}

	@Test
	fun component6() {
		val args = Args.of(
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
		val (_, _, _, _, _, a6) = args
		expect(a6).toEqual(args.a6)
	}

	@Test
	fun component7() {
		val args = Args.of(
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
		val (_, _, _, _, _, _, a7) = args
		expect(a7).toEqual(args.a7)
	}

}