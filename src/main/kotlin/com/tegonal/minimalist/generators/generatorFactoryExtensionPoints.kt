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
 * Extension point for factories which generate [ArbArgsGenerator].
 *
 * @since 2.0.0
 */
interface ArbExtensionPoint : IsComponentFactoryContainerProvider

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
 * Access to [ArbArgsGenerator] factory methods like [arb].[of][ArbExtensionPoint.of].
 *
 * @since 2.0.0
 */
val arb: ArbExtensionPoint = propertiesBasedComponentFactoryContainer.random
