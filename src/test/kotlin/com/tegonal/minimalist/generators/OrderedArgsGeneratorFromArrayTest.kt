package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple

class OrderedArgsGeneratorFromArrayTest : AbstractOrderedArgsGeneratorTest<Any>() {

	override fun createGenerators() = sequenceOf(
		byteArrayOf(1, 2, 3).let { Tuple("fromByteArray", ordered.fromArray(it), it.toList()) },
		charArrayOf('a', 'b', 'c').let { Tuple("fromCharArray", ordered.fromArray(it), it.toList()) },
		shortArrayOf(1, 2, 3).let { Tuple("fromShortArray", ordered.fromArray(it), it.toList()) },
		intArrayOf(1, 2, 3).let { Tuple("fromIntArray", ordered.fromArray(it), it.toList()) },
		longArrayOf(1, 2, 3).let { Tuple("fromLongArray", ordered.fromArray(it), it.toList()) },
		floatArrayOf(1f, 2f, 3f).let { Tuple("fromFloatArray", ordered.fromArray(it), it.toList()) },
		doubleArrayOf(1.0, 2.0, 3.0).let { Tuple("fromDoubleArray", ordered.fromArray(it), it.toList()) },
		booleanArrayOf(true, false).let { Tuple("fromBooleanArray", ordered.fromArray(it), it.toList()) },
		arrayOf<Any>(1, 2.0, 'a').let { Tuple("fromArray", ordered.fromArray(it), it.toList()) },
	)
}
