package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.first
import ch.tutteli.atrium.api.fluent.en_GB.second
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThanOrEqualTo
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.impl.createBoundsArbGenerator
import com.tegonal.minimalist.generators.impl.createIntDomainBasedBoundsArbGenerator
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInIntDomain
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInLongDomain
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.utils.BigInt
import com.tegonal.minimalist.utils.toBigInt
import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.Test

class ArbBoundsTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<Any> {
		val minSize2MaxSize2 = listOf('a' to 'b', 'b' to 'c', 'c' to 'd')
		val minSize1MaxSize2 = listOf('a' to 'a', 'b' to 'b', 'c' to 'c', 'd' to 'd') + minSize2MaxSize2
		return sequenceOf(
			Tuple(
				"charBounds minSize=1, maxSize=2",
				modifiedArb.charBounds('a', 'd', minSize = 1, maxSize = 2),
				minSize1MaxSize2
			),
			Tuple(
				"intBounds minSize=1, maxSize=2",
				modifiedArb.intBounds(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { it.first.code - 'a'.code + 1 to it.second.code - 'a'.code + 1 }
			),
			Tuple(
				"longBounds minSize=1, maxSize=2",
				modifiedArb.longBounds(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { (it.first.code - 'a'.code + 1).toLong() to it.second.code - 'a'.code + 1.toLong() }
			),
		)
	}

	@ParameterizedTest
	@ArgsSource("intSafeMinMaxAndMinSize")
	fun createIntMaxInIntDomain(minInclusive: Int, maxInclusive: Int, minSize: Int) {
		arb.createIntDomainBasedBoundsArbGenerator(
			minInclusive = minInclusive, maxInclusive = maxInclusive, minSize = minSize, maxSize = null
		) { s, l -> s to l }.generateAndTake(3).forEachIndexed { index, pair ->
			expect(index to pair) {
				second {
					first.toBeGreaterThanOrEqualTo(minInclusive).toBeLessThanOrEqualTo(maxInclusive)
					second.toBeGreaterThanOrEqualTo(pair.first).toBeLessThanOrEqualTo(maxInclusive)
				}
			}
		}
	}

	@ParameterizedTest
	@ArgsSource("intSafeMinMaxAndMinSize")
	fun createMaxInIntDomain(minInclusive: Int, maxInclusive: Int, minSize: Int) {
		val minInclusiveL = minInclusive.toLong()
		val maxInclusiveL = maxInclusive.toLong()
		arb.createBoundsArbGenerator(
			minInclusive = minInclusiveL, maxInclusive = maxInclusiveL, minSize = minSize.toLong(), maxSize = null
		) { s, l -> s to l }.generateAndTake(3).forEachIndexed { index, pair ->
			expect(index to pair) {
				second {
					first.toBeGreaterThanOrEqualTo(minInclusiveL).toBeLessThanOrEqualTo(maxInclusiveL)
					second.toBeGreaterThanOrEqualTo(pair.first).toBeLessThanOrEqualTo(maxInclusiveL)
				}
			}
		}
	}

	@ParameterizedTest
	@ArgsSource("intSafeMinMaxAndMinSize")
	fun createMaxInIntDomainButShifted(minInclusive: Int, maxInclusive: Int, minSize: Int) {
		val intMaxAsLong = Int.MAX_VALUE.toLong()
		val minInclusiveShifted = intMaxAsLong + minInclusive
		val maxInclusiveShifted = intMaxAsLong + maxInclusive
		arb.createBoundsArbGenerator(
			minInclusive = minInclusiveShifted,
			maxInclusive = maxInclusiveShifted,
			minSize = minSize.toLong(),
			maxSize = null
		) { s, l -> s to l }.generateAndTake(3).forEachIndexed { index, pair ->
			expect(index to pair) {
				second {
					first.toBeGreaterThanOrEqualTo(minInclusiveShifted).toBeLessThanOrEqualTo(maxInclusiveShifted)
					second.toBeGreaterThanOrEqualTo(pair.first).toBeLessThanOrEqualTo(maxInclusiveShifted)
				}
			}
		}
	}

	@ParameterizedTest
	@ArgsSource("longSafeMinMaxAndMinSize")
	fun createMaxInLongDomain(minInclusive: Long, maxInclusive: Long, minSize: Long) {
		arb.createBoundsArbGenerator(
			minInclusive = minInclusive, maxInclusive = maxInclusive, minSize = minSize, maxSize = null
		) { s, l -> s to l }.generateAndTake(3).forEachIndexed { index, pair ->
			expect(index to pair) {
				second {
					first.toBeGreaterThanOrEqualTo(minInclusive).toBeLessThanOrEqualTo(maxInclusive)
					second.toBeGreaterThanOrEqualTo(pair.first).toBeLessThanOrEqualTo(maxInclusive)
				}
			}
		}
	}

	@ParameterizedTest
	@ArgsSource("longUnsafeMinMaxAndSize")
	fun createMaxOutsideLongDomain(minInclusive: Long, maxInclusive: Long, minSize: Long) {
		arb.createBoundsArbGenerator(
			minInclusive = minInclusive, maxInclusive = maxInclusive, minSize = minSize, maxSize = null
		) { s, l -> s to l }.generateAndTake(3).forEachIndexed { index, pair ->
			expect(index to pair) {
				second {
					first.toBeGreaterThanOrEqualTo(minInclusive).toBeLessThanOrEqualTo(maxInclusive)
					second.toBeGreaterThanOrEqualTo(pair.first).toBeLessThanOrEqualTo(maxInclusive)
				}
			}
		}
	}

	@Test
	fun foo() {
		arb.createIntDomainBasedBoundsArbGenerator(
			minInclusive = -1561948995, maxInclusive = -1561948995, minSize = 1, maxSize = null
		) { s, l -> s to l }.generateAndTake(3).toList().let(::println)
	}

	//TODO 2.0.0 error in case of wrong min/max etc.

	companion object {
		@JvmStatic
		fun intSafeMinMaxAndMinSize() =
			intSafeMinMax().combineDependent {
				arb.intFromTo(1, it.second - it.first + 1)
			}

		@JvmStatic
		fun intSafeMinMax() =
			// we are not using arb.intRange here on purpose as we would use the function under test in the test-setup
			arb.intFromUntil(Int.MIN_VALUE, Int.MAX_VALUE - possibleMaxSizeSafeInIntDomain).combineDependent {
				arb.intFromUntil(it, it + possibleMaxSizeSafeInIntDomain)
			}

		@JvmStatic
		fun longSafeMinMaxAndMinSize() =
			longSafeMinMax().combineDependent {
				arb.longFromTo(1, it.second - it.first + 1)
			}

		@JvmStatic
		fun longSafeMinMax() =
			// we are not using arb.longRange here on purpose as we would use the function under test in the test-setup
			arb.longFromUntil(Long.MIN_VALUE, Long.MAX_VALUE - possibleMaxSizeSafeInLongDomain).combineDependent {
				// + possibleMaxSizeSafeInIntDomain as we could otherwise land in the Int-Domain
				arb.longFromUntil(it + possibleMaxSizeSafeInIntDomain, it + possibleMaxSizeSafeInLongDomain)
			}

		@JvmStatic
		fun longUnsafeMinMaxAndSize() =
			arb.longFromUntil(Long.MIN_VALUE, Long.MAX_VALUE - possibleMaxSizeSafeInLongDomain - 10).combineDependent {
				arb.longFromUntil(it + possibleMaxSizeSafeInLongDomain, Long.MAX_VALUE)
			}.combineDependent {
				arb.longFromTo(
					1,
					(it.second.toBigInt() - it.first.toBigInt() + BigInt.ONE).min(Long.MAX_VALUE.toBigInt()).toLong()
				)
			}
	}
}
