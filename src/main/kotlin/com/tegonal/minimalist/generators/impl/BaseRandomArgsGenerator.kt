package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.generators.RandomArgsGenerator
import kotlin.random.Random

abstract class BaseRandomArgsGenerator<T>(
	override val componentFactoryContainer: ComponentFactoryContainer,
) : RandomArgsGenerator<T>, ComponentFactoryContainerProvider {
	protected val config get(): MinimalistConfig = componentFactoryContainer.config

	protected fun createMinimalistRandom(): Random = componentFactoryContainer.createMinimalistRandom()
}
