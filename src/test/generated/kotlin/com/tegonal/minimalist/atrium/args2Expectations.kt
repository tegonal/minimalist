// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.atrium

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import com.tegonal.minimalist.*

val <A1, A2> Expect< Args2<A1, A2>>.a1 : Expect<A1>
		get() = feature(Args2<A1, A2>::a1)
val <A1, A2> Expect< Args2<A1, A2>>.a2 : Expect<A2>
		get() = feature(Args2<A1, A2>::a2)

val <A1, A2> Expect< Args2<A1, A2>>.representation1 : Expect<String?>
		get() = feature(Args2<A1, A2>::representation1)
val <A1, A2> Expect< Args2<A1, A2>>.representation2 : Expect<String?>
		get() = feature(Args2<A1, A2>::representation2)
