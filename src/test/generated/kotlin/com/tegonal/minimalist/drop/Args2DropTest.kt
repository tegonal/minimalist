// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.drop

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

class Args2DropTest {

	@Test
	fun dropArg1() {
		val args = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val argsResult: Args1<Int> = args.dropArg1()
		expect(argsResult) {
			a1.toEqual(args.a2)
			representation1.toEqual(args.representation2)
		}
	}

	@Test
	fun dropArg2() {
		val args = Args.of(
			"string",
			1,
			representation1 = "rep 1",
			representation2 = "rep 2"
		)
		val argsResult: Args1<String> = args.dropArg2()
		expect(argsResult) {
			a1.toEqual(args.a1)
			representation1.toEqual(args.representation1)
		}
	}

}