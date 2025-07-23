package com.tegonal.minimalist.config

import com.tegonal.minimalist.config.impl.DefaultComponentFactoryContainer
import com.tegonal.minimalist.config.impl.KotlinRandomFactory
import com.tegonal.minimalist.config.impl.createSingletonVia
import com.tegonal.minimalist.generators.OrderedExtensionPoint
import com.tegonal.minimalist.generators.RandomExtensionPoint
import com.tegonal.minimalist.generators.impl.DefaultOrderedExtensionPoint
import com.tegonal.minimalist.generators.impl.DefaultRandomExtensionPoint
import com.tegonal.minimalist.providers.AnnotationDataDeducer
import com.tegonal.minimalist.providers.ArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.GenericToArgsGeneratorConverter
import com.tegonal.minimalist.providers.impl.DefaultAnnotationDataDeducer
import com.tegonal.minimalist.providers.impl.DefaultArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.providers.impl.DefaultGenericToArgsGeneratorConverter
import com.tegonal.minimalist.utils.impl.loadService
import kotlin.random.Random
import kotlin.reflect.KClass

/**
 *
 * @since 2.0.0
 */
fun ComponentFactoryContainer.Companion.createBasedOnConfig(config: MinimalistConfig): ComponentFactoryContainer =
	ComponentFactoryContainer.create(
		mapOf(
			OrderedExtensionPoint::class createSingletonVia { c -> DefaultOrderedExtensionPoint(c) },
			RandomExtensionPoint::class createSingletonVia { c -> DefaultRandomExtensionPoint(c) },

			MinimalistConfig::class createSingletonVia { config },
			GenericToArgsGeneratorConverter::class createSingletonVia { _ ->
				DefaultGenericToArgsGeneratorConverter()
			},
			//TODO 2.1.0 change to a chained approach to make it extensible?
			AnnotationDataDeducer::class createSingletonVia { _ ->
				DefaultAnnotationDataDeducer()
			},
			ArgsGeneratorToArgumentsConverter::class createSingletonVia { _ ->
				DefaultArgsGeneratorToArgumentsConverter()
			},
			ArgsRangeDecider::class createSingletonVia { _ ->
				loadService<ArgsRangeDecider>(config.activeArgsRangeDecider).also {
					if (it is RequiresConfig) it.setConfig(config)
				}
			},

			RandomFactory::class createSingletonVia {
				KotlinRandomFactory()
			}
		),
		emptyMap()
	)

/**
 *
 * @since 2.0.0
 */
fun ComponentFactoryContainer.Companion.create(
	components: Map<KClass<*>, ComponentFactory>,
	chainedComponents: Map<KClass<*>, Sequence<ComponentFactory>> = emptyMap()
) = DefaultComponentFactoryContainer(components, chainedComponents)

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.config: MinimalistConfig get() = build<MinimalistConfig>()

/**
 *
 * @since 2.0.0
 */
fun ComponentFactoryContainer.createMinimalistRandom(): Random = build<RandomFactory>().create(config.seed)

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.ordered get() : OrderedExtensionPoint = build<OrderedExtensionPoint>()

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.random get() : RandomExtensionPoint = build<RandomExtensionPoint>()


/**
 * Returns the component of type [T] using a corresponding factory or throws an [IllegalStateException]
 * in case no factory was found which is able to build a component of the given type.
 *
 * @throws IllegalStateException in case [ComponentFactoryContainer.buildOrNull] returns `null`
 *   because not suitable factory was found.
 * @throws ClassCastException in case the factory returns an illegal type.
 *
 * @since 2.0.0
 *
 * Copied from [Atrium](https://atriumlib.org).
 */
inline fun <reified T : Any> ComponentFactoryContainer.build(): T = buildOrNull(T::class)
	?: error("No factory is registered in this ComponentFactoryContainer which is able to build a ${T::class.qualifiedName}")


/**
 * Returns a chain of components of type [T] using a corresponding factory or throws an [IllegalStateException]
 * in case no factory was found which is able to build a chain of components of the given type.

 * @throws IllegalStateException in case [ComponentFactoryContainer.buildChainedOrNull] returns `null`
 *   because no suitable factory was found.
 * @throws ClassCastException in case one of factories returns an illegal type.
 *
 * @since 2.0.0
 *
 * Copied from [Atrium](https://atriumlib.org).
 */
inline fun <reified T : Any> ComponentFactoryContainer.buildChained(): Sequence<T> = buildChainedOrNull(T::class)
	?: error("No factory is registered in this ComponentFactoryContainer which is able to build a chain of ${T::class.qualifiedName}")

