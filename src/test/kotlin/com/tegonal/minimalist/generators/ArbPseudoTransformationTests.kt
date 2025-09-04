package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Args2
import com.tegonal.minimalist.testutils.PseudoArbArgsGenerator
import com.tegonal.minimalist.utils.repeatForever
import kotlin.test.Test

class ArbPseudoTransformationTests {

	// Note, this test relies on implementation details and is thus fragile. E.g. it is undefined how two
	// RandomArgsGenerator are combined, since the result is random the combination is random. We use
	// PseudoRandomArgsGenerator which does not pick randomly but just iterates the given sequence forever

	val a1s = sequenceOf(1, 2, 3, 4)
	val a2s = sequenceOf('a', 'b', 'c', 'd')
	val a2sAfterCombine = a2s.drop(1) + sequenceOf('a')

	@Test
	fun combine() {
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.combine(PseudoArbArgsGenerator(a2s))
		val expected = a1s.zip(a2sAfterCombine)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		// combine doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun combineTransformed() {
		val f: (Int, Char) -> Args2<Int, Char> = { a1, a2 -> Args.of(a1, a2) }
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.combine(PseudoArbArgsGenerator(a2s), f)
		val expected = a1s.zip(a2sAfterCombine, f)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		// combineDependent doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun combineAll() {
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = Tuple(a1generator, PseudoArbArgsGenerator(a2s)).combineAll()
		val expected = a1s.zip(a2sAfterCombine)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		// combine doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun combineDependent() {
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.combineDependent { int ->
			PseudoArbArgsGenerator(a2s.map { char -> char + int })
		}

		// combineDependent doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		// note, our expectation is based on an implementation detail, we know that combineDependent just
		// picks the first generated value of the resulting RandomArgsGenerator and that PseudoArbArgsGenerator drops
		// according to seedOffset
		val a2sexpected = sequenceOf('b', 'd', 'f', 'h')
		val expected = a1s.zip(a2sexpected)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun map() {
		val f: (Int) -> Int = { it + 1 }
		val a1s = sequenceOf(1, 2, 3, 4)
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.map(f)

		// map doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		val expected = a1s.map(f)
		val take1 = expected.take(1).toList()
		val take4 = expected.take(4).toList()

		expect(generator.generateToList(1)).toContainExactlyElementsOf(take1)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(take4)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(take4 + take1)
	}

	@Test
	fun filter() {
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.filter { it % 2 == 0 }

		// filter doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		val expected = repeatForever((2 until 5 step 2).toList(), 0)
		expect(generator.generateToList(1)).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generateToList(5)).toContainExactlyElementsOf(expected.take(5).toList())
	}

	@Test
	fun filterNot() {
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.filterNot { it % 2 == 0 }

		// filterNot doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		val expected = repeatForever((1 until 5 step 2).toList(), 0)
		expect(generator.generateToList(1)).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generateToList(5)).toContainExactlyElementsOf(expected.take(5).toList())
	}

	@Test
	fun transform() {
		val a1s = sequenceOf(1, 2)
		val a1generator = PseudoArbArgsGenerator(a1s)
		val generator = a1generator.transform { seq ->
			seq.flatMap { sequenceOf('a' + it, 'A' + it) }
		}

		// transform doesn't change the seedBaseOffset
		expect(generator._core.seedBaseOffset).toEqual(a1generator.seedBaseOffset)

		val expected = repeatForever().flatMap { sequenceOf('b', 'B', 'c', 'C') }
		expect(generator.generateToList(1)).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generateToList(5)).toContainExactlyElementsOf(expected.take(5).toList())
	}

	fun <T> ArbArgsGenerator<T>.generateToList(amount: Int): List<T> = generate().take(amount).toList()
}
