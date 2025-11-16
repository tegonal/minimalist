package com.tegonal.variist.generators.impl

import com.tegonal.variist.config.*
import com.tegonal.variist.generators.CoreArbArgsGenerator
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

	protected val config get(): VariistConfig = componentFactoryContainer.config

	protected fun createVariistRandom(seedOffset: Int): Random =
		componentFactoryContainer.createVariistRandom(seedBaseOffset + seedOffset)
}
