package com.tegonal.minimalist.config

import kotlin.random.Random

/**
 * Factory to create a [Random].
 *
 * @since 2.0.0
 */
interface RandomFactory {
	fun create(seed: Int): Random
}
