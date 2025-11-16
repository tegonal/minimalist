package com.tegonal.variist

/**
 * Helper construct so that we can distinguish overloads in [Args.of].
 *
 * First Representation needs to use it, this way it is clear that we actually start with representations and don't
 * mean another argument.
 *
 * @since 2.0.0
 */
data class Representation(val text: String)
