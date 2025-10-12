package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.ComponentFactoryContainer
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.BaseArbArgsGenerator
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.utils.repeatForever

class PseudoArbArgsGenerator<T>(
	private val list: List<T>,
	seedBaseOffset: Int = 0,
	componentFactoryContainer: ComponentFactoryContainer = arb._components,
) : BaseArbArgsGenerator<T>(componentFactoryContainer, seedBaseOffset) {

	constructor(
		finiteSequence: Sequence<T>,
		seedBaseOffset: Int = 0,
		componentFactoryContainer: ComponentFactoryContainer = com.tegonal.minimalist.generators.arb._components,
	) : this(finiteSequence.toList(), seedBaseOffset, componentFactoryContainer)

	override fun generate(seedOffset: Int): Sequence<T> =
		repeatForever().flatMap { list }.drop(seedOffset % list.size)
}
