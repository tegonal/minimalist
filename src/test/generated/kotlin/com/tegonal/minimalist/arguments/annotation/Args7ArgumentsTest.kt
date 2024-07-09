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

class Args7ArgumentsTest {

	@Test
	fun `get returns correct array and value not wrapped in Named if representation not specified`() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now()
		)
		expect(args.get().toList()).toContainExactly(
			args.a1,
			args.a2,
			args.a3,
			args.a4,
			args.a5,
			args.a6,
			args.a7
		)
	}
	@Test
	fun `get returns correct array and value wrapped in Named if representation specified`() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			representation1 = Representation("rep 1"),
			representation2 = "rep 2",
			representation3 = "rep 3",
			representation4 = "rep 4",
			representation5 = "rep 5",
			representation6 = "rep 6",
			representation7 = "rep 7"
		)
		expect(args.get().toList()).toContainExactly(
			{
				toBeANamedOf<Int>(args.representation1!!, args.a1)
			},
			{
				toBeANamedOf<Long>(args.representation2!!, args.a2)
			},
			{
				toBeANamedOf<Float>(args.representation3!!, args.a3)
			},
			{
				toBeANamedOf<Double>(args.representation4!!, args.a4)
			},
			{
				toBeANamedOf<Char>(args.representation5!!, args.a5)
			},
			{
				toBeANamedOf<String>(args.representation6!!, args.a6)
			},
			{
				toBeANamedOf<LocalDate>(args.representation7!!, args.a7)
			}
		)
	}

	@Test
	fun `using null as representation does not wrap it into Named`() {
		val args = Args.of(
			1,
			2L,
			3F,
			4.0,
			'c',
			"string",
			LocalDate.now(),
			representation1 = null ,
			representation2 = null ,
			representation3 = null ,
			representation4 = null ,
			representation5 = null ,
			representation6 = null ,
			representation7 = null 
		)
		expect(args.get().toList()).toContainExactly(
			args.a1,
			args.a2,
			args.a3,
			args.a4,
			args.a5,
			args.a6,
			args.a7
		)
	}

	@ParameterizedTest
	@MethodSource("args")
	fun `can use Args7 in MethodSource`(
		a1: Int,
		a2: Long,
		a3: Float,
		a4: Double,
		a5: Char,
		a6: String,
		a7: LocalDate
	) {
		expect(a1).toEqual(1)
		expect(a2).toEqual(2L)
		expect(a3).toEqual(3F)
		expect(a4).toEqual(4.0)
		expect(a5).toEqual('c')
		expect(a6).toEqual("string")
		expect(a7).toEqual(LocalDate.now())
	}

	companion object {
		@JvmStatic
		fun args() = listOf(Args.of(1, 2L, 3F, 4.0, 'c', "string", LocalDate.now()))
	}
}