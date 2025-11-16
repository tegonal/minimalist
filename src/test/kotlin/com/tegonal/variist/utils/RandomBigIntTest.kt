package com.tegonal.variist.utils

import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.expect
import org.junit.jupiter.api.Test
import kotlin.random.Random

class RandomBigIntTest {

	@Test
	fun checkRejectionSampling() {
		val r = Random
		val toExclusive = Long.MAX_VALUE.toBigInt() + BigInt.ONE
		repeat(10) {
			expect(r.nextBigInt(toExclusive)).toBeLessThan(toExclusive)
		}
	}
}
