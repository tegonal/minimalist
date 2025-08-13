package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.intRange
import com.tegonal.minimalist.generators.longRange
import com.tegonal.minimalist.generators.map

interface PredefinedArgsSourceProviders {
	companion object {
		//TODO 2.0.0 add more predefined providers

		@JvmStatic
		fun arbIntBoundsMinSize2() = arb.intRange(minSize = 2).map { it.start to it.last }

		@JvmStatic
		fun arbLongBoundsMinSize2() = arb.longRange(minSize = 2).map { it.start to it.last }
	}
}
