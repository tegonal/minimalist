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

Take a look at [Examples] to see how you can use it.

---
❗ You are taking a *sneak peek* at the next version. It could be that some features you find on this page are not
released yet.  
Please have a look at the README of the corresponding release/git tag. Latest
version: [README of v2.0.0-RC-1](https://github.com/tegonal/minimalist/tree/v2.0.0-RC-1/README.md).

---

**Table of Content**

- [Installation](#installation)
- [Examples](#examples)
	- [Your first parameterized Test](#your-first-parameterized-test)
	- [Ordered and arbitrary arguments generators](#ordered-and-arbitrary-arguments-generators)
    - [Combine ArgGenerators](#combine-arggenerators)
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

Likewise jupiter params provides `@MethodSource`, Minimalist provides `@ArgsSource`.

<code-first-1>

```kotlin
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest

class FirstTest : PredefinedArgsSourceProviders {

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

At a first glance, `ArgsSource` in the above example behaves the same way as [
`MethodSource`](https://docs.junit.org/current/user-guide/#writing-tests-parameterized-tests-sources-MethodSource)
but the runtime behaviour is different. If you run the above code, then per default only a window of 5 values is taken
from the range `1..20` based on a randomly chosen seed resulting in 5 runs.
The base assumption of Minimalist is that your tests are expensive to execute and that you don't have the time to run
all values of interested in one go. But, eventually, over multiple runs, it would still be nice to cover all values.
The [configuration](#configuration) section explains how we can adjust the default profiles and more.
For now, we continue without going too much into details. Although Minimalist allows that one can provide "raw" values
as in `1..20` (could also have been `listOf(1, 2, 3, ...)`) it provides `ArgsGenerator`s which are way more efficient.
Raw values are turned into a `List` and then passed to `ordered.fromList`. The next section outlines what `ordered` is.

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

## Ordered and arbitrary arguments generators

Minimalist provides two entry points to create an `ArgsGenenerator`: `ordered` and `arb`.

`ordered` can be used to define an ordered (not to be confused with sorted) "set" of finite values where the generator
generates a sequence which repeats them indefinitely. For instance, if you use `ordered.of('a', 'b')` as provider, then
it results in two runs where in the first run you will get `'a'` and in the second `'b'` or you will get `'b'` in the
first run and `'a'` in the second. That is because the resulting sequence repeats indefinitely
`'a', 'b', 'a', 'b', ... `
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
ordered.intFromTo(1, 5)
ordered.longFromTo(1, 5)

ordered.fromList(listOf(6, 8, 1))
ordered.fromArray(arrayOf(4, 2, 7))
ordered.fromRange(1..10)
ordered.fromProgression(1..10 step 2)
```
</code-ordered-1>

The "counterpart" of `ordered` is `arb` which per definition generates an infinite sequence of values where it is
basically not known if they follow some order or not. The default implementations almost all are based on `Random`.
Following a few examples as well (import omitted, as it is the same as above):

<code-arb-1>

```kotlin
arb.of(1, 2, 3)
arb.fromEnum<Color>()
arb.int()
arb.intPositive()
arb.longNegative()

arb.fromList(listOf(1, 2, 3))
arb.fromArray(arrayOf(1, 2, 3))
//...
```
</code-arb-1>

For a few `arb` definitions we provide predefined `ArgsSourceProvider`s. Following an example:

<code-predefined-1>

```kotlin
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest


class PredefinedArgsSourceProvidersTest : PredefinedArgsSourceProviders {

	@ParameterizedTest
	// uses the predefined ArbArgsGenerator arbIntPositive defined in PredefinedArgsSourceProviders
	@ArgsSource("arbIntPositive")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}
}
```
</code-predefined-1>

In contrast to the [first parameterized test example](#your-first-parameterized-test), we now want to be sure the test
works for all positive integers. Since there are many, we use `arb` and no longer `ordered` (raw values are turned into
`ordered`).

Note, that even if we defined a provider with `arb.fromRange(1..20)` the values
it generates differs from `ordered`. Where `ordered` generates a window of all possible values (i.e. still ordered),
`arb` generates arbitrary/random values (possibly the same value multiple times). To illustrate it better, following
an example:

```kotlin
ordered.of(1, 2, 3) // results in 3 runs: 1, 2, 3 or 2, 3, 1 or 3, 1, 2
arb.of(1, 2, 3)     // results in 5 runs, order unknown
```

As a rule of thumb, use `ordered` only if you have explicit restrictions and would test all of them if the tests

where faster. Whenever you are in doubt, use `arb` and switch to `ordered` once you are convinced that it is better suited.

Typically, you will reuse your custom providers in several Tests. We recommend you create your own interface(s) which 
contain predefined `ArgsSource` providers (see `com.tegonal.minimalist.providers.PredefinedArgsSourceProviders` for 
an example).

## Combine ArgGenerators

A more detailed documentation will follow. The cleanest way is to use `Tuple` (from ch.tutteli.kbox) which exists up to 
`Tuple9`: 

<code-tuple-1>

```kotlin
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import org.junit.jupiter.params.ParameterizedTest

class TupleTest : PredefinedArgsSourceProviders {
	
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
</code-tuple-1>

Once you reach that limit you will have to combine tuples or use `combine` manually and provider a transformation 
function. The advantage of using tuples instead of manual `combine` are a) readability and b) you only define that
you would like to combine them without actually doing it. Which allows that you can for instance `append` another value
`glue` tuples together and more (see the documentation of [kbox](https://github.com/robstoll/kbox)). 

As you can see you can also combine `ordered` and `arb` (resulting in a SemiOrderedArgsGenerator). 
You only need to make sure your first `ArgsGenerator` is not an `ArbArgsGenerator`.

# Configuration

Minimalist provides a configuration via `MinimalistConfig` which per default can be customised via
`minimalist.properties`.
This file needs to be available on your classpath. Typically, you put it in src/test/resources.
Next to `minimalist.properties` which is intended to make project based adjustments, you can create a
`minimalist.local.properties`
which you should add on your git ignore list. This file overwrites settings in `minimalist.properties` and is intended
for personal adjustments.

More documentation about the configuration will follow, in the meantime, take a look at MinimalistConfig.

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
