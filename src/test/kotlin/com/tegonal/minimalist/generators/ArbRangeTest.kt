package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.impl.createClosedRangeArbGenerator
import com.tegonal.minimalist.generators.impl.createIntDomainBasedClosedRangeArbGenerator
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInIntDomain
import com.tegonal.minimalist.generators.impl.possibleMaxSizeSafeInLongDomain
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.utils.BigInt
import org.junit.jupiter.params.ParameterizedTest
import kotlin.math.min
import kotlin.test.Test

class ArbRangeTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint): ArbArgsTestFactoryResult<Any> {
		val minSize2MaxSize2 = listOf('a'..'b', 'b'..'c', 'c'..'d')
		val minSize1MaxSize2 = listOf('a'..'a', 'b'..'b', 'c'..'c', 'd'..'d') + minSize2MaxSize2
		val minSize0MaxSize2 = listOf('d'..'a') + minSize1MaxSize2
		return sequenceOf(
			Tuple(
				"charRange minSize=0, maxSize=2",
				modifiedArb.charRange('a', 'd', maxSize = 2),
				minSize0MaxSize2
			),
			Tuple(
				"charRange minSize=1, maxSize=2",
				modifiedArb.charRange('a', 'd', minSize = 1, maxSize = 2),
				minSize1MaxSize2
			),
			Tuple(
				"intRange minSize=0, maxSize=2",
				modifiedArb.intRange(1, 4, maxSize = 2),
				minSize0MaxSize2.map { it.start.code - 'a'.code + 1..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"intRange minSize=1, maxSize=2",
				modifiedArb.intRange(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { it.start.code - 'a'.code + 1..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"longRange minSize=0, maxSize=2",
				modifiedArb.longRange(1, 4, maxSize = 2),
				minSize0MaxSize2.map { (it.start.code - 'a'.code + 1).toLong()..it.last.code - 'a'.code + 1 }
			),
			Tuple(
				"longRange minSize=1, maxSize=2",
				modifiedArb.longRange(1, 4, minSize = 1, maxSize = 2),
				minSize1MaxSize2.map { (it.start.code - 'a'.code + 1).toLong()..it.last.code - 'a'.code + 1 }
			),
		)
	}

	@ParameterizedTest
	@ArgsSource("intSafeMinMaxAndMinSize")
	fun createIntMaxInIntDomain(minInclusive: Int, maxInclusive: Int, minSize: Int) {
		arb.createIntDomainBasedClosedRangeArbGenerator(
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
		arb.createClosedRangeArbGenerator(
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
		arb.createClosedRangeArbGenerator(
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
		arb.createClosedRangeArbGenerator(
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
		arb.createClosedRangeArbGenerator(
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

	//TODO 2.0.0 error in case of wrong min/max etc.

	companion object {
		@JvmStatic
		fun intSafeMinMaxAndMinSize() =
			intSafeMinMax().combineDependent {
				arb.intFromUntil(1, it.second - it.first)
			}

		@JvmStatic
		fun intSafeMinMax() =
			// we are not using arb.intRange here on purpose as we would use the same function for test-setup
			arb.intFromUntil(Int.MIN_VALUE, Int.MAX_VALUE - possibleMaxSizeSafeInIntDomain).combineDependent {
				arb.intFromTo(it, it + possibleMaxSizeSafeInIntDomain)
			}

		@JvmStatic
		fun longSafeMinMaxAndMinSize() =
			longSafeMinMax().combineDependent {
				arb.longFromUntil(1, it.second - it.first)
			}

		@JvmStatic
		fun longSafeMinMax() =
			// we are not using arb.intRange here on purpose as we would use the same function for test-setup
			arb.longFromUntil(Long.MIN_VALUE, Long.MAX_VALUE - possibleMaxSizeSafeInLongDomain).combineDependent {
				// + possibleMaxSizeSafeInIntDomain as we could otherwise land in the Int-Domain
				arb.longFromTo(it + possibleMaxSizeSafeInIntDomain, it + possibleMaxSizeSafeInLongDomain)
			}

		@JvmStatic
		fun longUnsafeMinMaxAndSize() =
			arb.longFromUntil(Long.MIN_VALUE, Long.MAX_VALUE - possibleMaxSizeSafeInLongDomain - 10).combineDependent {
				arb.longFromUntil(it + possibleMaxSizeSafeInLongDomain, Long.MAX_VALUE)
			}.combineDependent {
				arb.longFromUntil(
					1,
					(BigInt.valueOf(it.second) - BigInt.valueOf(it.first)).min(BigInt.valueOf(Long.MAX_VALUE)).toLong()
				)
			}
	}
}
