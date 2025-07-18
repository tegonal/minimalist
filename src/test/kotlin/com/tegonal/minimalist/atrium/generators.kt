package com.tegonal.minimalist.atrium

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.creating.Expect
import com.tegonal.minimalist.generators.SemiOrderedArgsGenerator

val <T, G : SemiOrderedArgsGenerator<T>> Expect<G>.size get() = feature(SemiOrderedArgsGenerator<T>::size)
