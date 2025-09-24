package com.tegonal.minimalist.generators

/**
 * Returns an [OrderedArgsGenerator] which generates [Boolean].
 *
 * @since 2.0.0
 */
fun OrderedExtensionPoint.boolean(): OrderedArgsGenerator<Boolean> =
	ordered.of(false, true)
