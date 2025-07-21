package com.tegonal.minimalist.config.impl

import com.tegonal.minimalist.config.RandomFactory
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
