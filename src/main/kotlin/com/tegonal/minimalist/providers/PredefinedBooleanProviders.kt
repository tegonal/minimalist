package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.*

/**
 * @since 2.0.0
 */
interface PredefinedBooleanProviders {
	companion object {

		@JvmStatic
		fun boolean() = ordered.boolean()

		@JvmStatic
		fun booleanAndNull() = ordered.boolean() + ordered.of(null)

		@JvmStatic
		fun arbBoolean() = arb.boolean()

		@JvmStatic
		fun arbBooleanOrNull() = arb.mergeWeighted(
			// TODO 2.1.0 base on edge case config
			95 to arb.boolean(),
			5 to arb.of(null)
		)
	}
}
