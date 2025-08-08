package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.utils.repeatForever
import kotlin.random.Random

abstract class RandomBasedArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
) : BaseArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {

	override fun generate(seedOffset: Int): Sequence<T> = createMinimalistRandom(seedOffset).let { random ->
		repeatForever().map {
			// Random is not thread safe, add synchronisation in case we run into issues
			random.nextElement()
		}
	}

	protected abstract fun Random.nextElement(): T
}

