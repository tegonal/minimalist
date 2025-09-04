package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.char

/**
 * @since 2.0.0
 */
interface PredefinedArbCharProviders {
	companion object {
		@JvmStatic
		fun arbChar() = arb.char()
	}
}
