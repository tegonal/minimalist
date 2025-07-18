package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.impl.createComponentFactoryContainerBasedOnConfig
import com.tegonal.minimalist.config.ordered

fun createOrderedWithCustomConfig(customConfig: MinimalistConfig): OrderedExtensionPoint =
	createComponentFactoryContainerBasedOnConfig(customConfig).ordered
