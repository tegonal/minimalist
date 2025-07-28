package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest

class ArbArgsGeneratorNumericRangeTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		// we skip testing int(), long(), double()
		Tuple("intFromUntil", modifiedArb.intFromUntil(1, 5), listOf(1, 2, 3, 4, 5)),
		Tuple("longFromUntil", modifiedArb.longFromUntil(1L, 3L), listOf(1L, 2L, 3L)),
		// we cannot test doubleFromUntil as the result range is infinite, see test below
	)
}

class DoubleFromUntilTest {

	@ParameterizedTest
	@ArgsSource("fromUntils")
	fun doubleFromUntil(from: Double, until: Double) {
		val g = arb.doubleFromUntil(from, until)
		g.generate().take(1000).forEach {
			expect(it).toBeGreaterThanOrEqualTo(from).toBeLessThan(until)
		}
	}

	companion object {
		@JvmStatic
		fun fromUntils() = arb.intFromUntil(Int.MIN_VALUE, Int.MAX_VALUE - 1).combineDependent {
			arb.intFromUntil(it + 1, Int.MAX_VALUE)
		}
	}
}
