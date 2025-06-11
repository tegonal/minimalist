package com.tegonal.minimalist.generators

class ArrayOrderedArgsGeneratorTest: AbstractOrderedArgsGeneratorTest() {

    override fun createGenerators() = mapOf(
		"fromByteArray" to ordered.fromArray(byteArrayOf(1, 2, 3)),
		"fromCharArray" to ordered.fromArray(charArrayOf('a', 'b', 'c')),
		"fromShortArray" to ordered.fromArray(shortArrayOf(1, 2, 3)),
		"fromIntArray" to ordered.fromArray(intArrayOf(1, 2, 3)),
		"fromLongArray" to ordered.fromArray(longArrayOf(1, 2, 3)),
		"fromFloatArray" to ordered.fromArray(floatArrayOf(1f, 2f, 3f)),
		"fromDoubleArray" to ordered.fromArray(doubleArrayOf(1.0, 2.0, 3.0)),
	)

}
