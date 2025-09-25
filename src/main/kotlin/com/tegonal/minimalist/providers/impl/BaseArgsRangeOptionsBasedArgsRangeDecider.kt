package com.tegonal.minimalist.providers.impl

import ch.tutteli.kbox.letIf
import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.OrderedArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.utils.seedToOffset

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

	final override fun decide(argsGenerator: ArgsGenerator<*>, annotationData: AnnotationData?): ArgsRange {
		val config = argsGenerator._components.config
		val profile = annotationData?.argsRangeOptions?.profile ?: config.defaultProfile

		return decideArgsRange(profile, config.activeEnv, argsGenerator)
			.restrictBasedOnConfigAndArgsRangeOptions(config, annotationData?.argsRangeOptions, argsGenerator)

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
			config.offsetToDecidedOffset
				?.let { this.copy(offset = seedToOffset(this.offset + it)) }
				?: this
		}.let { argsRange ->
			val maxArgs = config.maxArgs ?: argsRangeOptions?.maxArgs
			val requestedMinArgs = config.requestedMinArgs ?: argsRangeOptions?.requestedMinArgs
			val newTake = argsRange.take
				.letIf(maxArgs != null) { take ->
					minOf(maxArgs!!, take)
				}.let { take ->
					when (argsGenerator) {
						is OrderedArgsGenerator -> {
							// no need to take more as we would start to repeat values
							// we can ignore requestedMinArgs for OrderedArgsGenerator
							minOf(argsGenerator.size, take)

							// Note, we don't use offset=0 in case generatorSize is less than `take` (i.e. which means we can
							// run all combinations), because, who knows, maybe the tests are dependent somehow
							// and we want to be sure we cover this via different offsets
						}

						is SemiOrderedArgsGenerator ->
							minOf(argsGenerator.size, take).letIf(requestedMinArgs != null) { newTake ->
								// in case one specified requestedMinArgs, then we take it into account, i.e. we
								// start to repeat the fixed part of a SemiOrderedArgsGenerator
								maxOf(requestedMinArgs!!, newTake)
							}

						is ArbArgsGenerator ->
							take.letIf(requestedMinArgs != null) {
								// it could be that requestedMinArgs > maxArgs in case requestedMinArgs was defined in
								// config and maxArgs in argsRangeOptions.
								// This is because config has precedence over argsRangeOptions.
								maxOf(requestedMinArgs!!, take)
							}

						else -> throwUnsupportedArgsGenerator(argsGenerator)
					}
				}

			argsRange.letIf(newTake != argsRange.take) {
				argsRange.copy(take = newTake)
			}
		}
}
