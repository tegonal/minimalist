package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.createBasedOnConfig
import com.tegonal.minimalist.config.random
import com.tegonal.minimalist.generators.RandomExtensionPoint

fun createRandomWithCustomConfig(customConfig: MinimalistConfig): RandomExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).random
