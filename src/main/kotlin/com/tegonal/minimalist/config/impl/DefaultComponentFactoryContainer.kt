package com.tegonal.variist.config.impl

import com.tegonal.variist.config.ComponentFactory
import com.tegonal.variist.config.ComponentFactoryContainer
import com.tegonal.variist.utils.impl.loadServices
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.cast

/**
 * Copied from [Atrium](https://atriumlib.org)
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
internal abstract class DefaultComponentFactoryContainer : ComponentFactoryContainer {

	private val singletonComponents = ConcurrentHashMap<KClass<*>, Any>()

	final override fun <I : Any> buildOrNull(kClass: KClass<I>): I? =
		getFactoryOrNull(kClass)?.let { factory ->
			safeBuildAndMemoizeIfSingleton(kClass, factory)
		}

	final override fun <I : Any> buildChainedOrNull(kClass: KClass<I>): Sequence<I>? =
		getFactoryForChainedOrNull(kClass)?.map { factory ->
			safeBuildAndMemoizeIfSingleton(kClass, factory)
		}

	private fun <I : Any> safeBuildAndMemoizeIfSingleton(
		kClass: KClass<I>,
		factory: ComponentFactory
	): I {
		val untypedInstance = if (factory.producesSingleton) {
			// we first check so that we only invoke the factory more than once in case of a race condition
			// Note, we cannot use computeIfAbsent as the build might relay on other singleton components
			// which yet need to be built.
			singletonComponents[kClass] ?: factory.build(this).also { singletonComponents.putIfAbsent(kClass, it) }
		} else {
			factory.build(this)
		}
		return if (kClass.isInstance(untypedInstance)) kClass.cast(untypedInstance)
		else error("cannot cast ${untypedInstance::class.qualifiedName} to ${kClass.qualifiedName}")
	}

	override fun merge(componentFactoryContainer: ComponentFactoryContainer?): ComponentFactoryContainer =
		if (componentFactoryContainer == null) this
		else RedefiningComponentFactoryContainer(this, componentFactoryContainer)


	private class RootComponentFactoryContainer(
		private val components: ConcurrentHashMap<KClass<*>, ComponentFactory>,
		private val chainedComponents: ConcurrentHashMap<KClass<*>, Sequence<ComponentFactory>>
	) : DefaultComponentFactoryContainer() {

		override fun getFactoryOrNull(kClass: KClass<*>): ComponentFactory? =
			components[kClass]

		override fun getFactoryForChainedOrNull(kClass: KClass<*>): Sequence<ComponentFactory>? =
			chainedComponents[kClass]
	}

	private class RedefiningComponentFactoryContainer(
		private val previousFactoryContainer: ComponentFactoryContainer,
		private val redefiningFactoryContainer: ComponentFactoryContainer
	) : DefaultComponentFactoryContainer() {

		override fun getFactoryOrNull(kClass: KClass<*>): ComponentFactory? =
			redefiningFactoryContainer.getFactoryOrNull(kClass) ?: previousFactoryContainer.getFactoryOrNull(kClass)

		override fun getFactoryForChainedOrNull(kClass: KClass<*>): Sequence<ComponentFactory>? {
			val previousSequence = previousFactoryContainer.getFactoryForChainedOrNull(kClass)
			return redefiningFactoryContainer.getFactoryForChainedOrNull(kClass)?.let { redefinedSequence ->
				if (previousSequence != null) {
					redefinedSequence.plus(previousSequence)
				} else {
					redefinedSequence
				}
			} ?: previousSequence
		}
	}

	companion object {
		fun create(
			components: Map<KClass<*>, ComponentFactory>,
			chainedComponents: Map<KClass<*>, Sequence<ComponentFactory>>
		): ComponentFactoryContainer =
			RootComponentFactoryContainer(ConcurrentHashMap(components), ConcurrentHashMap(chainedComponents))
	}
}

/**
 * Copied from [Atrium](https://atriumlib.org)
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
infix fun <T : Any> KClass<T>.createVia(factory: (ComponentFactoryContainer) -> T): Pair<KClass<*>, ComponentFactory> =
	this to ComponentFactory(factory, producesSingleton = false)

/**
 * Copied from [Atrium](https://atriumlib.org)
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
infix fun <T : Any> KClass<T>.createSingletonVia(factory: (ComponentFactoryContainer) -> T): Pair<KClass<*>, ComponentFactory> =
	this to ComponentFactory(factory, producesSingleton = true)

/**
 * Copied from [Atrium](https://atriumlib.org)
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
infix fun <T : Any> KClass<T>.createChainVia(factories: Sequence<(ComponentFactoryContainer) -> T>): Pair<KClass<*>, Sequence<ComponentFactory>> =
	this to factories.map { ComponentFactory(it, producesSingleton = false) }

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <reified T : Any> createChainFromServiceLoaders() = T::class.createChainVia(
	loadServices<T>().asSequence().map {
		{ _ -> it }
	}
)
