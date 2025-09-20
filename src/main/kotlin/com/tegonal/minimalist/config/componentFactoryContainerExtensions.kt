package com.tegonal.minimalist.config

import com.tegonal.minimalist.config.impl.*
import com.tegonal.minimalist.generators.ArbExtensionPoint
import com.tegonal.minimalist.generators.OrderedExtensionPoint
import com.tegonal.minimalist.generators.impl.DefaultArbExtensionPoint
import com.tegonal.minimalist.generators.impl.DefaultOrderedExtensionPoint
import com.tegonal.minimalist.providers.*
import com.tegonal.minimalist.providers.impl.DefaultArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.providers.impl.DefaultGenericArgsGeneratorCombiner
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
			MinimalistConfig::class createSingletonVia { config },
			SuffixArgsGeneratorDecider::class createVia {
				loadService(config.activeSuffixArgsGeneratorDecider)
			},
			GenericArgsGeneratorCombiner::class createSingletonVia { _ ->
				DefaultGenericArgsGeneratorCombiner()
			},
			ArgsGeneratorToArgumentsConverter::class createSingletonVia { _ ->
				DefaultArgsGeneratorToArgumentsConverter()
			},
			ArgsRangeDecider::class createSingletonVia { _ ->
				loadService(config.activeArgsRangeDecider)
			},

			RandomFactory::class createSingletonVia {
				KotlinRandomFactory()
			}
		),
		mapOf(
			//TODO 2.1.0 allow to sort them?
			createChainFromServiceLoaders<AnnotationDataDeducer>(),
		)
	)

/**
 *
 * @since 2.0.0
 */
fun ComponentFactoryContainer.Companion.create(
	components: Map<KClass<*>, ComponentFactory>,
	chainedComponents: Map<KClass<*>, Sequence<ComponentFactory>> = emptyMap()
) = DefaultComponentFactoryContainer.create(components, chainedComponents)

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.config: MinimalistConfig get() = build<MinimalistConfig>()

/**
 *
 * @since 2.0.0
 */
fun ComponentFactoryContainer.createMinimalistRandom(seedOffset: Int): Random =
	build<RandomFactory>().create(config.seed + seedOffset).also { random ->
		config.offsetToDecidedOffset?.also { repeat(it) { random.nextInt() } }
	}

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.ordered get() : OrderedExtensionPoint = DefaultOrderedExtensionPoint(this)

/**
 *
 * @since 2.0.0
 */
val ComponentFactoryContainer.arb get() : ArbExtensionPoint = DefaultArbExtensionPoint(this, seedBaseOffset = 0)


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
