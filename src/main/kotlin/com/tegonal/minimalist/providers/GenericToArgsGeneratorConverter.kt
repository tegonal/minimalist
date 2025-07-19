package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator

interface GenericToArgsGeneratorConverter {

	fun toArgsGenerator(
		firstArgsGenerator: ArgsGenerator<*>,
		restMaybeArgGenerators: List<*>
	): ArgsGenerator<List<*>>
}

