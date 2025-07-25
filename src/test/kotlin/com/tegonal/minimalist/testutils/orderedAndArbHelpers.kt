package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.createBasedOnConfig
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.config.random
import com.tegonal.minimalist.generators.OrderedExtensionPoint
import com.tegonal.minimalist.generators.ArbExtensionPoint

fun createOrderedWithCustomConfig(customConfig: MinimalistConfig): OrderedExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).ordered

fun createArbWithCustomConfig(customConfig: MinimalistConfig): ArbExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).random

val orderedWithSeed0 = createOrderedWithCustomConfig(MinimalistConfig().copy(seed = 0))
