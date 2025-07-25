package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config.CategorizedMaxArgsLevels
import com.tegonal.minimalist.config.MaxArgsCategory
import com.tegonal.minimalist.config.MaxArgsLevel
import com.tegonal.minimalist.config.MaxArgsLevels
import com.tegonal.minimalist.config.MinimalistConfig
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.generators.fromEnum
import com.tegonal.minimalist.generators.fromRange
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.providers.impl.LevelBasedArgsRangeDecider
import com.tegonal.minimalist.testutils.createOrderedWithCustomConfig
import org.junit.jupiter.params.ParameterizedTest

@ArgsSourceOptions(category = "Unit")
class LevelBasedArgsRangeDeciderTest {

	val categorizedMaxArgsLevels = CategorizedMaxArgsLevels.Companion.create(
		MaxArgsCategory.entries.associate { category ->
			category.name to MaxArgsLevels.Companion.create(MaxArgsLevel.entries.associate {
				it.name to when (it) {
					MaxArgsLevel.Local -> 2
					MaxArgsLevel.PR -> 3
					MaxArgsLevel.Main -> 5
					MaxArgsLevel.Nightly -> 8
					MaxArgsLevel.Release -> 10
				}
			})
		}
	)

	@ParameterizedTest
	@ArgsSource("categoryLevelAndGeneratorSize")
	fun activeLevel_takeMatchesMaxArgsOfLevelUnlessArgsGeneratorSizeIsSmaller(
        activeCategory: MaxArgsCategory,
        activeLevel: MaxArgsLevel,
        argsGeneratorSize: Int
	) {
		val customConfig = MinimalistConfig().copy(
			defaultMaxArgsLevelCategory = activeCategory.name,
			activeMaxArgsLevel = activeLevel.name,
			categorizedMaxArgsLevels = categorizedMaxArgsLevels
		)
		val ordered = createOrderedWithCustomConfig(customConfig)


		val argsGenerator = ordered.fromRange(0 until argsGeneratorSize)
		val config = argsGenerator._components.config
		expect(config.seed).toEqual(customConfig.seed)

		val argsRange = LevelBasedArgsRangeDecider().decide(argsGenerator)

        expect(argsRange) {
            feature(ArgsRange::offset).toEqual(config.seed)
            feature(ArgsRange::take).toEqual(
                minOf(
                    argsGeneratorSize,
                    categorizedMaxArgsLevels[activeCategory.name][activeLevel.name]
                )
            )
        }
	}

	companion object {

		@JvmStatic
		fun categoryLevelAndGeneratorSize() = Tuple(
            ordered.fromEnum<MaxArgsCategory>(),
            ordered.fromEnum<MaxArgsLevel>(),
            ordered.fromRange(1..11),
        )
	}
}
