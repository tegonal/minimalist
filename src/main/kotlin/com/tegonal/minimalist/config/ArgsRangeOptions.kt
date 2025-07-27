package com.tegonal.minimalist.config

import ch.tutteli.kbox.failIf
import com.tegonal.minimalist.config.impl.checkIsNotBlank
import com.tegonal.minimalist.config.impl.checkIsPositive
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.generators.ArgsGenerator
import kotlin.String

/**
 * Represents options which influences an [ArgsRangeDecider] on what [ArgsRange] to choose.
 * @since 2.0.0
 */
class ArgsRangeOptions(
	/**
	 * Will take precedence over [MinimalistConfig.defaultProfile].
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
	val atMostArgs: Int? = null,
) {
	init {
		profile?.also { checkIsNotBlank(it, "profile") }

		requestedMinArgs?.also { checkIsPositive(it, "atLeastArgs") }
		atMostArgs?.also { checkIsPositive(it, "atMostArgs") }
		failIf(requestedMinArgs != null && atMostArgs != null && requestedMinArgs < atMostArgs) {
			"atLeastArgs ($requestedMinArgs) must to be less than atMostArgs ($atMostArgs)"
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
		atMostArgs = other.atMostArgs ?: this.atMostArgs,
	)
}
