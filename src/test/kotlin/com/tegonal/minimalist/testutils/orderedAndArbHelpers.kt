package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.generators.ArbExtensionPoint
import com.tegonal.minimalist.generators.OrderedExtensionPoint
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

fun createOrderedWithCustomConfig(customConfig: MinimalistConfig): OrderedExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).ordered

fun createArbWithCustomConfig(customConfig: MinimalistConfig): ArbExtensionPoint =
	ComponentFactoryContainer.createBasedOnConfig(customConfig).arb

val orderedWithSeed0 = createOrderedWithCustomConfig(MinimalistConfig().copy { seed = 0 })

fun <T> SemiOrderedArgsGenerator<T>.generateToList(amount: Int): List<T> =
	generate(offset = 0).take(amount).toList()
