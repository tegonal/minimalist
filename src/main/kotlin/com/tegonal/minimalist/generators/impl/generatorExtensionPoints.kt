package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.ComponentFactoryContainerProvider
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.ArbExtensionPoint
import com.tegonal.minimalist.generators.OrderedExtensionPoint

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultOrderedExtensionPoint(
	override val componentFactoryContainer: ComponentFactoryContainer
) : OrderedExtensionPoint, ComponentFactoryContainerProvider {
	override val arb: ArbExtensionPoint get() = componentFactoryContainer.arb
	override val ordered: OrderedExtensionPoint get() = componentFactoryContainer.ordered
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultArbExtensionPoint(
	override val componentFactoryContainer: ComponentFactoryContainer,
	override val seedBaseOffset: Int,
) : ArbExtensionPoint, ComponentFactoryContainerProvider {

	override val arb: ArbExtensionPoint get() = DefaultArbExtensionPoint(componentFactoryContainer, seedBaseOffset + 1)
	override val ordered: OrderedExtensionPoint get() = componentFactoryContainer.ordered
}
