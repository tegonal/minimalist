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
	@ArgsSource("weightsInTotalAlways100")
	fun `check weights are correct`(weights: List<Int>) {
		val g1 = PseudoArbArgsGenerator(
			(0..9).asSequence(),
			seedBaseOffset = 0,
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
		}.toThrow<IllegalStateException> {
			messageToContain("$weight is not a valid (1.) weight, must be greater than 0")
		}
		expect {
			mergeWeighted(50 to g1, weight to g2)
		}.toThrow<IllegalStateException> {
			messageToContain("$weight is not a valid (2.) weight, must be greater than 0")
		}

		expect {
			mergeWeighted(10 to g1, 50 to g2, weight to g3)
		}.toThrow<IllegalStateException> {
			messageToContain("$weight is not a valid (3.) weight, must be greater than 0")
		}
	}

	@ParameterizedTest
	@ArgsSource("twoWeightsInTotalIntMaxOrMore")
	fun `invalid total weights in case of 2`(weight1: Int, weight2: Int) {
		val g1 = arb.intFromUntil(1, 10)
		val g2 = arb.intFromUntil(20, 30)

		expect {
			mergeWeighted(weight1 to g1, weight2 to g2)
		}.toThrow<ArithmeticException> {
			messageToContain("integer overflow")
		}
	}

	@ParameterizedTest
	@ArgsSource("threeWeightsInTotalIntMaxOrMore")
	fun `invalid total weights in case of 3`(weight1: Int, weight2: Int, weight3: Int) {
		val g1 = arb.intFromUntil(1, 10)
		val g2 = arb.intFromUntil(20, 30)
		val g3 = arb.intFromUntil(40, 50)

		expect {
			mergeWeighted(weight1 to g1, weight2 to g2, weight3 to g3)
		}.toThrow<ArithmeticException> {
			messageToContain("integer overflow")
		}
	}

	companion object {
		@JvmStatic
		fun weightsInTotalAlways100() = createMinimalistRandom().let { minimalistRandom ->
			arb.intFromUntil(1, 10).map { numOfGenerators ->
				mutableListOf<Int>().also { weights ->
					val cumulativeWeight = (0..numOfGenerators - 1).fold(0) { cumulativeWeight, index ->
						val remainingWeight = 99 - numOfGenerators + index - cumulativeWeight
						val weight = if (remainingWeight <= 1) 1 else minimalistRandom.nextInt(1, remainingWeight)
						weights.add(weight)
						cumulativeWeight + weight
					}
					weights.add(100 - cumulativeWeight)
				}
			}
		}

		@JvmStatic
		fun invalidWeight() =
			//TODO 2.1.0 introduce the concept of edge cases, here we would like to be sure that 0 is invalid as well
			arb.intFromTo(Int.MIN_VALUE, 0)

		@JvmStatic
		fun twoWeightsInTotalIntMaxOrMore() =
			arb.intFromUntil(1, Int.MAX_VALUE).combineDependent {
				arb.intFromTo(Int.MAX_VALUE - it, Int.MAX_VALUE)
			}


		@JvmStatic
		fun threeWeightsInTotalIntMaxOrMore() =
			arb.intFromUntil(1, Int.MAX_VALUE).combineDependent {
				arb.intFromUntil(1, Int.MAX_VALUE)
			}.combineDependent({ (a, b) ->
				val total = a.toLong() + b.toLong()
				if (total > Int.MAX_VALUE) arb.intFromUntil(1, Int.MAX_VALUE)
				else arb.intFromTo(Int.MAX_VALUE - total.toInt(), Int.MAX_VALUE)
			}) { p, a3 -> p.append(a3) }
	}
}
