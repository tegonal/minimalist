package com.tegonal.variist.generators

/**
 * Returns an [ArbArgsGenerator] which generates [Boolean].
 *
 * @since 2.0.0
 */
fun ArbExtensionPoint.boolean(): ArbArgsGenerator<Boolean> =
	arb.of(false, true)
