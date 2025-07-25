package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.ArbArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.ArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import org.junit.jupiter.params.provider.Arguments

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultArgsGeneratorToArgumentsConverter : ArgsGeneratorToArgumentsConverter {

	override fun toArguments(
		annotationData: AnnotationData,
		argsGenerator: ArgsGenerator<List<*>>,
	): Sequence<Arguments> {
		val argsRange = decideArgsRange(annotationData, argsGenerator)
		val sequenceOfList = when (argsGenerator) {
			is ArbArgsGenerator<List<*>> -> argsGenerator.generate().take(argsRange.take)
			is SemiOrderedArgsGenerator<List<*>> -> argsGenerator.generate(argsRange.offset).take(argsRange.take)
			else -> throwUnsupportedArgsGenerator(argsGenerator)
		}
		return sequenceOfList.map { generatorResults ->
			when (generatorResults.size) {
				0 -> error(
					"The ${ArgsGenerator::class.simpleName}(s) returned by ${annotationData.argsSourceMethodName} do not generate any value"
				)

				1 -> generatorResults.first().let { result ->
					// we don't split TupleLike (in contrast to ArgsArgumentProvider)
					tupleToList(result)?.let { Arguments.of(*it.toTypedArray()) }
						?: (result as? Arguments)
						?: Arguments.of(result)
				}

				else -> {
					val flattenedArgs = generatorResults.flatMap { result ->
						tupleToList(result)
							?: when (result) {
								is Arguments -> result.get().toList()
								// assuming a raw values
								else -> listOf(result)
							}
					}

					Arguments.of(*flattenedArgs.toTypedArray())
				}
			}
		}
	}

	private fun decideArgsRange(
		annotationData: AnnotationData,
		argsGenerator: ArgsGenerator<*>,
	): ArgsRange {
		val componentFactoryContainer = argsGenerator._components
		val argsRangeDecider = componentFactoryContainer.build<ArgsRangeDecider>()
		return argsRangeDecider.decide(argsGenerator, annotationData.argsRangeOptions)
	}
}
