// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.arguments.annotation

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

class Args1ArgumentsTest {

	@Test
	fun `get returns correct array and value not wrapped in Named if representation not specified`() {
		val args = Args.of(
			"string"
		)
		expect(args.get().toList()).toContainExactly(
			args.a1
		)
	}
	@Test
	fun `get returns correct array and value wrapped in Named if representation specified`() {
		val args = Args.of(
			"string",
			representation1 = "rep 1"
		)
		expect(args.get().toList()).toContainExactly(
			{
				toBeANamedOf<String>(args.representation1!!, args.a1)
			}
		)
	}

	@ParameterizedTest
	@MethodSource("args")
	fun `can use Args1 in MethodSource`(
		a1: String
	) {
		expect(a1).toEqual("string")
	}

	companion object {
		@JvmStatic
		fun args() = listOf(Args.of("string"))
	}
}