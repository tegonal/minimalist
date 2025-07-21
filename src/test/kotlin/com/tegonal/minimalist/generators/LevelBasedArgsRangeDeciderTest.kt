package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config.MaxArgLevels
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.providers.ArgsRange
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.providers.impl.LevelBasedArgsRangeDecider
import com.tegonal.minimalist.testutils.createOrderedWithCustomConfig
import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.Test

class LevelBasedArgsRangeDeciderTest {

	@Test
	fun configNotSet_throws() {
		expect {
			LevelBasedArgsRangeDecider().decideArgsRange(ordered.of(1, 2))
		}.toThrow<UninitializedPropertyAccessException> {
			messageToContain("property config")
		}
	}

	@ParameterizedTest
	@ArgsSource("activeLevelAndGeneratorSize")
	fun activeLevel_takeMatchesMaxArgsOfLevelUnlessArgsGeneratorSizeIsSmaller(
		activeLevel: Int,
		argsGeneratorSize: Int
	) {
		val customConfig = MinimalistConfig().copy(
			activeMaxArgsLevel = activeLevel,
			maxArgsLevels = MaxArgLevels(20, maxArgsLevels)
		)
		val ordered = createOrderedWithCustomConfig(customConfig)


		val argsGenerator = ordered.fromRange(0 until argsGeneratorSize)
		val config = argsGenerator._components.config
		expect(config.seed).toEqual(customConfig.seed)

		val argsRange = LevelBasedArgsRangeDecider().also { it.setConfig(customConfig) }.decideArgsRange(argsGenerator)

		expect(argsRange) {
			feature(ArgsRange::offset).toEqual(config.seed)
			feature(ArgsRange::take).toEqual(minOf(argsGeneratorSize, maxArgsLevels[activeLevel]!!))
		}
	}

	companion object {
		val maxArgsLevels = mapOf(1 to 2, 2 to 3, 3 to 10)

		@JvmStatic
		fun activeLevelAndGeneratorSize() = Tuple(
			ordered.fromRange(1..3),
			ordered.fromRange(1..11),
		)
	}
}


