package com.tegonal.minimalist.generators

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import kotlin.test.Test

abstract class AbstractOrderedArgsGeneratorTest() {

	abstract fun createGenerators(): Map<String, OrderedArgsGenerator<*>>

	@Test
	fun isNeverEmpty() {
		expectGrouped {
			createGenerators().forEach { name, generator ->
				group(name) {
					val l = generator.generate().take(1).toList()
					expect(l).notToBeEmpty()
				}
			}
		}
	}

	@OptIn(ExperimentalWithOptions::class)
	@Test
	fun returnsDifferentValuesUntilReachingSizeAndThenRepeats() {
		expectGrouped {
			createGenerators().forEach { name, generator ->
				group(name) {
					val l = generator.generate().take(generator.size + 1).toList()
					expect(l) {
						size.toEqual(generator.size + 1)
						feature("toSet", { toSet() }) {
							size.toEqual(generator.size)
						}
						get(0).toEqual(l.last())
					}

					(1..generator.size).forEach { offset ->
						generator.generate(offset).take(generator.size).forEachIndexed { index, value ->
							group("offset $offset / index $index") {
								expect(value).toEqual(l[index - offset])
							}
						}
					}
				}
			}
		}
	}
}
