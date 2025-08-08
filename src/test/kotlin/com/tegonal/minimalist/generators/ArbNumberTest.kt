package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.withMockedRandom
import com.tegonal.minimalist.utils.BigInt
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

class ArbNumberTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		// we skip testing int(), long(), double(), intPositive, longPositive here, range is too big (see tests below)

		Tuple("intFromUntil", modifiedArb.intFromUntil(1, 5), listOf(1, 2, 3, 4)),
		Tuple("longFromUntil", modifiedArb.longFromUntil(1L, 3L), listOf(1L, 2L)),
		Tuple(
			"bigIntFromUntil",
			modifiedArb.bigIntFromUntil(BigInt.ONE, BigInt.valueOf(3L)),
			listOf(BigInt.ONE, BigInt.TWO)
		),
		// we cannot test doubleFromUntil as the result range is infinite, see test below

		Tuple("intFromTo", modifiedArb.intFromTo(1, 5), listOf(1, 2, 3, 4, 5)),
		Tuple("longFromTo", modifiedArb.longFromTo(1, 5), listOf(1L, 2L, 3L, 4L, 5L)),
		Tuple(
			"bigIntFromTo",
			modifiedArb.bigIntFromTo(BigInt.ONE, BigInt.valueOf(3L)),
			listOf(BigInt.ONE, BigInt.TWO, BigInt.valueOf(3))
		),
	)

	@Test
	fun int() {
		val ints = (0..10).shuffled()
		val arb = arb._components.withMockedRandom(ints = ints).arb
		expect(arb.int().generateAndTake(11).toList()).toEqual(ints)
	}

	@Test
	fun long() {
		val longs = (0L..10L).shuffled()
		val arb = arb._components.withMockedRandom(longs = longs).arb
		expect(arb.long().generateAndTake(11).toList()).toEqual(longs)
	}

	@Test
	fun double() {
		val doubles = (0..10L).shuffled().map { it.toDouble() }
		val arb = arb._components.withMockedRandom(doubles = doubles).arb
		expect(arb.double().generateAndTake(11).toList()).toEqual(doubles)
	}

	@Test
	fun intPositive() {
		arb.intPositive().generateAndTakeBasedOnDecider().forEach {
			expect(it).toBeGreaterThan(0)
		}
	}

	@Test
	fun longPositive() {
		arb.longPositive().generateAndTakeBasedOnDecider().forEach {
			expect(it).toBeGreaterThan(0)
		}
	}

	@ParameterizedTest
	@ArgsSource("fromUntils")
	fun doubleFromUntil(from: Double, until: Double) {
		arb.doubleFromUntil(from, until).generateAndTakeBasedOnDecider().forEach {
			expect(it).toBeGreaterThanOrEqualTo(from).toBeLessThan(until)
		}
	}

	companion object {
		@JvmStatic
		fun fromUntils() = arb.intFromUntil(Int.MIN_VALUE, Int.MAX_VALUE - 1).combineDependent {
			arb.intFromTo(it + 1, Int.MAX_VALUE)
		}
	}
}
