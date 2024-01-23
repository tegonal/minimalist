// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
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

class Args2WithArgTest {

	@Test
	fun withArg1() {
		val args = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val argsResult = args.withArg1("another string", "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual("string")
			a2.toEqual(1)
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
		}

		expect(argsResult) {
			a1.toEqual("another string")
			a2.toEqual(args.a2)
			representation1.toEqual("new rep")
			representation2.toEqual(args.representation2)
		}
	}

	@Test
	fun withArg2() {
		val args = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val argsResult = args.withArg2(2, "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual("string")
			a2.toEqual(1)
			representation1.toEqual("rep 1")
			representation2.toEqual("rep 2")
		}

		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(2)
			representation1.toEqual(args.representation1)
			representation2.toEqual("new rep")
		}
	}

}