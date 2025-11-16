// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest
// --------------------------------------------------------------------------------------------------------------------
@file:Suppress("UnusedImport")

package com.tegonal.variist.testutils.atrium

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import com.tegonal.variist.*

val <A1> Expect<Args1<A1>>.a1 : Expect<A1>
		get() = feature(Args1<A1>::a1)

val <A1> Expect<Args1<A1>>.representation1 : Expect<String?>
		get() = feature(Args1<A1>::representation1)
