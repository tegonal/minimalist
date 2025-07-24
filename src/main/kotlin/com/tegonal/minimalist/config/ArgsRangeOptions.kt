package com.tegonal.minimalist.config

import ch.tutteli.kbox.failIf
import com.tegonal.minimalist.config.impl.checkIsPositive
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.generators.ArgsGenerator

/**
 * Represents options which influences an [ArgsRangeDecider] on what [ArgsRange] to choose.
 * @since 2.0.0
 */
data class ArgsRangeOptions(
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

	/**
	 * Will take precedence over [MinimalistConfig.defaultMaxArgsLevelCategory] if the [ArgsRangeDecider] takes
	 * [MaxArgsLevels] into account.
	 */
	val category: String? = null
) {
	init {
		requestedMinArgs?.also { checkIsPositive(it, "atLeastArgs") }
		atMostArgs?.also { checkIsPositive(it, "atMostArgs") }
		failIf(requestedMinArgs != null && atMostArgs != null && requestedMinArgs < atMostArgs) {
			"atLeastArgs ($requestedMinArgs) must to be less than atMostArgs ($atMostArgs)"
		}
	}

	companion object
}

/**
 * @since 2.0.0
 */
fun ArgsRangeOptions.merge(other: ArgsRangeOptions): ArgsRangeOptions {
	return copy(
		requestedMinArgs = other.requestedMinArgs ?: this.requestedMinArgs,
		atMostArgs = other.atMostArgs ?: this.atMostArgs,
	)
}
