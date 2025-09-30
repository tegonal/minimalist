package com.tegonal.minimalist.providers

import ch.tutteli.kbox.Tuple2
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.intBounds
import com.tegonal.minimalist.generators.longBounds

/**
 * @since 2.0.0
 */
interface PredefinedBoundProviders {
	companion object {

		/**
		 * Returns an [ArbArgsGenerator] which generates [Tuple2] representing a lower and upper bound where the bounds
		 * range from [Int.MIN_VALUE] to [Int.MAX_VALUE] where their difference is at least 1 and thus results in a
		 * minimal size of 2 if the upper bound is treated as inclusive.
		 */
		@JvmStatic
		fun arbIntBoundsMinSize2() = arb.intBounds(minSize = 2)

		/**
		 * Returns an [ArbArgsGenerator] which generates [Tuple2] representing a lower and upper bound where the bounds
		 * range from [Long.MIN_VALUE] to [Long.MAX_VALUE] where their difference is at least 1 and thus results in a
		 * minimal size of 2 if the upper bound is treated as inclusive.
		 */
		@JvmStatic
		fun arbLongBoundsMinSize2() = arb.longBounds(minSize = 2)
	}
}
