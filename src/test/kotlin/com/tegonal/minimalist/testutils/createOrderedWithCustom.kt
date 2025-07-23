package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.createBasedOnConfig
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.OrderedExtensionPoint

fun createOrderedWithCustomConfig(customConfig: MinimalistConfig): OrderedExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).ordered

val orderedWithSeed0 = createOrderedWithCustomConfig(MinimalistConfig().copy(seed = 0))
