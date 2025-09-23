<!-- for main -->
<!--
[![Download](https://img.shields.io/badge/Download-v2.0.0--RC--1-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/2.0.0-RC-1)
[![EUPL 1.2](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Quality Assurance](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml/badge.svg?event=push&branch=main)](https://github.com/tegonal/minimalist/actions/workflows/quality-assurance.yml?query=branch%3Amain)
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")
-->
<!-- for main end -->
<!-- for release -->

[![Download](https://img.shields.io/badge/Download-v2.0.0--RC--1-%23007ec6)](https://central.sonatype.com/artifact/com.tegonal.minimalist/minimalist/2.0.0-RC-1)
[![EUPL](https://img.shields.io/badge/%E2%9A%96-EUPL%201.2-%230b45a6)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12 "License")
[![Newcomers Welcome](https://img.shields.io/badge/%F0%9F%91%8B-Newcomers%20Welcome-blueviolet)](https://github.com/tegonal/minimalist/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22 "Ask in discussions for help")

<!-- for release end -->

# Minimalist

![Minimalist](https://raw.githubusercontent.com/tegonal/minimalist/main/.idea/icon.png) M like Minimalist,
a library which helps you in setting up parameterized tests and prioritise them in case you don't have enough time
to execute all of them.

Although it might resemble a property based testing library, its focus is on tests that take longer
(integration, e2e and system integration tests) and is more data-driven oriented.

Take a look at [Examples](#examples) to see how you can use it.

---
❗ You are taking a *sneak peek* at the next version. It could be that some features you find on this page are not
released yet.  
Please have a look at the README of the corresponding release/git tag. Latest
version: [README of v2.0.0-RC-1](https://github.com/tegonal/minimalist/tree/v2.0.0-RC-1/README.md).

---

**Table of Contents**

- [Installation](#installation)
- [Examples](#examples)
	- [Your first parameterized Test](#your-first-parameterized-test)
	- [Ordered and arbitrary arguments generators](#ordered-and-arbitrary-arguments-generators)
	- [Combinators](#combinators)
		- [combine](#combine)
		- [combineDependent](#combinedependent)
		- [transform](#transform)
		- [map](#map)
		- [filter/filterNot](#filter)
		- [chunked](#chunked)
		- [ordered.concatenation](#ordered-concatenation)
		- [arb.mergeWeighted](#arb-mergeweighted)
		- [ordered.toArbArgsGenerator](#ordered-toarbargsgenerator)
- [Configuration](#configuration)
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
	testImplementation("com.tegonal.minimalist:minimalist:2.0.0-RC-1")
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

`ordered` can be used to define an ordered (not to be confused with sorted) list of finite values where the
corresponding
`OrderedArgsGenerator` generates a sequence which repeats them indefinitely.
For instance, if you use `ordered.of('a', 'b')` as provider, then it results in two runs where in the first run you
will get `'a'` and in the second `'b'` or you will get `'b'` in the first run and `'a'` in the second.
That is because the resulting sequence repeats indefinitely `'a', 'b', 'a', 'b', ... `
and it depends on a randomly chosen seed what offset is taken.

Following a few examples what predefined `ordered` exist (take a look at the [Code Documentation](#code-documentation)
to see all):

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

ordered.intFromUntil(1, 5)
ordered.longFromTo(1, 5)
//...
```
</code-ordered-1>

The "counterpart" of `ordered` is `arb` that allows to create `ArbArgsGenerator`s which per definition generate
an infinite sequence of values where it is basically not known if they follow some order or not.
The default implementations almost all are based on `Random`.
Following a few examples as well (import and enum definition omitted, as it is the same as above):

<code-arb-1>

```kotlin
arb.of(1, 2, 3)
arb.fromEnum<Color>()
arb.fromList(listOf(1, 2, 3))
arb.fromArray(arrayOf(1, 2, 3))
//...

arb.int()
arb.intPositive()
arb.longNegative()
arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
//...

LocalDate.now().let { now ->
	arb.localDateFromUntil(now, now.plusMonths(4))
}
LocalDateTime.now().let { now ->
	arb.localDateTimeFromUntil(now, now.plusHours(48), ChronoUnit.MINUTES)
}
//...

arb.intRange(minInclusive = 1, maxInclusive = 1000, minSize = 3, maxSize = 10)
arb.longBounds(minInclusive = -10, maxInclusive = 10, minSize = 0)
//...

arb.string(minLength = 0, maxLength = 20, allowedRanges = UnicodeRanges.ASCII_PRINTABLE.ranges)
```
</code-arb-1>

For a few `arb` definitions we provide predefined `ArgsSourceProvider`s. Following an example:

<code-predefined-1>

```kotlin
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest


class PredefinedArgsProvidersTest : PredefinedArgsProviders {

	@ParameterizedTest
	// uses the predefined ArbArgsGenerator arbIntPositive defined in PredefinedArgsSourceProviders
	@ArgsSource("arbIntPositive")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}
}
```
</code-predefined-1>

Typically, you will reuse your custom providers in several tests. We recommend you create your own interfaces which
contain predefined `ArgsSource` providers and one ArgsSourceProvider which extends all of them
(see `com.tegonal.minimalist.providers.PredefinedArgsSourceProviders` for an example) and you might want to extend
from Minimalist's PredefinedArgsSourceProviders as well.

Back to the example, in contrast to the [first parameterized test example](#your-first-parameterized-test) where we used
raw values (which are
turned into a `List` and then passed to `ordered.fromList`), we now want to be sure the test
works for all positive integers and not only `1..20`. Since there are many positive integers, we use `arb` and no
longer `ordered`.

Note, that even if we defined a provider with `arb.intFromTo(1, 20)` the runtime behaviour differs from `ordered`.
Where `OrderedArgsGenerator`s generate a window of all possible values (i.e. still ordered),
an `ArbArgsGenerator` generates arbitrary/random values (possibly the same value multiple times).
To illustrate it better, following an example where `maxArgs=5` (more on `maxArgs` in
the [Configuration](#configuration)
section):

```kotlin
ordered.of(1, 2, 3) // results in 3 runs: 1, 2, 3 or 2, 3, 1 or 3, 1, 2
arb.of(1, 2, 3)     // results in 5 runs, order unknown
```

As a rule of thumb, use `ordered` only if you have explicit restrictions and you would test all of them if the tests
were faster. Whenever you are in doubt, use `arb` and switch to `ordered` once you are convinced that it is better
suited.

## Combinators

Minimalist provides different combinators to produce new `ArgsGenerator`.
The most common combinator is to combine multiple `ArgsGenerator`s, a reason why it got some extra care.

### Combine

Although Minimalist provides a `combine` function (more on that later on), the cleanest way to define that two
`ArgsGenerator`s shall be combined, is to use `Tuple` (from ch.tutteli.kbox) which exists up to `Tuple9`:

<code-combine-tuple>

```kotlin
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import org.junit.jupiter.params.ParameterizedTest

class CombineTupleTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("ageAndName")
	fun foo(age: Int, name: String) {
		//...
	}

	companion object {
		@JvmStatic
		fun ageAndName() = Tuple(
			ordered.intFromTo(15, 30),
			arb.string(minLength = 3, maxLength = 50)
		)
	}
}
```
</code-combine-tuple>

As you can see in the example, you can also combine `ordered` and `arb` (resulting in a `SemiOrderedArgsGenerator`).
You only need to make sure that your first `ArgsGenerator` in the tuple is a `SemiOrderedArgsGenerator`
(`OrderedArgsGenerator` is a subtype of `SemiOrderedArgsGenerator`). If your first `ArgsGenerator` in the tuple is
an `ArbArgsGenerator` then all generators which follow need to be an `ArbArgsGenerator` as well (otherwise it will
fail at runtime).

Maybe you are asking yourself how many runs result out of the above definition. `SemiOrderedArgsGenerator`s have a
property `size` and as long as no `maxArgs` definition restricts it, it will result in that many runs.
So for `ordered.intFromTo(15, 30)`, we will get 30 - 15 + 1 = 16 runs at max (+1 since bounds are inclusive for
`intFromTo`). Which means we combine 16 arbitrary names with the defined ages.

If you combine two `OrderedArgsGenerator`s A and B (or `SemiOrderedArgsGenerator`s) the resulting `OrderedArgsGenerator`
will be their cartesian product and the size correspondingly `A.size * B.size`. I.e. such combinations can grow quickly,
but Minimalist has you covered in therms that this is just a definition (nothing generated yet) and you still execute
only a window of those values in a fast and efficient way.

If you combine two `ArbArgsGenerator`s then you will get back an `ArbArgsGenerator` which semantically
[`zip`s](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/zip.html) the generates values.

What if you want to combine more than 9 `ArgsGenerators`? In such a case you have to combine them via
`TupleX.combineAll()` to get an `ArgsGenerators<TupleX<...>>` which then again can be used in a tuple.
Or use `ArgsGenerator.combine(otherArgsGenerator)` to create an `ArgsGenerators<Tuple2<...>>`.
Following an example (using less than 9 `ArgsGenerators` for brevity -- imports omitted, same as in
`TupleCombine1Test` above)

<code-combine-manually>

```kotlin
class CombineManuallyTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("numbersAndChar")
	fun bar(i: Int, l: Long, d: Double, b: BigInt, c: Char) {
		//...
	}

	companion object {
		@JvmStatic
		fun numbersAndChar() = run { // use run to let the compiler infer the return type
			val numbers = Tuple(
				arb.int().combine(arb.long()), // combines them into an ArbArgsGenerators<Tuple2<Int, Long>>
				arb.double(),
				arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
			).combineAll() // combines all into an ArbArgsGenerators<Tuple3<...>>

			Tuple(
				numbers, // can again be used in a tuple to define that it shall be combined
				arb.char()
			) // the ArgsArgumentProvider will flatten all tuples, resulting in 5 arguments (see bar above)
		}
	}
}
```
</code-combine-manually>

Note two things. First, tuples are flattened in the process of transforming the definition into `Arguments`. Second,
`Tuple2`/`Tuple3` are just type aliases for `Pair`/`Triple`. Which means, if you want that an argument is like a
`Pair`/`Triple` without being flattened, then define e.g. a `data class`.

The advantage of using tuples instead of manual `combine` are:

- a) readability (less consecutive `combine` method calls -- less cluttering) and
- b) you only define that you would like to combine them without actually doing it. Which allows that you can for
  instance `append` another `ArgsGenerator` to the tuple, replace one at a specific position, `glue` tuples together and
  more (see the documentation of [kbox](https://github.com/robstoll/kbox) regarding tuples).

`combine` also provides an overload which takes a `transform` function so that you can turn it into something else than
`Tuple2`.

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
	@ArgsSource("moreThan10InSum")
	fun foo(a: Int, b: Int) {
		assertTrue(a + b > 10)
	}

	companion object {
		@JvmStatic
		fun moreThan10InSum() = arb.intFromTo(1, 10).combineDependent { a ->
			arb.intFromTo(11 - a, 10)
		}
	}
}
```
</code-combine-dependent-arb>

`combineDependent` also exists on `SemiOrderedArgsGenerator` and even on `OrderedArgsGenerator` where the resulting
generator is then an `SemiOrderedArgsGenerator`. Following an example:

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

Note three things, first `hexColor` doesn't exist (yet) in Minimalist and is only there for illustration purposes.
Secondly, `combineDependent` also has two overloads (like `combine`) where the one with a `transform` function allows
to turn the values into something else. And last but not least, this is a way to define that we want to have x test runs
at maximum where x is the number of elements in the `Color` enum but we are not interested in `Color` as such but
something arbitrary which depends on it. If we did not want to limit the number of runs, we could also have used
[arb.mergeWeighted](#arb-mergeweighted) instead.

### transform

Minimalist provides different means to transform them. Not all types of `ArgsGenerator` provide the same extension
methods. For instance, since an `OrderedArgsGenerator` needs to know how many values it can generate before repeating
them, methods like `filter` require that a full cycle gets materialised first.
Such methods are signified with a `Materialised` suffix.

Most transformation functionality is based on `transform`, `transformMaterialised` respectively where a function
operates on the generated `Sequence` and must return another `Sequence` which still adheres to the corresponding
`ArgsGenerator` contract. Following an example:

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
an explanation about `Materialised`). Note that `filterMaterialised`/`filterNotMaterialised` is not available for
`SemiOrderedArgsGenerator`s as this would fix the arbitrary part of it.

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
// ... instead of filter (which is slower)
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
arb.charFromTo('a', 't').combine(arb.intFromTo(1, 100)).chunked(3) { it.toMap() }
```
</code-chunked>

So far we did not come across a use case where `chunked` would be valuable for `OrderedArgsGenerator` and hence don't
provide a shortcut. Let us know your use cases, we happily add the shortcut if it is of value (we try to not clutter
the API with methods we have not used ourselves so far).

### ordered concatenation

Concatenating `OrderedArgsGenerators` can be done via `+` or via `concatAll` where the resulting `size` will
correspondingly be
the size of `this.size` + `other.size`.

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

Concatenating an `ArbArgsGenerator` isn't possible as its size is infinite, i.e. we would never see the values
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

# Configuration

Minimalist provides a configuration via `MinimalistConfig` which per default can be customised via
`minimalist.properties`.
This file needs to be available on your classpath. Typically, you put it in src/test/resources.
Next to `minimalist.properties` which is intended to make project based adjustments, you can create a
`minimalist.local.properties`
which you should add on your git ignore list. This file overwrites settings in `minimalist.properties` and is intended
for personal adjustments and debugging.

More documentation about the configuration will follow, in the meantime, take a look at the KDoc of MinimalistConfig.

# Code Documentation

Code documentation can be found on github-pages: <https://tegonal.github.io/minimalist/2.0.0-RC-1/kdoc>.

# License

Minimalist is licensed
under [European Union Public Licence 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).

Minimalist is using

- [junit-jupiter-params/junit-platform-commons](https://github.com/junit-team/junit5) licensed
  under [EPL 2.0](https://www.eclipse.org/legal/epl-v20.html)
- [KBox](https://github.com/robstoll/kbox) licensed under [Apache 2.0](https://opensource.org/licenses/Apache2.0)
- Copied some classes and interfaces from [Atrium](https://atriumlib.org) licensed
  under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).
