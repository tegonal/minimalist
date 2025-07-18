package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator
import org.junit.jupiter.params.provider.Arguments
import java.lang.reflect.Method

interface ArgsGeneratorToArgumentsConverter {

	fun toArguments(
		testMethod: Method,
		annotation: ArgsSource,
		argsGenerator: ArgsGenerator<List<*>>,
	): List<Arguments>
}

