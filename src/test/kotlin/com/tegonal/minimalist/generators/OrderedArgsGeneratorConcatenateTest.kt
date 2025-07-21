package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.config

@Suppress("UNCHECKED_CAST")
class OrderedArgsGeneratorConcatenateTest : AbstractOrderedArgsGeneratorConcatenateTest() {

    override fun createGenerators(): OrderedArgsTestFactoryResult<Any> {
        val g1 = variants(0)
        val g2 = variants(1)

        val combined = g1.combine(g2) { (name1, g1), (name2, g2) ->
            Tuple("$name1 + $name2", g1 + g2, arrayToList(getValue(name1, 0)) + arrayToList(getValue(name2, 1)))
        }

        // we override ordered in AbstractOrderedArgsGeneratorWithoutAnnotationsTest because we want to test that
        // the ComponentContainer is passed correctly but that would also mean that we always use seed = 1 as offset here
        // that's not what we want, hence we use the "normal" MinimalistConfig and its defined seed
        val seed = com.tegonal.minimalist.generators.ordered._components.config.seed
        return combined.generate(seed).take(50)
    }
}
