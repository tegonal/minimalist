package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.append
import ch.tutteli.kbox.toVararg
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.providers.ArgsSource
import com.tegonal.minimalist.testutils.PseudoArbArgsGenerator
import com.tegonal.minimalist.testutils.withMockedRandom
import com.tegonal.minimalist.utils.createMinimalistRandom
import com.tegonal.minimalist.utils.repeatForever
import org.junit.jupiter.params.ParameterizedTest

class ArbMergeWeightedTest {

	@ParameterizedTest
	@ArgsSource("weights")
	fun `check weights are correct`(weights: List<Int>) {
		val g1 = PseudoArbArgsGenerator(
			(0..9).asSequence(),
			arb._components.withMockedRandom(ints = (1..100).toList())
		)
		val (secondWithWeights, othersWithWeights) = weights.drop(1).mapIndexed { index, weight ->
			weight to PseudoArbArgsGenerator((0..9).asSequence().map { it + (index + 1) * 10 })
		}.toVararg()

		val merged = mergeWeighted(weights.first() to g1, secondWithWeights, *othersWithWeights)

		val l = merged.generate().take(100).toList()

		// The following depends heavily on implementation details: we know that we use Random inside for a
		// uniform distribution and we know that weights keep the given order, i.e. if g1 has weight 10 then if
		// Random.next(1,100) yields a value between 1 and 10 g1 is picked to contribute a value
		val expected = weights.flatMapIndexed { index, weight ->
			val s = repeatForever().flatMap { (0..9).asSequence().map { it + index * 10 } }
			s.take(weight)
		}.toList()

		expect(l).toContainExactlyElementsOf(expected)
	}


	@ParameterizedTest
	@ArgsSource("invalidWeight")
	fun invalidWeights(weight: Int) {
		val g1 = arb.intFromUntil(1, 10)
		val g2 = arb.intFromUntil(20, 30)
		val g3 = arb.intFromUntil(40, 50)

		expect {
			mergeWeighted(weight to g1, 50 to g2)
		}.toThrow<IllegalArgumentException> {
			messageToContain("weight is from 1 to 99 (percentage), given $weight")
		}
		expect {
			mergeWeighted(50 to g1, weight to g2)
		}.toThrow<IllegalArgumentException> {
			messageToContain("weight is from 1 to 99 (percentage), given $weight")
		}

		expect {
			mergeWeighted(10 to g1, 50 to g2, weight to g3)
		}.toThrow<IllegalArgumentException> {
			messageToContain("weight is from 1 to 99 (percentage), given $weight")
		}
	}

	@ParameterizedTest
	@ArgsSource("invalidTotalWeight2")
	fun `invalid total weights in case of 2`(weight1: Int, weight2: Int) {
		val g1 = arb.intFromUntil(1, 10)
		val g2 = arb.intFromUntil(20, 30)

		expect {
			mergeWeighted(weight1 to g1, weight2 to g2)
		}.toThrow<IllegalArgumentException> {
			messageToContain("weights must add up to 100, given ${weight1 + weight2}")
		}
	}

	@ParameterizedTest
	@ArgsSource("invalidTotalWeight3")
	fun `invalid total weights in case of 3`(weight1: Int, weight2: Int, weight3: Int) {
		val g1 = arb.intFromUntil(1, 10)
		val g2 = arb.intFromUntil(20, 30)
		val g3 = arb.intFromUntil(40, 50)

		expect {
			mergeWeighted(weight1 to g1, weight2 to g2, weight3 to g3)
		}.toThrow<IllegalArgumentException> {
			messageToContain("weights must add up to 100, given ${weight1 + weight2 + weight3}")
		}
	}

	companion object {
		@JvmStatic
		fun weights() = createMinimalistRandom().let { minimalistRandom ->
			arb.intFromUntil(1, 10).map { numOfGenerators ->
				mutableListOf<Int>().also { weights ->
					val cumulativeWeight = (0..numOfGenerators - 1).fold(0) { cumulativeWeight, index ->
						val remainingWeight = 99 - numOfGenerators + index - cumulativeWeight
						val weight = if (remainingWeight <= 1) 1 else minimalistRandom.nextInt(1, remainingWeight)
						weights.add(weight)
						cumulativeWeight + weight
					}
					check(cumulativeWeight < 100) {
						"wrong test-setup, cumulativeWeight was $cumulativeWeight"
					}
					weights.add(100 - cumulativeWeight)
				}
			}
		}

		@JvmStatic
		fun invalidWeight() = arb.intFromUntil(Int.MIN_VALUE, 1) + arb.intFromUntil(100, Int.MAX_VALUE)

		@JvmStatic
		fun invalidTotalWeight2() = run {
			val lessThan100 = arb.intFromUntil(1, 98).combineDependent {
				if (it == 98) arb.of(1)
				else arb.intFromUntil(1, 99 - it)
			}
			val moreThan100 = arb.intFromUntil(2, 99).combineDependent {
				val from = 100 - it + 1
				if (from == 99) arb.of(from) else arb.intFromUntil(from, 99)
			}
			lessThan100 + moreThan100
		}

		@JvmStatic
		fun invalidTotalWeight3() = run {
			val lessThan100 = arb.intFromUntil(1, 97).combineDependent {
				if (it == 97) arb.of(1)
				else arb.intFromUntil(1, 98 - it)
			}.combineDependent({ (a, b) ->
				val total = a + b
				if (total == 98) arb.of(1)
				else arb.intFromUntil(1, 99 - total)
			}) { p, a3 -> p.append(a3) }

			val moreThan100 = arb.intFromUntil(1, 99).combineDependent {
				arb.intFromUntil(1, 99)
			}.combineDependent({ (a, b) ->
				val total = a + b
				if (total == 2) arb.of(99)
				else arb.intFromUntil(maxOf(1, 100 - total + 1), 99)
			}) { p, a3 -> p.append(a3) }

			lessThan100 + moreThan100
		}
	}
}
