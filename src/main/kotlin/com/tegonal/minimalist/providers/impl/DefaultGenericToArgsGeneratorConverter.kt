package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.combine
import com.tegonal.minimalist.generators.impl.throwDontKnowHowToConvertToArgsGenerator
import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator
import com.tegonal.minimalist.generators.map
import com.tegonal.minimalist.providers.GenericToArgsGeneratorConverter

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultGenericToArgsGeneratorConverter : GenericToArgsGeneratorConverter {
	override fun toArgsGenerator(
		firstArgsGenerator: ArgsGenerator<*>,
		restMaybeArgGenerators: List<*>
	): ArgsGenerator<List<*>> = when (firstArgsGenerator) {
		is ArbArgsGenerator<*> -> {
			val initial = firstArgsGenerator.map { mutableListOf(it) }
			restMaybeArgGenerators.fold(initial) { generator, next ->
				when (next) {
					is ArbArgsGenerator<*> -> generator.combine(next) { list, aNext ->
						list.also { it.add(aNext) }
					}

					is SemiOrderedArgsGenerator<*> -> error("wrong ordering of ArgsGenerators, make sure at leat one (Semi)OrderedArgsGenerators comes before an ArbArgsGenerator.")
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
