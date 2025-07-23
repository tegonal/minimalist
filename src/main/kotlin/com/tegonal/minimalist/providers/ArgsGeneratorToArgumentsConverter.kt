package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator
import org.junit.jupiter.params.provider.Arguments

/**
 * Responsible to generate values for a given [ArgsGenerator] and to transform them to [Arguments].
 *
 * @since 2.0.0
 */
interface ArgsGeneratorToArgumentsConverter {

	fun toArguments(
		annotationData: AnnotationData,
		//TODO 2.1.0 consider to switch to Array<*> for performance reasons, we never mutate
		argsGenerator: ArgsGenerator<List<*>>,
	): Sequence<Arguments>
}

