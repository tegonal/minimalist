package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.generators.CoreArbArgsGenerator
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class BaseArbArgsGenerator<T>(
	override val componentFactoryContainer: ComponentFactoryContainer,
	override val seedBaseOffset: Int
) : CoreArbArgsGenerator<T>, ComponentFactoryContainerProvider {

	constructor(arbGenerator: CoreArbArgsGenerator<*>) : this(
		arbGenerator.componentFactoryContainer,
		arbGenerator.seedBaseOffset
	)

	protected val config get(): MinimalistConfig = componentFactoryContainer.config

	protected fun createMinimalistRandom(seedOffset: Int): Random =
		componentFactoryContainer.createMinimalistRandom(seedBaseOffset + seedOffset)
}
