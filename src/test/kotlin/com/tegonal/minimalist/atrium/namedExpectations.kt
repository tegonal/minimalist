package com.tegonal.minimalist.atrium

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBeAnInstanceOf
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Named

fun <T> Expect<Named<T>>.name(assertionCreator: Expect<String>.() -> Unit): Expect<Named<T>> =
	feature(Named<T>::getName, assertionCreator)

fun <T> Expect<Named<T>>.payload(assertionCreator: Expect<T>.() -> Unit): Expect<Named<T>> =
	feature(Named<T>::getPayload, assertionCreator)

inline fun <reified T> Expect<Any>.toBeANamedOf(representation: String, payload: T): Expect<Named<T>> =
	toBeAnInstanceOf<Named<T>> {
		name { toEqual(representation) }
		payload { toEqual(payload) }
	}
