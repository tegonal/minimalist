package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.providers.ArgsRangeDecider

@Suppress("UNCHECKED_CAST")
class SemiOrderedArgsGeneratorConcatenateTest : AbstractOrderedArgsGeneratorConcatenateTest() {

	override fun createGenerators(): OrderedArgsTestFactoryResult<Any> {
		val g1 = variants(0)
		val g2 = variants(1)

		val combined = g1.combine(g2) { (name1, g1), (name2, g2) ->
			val semiG1: SemiOrderedArgsGenerator<Any> = g1
			val semiG2: SemiOrderedArgsGenerator<Any> = g2

			Tuple("$name1 + $name2", semiG1 + semiG2, arrayToList(getValue(name1, 0)) + arrayToList(getValue(name2, 1)))
		}

		// we override `ordered` in AbstractOrderedArgsGeneratorWithoutAnnotationsTest because we want to test that
		// the ComponentContainer is passed correctly from generator to generator. But that also means that if we would
		// use the components from it, that seed is always 1. That's not what we want, hence we use the "normal"
		// MinimalistConfig and its defined seed
		val components = com.tegonal.minimalist.generators.ordered._components
		val argsRange = components.build<ArgsRangeDecider>().decideArgsRange(combined)
		return combined.generateAndTake(argsRange)
	}
}
