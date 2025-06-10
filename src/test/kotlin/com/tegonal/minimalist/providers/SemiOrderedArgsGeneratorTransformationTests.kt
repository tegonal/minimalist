package com.tegonal.minimalist.providers

import ch.tutteli.atrium.api.fluent.en_GB.toContainExactlyElementsOf
import ch.tutteli.atrium.api.verbs.expect
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.utils.repeatForever
import com.tegonal.minimalist.utils.repeatForeverFromUntil
import kotlin.test.Test

class SemiOrderedArgsGeneratorTransformationTests {

	// see OrderedArgsGeneratorCombinerTest for tests about combine

	@Test
	fun map() {
		val f: (Int) -> Int = { it + 1 }
		val a1s = listOf(1, 2, 3, 4)
		val generator = (OrderedArgsGenerator.fromList(a1s) as SemiOrderedArgsGenerator<Int>).map(f)

		val expected = a1s.map(f)
		val take1 = expected.take(1)
		val take4 = expected.take(4)

		expect(generator.generate().take(1).toList()).toContainExactlyElementsOf(take1)
		expect(generator.generate().take(2).toList()).toContainExactlyElementsOf(expected.take(2))
		expect(generator.generate().take(3).toList()).toContainExactlyElementsOf(expected.take(3))
		expect(generator.generate().take(4).toList()).toContainExactlyElementsOf(take4)
		expect(generator.generate().take(5).toList()).toContainExactlyElementsOf(take4 + take1)
	}


	@Test
	fun filter() {
		val a1s = listOf(1, 2, 3, 4)
		val generator = OrderedArgsGenerator.fromList(a1s).filter { it % 2 == 0 }

		val expected = repeatForeverFromUntil(2, 5, step = 2)
		expect(generator.generate().take(1).toList()).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generate().take(2).toList()).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generate().take(3).toList()).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generate().take(4).toList()).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generate().take(5).toList()).toContainExactlyElementsOf(expected.take(5).toList())
	}

	@Test
	fun filterNot() {
		val a1s = listOf(1, 2, 3, 4)
		val generator = OrderedArgsGenerator.fromList(a1s).filterNot { it % 2 == 0 }

		val expected = repeatForeverFromUntil(1, 4, step = 2)
		expect(generator.generate().take(1).toList()).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generate().take(2).toList()).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generate().take(3).toList()).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generate().take(4).toList()).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generate().take(5).toList()).toContainExactlyElementsOf(expected.take(5).toList())
	}


	@Test
	fun transform() {
		val a1s = listOf(1, 2)
		val generator = OrderedArgsGenerator.fromList(a1s).transform { seq ->
			seq.flatMap { sequenceOf('a' + it, 'A' + it) }
		}

		val expected = repeatForever().flatMap { sequenceOf('b', 'B', 'c', 'C') }
		expect(generator.generate().take(1).toList()).toContainExactlyElementsOf(expected.take(1).toList())
		expect(generator.generate().take(2).toList()).toContainExactlyElementsOf(expected.take(2).toList())
		expect(generator.generate().take(3).toList()).toContainExactlyElementsOf(expected.take(3).toList())
		expect(generator.generate().take(4).toList()).toContainExactlyElementsOf(expected.take(4).toList())
		expect(generator.generate().take(5).toList()).toContainExactlyElementsOf(expected.take(5).toList())
	}
}
