package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.RandomArgsGenerator

abstract class BaseRandomArgsGenerator<T>(
	override val componentFactoryContainer: ComponentFactoryContainer,
) : RandomArgsGenerator<T>, ComponentFactoryContainerProvider {
	val config get() = componentFactoryContainer.config
}
