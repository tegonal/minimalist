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
import kotlin.math.absoluteValue

/**
 * Not really a good name, but hard to come up with a good one.
 *
 * This class is responsible to get an [com.tegonal.minimalist.providers.ArgsRange] from a subclass based on a given profile, env and ArgsGenerator
 * and then restrict it based on [ArgsRangeOptions] and [ArgsGenerator].
 *
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseArgsRangeOptionsBasedArgsRangeDecider : ArgsRangeDecider {

	final override fun decide(argsGenerator: ArgsGenerator<*>, argsRangeOptions: ArgsRangeOptions?): ArgsRange {
		val config = argsGenerator._components.config
		val profile = argsRangeOptions?.profile ?: config.defaultProfile

		return decideArgsRange(profile, config.activeEnv, argsGenerator)
			.restrictBasedOnConfigAndArgsRangeOptions(config, argsRangeOptions, argsGenerator)

	}

	/**
	 * Returns the [ArgsRange] solely based on the given [profileName], [env] and [argsGenerator].
	 *
	 * Restricting the choice based on given [ArgsRangeOptions] and [MinimalistConfig] is the
	 * responsibility of [BaseArgsRangeOptionsBasedArgsRangeDecider].
	 */
	protected abstract fun decideArgsRange(
		profileName: String,
		env: String,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange

	private fun ArgsRange.restrictBasedOnConfigAndArgsRangeOptions(
		config: MinimalistConfig,
		argsRangeOptions: ArgsRangeOptions?,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange =
		run {
			config.offsetToDecidedOffset?.let { this.copy(offset = (this.offset + it).absoluteValue) } ?: this
		}.let { argsRange ->
			val maxArgs = config.maxArgs ?: argsRangeOptions?.maxArgs
			val requestedMinArgs = config.requestedMinArgs ?: argsRangeOptions?.requestedMinArgs
			val generatorSize = maybeGeneratorSize(argsGenerator)

			val newTake = argsRange.take.letIf(maxArgs != null) {
				minOf(maxArgs!!, it)
			}.letIf(requestedMinArgs != null) {
				// it could be that requestedMinArgs > maxArgs in case requestedMinArgs was defined in config and
				// maxArgs in argsRangeOptions. This is because config has precedence over argsRangeOptions.
				maxOf(requestedMinArgs!!, it)
			}.letIf(generatorSize != null) {
				// no need to take more as we would start to repeat values, i.e. even if config defines requestedMinArgs
				// we only take generatorSize if it is less than requestedMinArgs
				// We don't use offset=0 in such cases because, who knows, maybe the tests are dependent somehow
				// and we want to be sure we cover this via different offsets
				minOf(generatorSize!!, it)
			}

			argsRange.letIf(newTake != argsRange.take) {
				argsRange.copy(take = newTake)
			}
		}

	private fun maybeGeneratorSize(argsGenerator: ArgsGenerator<*>): Int? =
		(argsGenerator as? SemiOrderedArgsGenerator<*>)?.size

}
