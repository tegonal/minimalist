package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import kotlin.math.absoluteValue


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ProfileBasedArgsRangeDecider() : BaseArgsRangeOptionsBasedArgsRangeDecider() {

	override fun decideArgsRange(
		profileName: String,
		env: String,
		argsGenerator: ArgsGenerator<*>
	): ArgsRange = argsGenerator._components.config.let { config ->
		ArgsRange(
			offset = config.seed.absoluteValue,
			take = config.testProfiles.get(profileName, env).atMostArgs
		)
	}
}
