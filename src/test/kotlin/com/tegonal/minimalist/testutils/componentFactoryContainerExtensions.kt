package com.tegonal.minimalist.testutils

import ch.tutteli.kbox.Tuple3
import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.RandomFactory
import com.tegonal.minimalist.config.create
import com.tegonal.minimalist.config.impl.createSingletonVia
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider

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
						argsRangeOptions: ArgsRangeOptions?
					): ArgsRange = ArgsRange(offset = offset, take = take)
				}
			})
		)
	)
