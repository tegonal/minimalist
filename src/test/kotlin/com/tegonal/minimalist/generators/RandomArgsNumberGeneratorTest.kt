package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest

class RandomArgsGeneratorNumericRangeTest : AbstractRandomArgsGeneratorTest<Any>() {

    override fun createGenerators() = sequenceOf(
        Tuple("intFromUntil", modifiedRandom.intFromUntil(1, 5), listOf(1, 2, 3, 4, 5)),
        Tuple("longFromUntil", modifiedRandom.longFromUntil(1L, 3L), listOf(1L, 2L, 3L)),
        // we cannot test doubleFromUntil as the result range is infinite, see test below
    )
}

class DoubleFromUntilTest {

    @ParameterizedTest
    @ArgsSource("fromUntils")
    fun doubleFromUntil(from: Double, until: Double) {
        val g = random.doubleFromUntil(from, until)
        g.generate().take(1000).forEach {
            expect(it).toBeGreaterThanOrEqualTo(from).toBeLessThan(until)
        }
    }

    companion object {
        @JvmStatic
        fun fromUntils() = random.intFromUntil(Int.MIN_VALUE, Int.MAX_VALUE - 1).combineDependent {
            random.intFromUntil(it + 1, Int.MAX_VALUE)
        }
    }
}
