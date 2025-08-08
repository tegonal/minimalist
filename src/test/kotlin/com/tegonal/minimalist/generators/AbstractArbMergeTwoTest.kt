package com.tegonal.minimalist.generators

import com.tegonal.minimalist.testutils.AbcdEnum
import com.tegonal.minimalist.testutils.XyzEnum
import com.tegonal.minimalist.testutils.getTestValue

abstract class AbstractArbMergeTwoTest : AbstractArbArgsGeneratorTest<Any>() {

	@Suppress("UNCHECKED_CAST")
	fun variants(modifiedArb: ArbExtensionPoint, index: Int) =
		ordered.of(
			"of".let { it to modifiedArb.of(*getTestValue(it, index) as Array<Int>) },
			"fromList".let { it to modifiedArb.fromList(getTestValue(it, index) as List<Any>) },
			"fromByteArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as ByteArray) },
			"fromCharArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as CharArray) },
			"fromShortArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as ShortArray) },
			"fromIntArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as IntArray) },
			"fromLongArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as LongArray) },
			"fromFloatArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as FloatArray) },
			"fromDoubleArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as DoubleArray) },
			"fromBooleanArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as BooleanArray) },
			"fromArray".let { it to modifiedArb.fromArray(getTestValue(it, index) as Array<Any>) },
			"fromEnum" to if (index == 0) modifiedArb.fromEnum<AbcdEnum>() else modifiedArb.fromEnum<XyzEnum>(),
			"fromCharRange".let { it to modifiedArb.fromRange(getTestValue(it, index) as CharRange) },
			"fromIntRange".let { it to modifiedArb.fromRange(getTestValue(it, index) as IntRange) },
			"fromLongRange".let { it to modifiedArb.fromRange(getTestValue(it, index) as LongRange) },
			"fromCharProgression".let { it to modifiedArb.fromProgression(getTestValue(it, index) as CharProgression) },
			"fromIntProgression".let { it to modifiedArb.fromProgression(getTestValue(it, index) as IntProgression) },
			"fromLongProgression".let { it to modifiedArb.fromProgression(getTestValue(it, index) as LongProgression) },
			// we don't include, int, long, double, intPositive, longPositive as their ranges are too big
			"intFromUntil".let {
				it to (getTestValue(it, index) as IntRange).let { range ->
					modifiedArb.intFromUntil(range.start, range.endInclusive + 1)
				}
			},
			"longFromUntil".let {
				it to (getTestValue(it, index) as LongRange).let { range ->
					modifiedArb.longFromUntil(range.start, range.endInclusive + 1)
				}
			},
			"intFromTo".let {
				it to (getTestValue(it, index) as IntRange).let { range ->
					modifiedArb.intFromTo(range.start, range.endInclusive)
				}
			},
			"longFromTo".let {
				it to (getTestValue(it, index) as LongRange).let { range ->
					modifiedArb.longFromTo(range.start, range.endInclusive)
				}
			},
		)
}
