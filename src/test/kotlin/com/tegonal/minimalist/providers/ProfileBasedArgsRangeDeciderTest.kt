package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.notToThrow
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config.*
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.generators.impl.DefaultArbExtensionPoint
import com.tegonal.minimalist.providers.impl.ProfileBasedArgsRangeDecider
import com.tegonal.minimalist.testutils.createOrderedWithCustomConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import kotlin.math.absoluteValue

@ArgsSourceOptions(profile = TestType.ForAnnotation.Unit)
class ProfileBasedArgsRangeDeciderTest {

	val ownTestProfiles = TestProfiles.create(
		TestType.entries.associate { testType ->
			testType.name to Env.entries.associate {
				it.name to TestConfig(
					maxArgs = when (it) {
						Env.Local -> 2
						Env.PR -> 3
						Env.Main -> 5
						Env.DeployTest -> 7
						Env.DeployInt -> 8
						Env.NightlyTest -> 9
						Env.NightlyInt -> 10
						Env.HotfixPR -> 4
						Env.Hotfix -> 6
						Env.Release -> 6
					}
				)
			}
		}
	)

	@ParameterizedTest
	@ArgsSource("categoryLevelAndGeneratorSize")
	fun activeLevel_takeMatchesMaxArgsOfLevelUnlessArgsGeneratorSizeIsSmaller(
		profile: TestType,
		env: Env,
		argsGeneratorSize: Int
	) {
		val customConfig = MinimalistConfig().copy {
			defaultProfile = profile.name
			activeEnv = env.name
			testProfiles = ownTestProfiles.toHashMap()
		}
		val ordered = createOrderedWithCustomConfig(customConfig)


		val argsGenerator = ordered.fromRange(0 until argsGeneratorSize)
		val config = argsGenerator._components.config
		expect(config.seed).toEqual(customConfig.seed)

		val argsRange = ProfileBasedArgsRangeDecider().decide(argsGenerator)

		expect(argsRange) {
			feature(ArgsRange::offset).toEqual(config.seed.absoluteValue)
			feature(ArgsRange::take).toEqual(
				minOf(
					argsGeneratorSize,
					ownTestProfiles.get(profile.name, env.name).maxArgs
				)
			)
		}
	}

	@Test
	fun canCopeWithALargeSeedOffset() {
		expect {
			DefaultArbExtensionPoint(arb._components, Int.MAX_VALUE).arb.int().generateAndTakeBasedOnDecider().count()
		}.notToThrow()
	}

	@Test
	fun canCopeWithALargeOffsetToDecidedOffset() {
		expect {
			val ordered = createOrderedWithCustomConfig(
				ordered._components.config.copy { offsetToDecidedOffset = Int.MAX_VALUE }
			).ordered
			ordered.of(1, 2, 3, 4).generateAndTakeBasedOnDecider().count()
		}.notToThrow()
	}

	@Test
	fun canCopeWithALargeSeed() {
		expect {
			val ordered = createOrderedWithCustomConfig(
				ordered._components.config.copy { seed = Int.MAX_VALUE }
			).ordered
			ordered.of(1, 2, 3, 4).generateAndTakeBasedOnDecider().count()
		}.notToThrow()
	}

	//TODO 2.0.0
	// check that config requestedMinArgs/maxArgs and passed argsRangeOptions are taken into consideration as expected

	companion object {

		@JvmStatic
		fun categoryLevelAndGeneratorSize() = Tuple(
			ordered.fromEnum<TestType>(),
			ordered.fromEnum<Env>(),
			ordered.fromRange(1..11),
		)
	}
}
