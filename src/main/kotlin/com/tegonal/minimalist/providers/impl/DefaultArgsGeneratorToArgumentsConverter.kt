package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.RandomArgsGenerator
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator
import com.tegonal.minimalist.generators.impl.throwUnsupportedArgsGenerator
import com.tegonal.minimalist.providers.ArgsGeneratorToArgumentsConverter
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsRangeDecider
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.params.provider.Arguments
import java.lang.reflect.Method

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultArgsGeneratorToArgumentsConverter : ArgsGeneratorToArgumentsConverter {

	override fun toArguments(
		testMethod: Method,
		annotation: ArgsSource,
		argsGenerator: ArgsGenerator<List<*>>,
	): List<Arguments> {
		val argsRange = determineArgsRange(testMethod, annotation, argsGenerator)
		val sequenceOfValues = when (argsGenerator) {
			is RandomArgsGenerator<List<*>> -> argsGenerator.generate().take(argsRange.take)
			is SemiOrderedArgsGenerator<List<*>> -> argsGenerator.generate(argsRange.offset).take(argsRange.take)
			else -> throwUnsupportedArgsGenerator(argsGenerator)
		}
		return sequenceOfValues.map { args ->
			when (args.size) {
				0 -> error("The resulting ArgsGenerator returned by ${annotation.methodName} doesn't produce any arguments")
				1 -> args.first().let { first ->
					tupleLikeToList(first)?.let { Arguments.of(*it.toTypedArray()) } ?: Arguments.of(first)
				}

				else -> Arguments.of(*args.toTypedArray())
			}
		}.toList()
	}

	private fun determineArgsRange(
		@Suppress("UNUSED_PARAMETER") testMethod: Method,
		annotation: ArgsSource,
		argsGenerator: ArgsGenerator<*>,
	): ArgsRange =
		if (annotation.fixedNumberOfArgs > 0 && annotation.fixedOffset >= 0) {
			// values in annotation takes precedence over ArgsRangeDecider
			ArgsRange(offset = annotation.fixedOffset, take = annotation.fixedNumberOfArgs)
		} else {
			val componentFactoryContainer = argsGenerator._components
			val argsRangeDecider = componentFactoryContainer.build<ArgsRangeDecider>()
			argsRangeDecider.decideArgsRange(argsGenerator)
				.also {
					val maxArgsInGeneral = componentFactoryContainer.config.maxArgsLevels.maxArgsInGeneral
					check(it.take <= maxArgsInGeneral) {
						"ArgsRangeDecider ${argsRangeDecider::class.qualifiedName} decided to take ${it.take} although the configured maxArgsInGeneral is $maxArgsInGeneral"
					}
				}
				.let {
					if (annotation.fixedOffset >= 0) {
						it.copy(offset = annotation.fixedOffset)
					} else {
						it
					}
				}.let {
					if (annotation.fixedNumberOfArgs > 0) {
						it.copy(take = annotation.fixedNumberOfArgs)
					} else {
						it
					}
				}
		}
}
