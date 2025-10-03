<!-- for main -->

[![Download](https://img.shields.io/badge/Download-v2.0.0--RC--2-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/2.0.0-RC-2)
[![EUPL 1.2](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Quality Assurance](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml/badge.svg?event=push&branch=main)](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml?query=branch%3Amain)
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")

<!-- for main end -->
<!-- for release -->
<!--
[![Download](https://img.shields.io/badge/Download-v2.0.0--RC--2-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/2.0.0-RC-2)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")
-->
<!-- for release end -->

# Minimalist

![Minimalist](https://raw.githubusercontent.com/tegonal/minimalist/main/.idea/icon.png) M like Minimalist,
a library which helps you in setting up parameterized tests and prioritise them in case you don't have enough time
to execute all of them.

Although it might resemble a property based testing library, its focus is on tests that take longer
(integration, e2e and system integration tests) and is more data-driven oriented. A reason why you can also [use
it in other contexts than JUnit](#use-minimalist-in-other-contexts-than-junit).

Take a look at the [Examples](#examples) to see how you can use it.

---
❗ You are taking a *sneak peek* at the next version. It could be that some features you find on this page are not
released yet.  
Please have a look at the README of the corresponding release/git tag. Latest
version: [README of v2.0.0-RC-2](https://github.com/tegonal/minimalist/tree/v2.0.0-RC-2/README.md).

---

**Table of Contents**

- [Installation](#installation)
- [Examples](#examples)
	- [Your first parameterized Test](#your-first-parameterized-test)
	- [Ordered and arbitrary arguments generators](#ordered-and-arbitrary-arguments-generators)
		- [Predefined ordered factories](#predefined-ordered-factories)
		- [Predefined arb factories](#predefined-arb-factories)
		- [Predefined args providers](#predefined-args-providers)
	- [Combinators](#combinators)
		- [Generic combine](#generic-combine)
		- [ordered.cartesian](#ordered-cartesian)
		- [arb.zip](#arb-zip)
		- [combineDependent](#combinedependent)
		- [transform](#transform)
		- [map](#map)
		- [filter/filterNot](#filter)
		- [chunked](#chunked)
		- [ordered.concatenation](#ordered-concatenation)
		- [arb.mergeWeighted](#arb-mergeweighted)
		- [ordered.toArbArgsGenerator](#ordered-toarbargsgenerator)
- [Use Minimalist in other contexts than JUnit](#use-minimalist-in-other-contexts-than-junit)
- [Configuration](#configuration)
	- [Profiles and Envs](#profiles-and-envs)
	- [Fixing the seed](#fixing-the-seed)
    - [Change the ArgsRangeDecider](#change-the-argsrangedecider)
    - [Use a SuffixArgsGeneratorDecider](#use-a-suffixargsgeneratordecider)
- [Helpers](#helpers)
	- [Random helpers](#random-helpers)
	- [Sequence helpers](#sequence-helpers)
	- [BigInt helpers](#bigint-helpers)
- [Code Documentation](#code-documentation)
- [License](#license)

# Installation

Minimalist is published to maven central.

*build.gradle.kts*:

```kotlin
repositories {
	mavenCentral()
}
dependencies {
	testImplementation("com.tegonal.minimalist:minimalist:2.0.0-RC-2")
}
```

Minimum requirements:

- Kotlin: 1.9
- JDK: 11
- JUnit: 5.13.4

# Examples

## Your first parameterized Test

Likewise JUnit Jupiter params provides `@MethodSource`, Minimalist provides `@ArgsSource`.

<!--suppress HtmlUnknownTag -->
<code-first-1>

```kotlin
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest

class FirstTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("myProvider")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}

	companion object {
		@JvmStatic
		fun myProvider() = 1..20
	}
}
```

</code-first-1>

At a first glance, `ArgsSource` in the above example behaves the same way as
[ `MethodSource`](https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests-sources-MethodSource)
but the runtime behaviour is different. If you run the above code, then per default only a window of 5 values is taken
from the range `1..20` based on a randomly chosen seed resulting in 5 runs.
The base assumption of Minimalist is that your tests are expensive to execute and that you don't have the time to run
all values of interested in one go. But, eventually, over multiple runs, it would still be nice to cover all values.
The [configuration](#configuration) section explains how we can adjust the default profiles and more.
For now, we continue without going too much into configuration details.

> [!Note]
> Unfortunately, due to a [bug in intellij](https://youtrack.jetbrains.com/issue/KTIJ-35010), you won't be able to
> click on `myProvider` and jump to the function definition. As workaround, you can define your own ArgsSource as
> follows:
> ```kotlin
> package com.example
> 
> import com.tegonal.minimalist.providers.ArgsArgumentProvider
> import com.tegonal.minimalist.providers.ArgsSourceLike
> import org.intellij.lang.annotations.Language
> import org.junit.jupiter.params.provider.ArgumentsSource
> 
> @Target(AnnotationTarget.FUNCTION)
> @Retention(AnnotationRetention.RUNTIME)
> @MustBeDocumented
> @ArgumentsSource(ArgsArgumentProvider::class)
> @ArgsSourceLike
> annotation class ArgsSource(
>     @Suppress("unused") // it is used via reflection by Minimalist
>     @Language("jvm-method-name") val methodName: String,
> )
> ```

Although Minimalist allows that one can provide "raw" values as in `1..20` (could also have been `listOf(1, 2, 3, ...)`)
it provides `ArgsGenerator`s which are way more efficient.
Raw values are turned into a `List` and then passed to `ordered.fromList`. The next section outlines what `ordered` is.

## Ordered and arbitrary arguments generators

Minimalist provides two entry points to create an `ArgsGenenerator`: `ordered` and `arb`.

`ordered` can be used to define an ordered (not to be confused with sorted) list of finite
values where the corresponding `OrderedArgsGenerator` generates a sequence which repeats them indefinitely.
For instance, if you use `ordered.of('a', 'b')` as provider, then `OrderedArgsGenerator.size = 2` and correspondingly
it results in two runs. You either will get `'a'` in the first run and in the second `'b'` or
you will get `'b'` in the first run and `'a'` in the second.
That is because the resulting sequence repeats indefinitely `'a', 'b', 'a', 'b', ... `
and it depends on a randomly chosen seed what offset is taken.

The "counterpart" of `ordered` is `arb` that allows to create `ArbArgsGenerator`s which per definition generate
an infinite sequence of values where it is basically not known if they follow some order or not.
The default implementations are almost all based on `Random`.
The number of runs of such a provider is in theory infinite as well (`ArbArgsGenerator.size` doesn't exist) but gets
limited by the [profile](#profiles-and-envs) the test falls into, the [environment](#profiles-and-envs) where the test
runs and what configuration
was set up for this combination. Also `OrderedArgsGenerator` are limited by profile/env but introduce an own limit in
addition. Following an example of how to use an `ArgsGenerator` as args provider.

<code-arb-provider>

```kotlin
class ArbProviderTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("arb1To50000")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}

	companion object {
		@JvmStatic
		fun arb1To50000() = arb.intFromTo(1, 50_000)
	}
}
```

</code-arb-provider>

In contrast to the [first parameterized test example](#your-first-parameterized-test) where we used
raw values (which are turned into a `List` and then passed to `ordered.fromList`), we now want to be sure the test
covers integers from `1..50000` and not only `1..20` (and in [predefined args providers](#predefined-args-providers) you
will see how we
define that it shall work for all positive integers). But why did we use `arb` and not `ordered`? The short answer: too
many values -- say your test takes ~2s and you cannot parallelise, running all would already take ~24h -- and most
likely this is not your single test. In this case we don't expect that we cover all cases in a reasonable amount of
time, hence we use `arb`. An `ArbArgsGenerator` has a different runtime behaviour regarding:

1. how [multiple ArgsGenerators are combined implicitly](#generic-combine) (which we discuss there)
2. how many runs are generated

Let's take a look at a simple example to see the difference in how many runs are generated.
Where `OrderedArgsGenerator`s generate a window of all possible values (i.e. still ordered),
an `ArbArgsGenerator` generates arbitrary/random values (possibly the same value multiple times).
Following an example where `maxArgs=5` (more on `maxArgs` in the [Configuration](#configuration) section):

```kotlin
ordered.of(1, 2, 3) // results in 3 runs: 1, 2, 3 or 2, 3, 1 or 3, 1, 2
arb.of(1, 2, 3)     // results in 5 runs, order unknown (will change to 3 runs in v2.1.0)
```

As a rule of thumb, use `ordered` only if you have explicit restrictions and you would test all of them if the tests
were faster. Whenever you are in doubt, use `arb` and switch to `ordered` once you are convinced that it is better
suited. Read on to get a better understanding how they differ (especially the [Combinators](#combinators) section).

### Predefined ordered factories

Following a few examples what predefined factories exist on `ordered` (take a look at the
[Code Documentation](#code-documentation) to see all):

<code-ordered-1>

```kotlin
import com.tegonal.minimalist.generators.*

enum class Color {
	Red, Blue, Green
}

ordered.of(1, 3, 2)
ordered.fromEnum<Color>()
ordered.fromList(listOf(6, 8, 1))
ordered.fromArray(arrayOf(4, 2, 7))
ordered.fromRange(1..10)
ordered.fromProgression(1..10 step 2)

ordered.boolean()
ordered.intFromUntil(1, 5)
ordered.longFromTo(1, 5)
//...
```

</code-ordered-1>

### Predefined arb factories

Following a few examples what predefined factories exist on `arb` (take a look at the
[Code Documentation](#code-documentation) to see all):

<code-arb-1>

```kotlin
import com.tegonal.minimalist.generators.*

enum class Color {
	Red, Blue, Green
}

arb.of(1, 2, 3)
arb.fromEnum<Color>()
arb.fromList(listOf(1, 2, 3))
arb.fromArray(arrayOf(1, 2, 3))
arb.fromRange(1..10)
arb.fromProgression(1..10 step 2)

arb.boolean()
arb.char()
arb.int()
arb.long()
arb.double()

arb.intPositive()
arb.intNegative()
arb.longPositiveAndZero()
arb.longNegativeAndZero()
// ...
arb.intFromUntil(-1000, 1000)
arb.longFromTo(7, 420)
arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
//...

LocalDate.now().let { now ->
	arb.localDateFromUntil(now, now.plusMonths(4))
}
LocalDateTime.now().let { now ->
	arb.localDateTimeFromUntil(now, now.plusHours(48), ChronoUnit.MINUTES)
}
// ZonedDateTime/OffsetDateTime
//...

arb.charRange(minInclusive = 'A', maxInclusive = 'Z', minSize = 1)
arb.intBounds(minInclusive = 1, maxInclusive = 1000, minSize = 3, maxSize = 10)

arb.longBoundsBased(minInclusive = -10, maxInclusive = 10, maxSize = 4) { lower, upper ->
	//..
}
//...

arb.string(minLength = 0, maxLength = 20, allowedRanges = UnicodeRanges.ASCII_PRINTABLE.ranges)
```

</code-arb-1>

### Predefined args providers

For some `arb` and a few `ordered` definitions we provide predefined args providers which you can use in `ArgsSource`.
You only need to extend (directly or indirectly) Minimalist's `PredefinedArgsProviders`. Following an example:

<code-predefined-1>

```kotlin
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest


class PredefinedArgsProvidersTest : PredefinedArgsProviders {

	@ParameterizedTest
	// uses the predefined ArbArgsGenerator arbIntPositive defined in PredefinedArgsProviders
	@ArgsSource("arbIntPositive")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}
}
```

</code-predefined-1>

Typically, you will reuse your custom providers in several tests. We recommend you create your own interfaces which
contain predefined `ArgsSource` providers and one `ArgsProviders` which extends all of them
(see `com.tegonal.minimalist.providers.PredefinedArgsProviders` for an example) and you might want to extend
from Minimalist's `PredefinedArgsProviders` as well.

## Combinators

Minimalist provides different combinators to produce new `ArgsGenerator`.

### Generic Combine

The most frequently used combinator is probably a way to combine multiple `ArgsGenerator`s in some way. A reason why
we added a bit of magic to Minimalist. The idiomatic way to define that we want to combine multiple generators is
to use `Tuple` (from ch.tutteli.kbox) which exists up to `Tuple9`:

<code-combine-tuple>

```kotlin
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import org.junit.jupiter.params.ParameterizedTest

class CombineTupleTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("ageAndArbName")
	fun foo(age: Int, name: String) {
		//...
	}

	companion object {
		@JvmStatic
		fun ageAndArbName() = Tuple(
			ordered.intFromTo(15, 30),
			arb.string(minLength = 3, maxLength = 50)
		)
	}
}
```

</code-combine-tuple>

Combining two `OrderedArgsGenerator`s A and B (or `SemiOrderedArgsGenerator`s) results in an `OrderedArgsGenerator`
representing their cartesian product and the size correspondingly `A.size * B.size`. I.e. such combinations can
grow quickly, but Minimalist has you covered in therms that this is just a definition (nothing generated yet) and
you still execute only a window of those values in a fast and efficient way.
On the other hand, combining two `ArbArgsGenerator` means zipping them and results in another `ArbArgsGenerator`.

Combining an `OrderedArgsGenerators` and an `ArbArgsGenerator` works as well (as shown in the example) and uses
again zip behaviour, where the result is no longer an `OrderedArgsGenerators` but a `SemiOrderedArgsGenerators` (which
still has a `size` property). You only need to make sure that your first `ArgsGenerator` in the tuple is a
`SemiOrderedArgsGenerator` (`OrderedArgsGenerator` is a subtype of `SemiOrderedArgsGenerator`).
If your first `ArgsGenerator` in the tuple is an `ArbArgsGenerator` then all generators which follow need to be an
`ArbArgsGenerator` as well (otherwise it will fail at runtime).

Maybe you are asking yourself how many runs result out of the above definition. As long as no `maxArgs` definition
restricts it, it will result in `SemiOrderedArgsGenerator.size` runs.
So for `ordered.intFromTo(15, 30)`, we will get 30 - 15 + 1 = 16 runs at max (+1 since bounds are inclusive for
`intFromTo`). Which means we combine 16 arbitrary names with the defined ages.

What if you want to combine more than 9 `ArgsGenerators`? In such a case you have to combine them via
`TupleX.combineAll()` to get an `ArgsGenerators<TupleX<...>>` which then again can be used in a tuple.
Or use `cartesian`, `zip` to create an `ArgsGenerators<Tuple2<...>>`.
Following an example (using less than 9 `ArgsGenerators` for brevity -- imports omitted, same as in
`CombineTupleTest` above)

<code-combine-manually>

```kotlin
class CombineManuallyTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("arbNumbersAndChar")
	fun bar(i: Int, l: Long, d: Double, b: BigInt, c: Char) {
		//...
	}

	companion object {
		@JvmStatic
		fun arbNumbersAndChar() = run { // use run to let the compiler infer the return type
			val numbers = Tuple(
				arb.int().zip(arb.long()), // combines them into an ArbArgsGenerators<Tuple2<Int, Long>>
				arb.double(),
				arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
			).combineAll() // combines all into an ArbArgsGenerators<Tuple3<...>>

			Tuple(
				numbers, // can again be used in a tuple to define that it shall be combined
				arb.char()
			) // the ArgsProvider will flatten all tuples, resulting in 5 arguments (see bar above)
		}
	}
}
```

</code-combine-manually>

Note two things. First, tuples are flattened in the process of transforming the definition into JUnit's `Arguments`.
Second, `Tuple2`/`Tuple3` are just type aliases for `Pair`/`Triple`. Which means, if you want that an argument is like a
`Pair`/`Triple` without being flattened, then define e.g. a `data class`.

The advantage of using tuples instead of manual `cartesian`/`zip` are:

- a) readability (less consecutive `combine` method calls -- less cluttering) and
- b) you only define that you would like to combine them without actually doing it. Which allows that you can for
  instance `append` another `ArgsGenerator` to the tuple, replace one at a specific position, `glue` tuples together and
  more (see the documentation of [kbox](https://github.com/robstoll/kbox) regarding tuples).

`cartesian` and `zip` provide an overload which takes a `transform` function so that you can turn the generates values
pairwise into something else than `Tuple2`.

### ordered cartesian

As mentioned in [generic combine](#generic-combine), combining multiple `OrderArgsGenerator`s by using a `Tuple` uses
`cartesian` behind the scenes and results in a new `OrderArgsGenerator` which represents the cartesian product of them,
i.e. all possible combinations.

<code-cartesian-1>

```kotlin
ordered.of(1, 2).cartesian(ordered.of('A', 'B'))
```

</code-cartesian-1>

For the above example, the possible combinations are 1/A, 2/A, 1/B, 2/B
You can pass a `transform` function as last argument and map the values pairwise to another type:

<code-cartesian-2>

```kotlin
ordered.of(1, 2).cartesian(ordered.of(4, 5)) { i1, i2 ->
	i1 + i2
}
```

</code-cartesian-2>

The above example results in an `OrderedArgsGenerator<Int>` with the values 5 (1+4), 6 (2+4), 6 (1+5), 7 (2+5)
(the order of the values is implementation specific). As you can see, an `OrderedArgsGenerator` can also generate the
same value multiple times.

### arb zip

As mentioned in [generic combine](#generic-combine), combining multiple `ArbArgsGenerator`s by using a `Tuple` uses
`zip` behind the scenes and results in a new `ArbArgsGenerator`:

<code-zip-1>

```kotlin
arb.intFromUntil(1, 100).zip(arb.charFromTo('A', 'Z'))
```

</code-zip-1>

For the above example, the possible combinations are 1/A, 2/A,... 99/A, 1/B, 2/B... 99/Z.
As outlined in [ordered and arbitrary arguments generators](#ordered-and-arbitrary-arguments-generators), the order of
the generated values of an `ArbArgsGenerator` is undefined and it could occur that you see a combination more than once.
You can pass a `transform` function as last argument and map the values pairwise to another type:

<code-zip-2>

```kotlin
arb.intFromUntil(1, 100).zip(arb.intFromUntil(1000, 2000)) { i1, i2 ->
	i1 + i2
}
```

</code-zip-2>

The above example results in an `ArbArgsGenerator<Int>` generating values from 1001 until 2098 where 1002 until 1099 are
more likely to appear since they result twice (1+1001 and 2+1000 = 1002 etc.).

### combineDependent

In many cases you have dependencies between `ArgsGenerators`. Something like, you need two `Int` where the first
is less than the second (defining two bounds). For this particular case we provide an optimised implementation which
adheres uniform distribution at its core: `arb.intBounds` or `arb.intRange` depending on what you need. And you can use
`arb.xyzBoundsBased` as building block to create other bounds based `ArbArgsGenerators`. For other relationships you
can use `combineDependent` as follows:

<code-combine-dependent-arb>

```kotlin
class CombineDependentTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("arbMoreThan10InSum")
	fun foo(a: Int, b: Int) {
		assertTrue(a + b > 10)
	}

	companion object {
		@JvmStatic
		fun arbMoreThan10InSum() = arb.intFromTo(1, 10).combineDependent { a ->
			arb.intFromTo(11 - a, 10)
		}
	}
}
```

</code-combine-dependent-arb>

`combineDependent` takes a factory which creates an `ArbArgsGenerator` based on a value this `ArgsGenerator` creates
and then uses `ArbArgsGenerator.generateOne` to combine the value with one value of this other generator.
It also exists on `SemiOrderedArgsGenerator` and even on `OrderedArgsGenerator` where the resulting generator
is an `SemiOrderedArgsGenerator`.
Following an example:

<code-combine-dependent-ordered>

```kotlin
enum class Color {
	Red, Blue, Green
}

ordered.fromEnum<Color>().combineDependent({ color ->
	arb.hexColor(dominant = color)
}) { _, hex -> hex }
```

</code-combine-dependent-ordered>

Note three things, first `hexColor` does not exist (yet) in Minimalist and is only there for illustration purposes.
Secondly, `combineDependent` also has two overloads (like `cross`/`zip`) where the one with a `transform` function
allows to turn the values into something else than `Tuple2`. And last but not least, this is a way to define that
we want to have x test runs at maximum where x is the number of elements in the `Color` enum but we are not
interested in `Color` as such but something arbitrary which depends on it.
If we did not want to limit the number of runs, we could also have used [arb.mergeWeighted](#arb-mergeweighted) instead.

`OrderedArgsGenerator` also provides a `combineDependentMaterialised` which expects a factory that creates another
`OrderedArgsGenerator` based on a given value from the first `OrderedArgsGenerator`. Following an example (definition of
`Color` emitted, see previous example):

<code-combine-dependent-ordered-ordered>

```kotlin
ordered.fromEnum<Color>().combineDependentMaterialised { color ->
	ordered.colorMoods(color)
}
```

</code-combine-dependent-ordered-ordered>

As the name implies, using it means that `this` `OrderedArgsGenerator` gets materialised and also that the resulting
`OrderedArgsGenerator` gets materialised. You can think of it as `toList().flatMap { ... }.let(ordered:fromList)` but
does a bit more behind the scene.
Also `combineDependentMaterialised` provides an overload which lets you pass a `transform` function.

You may be wondering why this method does not exist for `SemiOrderedArgsGenerator`, the next section will shed light
on it.

### transform

Minimalist provides different means to transform `ArgsGenerator`s. But not all extension methods are defined
for all types of `ArgsGenerator`. For instance, since an `OrderedArgsGenerator` needs to know how many values it can
generate before repeating them, methods like `filter` require that a full cycle gets materialised first.
Such methods are signified with a `Materialised` suffix. A `SemiOrderedArgsGenerator` on the other hand does not
even provide a `Materialised` version as materialising would mean you fix the arbitrary part of it and most likely that
would not be the intention. To be precise, `SemiOrderedArgsGenerator` actually provides the `Materialised` function but
with a deprecation level `Error`. We have it there to remind you that you most likely don't want to use it (better than
let you search for it just to realise after a while that it does not exist for `SemiOrderedArgsGenerator`).

Most transformation functionality is based on `transform`/`transformMaterialised` where a function operates on the
generated `Sequence` and must return another `Sequence` which still adheres to the corresponding
`ArgsGenerator` contract (e.g. the `Sequence` must still be infinite in case of `ArbArgsGenerator`).
Following an example:

<code-transform>

```kotlin
arb.intFromTo(1, 10).transform { sequence ->
	// return a generated value twice
	sequence.flatMap { listOf(it, it) }
}

ordered.intFromTo(1, 10).transformMaterialised { sequence ->
	sequence.zipWithNext()
}
```

</code-transform>

Which means you can use `transform` as building block for custom transformations based on `Sequence`.
Some functions are so common, that Minimalist provides them as extension of `ArgsGenerator` as well,
the following section outlines some.

### map

You can map values of `ArgsGenerator` to another type by providing a mapping function:

<code-map>

```kotlin
val now = LocalDate.now()
arb.localDateFromTo(now.withDayOfYear(1), now).map { localDate -> localDate.atTime(12, 0) }

ordered.intFromTo('A'.code, 'Z'.code).map { it.toChar() }
```

</code-map>

### filter

Filtering an `ArbArgsGenerator` can be done via `filter`/`filterNot`.
Filtering an `OrderedArgsGenerator` via `filterMaterialised`/`filterNotMaterialised` (see [transform](#transform) for
an explanation about `Materialised` and why it does not exist for `SemiOrderedArgsGenerator`).

<code-filter>

```kotlin
arb.intFromUntil(1, 1000).filterNot { it == 523 }
ordered.intFromUntil(1, 1000).filterNotMaterialised { it == 523 }
```

</code-filter>

Be aware of that filtering an `ArbArgsGenerator` might be a simple way to achieve something but could be slow due to
the nature of the generator generating arbitrary values. Following an example:

<code-dont-filter>

```kotlin
// prefer a progression as follows ...
arb.fromProgression(1..1000 step 2)
// ... instead of filter (which is slower as every ~2nd time the number is even)
arb.intFromTo(1, 1000).filter { it % 2 == 1 }
```

</code-dont-filter>

### chunked

If you want to take a static number of values from an `ArbArgsGenerator` and transform them into another type, then
`chunked` comes in handy where as for `Sequence` two overloads are provided. The first transforms the values into a
`List` and the second takes a `transform` function in addition which allows to map the `List` of values into something
else.

<code-chunked>

```kotlin
arb.intFromTo(1, 100).chunked(3)
arb.intFromTo(1, 100).chunked(3) { it.sorted() }
arb.charFromTo('a', 't').zip(arb.intFromTo(1, 100)).chunked(3) { it.toMap() }
```

</code-chunked>

So far we did not come across a use case where `chunked` would be valuable for `OrderedArgsGenerator` and hence do not
provide a shortcut. [Let us know your use cases](https://github.com/tegonal/minimalist/discussions/new?category=ideas),
we happily add the shortcut if it is of value (we try to not clutter the API with methods we have not
used ourselves so far).

### ordered concatenation

Concatenating `OrderedArgsGenerators` can be done via `+` or via `concatAll` where the resulting `size` will
correspondingly be the size of `this.size` + `other.size`.

<code-concat>

```kotlin
ordered.of(1, 2) + ordered.intFromTo(100, 120)

// works with Iterable/Sequence as well
(0..3).map {
	val offset = 10 * it
	ordered.of(0 + offset, 3 + offset)
}.concatAll() // generates 0,3, 10,13, 20,23, 30,33, ...
```

</code-concat>

Concatenating an `ArbArgsGenerator` is not possible as its size is infinite, i.e. we would never see the values
of the second `ArbArgsGenerator`. But you can merge them, see next section.

### arb mergeWeighted

Sometimes you want to use two or more `ArbArgsGenerator`s as source of a test. In such a case you can use
`arb.mergeWeighted` to merge them where you can define the weighting of the individual generators:

<code-mergeWeighted>

```kotlin
arb.mergeWeighted(
	80 to arb.intFromUntil(100, 200),
	10 to arb.of(201),
	10 to arb.of(null)
)
```

</code-mergeWeighted>

The weighting does not need to add up to 100. If they do, then the numbers correspond to percentage. So in the above
case, out of 100 generates values, around 80 will be between 100 and 200 (exclusive), around 10 will be 201 and
around 10 will be `null`. The defined weighting is uniformly distributed, which means that for a small number of values,
it might be skewed; for example, 85 values could fall between 100 and 200, etc.

### ordered toArbArgsGenerator

You can turn a `(Semi)OrderedArgsGenerator` into an `ArbArgsGenerator` by using the `toArbArgsGenerator()` extension
method.

# Use Minimalist in other contexts than JUnit

Minimalist is not bound to `@ParameterizedTest`s, not even to JUnit. It is a library which can be used whenever
you have a data-driven situation (and you do not have time to consider all of it).
For instance, we have used it in load tests as source for (arbitrary) input.
You can also use it in combination
with [JUnit's Dynamic Tests](https://docs.junit.org/current/user-guide/#writing-tests-dynamic-tests)
Following an example:

<code-dynamic-test-1>

```kotlin
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.generateAndTakeBasedOnDecider
import com.tegonal.minimalist.generators.string
import com.tegonal.minimalist.generators.zip
import com.tegonal.minimalist.providers.PredefinedNumberProviders.Companion.arbIntPositive
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicTest : PredefinedArgsProviders {

	@TestFactory
	fun arbExample() =
		arbIntPositive().zip(arb.string(maxLength = 20))
			.generateAndTakeBasedOnDecider()
			.map { (positiveNumber, label) ->
				dynamicTest("$positiveNumber $label") {
					assertTrue(positiveNumber * -1 < 0)
				}
			}
}
```

</code-dynamic-test-1>

Note however, that all the magic of `ArgsSource` is not available (yet). Which means:

- you need to combine ArgsGenerators manually (see [arb.zip](#arb-zip) and [ordered.cartesian](#ordered-cartesian)) or
  use [combineAll](#generic-combine) if you deal with generators in `Tuple`s -- the good side, you do not lose the types as you would
  with JUnit's `Arguments`.
- definitions like `@ArgSourceOptions` are ignored, but as long as you use `generateAndTakeBasedOnDecider` the defined
  seed and co. (see [fixing the seed](#fixing-the-seed) are taken into account
- and you can pass `AnnotationData` to `generateAndTakeBasedOnDecider` to get back the same options as with
  `ArgSourceOptions`

<code-dynamic-test-2>

```kotlin
@TestFactory
fun orderedExample() =
	ordered.intFromUntil(1, 100)
		.generateAndTakeBasedOnDecider(
			AnnotationData.outsideParameterizedTest(
				argsRangeOptions = ArgsRangeOptions(profile = "Integration", maxArgs = 20)
			)
		)
		.map { positiveNumber ->
			dynamicTest("$positiveNumber") {
				assertTrue(positiveNumber * -1 < 0)
			}
		}
```

</code-dynamic-test-2>

# Configuration

Minimalist provides a configuration via `MinimalistConfig` which per default can be customised via
`minimalist.properties`.
This file needs to be available on your classpath. Typically, you put it in src/test/resources.
Next to `minimalist.properties` which is intended to make project based adjustments
(e.g. change `Miniamlist.defaultProfile` to `E2E`, see [Profiles and Envs](#profiles-and-envs)), you can create a
`minimalist.local.properties` which you should add on your git ignore list.
This file overwrites settings in `minimalist.properties` and is intended for personal adjustments and debugging.

More documentation about the configuration will follow, in the meantime, take a look at the KDoc of MinimalistConfig.

## Profiles and Envs

Minimalist steers how many runs will result at maximum (if not limited by other factors such as
`OrderedArgsGenerator.size`) by the profile definition in use (`MinimalistConfig.defaultProfile` is `Integration`)
and the environment the test runs in (defined via `MinimalistConfig.activeEnv`).
The active environment is determined from environment variables (GitHub and GitLab env vars),
defaulting to `Local` if it cannot be deduced.

Minimalist comes with two predefined enums, `TestType`s which are used as profile names and predefined `Env`s.
See `MinimalistConfig.testProfiles` for what `maxArgs` are defined per default.

## Fixing the seed

Minimalist outputs the used seed once the config is fully loaded. Use it in `minimalist.local.properties` to fix the
seed to e.g. a previous run. You might want to restrict `maxArgs` in such a case as well and use `offsetToDecidedOffset`
to skip some runs, i.e. jump to a particular run.

## Change the ArgsRangeDecider

An `ArgsRangeDecider` is responsible to decide from which offset and how many arguments shall be taken from an 
`ArgsGenerator`. The offset is only taken into account for `(Semi)OrderedArgsGenerator`s.

The default implementation is solely based on the configured [profiles](#profiles-and-envs) - more implementations will
follow in an upcoming version of Minimalist. 

If you want to provide an own implementation, then you need to make it available to be loaded via `ServiceLoader`. 
Create the file `src/resource/META-INF/services/com.tegonal.minimalist.providers.ArgsRangeDecider` and put the fully
qualified name in it. Moreover, you need to set `activeArgRangeDecider` in the MinimalistConfig 
(typically via `minimalist.properties`) to the fully qualified name as well.

## Use a SuffixArgsGeneratorDecider

A `SuffixArgsGeneratorDecider` is responsible to decide if an `ArgsGenerator` shall be combined as suffix (i.e. as last)
with the `ArgsGenerator`(s) defined by the method specified in `ArgsSource`.

This can be handy if you for instance have a kind of test which always require something. This way you don't have to add
it to every single `ArgsSource` but can define it in a single place. 
Imagine you have implemented a SuffixArgsGeneratorDecider which always returns `arb.fromEnum<Color>()`. In the test you 
could then write:

<code-suffix-args-generator>

```kotlin
@ParameterizedTest
@ArgsSource("arbIntPositive") // comes from PredefinedArgsProviders
fun foo(i: Int, c: Color) {
	// the argument i comes from arbIntPositive
	// the argument c comes from the SuffixArgsGeneratorDecider
}
```

</code-suffix-args-generator>

If you want to provide an own implementation, then you need to make it available to be loaded via `ServiceLoader`.
Create the file `src/resource/META-INF/services/com.tegonal.minimalist.providers.SuffixArgsGeneratorDeciderr` and 
put the fully qualified name in it. Moreover, you need to set `activeArgRangeDecider` in the MinimalistConfig
(typically via `minimalist.properties`) to the fully qualified name as well.

# Helpers

Minimalist provides some helpers in addition to `ArgGenerators` and the `ArgsSource` machinery.

## Random helpers

Minimalist provides some helper methods and functionality in case you want to add randomness but still benefit
from the possibility to re-run it in a deterministic way (by [fixing the seed](#fixing-the-seed)).

<code-random-helper>

```kotlin
import com.tegonal.minimalist.utils.createMinimalistRandom
import com.tegonal.minimalist.utils.pickOneRandomly
import com.tegonal.minimalist.utils.takeRandomly

// Imagine the list is more complicated than that, because if not, then better define it via arb or ordered
// since then it is most likely more efficient (would not allocate the memory for 1001 Ints)
val someList = (0..1000).toList()
val i1: Int = someList.pickOneRandomly()
val l1: List<Int> = someList.takeRandomly(10)

// Imagine a more complicated ordered which combines multiple generators and in the end maps to a model.
// In case you want to re-use it in another context than ParameterizedTests those helpers might come in handy
val complicatedSetup = ordered.of(1, 2, 3)
val i2: Int = complicatedSetup.pickOneRandomly()
val l2: List<Int> = complicatedSetup.takeRandomly(100)
// and of course, if you want to do more than that, then you can always turn your OrderedArgsGenerator
// into an ArbArgsGenerator and then work on Sequence:
val l3: Set<Int> = complicatedSetup.toArbArgsGenerator().generate()
	.map { it + i1 + i2 }
	//...
	.take(50)
	.toSet()

// creates a Random based on the configured seed, i.e. if you fix the seed, then you get a deterministic result
createMinimalistRandom().let { random ->
	val i = random.nextInt()
	//...
}
```

</code-random-helper>

## Sequence helpers

In case you want to repeat something forever, then `repeatForever` might come in handy for you as well:

<code-repeat-forever>

```kotlin
import com.tegonal.minimalist.utils.repeatForever

// creates a Sequence which yields the given constant forever
repeatForever(constant = 1)

// creates a Sequence which yields 1, 2, 3 forever
repeatForever(arrayOf(1, 2, 3), offset = 0)

// creates a Sequence which yields 2, 3, 1 forever
repeatForever(listOf(1, 2, 3), offset = 1)

// repeats Unit forever and can be used as a building block
repeatForever().flatMap { _ ->
	// will repeat 11, 22, 33 forever
	sequenceOf(1, 2, 3).mapIndexed { index, it -> it + (index + 1) * 10 }
}
```

</code-repeat-forever>

## BigInt helpers

We think `BigInteger` is too cumbersome to write and hence use `BigInt` instead (also aligns better with Kotlin's choice
to use `Int` instead of `Integer`. For instance, we use `ordered.bigIntFromUntil` instead of
`ordered.bigIntegerFromUntil`. And since it would look odd if this function takes `BigInteger`, we introduced
a corresponding `typealias` and an extension method `toBigInt` for `Int` and `Long`.

Last but not least, we provide the extension method `Random.nextBigInt`.

# Code Documentation

Code documentation can be found on github-pages: <https://tegonal.github.io/minimalist/latest#/kdoc>.

# License

Minimalist is licensed
under [European Union Public Licence 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

- Classes which are copied from [junit-jupiter-params/junit-platform-commons](https://github.com/junit-team/junit5)
  are licensed under [EPL 2.0](https://www.eclipse.org/legal/epl-v20.html) (see
  src/main/lib/java/com/tegonal/minimalist/export/org/junit).
- Copied some classes and interfaces from [Atrium](https://atriumlib.org) licensed
  under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Minimalist is using

- [junit-jupiter-params/junit-platform-commons](https://github.com/junit-team/junit5) licensed
  under [EPL 2.0](https://www.eclipse.org/legal/epl-v20.html)
- [KBox](https://github.com/robstoll/kbox) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
