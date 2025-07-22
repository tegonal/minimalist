package com.tegonal.minimalist.generators

import com.tegonal.minimalist.testutils.AbcdEnum
import com.tegonal.minimalist.testutils.XyzEnum
import com.tegonal.minimalist.testutils.getTestValue

abstract class AbstractRandomArgsGeneratorMergeTwoTest : AbstractRandomArgsGeneratorTest<Any>() {

	@Suppress("UNCHECKED_CAST")
	fun variants(index: Int) =
		ordered.of(
			"of".let { it to modifiedRandom.of(*getTestValue(it, index) as Array<Int>) },
			"fromList".let { it to modifiedRandom.fromList(getTestValue(it, index) as List<Any>) },
			"fromByteArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as ByteArray) },
			"fromCharArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as CharArray) },
			"fromShortArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as ShortArray) },
			"fromIntArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as IntArray) },
			"fromLongArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as LongArray) },
			"fromFloatArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as FloatArray) },
			"fromDoubleArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as DoubleArray) },
			"fromBooleanArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as BooleanArray) },
			"fromArray".let { it to modifiedRandom.fromArray(getTestValue(it, index) as Array<Any>) },
			"fromEnum" to if (index == 0) modifiedRandom.fromEnum<AbcdEnum>() else modifiedRandom.fromEnum<XyzEnum>(),
			"fromCharRange".let { it to modifiedRandom.fromRange(getTestValue(it, index) as CharRange) },
			"fromIntRange".let { it to modifiedRandom.fromRange(getTestValue(it, index) as IntRange) },
			"fromLongRange".let { it to modifiedRandom.fromRange(getTestValue(it, index) as LongRange) },
			"fromCharProgression".let { it to modifiedRandom.fromProgression(getTestValue(it, index) as CharProgression) },
			"fromIntProgression".let { it to modifiedRandom.fromProgression(getTestValue(it, index) as IntProgression) },
			"fromLongProgression".let { it to modifiedRandom.fromProgression(getTestValue(it, index) as LongProgression) },
			"intFromUntil".let {
				it to (getTestValue(it, index) as IntRange).let { range ->
					modifiedRandom.intFromUntil(range.start, range.endInclusive + 1)
				}
			},
			"longFromUntil".let {
				it to (getTestValue(it, index) as LongRange).let { range ->
					modifiedRandom.longFromUntil(range.start, range.endInclusive + 1)
				}
			}
		)
}
