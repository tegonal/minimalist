package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.intBounds
import com.tegonal.minimalist.generators.longBounds

/**
 * @since 2.0.0
 */
interface PredefinedBoundProviders {
	companion object {
		@JvmStatic
		fun arbIntBoundsMinSize2() = arb.intBounds(minSize = 2)

		@JvmStatic
		fun arbLongBoundsMinSize2() = arb.longBounds(minSize = 2)
	}
}
