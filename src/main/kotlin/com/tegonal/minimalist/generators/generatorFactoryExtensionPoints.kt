package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.config.impl.MinimalistConfigViaPropertiesLoader

/**
 * Extension point for factories which generate [OrderedArgsGenerator].
 *
 * @since 2.0.0
 */
interface OrderedExtensionPoint : IsComponentFactoryContainerProvider

/**
 * Extension point for factories which generate [RandomArgsGenerator].
 *
 * @since 2.0.0
 */
interface RandomExtensionPoint : IsComponentFactoryContainerProvider

/**
 * @since 2.0.0
 */
private val propertiesBasedComponentFactoryContainer: ComponentFactoryContainer = run {
	val config = MinimalistConfigViaPropertiesLoader().config
	ComponentFactoryContainer.createBasedOnConfig(config)
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
