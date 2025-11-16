package com.tegonal.variist.testutils

import ch.tutteli.kbox.Tuple3
import com.tegonal.variist.config.ComponentFactoryContainer
import com.tegonal.variist.config.RandomFactory
import com.tegonal.variist.config.create
import com.tegonal.variist.config.impl.createSingletonVia
import com.tegonal.variist.generators.ArgsGenerator
import com.tegonal.variist.providers.AnnotationData
import com.tegonal.variist.providers.ArgsRange
import com.tegonal.variist.providers.ArgsRangeDecider

fun ComponentFactoryContainer.withMockedRandom(
	ints: List<Int> = emptyList(),
	longs: List<Long> = emptyList(),
	doubles: List<Double> = emptyList()
): ComponentFactoryContainer =
	this.merge(
		ComponentFactoryContainer.create(
			mapOf(RandomFactory::class createSingletonVia { MockedRandomFactory(ints, longs, doubles) })
		)
	)

fun ComponentFactoryContainer.withMockedRandom(
	seedToTriple: (Int) -> Tuple3<List<Int>, List<Long>, List<Double>>
): ComponentFactoryContainer =
	this.merge(
		ComponentFactoryContainer.create(
			mapOf(RandomFactory::class createSingletonVia { MockedRandomBasedOnSeedFactory(seedToTriple) })
		)
	)

fun ComponentFactoryContainer.withMockedArgsRange(
	offset: Int,
	take: Int,
): ComponentFactoryContainer =
	this.merge(
		ComponentFactoryContainer.create(
			mapOf(ArgsRangeDecider::class createSingletonVia {
				object : ArgsRangeDecider {
					override fun decide(
						argsGenerator: ArgsGenerator<*>,
						annotationData: AnnotationData?
					): ArgsRange = ArgsRange(offset = offset, take = take)
				}
			})
		)
	)
