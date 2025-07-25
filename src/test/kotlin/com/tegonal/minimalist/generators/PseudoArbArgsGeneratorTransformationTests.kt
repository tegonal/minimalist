package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.verbs.expect
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Args2
import com.tegonal.minimalist.testutils.PseudoArbArgsGenerator
import com.tegonal.minimalist.utils.repeatForever
import kotlin.test.Test

class PseudoArbArgsGeneratorTransformationTests {

	// Note, this test relies on implementation details and is thus fragile. E.g. it is undefined how two
	// RandomArgsGenerator are combined, since the result is random the combination is random. We use
	// PseudoRandomArgsGenerator which does not pick randomly but just iterates the given sequence forever

	@Test
	fun combine() {
		val a1s = sequenceOf(1, 2, 3, 4)
		val a2s = sequenceOf('a', 'b', 'c', 'd')
		val generator = PseudoArbArgsGenerator(a1s).combine(PseudoArbArgsGenerator(a2s))
		val expected = a1s.zip(a2s)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun combineTransformed() {
		val f: (Int, Char) -> Args2<Int, Char> = { a1, a2 -> Args.of(a1, a2) }
		val a1s = sequenceOf(1, 2, 3, 4)
		val a2s = sequenceOf('a', 'b', 'c', 'd')
		val generator = PseudoArbArgsGenerator(a1s).combine(PseudoArbArgsGenerator(a2s), f)
		val expected = a1s.zip(a2s, f)
		val oneCombined = expected.take(1).toList()
		val fourCombined = expected.take(4).toList()

		expect(generator.generateToList(1)).toContainExactlyElementsOf(oneCombined)
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(fourCombined)
		expect(generator.generateToList(5)).toContainExactlyElementsOf(fourCombined + oneCombined)
	}

	@Test
	fun combineDependent() {
		val a1s = sequenceOf(1, 2, 3, 4)
		val a2s = sequenceOf('a', 'b', 'c', 'd')
		val generator = PseudoArbArgsGenerator(a1s).combineDependent { int ->
            PseudoArbArgsGenerator(a2s.map { char -> char + int })
		}

		// note, we expect 'b' to 'e' for a2 because of an implementation detail, we know that combineDependent just
		// picks the first generated value of the resulting RandomArgsGenerator (i.e. in our case it always picks 'a'),
		// we expect
		val a2sexpected = sequenceOf('b', 'c', 'd', 'e')
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
		val generator = PseudoArbArgsGenerator(a1s).map(f)

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
		val a1s = sequenceOf(1, 2, 3, 4)
		val generator = PseudoArbArgsGenerator(a1s).filter { it % 2 == 0 }

		val expected = repeatForever((2 until 5 step 2).toList(), 0)
		expect(generator.generateToList(1)).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generateToList(5)).toContainExactlyElementsOf(expected.take(5).toList())
	}

	@Test
	fun filterNot() {
		val a1s = sequenceOf(1, 2, 3, 4)
		val generator = PseudoArbArgsGenerator(a1s).filterNot { it % 2 == 0 }

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
		val generator = PseudoArbArgsGenerator(a1s).transform { seq ->
			seq.flatMap { sequenceOf('a' + it, 'A' + it) }
		}

		val expected = repeatForever().flatMap { sequenceOf('b', 'B', 'c', 'C') }
		expect(generator.generateToList(1)).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generateToList(2)).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generateToList(3)).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generateToList(4)).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generateToList(5)).toContainExactlyElementsOf(expected.take(5).toList())
	}

	fun <T> ArbArgsGenerator<T>.generateToList(amount: Int): List<T> = generate().take(amount).toList()
}
