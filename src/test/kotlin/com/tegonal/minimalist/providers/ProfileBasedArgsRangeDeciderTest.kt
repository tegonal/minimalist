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
import org.junit.jupiter.params.provider.ValueSource

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
	@ArgsSource("testTypeEnvAndGeneratorSize")
	fun activeLevel_takeMatchesMaxArgsOfLevelUnlessArgsGeneratorSizeIsSmaller(
		testType: TestType,
		env: Env,
		argsGeneratorSize: Int
	) {
		val customConfig = MinimalistConfig().copy {
			defaultProfile = testType.name
			activeEnv = env.name
			testProfiles = ownTestProfiles.toHashMap()
		}
		val ordered = createOrderedWithCustomConfig(customConfig)

		val argsGenerator = ordered.fromRange(0 until argsGeneratorSize)
		val config = argsGenerator._components.config
		expect(config.seed).toEqual(customConfig.seed)

		val argsRange = ProfileBasedArgsRangeDecider().decide(argsGenerator)

		expect(argsRange) {
			feature(ArgsRange::offset).toEqual(config.seed.toOffset())
			feature(ArgsRange::take).toEqual(
				minOf(
					argsGeneratorSize,
					ownTestProfiles.get(testType.name, env.name).maxArgs
				)
			)
		}
	}

	@ParameterizedTest
	@ValueSource(ints = [Int.MAX_VALUE, Int.MIN_VALUE])
	fun canCopeWithALargeSeedOffset(offset: Int) {
		expect {
			DefaultArbExtensionPoint(arb._components, offset).arb.int().generateAndTakeBasedOnDecider().count()
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

	@ParameterizedTest
	@ValueSource(ints = [Int.MAX_VALUE, Int.MIN_VALUE])
	fun canCopeWithALargeSeed(offset: Int) {
		expect {
			val ordered = createOrderedWithCustomConfig(
				ordered._components.config.copy { seed = offset }
			).ordered
			ordered.of(1, 2, 3, 4).generateAndTakeBasedOnDecider().count()
		}.notToThrow()
	}

	@Test
	fun requiredMinIgnoredForOrderedArgsGenerator() {
		val argsRange = ProfileBasedArgsRangeDecider().decide(
			ordered.of(1, 2),
			AnnotationData("bla", ArgsRangeOptions(requestedMinArgs = 10))
		)
		expect(argsRange) {
			feature(ArgsRange::take).toEqual(2)
			feature(ArgsRange::offset).toEqual(ordered._components.config.seed.toOffset())
		}
	}

	@Test
	fun requiredMinTakenIntoAccountForSemiOrderedArgsGenerator() {
		val argsRange = ProfileBasedArgsRangeDecider().decide(
			ordered.of(1, 2).zip(arb.of('A')),
			AnnotationData("bla", ArgsRangeOptions(requestedMinArgs = 10))
		)
		expect(argsRange) {
			feature(ArgsRange::take).toEqual(10)
			feature(ArgsRange::offset).toEqual(ordered._components.config.seed.toOffset())
		}
	}

	//TODO 2.0.0
	// check that config requestedMinArgs/maxArgs and passed argsRangeOptions are taken into consideration as expected

	companion object {

		@JvmStatic
		fun testTypeEnvAndGeneratorSize() = Tuple(
			ordered.fromEnum<TestType>(),
			ordered.fromEnum<Env>(),
			ordered.fromRange(1..11),
		)
	}
}
