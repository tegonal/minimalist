package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config.impl.createComponentFactoryContainerBasedOnConfig
import com.tegonal.minimalist.config.random

fun createRandomWithCustomConfig(customConfig: MinimalistConfig): RandomExtensionPoint =
	createComponentFactoryContainerBasedOnConfig(customConfig).random
