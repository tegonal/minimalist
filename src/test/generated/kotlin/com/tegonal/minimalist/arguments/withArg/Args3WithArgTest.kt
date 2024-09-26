// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.minimalist.arguments.withArg


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

class Args3WithArgTest {

	@Test
	fun withArg1() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.withArg1(2, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(2)
			a2.toEqual(args.a2)
			a3.toEqual(args.a3)
			representation1.toEqual("new rep")
			representation2.toEqual(args.representation2)
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun withArg2() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.withArg2(3L, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(3L)
			a3.toEqual(args.a3)
			representation1.toEqual(args.representation1)
			representation2.toEqual("new rep")
			representation3.toEqual(args.representation3)
		}
	}

	@Test
	fun withArg3() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult = args.withArg3(4F, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual(1)
			a2.toEqual(2L)
			a3.toEqual(3F)
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
			representation3.toEqual("rep 3")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			a3.toEqual(4F)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
			representation3.toEqual("new rep")
		}
	}

}