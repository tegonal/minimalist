package com.tegonal.variist.config.impl

import com.tegonal.variist.config.RandomFactory
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class KotlinRandomFactory : RandomFactory {
	override fun create(seed: Int): Random = Random(seed)
}
