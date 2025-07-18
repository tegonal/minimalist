package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.combine
import com.tegonal.minimalist.generators.impl.throwDontKnowHowToConvertToArgsGenerator
import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator
import com.tegonal.minimalist.generators.map
import com.tegonal.minimalist.providers.GenericToArgsGeneratorConverter

class DefaultGenericToArgsGeneratorConverter : GenericToArgsGeneratorConverter {
	override fun toArgsGenerator(
		firstArgsGenerator: ArgsGenerator<*>,
		restMaybeArgGenerators: List<*>
	): ArgsGenerator<List<*>> = when (firstArgsGenerator) {
		is RandomArgsGenerator<*> -> {
			val initial = firstArgsGenerator.map { mutableListOf(it) }
			restMaybeArgGenerators.fold(initial) { generator, next ->
				when (next) {
					is RandomArgsGenerator<*> -> generator.combine(next) { list, aNext ->
						list.also { it.add(aNext) }
					}

					is SemiOrderedArgsGenerator<*> -> error("wrong ordering of ArgsGenerators, make sure at leat one (Semi)OrderedArgsGenerators come before a RandomArgsGenerator<*>")
					is ArgsGenerator<*> -> throwUnsupportedArgsGenerator(next)
					else -> throwDontKnowHowToConvertToArgsGenerator(next)
				}
			}
		}

		is SemiOrderedArgsGenerator<*> -> {
			val first = firstArgsGenerator.map { mutableListOf(it) }
			restMaybeArgGenerators.fold(first) { generator, next ->
				when (next) {
					is ArgsGenerator<*> -> generator.combine(next) { list, aNext ->
						list.also { it.add(aNext) }
					}

					else -> throwDontKnowHowToConvertToArgsGenerator(next)
				}
			}
		}

		else -> throwUnsupportedArgsGenerator(firstArgsGenerator)
	}
}
