package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.arb
import com.tegonal.minimalist.config.config
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.createArbWithCustomConfig
import com.tegonal.minimalist.testutils.withMockedRandom
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import kotlin.collections.map

class ArbSeedOffsetTest {

	@ParameterizedTest
	@ArgsSource("arb0To10")
	fun mapIndexedTakesConfigDeterminedOffsetIntoAccount(offset: Int) {
		val modifiedArb = createArbWithCustomConfig(
			arb._components.config.copy { offsetToDecidedOffset = offset }
		)._components.arb

		val indices = modifiedArb.int().mapIndexed { index, _ -> index }.generate().take(10).toList()

		expect(indices).toEqual((0..9).map { it + offset })
	}

	@Test
	fun combineIncrementsSeedOffset() {
		val arb =
			createArbWithCustomConfig(arb._components.config.copy { seed = 0 })._components.withMockedRandom { seed ->
				Tuple((0..9).map { it + seed * 10 }, emptyList(), emptyList())
			}.arb

		val listOfPairs = arb.int().zip(arb.int()).generateAndTake(10).toList()
		val a1s = listOfPairs.map { it.first }
		val a2s = listOfPairs.map { it.second }
		expect(a1s).toEqual((0..9).toList())
		expect(a2s).toEqual((10..19).toList())
	}

	@Test
	fun combineTakesGivenSeedOffsetIntoAccount() {
		val arb =
			createArbWithCustomConfig(arb._components.config.copy { seed = 0 })._components.withMockedRandom { seed ->
				Tuple((0..9).map { it + seed * 10 }, emptyList(), emptyList())
			}.arb

		val listOfPairs = arb.int().zip(arb.int()).generate(seedOffset = 2).take(10).toList()
		val a1s = listOfPairs.map { it.first }
		val a2s = listOfPairs.map { it.second }
		expect(a1s).toEqual((20..29).toList())
		expect(a2s).toEqual((30..39).toList())
	}

	@Test
	fun combineDependentUsingArbInsideIncrementsBaseSeedOffset() {
		val arb1 =
			createArbWithCustomConfig(arb._components.config.copy { seed = 0 })._components.withMockedRandom { seed ->
				Tuple((0..9).map { it + seed * 10 }, emptyList(), emptyList())
			}.arb

		val listOfPairs = arb1.int().combineDependent { arb.int() }.generateAndTake(10).toList()
		val a1s = listOfPairs.map { it.first }
		val a2s = listOfPairs.map { it.second }
		expect(a1s).toEqual((0..9).toList())
		expect(a2s).toEqual((10..100 step 10).toList())
	}

	@Test
	fun combineDependentTakesGivenSeedOffsetIntoAccount() {
		val arb1 =
			createArbWithCustomConfig(arb._components.config.copy { seed = 0 })._components.withMockedRandom { seed ->
				Tuple((0..9).map { it + seed * 10 }, emptyList(), emptyList())
			}.arb

		val listOfPairs = arb1.int().combineDependent { arb.int() }.generate(seedOffset = 3).take(10).toList()
		val a1s = listOfPairs.map { it.first }
		val a2s = listOfPairs.map { it.second }
		expect(a1s).toEqual((30..39).toList())
		expect(a2s).toEqual((40..130 step 10).toList())
	}

	@Test
	fun usingArbInAnArbExtensionReceiverIncrementsBaseSeedOffset() {
		val arb1 =
			createArbWithCustomConfig(arb._components.config.copy { seed = 0 })._components.withMockedRandom { seed ->
				Tuple((0..9).map { it + seed * 10 }, emptyList(), emptyList())
			}.arb
		with(arb1) {
			val arb2 = arb
			val a1s = arb1.int().generateAndTake(10).toList()
			val a2s = arb2.int().generateAndTake(10).toList()
			expect(a1s).toEqual((0..9).toList())
			expect(a2s).toEqual((10..19).toList())
		}
	}

	companion object {
		@JvmStatic
		fun arb0To10() = arb.intFromUntil(0, 10)
	}
}
