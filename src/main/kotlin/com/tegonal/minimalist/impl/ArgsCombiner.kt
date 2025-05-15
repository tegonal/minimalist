package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*
import org.apiguardian.api.API
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.stream.Stream

//TODO 2.0.0 remove?
class OrderedArgsGenerator11Combiner<A1, A2>(
    private val argsGenerator1: ArgsGenerator<Args1<A1>>,
    private val argsGenerator2: ArgsGenerator<Args1<A2>>
) {
    init {
        require(argsGenerator1 is OrderedArgsGenerator<*> || argsGenerator2 is OrderedArgsGenerator<*>) {
            "at least one argsGenerator needs to be an OrderedArgsGenerator"
        }
    }
}

//TODO 2.0.0 remove?
class RandomArgsGeneratorCombiner<A1, A2>(
    private val argsGenerator1: RandomArgsGenerator<Args1<A1>>,
    private val argsGenerator2: RandomArgsGenerator<Args1<A2>>
)


