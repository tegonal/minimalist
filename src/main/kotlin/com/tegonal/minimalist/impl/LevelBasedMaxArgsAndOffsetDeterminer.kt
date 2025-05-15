package com.tegonal.minimalist.impl

import com.tegonal.minimalist.MaxArgsAndOffsetDeterminer
import com.tegonal.minimalist.MinimalistConfigLoader
import com.tegonal.minimalist.OrderedArgsGenerator
import com.tegonal.minimalist.RandomArgsGenerator
import java.lang.reflect.Method

class LevelBasedMaxArgsAndOffsetDeterminer : MaxArgsAndOffsetDeterminer {
	override fun determineMaxArgsAndOffset(
		testMethod: Method,
		orderedArgsGenerators: List<Pair<Int, OrderedArgsGenerator<*>>>,
		randomArgsGenerators: List<Pair<Int, RandomArgsGenerator<*>>>
	): Pair<Int, Int> = Pair(
		MinimalistConfigLoader.config.let { config ->
			config.maxArgsLevels[config.activeMaxArgsLevel]
				?: error(
					"the defined activeMaxArgsLevel ${config.activeMaxArgsLevel} is not defined in maxArgsLevels:\n${
						config.maxArgsLevels.entries.joinToString("\n") { (k, v) -> "$k=$v" }
					}"
				)
		},
		0
	)

}
