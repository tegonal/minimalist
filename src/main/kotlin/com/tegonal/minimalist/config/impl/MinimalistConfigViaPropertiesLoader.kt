package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.MinimalistConfig
import java.util.*

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class MinimalistConfigViaPropertiesLoader {
	val config by lazy {
		val parser = MinimalistPropertiesParser()
		val initialConfig = MinimalistConfig()
		initialConfig
			.run {
				mergeWithPropertiesInResource("/minimalist.properties", parser).also {
					check(it.seed == initialConfig.seed) {
						"You are not allowed to modify the seed via minimalist.properties use minimalist.local.properties to fix a seed"
					}
				}
			}.run { mergeWithPropertiesInResource("/minimalist.local.properties", parser) }
			.also {
				val fixedSeed = it.seed != initialConfig.seed
				println("Minimalist${if (fixedSeed) " fixed" else ""} seed: ${it.seed}")
				//TODO 2.1.0 add seedFixedAt to MinimalistConfig and write it to minimalist.local.properties in
				// case the seed is fixed and error after x hours, minutes or whatever (could be configurable)
				// so that we warn a user if he forgot to remove it again - a user could set seedFixedAt manually to
				// current time, this way the user would be reminded again. Or we introduce a property
				// errorAboutFixedSeeedAt. Would be simpler for a user to define. Imagine the following, config property
				// errorAboutFixedSeedAfterDuration = PT30M is set and the user gets notified after 30min (because
				// they did not figure out in those 30min why the test failed. They estimate they need another 1h
				// instead of calculating what seedFixedAt they should set so that they are reminded after 1h, it
				// would be simpler if they could just set the DateTime in errorAboutFixedSeedAt
			}
	}

	private fun MinimalistConfig.mergeWithPropertiesInResource(
		propertiesFile: String,
		parser: MinimalistPropertiesParser
	): MinimalistConfig = this::class.java.getResourceAsStream(propertiesFile)?.let {
		it.use { input ->
			val props = Properties()
			props.load(input)
			parser.mergeWithProperties(this, props)
		}
	} ?: this
}
