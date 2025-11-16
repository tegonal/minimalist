package com.tegonal.variist.config

import ch.tutteli.kbox.failIf
import com.tegonal.variist.utils.impl.checkIsNotBlank
import com.tegonal.variist.utils.impl.checkIsPositive
import com.tegonal.variist.providers.ArgsRangeDecider
import com.tegonal.variist.providers.ArgsRange
import com.tegonal.variist.generators.ArgsGenerator
import kotlin.String

/**
 * Represents options which influences an [ArgsRangeDecider] on what [ArgsRange] to choose.
 * @since 2.0.0
 */
class ArgsRangeOptions(
	/**
	 * Will take precedence over [VariistConfig.defaultProfile].
	 */
	val profile: String? = null,

	/**
	 * Should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should be at least
	 * the specified amount, unless the [ArgsGenerator] repeats values beforehand.
	 */
	val requestedMinArgs: Int? = null,

	/**
	 * Should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take], signaling that it should not be greater
	 * than the specified amount.
	 */
	val maxArgs: Int? = null,
) {
	init {
		profile?.also { checkIsNotBlank(it, "profile") }

		requestedMinArgs?.also { checkIsPositive(it, "atLeastArgs") }
		maxArgs?.also { checkIsPositive(it, "maxArgs") }
		failIf(requestedMinArgs != null && maxArgs != null && requestedMinArgs < maxArgs) {
			"requestedMinArgs ($requestedMinArgs) must to be less than maxArgs ($maxArgs)"
		}
	}
}

/**
 * @since 2.0.0
 */
fun ArgsRangeOptions.merge(other: ArgsRangeOptions): ArgsRangeOptions {
	return ArgsRangeOptions(
		profile = other.profile ?: this.profile,
		requestedMinArgs = other.requestedMinArgs ?: this.requestedMinArgs,
		maxArgs = other.maxArgs ?: this.maxArgs,
	)
}
