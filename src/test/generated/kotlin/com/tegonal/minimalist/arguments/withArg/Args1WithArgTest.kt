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

class Args1WithArgTest {

	@Test
	fun withArg1() {
		val args = Args.of(
			"string",
			representation1 = "rep 1"
		)
		val argsResult = args.withArg1("another string", "new rep")

		// no changes to args
		expect(args) {
			a1.toEqual("string")
			representation1.toEqual("rep 1")
		}

		expect(argsResult) {
			a1.toEqual("another string")
			representation1.toEqual("new rep")
		}
	}

}