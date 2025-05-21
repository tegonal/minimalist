package com.tegonal.minimalist.providers

import org.junit.jupiter.params.provider.Arguments
import java.lang.reflect.Method

interface GenericToArgumentsTransformer {

	fun toArguments(
		testMethod: Method,
		annotation: ArgsSource,
		maybeGenerators: List<Any?>
	): List<Arguments>
}

