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

class Args3ComponentsTest {

	@Test
	fun component1() {
		val args = Args.of(
			"string",
			1,
			2L,
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
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
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
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
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val (_, _, a3) = args
		expect(a3).toEqual(args.a3)
	}

}