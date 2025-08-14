package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.*

/**
 * @since 2.0.0
 */
interface PredefinedArgsSourceProviders {
	companion object {

		// arbBounds ----------------------------------------------------------

		@JvmStatic
		fun arbIntBoundsMinSize2() = arb.intBounds(minSize = 2)

		@JvmStatic
		fun arbLongBoundsMinSize2() = arb.longBounds(minSize = 2)

		// arbChar ------------------------------------------------------------

		@JvmStatic
		fun arbChar() = arb.char()

		// arbNumbers ---------------------------------------------------------

		@JvmStatic
		fun arbInt() = arb.int()

		@JvmStatic
		fun arbLong() = arb.long()

		@JvmStatic
		fun arbDouble() = arb.double()

		@JvmStatic
		fun arbIntPositive() = arb.intPositive()

		@JvmStatic
		fun arbLongPositive() = arb.longPositive()

		@JvmStatic
		fun arbIntPositiveAndZero() = arb.intPositiveAndZero()

		@JvmStatic
		fun arbLongPositiveAndZero() = arb.longPositiveAndZero()

		@JvmStatic
		fun arbIntNegative() = arb.intNegative()

		@JvmStatic
		fun arbLongNegative() = arb.longNegative()

		@JvmStatic
		fun arbIntNegativeAndZero() = arb.intNegativeAndZero()

		@JvmStatic
		fun arbLongNegativeAndZero() = arb.longNegativeAndZero()
	}
}
