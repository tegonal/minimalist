package com.tegonal.variist.testutils.atrium

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.creating.Expect

val <E : Comparable<E>, T : ClosedRange<E>> Expect<T>.start: Expect<E>
	get() = feature(ClosedRange<E>::start)

fun <E : Comparable<E>, T : ClosedRange<E>> Expect<T>.start(expectationCreator: Expect<E>.() -> Unit): Expect<T> =
	feature(ClosedRange<E>::start, expectationCreator)

val <E : Comparable<E>, T : ClosedRange<E>> Expect<T>.endInclusive: Expect<E>
	get() =
		feature(ClosedRange<E>::endInclusive)

fun <E : Comparable<E>, T : ClosedRange<E>> Expect<T>.endInclusive(expectationCreator: Expect<E>.() -> Unit): Expect<T> =
	feature(ClosedRange<E>::endInclusive, expectationCreator)
