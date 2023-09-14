// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.drop

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Named
import com.tegonal.minimalist.*
import com.tegonal.minimalist.atrium.*
import java.math.BigInteger
import java.time.LocalDate

class Args3DropTest {

	@Test
	fun dropArg1() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult: Args2<Long, Float> = args.dropArg1()
		expect(argsResult) {
			a1.toEqual(args.a2)
			a2.toEqual(args.a3)
			representation1.toEqual(args.representation2)
			representation2.toEqual(args.representation3)
		}
	}

	@Test
	fun dropArg2() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult: Args2<Int, Float> = args.dropArg2()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a3)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation3)
		}
	}

	@Test
	fun dropArg3() {
		val args = Args.of(
			1,
			2L,
			3F,
			representation1 = "rep 1",
			representation2 = "rep 2",
			representation3 = "rep 3"
		)
		val argsResult: Args2<Int, Long> = args.dropArg3()
		expect(argsResult) {
			a1.toEqual(args.a1)
			a2.toEqual(args.a2)
			representation1.toEqual(args.representation1)
			representation2.toEqual(args.representation2)
		}
	}

}