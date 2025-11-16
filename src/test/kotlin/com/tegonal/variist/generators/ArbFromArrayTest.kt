package com.tegonal.variist.generators

import ch.tutteli.kbox.Tuple

class ArbFromArrayTest : AbstractArbArgsGeneratorTest<Any>() {

	override fun createGenerators(modifiedArb: ArbExtensionPoint) = sequenceOf(
		byteArrayOf(1, 2, 3).let { Tuple("fromByteArray", modifiedArb.fromArray(it), it.toList()) },
		charArrayOf('a', 'b', 'c').let { Tuple("fromCharArray", modifiedArb.fromArray(it), it.toList()) },
		shortArrayOf(1, 2, 3).let { Tuple("fromShortArray", modifiedArb.fromArray(it), it.toList()) },
		intArrayOf(1, 2, 3).let { Tuple("fromIntArray", modifiedArb.fromArray(it), it.toList()) },
		longArrayOf(1, 2, 3).let { Tuple("fromLongArray", modifiedArb.fromArray(it), it.toList()) },
		floatArrayOf(1f, 2f, 3f).let { Tuple("fromFloatArray", modifiedArb.fromArray(it), it.toList()) },
		doubleArrayOf(1.0, 2.0, 3.0).let { Tuple("fromDoubleArray", modifiedArb.fromArray(it), it.toList()) },
		booleanArrayOf(true, false).let { Tuple("fromBooleanArray", modifiedArb.fromArray(it), it.toList()) },
		arrayOf<Any>(1, 2.0, 'a').let { Tuple("fromArray", modifiedArb.fromArray(it), it.toList()) },
	)
}
