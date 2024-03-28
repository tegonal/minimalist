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

class Args4ArgumentsTest {

	@Test
	fun `get returns correct array and value not wrapped in Named if representation not specified`() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F
		)
		expect(args.get().toList()).toContainExactly(
			args.a1,
			args.a2,
			args.a3,
			args.a4
		)
	}
	@Test
	fun `get returns correct array and value wrapped in Named if representation specified`() {
		val args = Args.of(
			"string",
			1,
			2L,
			3F,
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4"
		)
		expect(args.get().toList()).toContainExactly(
			{
				toBeANamedOf<String>(args.representation1!!, args.a1)
			},
			{
				toBeANamedOf<Int>(args.representation2!!, args.a2)
			},
			{
				toBeANamedOf<Long>(args.representation3!!, args.a3)
			},
			{
				toBeANamedOf<Float>(args.representation4!!, args.a4)
			}
		)
	}

	@ParameterizedTest
	@MethodSource("args")
	fun `can use Args4 in MethodSource`(
		a1: String,
		a2: Int,
		a3: Long,
		a4: Float
	) {
		expect(a1).toEqual("string")
		expect(a2).toEqual(1)
		expect(a3).toEqual(2L)
		expect(a4).toEqual(3F)
	}

	companion object {
		@JvmStatic
		fun args() = listOf(Args.of("string", 1, 2L, 3F))
	}
}