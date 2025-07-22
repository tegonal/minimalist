package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config.RandomFactory
import com.tegonal.minimalist.config.create
import com.tegonal.minimalist.config.impl.createSingletonVia

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
