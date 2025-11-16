// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.variist.testutils.atrium

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import com.tegonal.variist.*

val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.a1 : Expect<A1>
		get() = feature(Args3<A1, A2, A3>::a1)
val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.a2 : Expect<A2>
		get() = feature(Args3<A1, A2, A3>::a2)
val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.a3 : Expect<A3>
		get() = feature(Args3<A1, A2, A3>::a3)

val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.representation1 : Expect<String?>
		get() = feature(Args3<A1, A2, A3>::representation1)
val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.representation2 : Expect<String?>
		get() = feature(Args3<A1, A2, A3>::representation2)
val <A1, A2, A3> Expect<Args3<A1, A2, A3>>.representation3 : Expect<String?>
		get() = feature(Args3<A1, A2, A3>::representation3)
