package com.tegonal.minimalist.generators

abstract class AbstractOrderedArgsGeneratorConcatenateTest : AbstractOrderedArgsGeneratorTest<Any>() {

	private val values: Map<String, List<Any>> = mapOf(
		"of" to listOf(arrayOf(1, 2, 3), arrayOf(10, 11, 12, 13)),
		"fromList" to listOf(listOf(4, 5, 6), listOf(14, 15)),
		"fromByteArray" to listOf(byteArrayOf(7, 8, 9), byteArrayOf(70)),
		"fromCharArray" to listOf(charArrayOf('a', 'b', 'c'), charArrayOf('A', 'B', 'C', 'D')),
		"fromShortArray" to listOf(shortArrayOf(10, 11, 12), shortArrayOf(100, 110)),
		"fromIntArray" to listOf(intArrayOf(16, 17, 18), intArrayOf(19)),
		"fromLongArray" to listOf(longArrayOf(20, 21, 22, 23), longArrayOf(24, 25, 26)),
		"fromFloatArray" to listOf(floatArrayOf(1.2f, 2.5f), floatArrayOf(10.2f, 20.5f, 78.2f)),
		"fromDoubleArray" to listOf(doubleArrayOf(12.3, 17.4, 16.0, 17.89), doubleArrayOf(120.3, 170.4)),
		"fromBooleanArray" to listOf(booleanArrayOf(true), booleanArrayOf(false)),
		"fromArray" to listOf(arrayOf<Any>(1000, 2.0, 'z'), arrayOf<Any>(10_000, 'Z')),
		"fromEnum" to listOf(TestEnum1.entries.toList(), TestEnum2.entries.toList()),
		"fromCharRange" to listOf('f'..'i', 'F'..'H'),
		"fromIntRange" to listOf(100..104, 105..107),
		"fromLongRange" to listOf(110L..113L, 114L..119L),
		"fromCharProgression" to listOf('k'..'p' step 2, 'K'..'O' step 3),
		"fromIntProgression" to listOf(120..130 step 2, 150 downTo 140 step 2),
		"fromLongProgression" to listOf(160L..170L step 3, 190L downTo 171L step 5),
	)

	@Suppress("UNCHECKED_CAST")
	fun variants(index: Int) =
		ordered.of(
			"of".let { it to ordered.of(*getValue(it, index) as Array<Int>) },
			"fromList".let { it to ordered.fromList(getValue(it, index) as List<Any>) },
			"fromByteArray".let { it to ordered.fromArray(getValue(it, index) as ByteArray) },
			"fromCharArray".let { it to ordered.fromArray(getValue(it, index) as CharArray) },
			"fromShortArray".let { it to ordered.fromArray(getValue(it, index) as ShortArray) },
			"fromIntArray".let { it to ordered.fromArray(getValue(it, index) as IntArray) },
			"fromLongArray".let { it to ordered.fromArray(getValue(it, index) as LongArray) },
			"fromFloatArray".let { it to ordered.fromArray(getValue(it, index) as FloatArray) },
			"fromDoubleArray".let { it to ordered.fromArray(getValue(it, index) as DoubleArray) },
			"fromBooleanArray".let { it to ordered.fromArray(getValue(it, index) as BooleanArray) },
			"fromArray".let { it to ordered.fromArray(getValue(it, index) as Array<Any>) },
			"fromEnum" to if (index == 0) ordered.fromEnum<TestEnum1>() else ordered.fromEnum<TestEnum2>(),
			"fromCharRange".let { it to ordered.fromRange(getValue(it, index) as CharRange) },
			"fromIntRange".let { it to ordered.fromRange(getValue(it, index) as IntRange) },
			"fromLongRange".let { it to ordered.fromRange(getValue(it, index) as LongRange) },
			"fromCharProgression".let { it to ordered.fromProgression(getValue(it, index) as CharProgression) },
			"fromIntProgression".let { it to ordered.fromProgression(getValue(it, index) as IntProgression) },
			"fromLongProgression".let { it to ordered.fromProgression(getValue(it, index) as LongProgression) },
		)

	@Suppress("UNCHECKED_CAST")
	fun arrayToList(v: Any): List<Any> = when (v) {
		is List<*> -> v as List<Any>
		is Array<*> -> v.toList() as List<Any>
		is ByteArray -> v.toList()
		is CharArray -> v.toList()
		is ShortArray -> v.toList()
		is IntArray -> v.toList()
		is LongArray -> v.toList()
		is FloatArray -> v.toList()
		is DoubleArray -> v.toList()
		is BooleanArray -> v.toList()
		is CharRange -> v.toList()
		is IntRange -> v.toList()
		is LongRange -> v.toList()
		is CharProgression -> v.toList()
		is IntProgression -> v.toList()
		is LongProgression -> v.toList()
		else -> error("a type which we did not expect ${v::class.qualifiedName}")
	}

	enum class TestEnum1 {
		A, B, C, D
	}

	enum class TestEnum2 {
		X, Y, Z
	}

	protected fun getValue(key: String, index: Int) = (values[key]!!).let { it[index] }
}
