package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.IsComponentFactoryContainerProvider
import com.tegonal.minimalist.config.impl.MinimalistConfigViaPropertiesLoader
import com.tegonal.minimalist.config.impl.createComponentFactoryContainerBasedOnConfig
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.config.random

/**
 * Extension point for factories which generate [OrderedArgsGenerator].
 */
interface OrderedExtensionPoint : IsComponentFactoryContainerProvider

/**
 * Extension point for factories which generate [RandomArgsGenerator].
 */
interface RandomExtensionPoint : IsComponentFactoryContainerProvider

/**
 * @since 2.0.0
 */
private val propertiesBasedComponentFactoryContainer: ComponentFactoryContainer = run {
	val config = MinimalistConfigViaPropertiesLoader().config
	createComponentFactoryContainerBasedOnConfig(config)
}

/**
 * Access to [OrderedArgsGenerator] factory methods like [ordered].[of][OrderedExtensionPoint.of].
 *
 * @since 2.0.0
 */
val ordered: OrderedExtensionPoint = propertiesBasedComponentFactoryContainer.ordered

/**
 * Access to [RandomArgsGenerator] factory methods like [random].[of][RandomExtensionPoint.of].
 *
 * @since 2.0.0
 */
val random: RandomExtensionPoint = propertiesBasedComponentFactoryContainer.random
