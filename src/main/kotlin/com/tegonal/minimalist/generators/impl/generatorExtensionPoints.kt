package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.generators.OrderedExtensionPoint
import com.tegonal.minimalist.generators.ArbExtensionPoint

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultOrderedExtensionPoint(
	override val componentFactoryContainer: ComponentFactoryContainer
) : OrderedExtensionPoint, ComponentFactoryContainerProvider

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultArbExtensionPoint(
	override val componentFactoryContainer: ComponentFactoryContainer
) : ArbExtensionPoint, ComponentFactoryContainerProvider
