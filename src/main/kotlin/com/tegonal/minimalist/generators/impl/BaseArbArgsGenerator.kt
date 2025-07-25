package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.generators.ArbArgsGenerator
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseArbArgsGenerator<T>(
	override val componentFactoryContainer: ComponentFactoryContainer,
) : ArbArgsGenerator<T>, ComponentFactoryContainerProvider {
	protected val config get(): MinimalistConfig = componentFactoryContainer.config

	protected fun createMinimalistRandom(): Random = componentFactoryContainer.createMinimalistRandom()
}
