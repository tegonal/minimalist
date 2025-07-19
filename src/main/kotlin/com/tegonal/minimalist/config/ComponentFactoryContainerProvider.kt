package com.tegonal.minimalist.config


/**
 * Marker interface for types which can safely be cast to ComponentContainer, which just don't reveal it via public type
 * so that [ComponentFactoryContainer] doesn't clutter their API.
 *
 * @since 2.0.0
 */
interface IsComponentFactoryContainerProvider

interface ComponentFactoryContainerProvider {
	val componentFactoryContainer: ComponentFactoryContainer
}

/**
 * Casts `this` to a [ComponentFactoryContainerProvider] and returns the [ComponentFactoryContainerProvider.componentFactoryContainer].

 * @since 2.0.0
 */
@Suppress("ObjectPropertyName")
val IsComponentFactoryContainerProvider._components: ComponentFactoryContainer get() = (this as ComponentFactoryContainerProvider).componentFactoryContainer
