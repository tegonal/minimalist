package com.tegonal.minimalist.providers.impl

import ch.tutteli.kbox.letIf
import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseOptionsBasedArgsRangeDecider : ArgsRangeDecider {

	final override fun decide(argsGenerator: ArgsGenerator<*>, argsRangeOptions: ArgsRangeOptions?): ArgsRange {
		val config = argsGenerator._components.config
		val category = config.argsRangeOptions.category
			?: argsRangeOptions?.category
			?: config.defaultMaxArgsLevelCategory

		return decideArgsRange(category, config.activeMaxArgsLevel, argsGenerator)
			.restrictBasedOnConfigAndArgsRangeOptions(config, argsRangeOptions, argsGenerator)

	}

	protected abstract fun decideArgsRange(
		category: String,
		level: String,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange


	fun ArgsRange.restrictBasedOnConfigAndArgsRangeOptions(
		config: MinimalistConfig,
		argsRangeOptions: ArgsRangeOptions?,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange =
		run {
			config.offsetToDecidedOffset?.let { this.copy(offset = this.offset + it) } ?: this
		}.let { argsRange ->
			val atMostArgs = config.argsRangeOptions.atMostArgs ?: argsRangeOptions?.atMostArgs
			val requestedMinArgs = config.argsRangeOptions.requestedMinArgs ?: argsRangeOptions?.requestedMinArgs
			val generatorSize = maybeGeneratorSize(argsGenerator)

			val newTake = argsRange.take.letIf(atMostArgs != null) {
				minOf(atMostArgs!!, it)
			}.letIf(requestedMinArgs != null) {
				// it could be that requestedMinArgs > atMostArgs in case requestedMinArgs was defined in config and
				// atMostArgs in argsRangeOptions. This is because config has precedence over argsRangeOptions.
				maxOf(requestedMinArgs!!, it)
			}.letIf(generatorSize != null) {
				// no need to take more as we would start to repeat values, i.e. even if config defines requestedMinArgs
				// we only take generatorSize if it is less than requestedMinArgs
				minOf(generatorSize!!, it)
			}

			argsRange.letIf(newTake != argsRange.take) {
				argsRange.copy(take = newTake)
			}
		}

	private fun maybeGeneratorSize(argsGenerator: ArgsGenerator<*>): Int? =
		(argsGenerator as? SemiOrderedArgsGenerator<*>)?.size

}
