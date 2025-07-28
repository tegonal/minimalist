package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.IntFromUntilArbArgsGenerator

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging from
 * [Int.MIN_VALUE] (inclusive) to [Int.MAX_VALUE] (inclusive).
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.char(): ArbArgsGenerator<Char> =
	charFromTo(Char.MIN_VALUE, Char.MAX_VALUE)

/**
 * Returns an [ArbArgsGenerator] which generates [Int]s ranging [from] (inclusive) to [toInclusive].
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.charFromTo(from: Char, toInclusive: Char): ArbArgsGenerator<Char> =
	// I guess it is not worth to introduce an UShortFromUntil but in case we should because we want to generate UShort
	// then we could re-use it here in case toInclusive != Char, would use less memory
	IntFromUntilArbArgsGenerator(_components, from.code, toInclusive.code + 1) { it.toChar() }

