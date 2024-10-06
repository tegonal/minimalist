package com.tegonal.minimalist

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import org.junit.platform.commons.util.*
import java.lang.reflect.Method
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.collections.set
import kotlin.random.Random
import kotlin.system.measureNanoTime


enum class A {
	A, B, C, D, E, F, G, H
}


class DummyTest {

	@Test
	fun test() {

		OrderedArgsGenerator.fromEnum<A>()
		Duration.ofDays(1).plus(Duration.ofHours(2))
		RandomArgsGenerator.localDateFromUntil(LocalDate.now(), LocalDate.now().plusYears(1), ChronoUnit.DAYS)
		println()
	}

	@Test
	fun `mainly here to see if setup works`() {
		listOf(1, 2, 3, 4).shuffled()
		val max = 60_000
		val maxNumber = 1_000_000
		bench(
			"int range" to {
				(0..maxNumber).shuffled().take(max).joinToString(", ")
			},
			"generateSequence " to {
				generateSequence(1) { it + 1 }.take(maxNumber).shuffled().take(max).joinToString(", ")
			},
			"sequence intrange" to {
				(0..maxNumber).asSequence().shuffled().take(max).joinToString(", ")
			},
			"bitset" to {
				val bs = BitSet(maxNumber)
				val numbers = Array(max) { 0 }
				var cardinality = 0
				while (cardinality < max) {
					val v: Int = Random.nextInt(maxNumber + 1)
					if (!bs[v]) {
						bs.set(v)
						numbers[cardinality] = v
						cardinality++
					}
				}
				numbers.joinToString(", ")
			},
			"hashset" to {
				val s = HashSet<Int>()
				while (s.size < max) s.add(Random.nextInt(maxNumber + 1))
				s.joinToString(", ")
			},
			"random" to {
				Random.nextInt(maxNumber + 1)
			}
		)
	}

	fun bench(vararg pairs: Pair<String, () -> Unit>) {
		val origin = pairs.clone()
		var map = hashMapOf(*pairs.map { it.first to 0L }.toTypedArray())
		repeat(100) {
			pairs.apply { shuffle() }.forEach {
				map[it.first] = map[it.first]!! + measureNanoTime { it.second() }
			}
		}
		origin.forEach {
			println("${it.first.padEnd(20)}: ${map[it.first]!!.toString().padStart(10)}")
		}
	}

	@ParameterizedTest
	@ArgsSource("race1")
	fun foo(
		@ArgsFilter("bla") startDate: LocalDate,
		endDate: LocalDate,
		rank: Int
	) {
		val now = LocalDate.now()
		expect(startDate)
			.toBeAfterOrTheSamePointInTimeAs(now)
			.toBeLessThan(now.with(TemporalAdjusters.lastDayOfYear()))
	}

	companion object {
		@JvmStatic
		fun race1(): Pair<RandomArgsGenerator<Args2<LocalDate, LocalDate>>, RandomArgsGenerator<Args1<Int>>> {
			val now = LocalDate.now()
			val startDates = RandomArgsGenerator.localDateFromUntil(now, now.with(TemporalAdjusters.lastDayOfYear()))

			val startAndEndDates = startDates.appendDependent {
				RandomArgsGenerator.localDateFromUntil(it.a1, it.a1.plusYears(1))
			}

			return startAndEndDates to RandomArgsGenerator.intFromUntil(2, 10)
//			return OrderedArgsGenerator.of(
//				Args.of(LocalDate.now(), LocalDate.now().plusYears(1)),
//				Args.of(LocalDate.now(), LocalDate.now().plusYears(2))
//			) to startDates
		}

		@JvmStatic
		fun bla(): (Args2<LocalDate, LocalDate>) -> Boolean = { (a1) ->
			// start date should not be first of year
			a1.isEqual(a1.with(TemporalAdjusters.firstDayOfYear())).not()
		}
	}

}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ArgumentsSource(ArgsArgumentProvider::class)
annotation class ArgsSource(val methodName: String, val numberOfArgs: Int = 0)

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ArgsFilter(val methodName: String)

class ArgsArgumentProvider : AnnotationBasedArgumentsProvider<ArgsSource>() {

	override fun provideArguments(context: ExtensionContext, annotation: ArgsSource): Stream<out Arguments> {
		val testClass = context.requiredTestClass
		val testMethod = context.requiredTestMethod
		val testInstance = context.testInstance.orElse(null)

		val factoryMethod = findMethod(testClass, testMethod, annotation.methodName).also {
			validateFactoryMethod(it, testInstance)
		}

		val factoryResult = context.executableInvoker.invoke(factoryMethod, testInstance)
		return factoryResultToArguments(factoryResult, annotation).stream()
	}

	private fun factoryResultToArguments(
		result: Any,
		annotation: ArgsSource
	): List<Arguments> = argsGeneratorsToArguments(
		annotation,
		when (result) {
			is ArgsGenerator<*> -> listOf(result)

			is Pair<*, *> -> listOf(result.first, result.second)
			is Triple<*, *, *> -> listOf(result.first, result.second, result.third)
			is Tuple4<*, *, *, *> -> listOf(result.a1, result.a2, result.a3, result.a4)
			is Tuple5<*, *, *, *, *> -> listOf(result.a1, result.a2, result.a3, result.a4, result.a5)
			is Tuple6<*, *, *, *, *, *> -> listOf(result.a1, result.a2, result.a3, result.a4, result.a5, result.a6)
			is Tuple7<*, *, *, *, *, *, *> ->
				listOf(result.a1, result.a2, result.a3, result.a4, result.a5, result.a6, result.a7)

			is Tuple8<*, *, *, *, *, *, *, *> ->
				listOf(result.a1, result.a2, result.a3, result.a4, result.a5, result.a6, result.a7, result.a8)

			is Tuple9<*, *, *, *, *, *, *, *, *> -> listOf(
				result.a1,
				result.a2,
				result.a3,
				result.a4,
				result.a5,
				result.a6,
				result.a7,
				result.a8,
				result.a9
			)

			is List<*> -> {
				check(result.isNotEmpty()) {
					"No ArgumentsGenerator defined"
				}
				result
			}

			//TODO provide link in error message
			else -> throw UnsupportedOperationException("don't know how to convert ${result::class.qualifiedName} into Arguments, please open a feature request")
		}
	)

	private fun argsGeneratorsToArguments(annotation: ArgsSource, maybeGenerators: List<Any?>): List<Arguments> {
		//TODO 10 should be read by a config or the like
		val determineMaxSize = if (annotation.numberOfArgs > 0) annotation.numberOfArgs else 10
		val orderedArgsGenerators = mutableListOf<Pair<Int, OrderedArgsGenerator<*>>>()
		val randomArgsGenerators = mutableListOf<Pair<Int, RandomArgsGenerator<*>>>()
		maybeGenerators.forEachIndexed { index, maybeGenerator ->
			when (maybeGenerator) {
				is OrderedArgsGenerator<*> -> orderedArgsGenerators.add(index to maybeGenerator)
				is RandomArgsGenerator<*> -> randomArgsGenerators.add(index to maybeGenerator)
				//TODO revise text and add link
				is ArgsGenerator<*> -> throw UnsupportedOperationException("found ArgsGenerator ${maybeGenerators::class.qualifiedName} but we don't know how to treat it, please open a feature request that this handling shall be extendable")
				else -> throw IllegalStateException("Found ${maybeGenerators::class.qualifiedName} which is not an ArgsGenerator")
			}
		}

		// in case we only have randomArgsGenerators, then we can just zip them
		return if (orderedArgsGenerators.isEmpty()) {
			randomArgsGenerators
				.asSequence()
				.map { (_, generator) -> generator }
				.zipAllRandomGenerators()
				.map { sequence ->
					// flattening the different args into one Arguments
					Arguments.of(*sequence.flatMap { it.get().asList() }.toList().toTypedArray())
				}
				.take(determineMaxSize)
				.toList()
		} else {

			TODO()
		}
	}

	private fun Sequence<RandomArgsGenerator<*>>.zipAllRandomGenerators(): Sequence<Sequence<Args>> = sequence {
		val iterators = this@zipAllRandomGenerators.map { it.generate().iterator() }

		// random generators should always generate a next value, hence no need to check if the iterator has next
		// (and if not, then the call to next will fail)
		while (true) {
			yield(iterators.map { it.next() })
		}
	}

	// copied from JUnit's MethodArgumentsProvider and adopted to our needs
	private fun findMethod(testClass: Class<*>, testMethod: Method, methodName: String): Method {
		val methodNameToSearch = if (looksLikeAFullyQualifiedMethodName(methodName)) {
			methodName
		} else {
			// Convert local factory method name to fully qualified method name.
			testClass.name + "#" + methodName
		}
		// Find method using fully qualified name.
		return findMethodByFullyQualifiedName(testClass, testMethod, methodNameToSearch)
	}

	private fun findFactoryMethodBySimpleName(clazz: Class<*>, testMethod: Method, factoryMethodName: String): Method {
		val isCandidate =
			Predicate { candidate: Method ->
				factoryMethodName == candidate.name
					&& testMethod != candidate
			}
		val candidates = ReflectionUtils.findMethods(clazz, isCandidate)

		val factoryMethods =
			candidates.stream().filter(isFactoryMethod).collect(Collectors.toList())

		Preconditions.condition(factoryMethods.size > 0) {
			// If we didn't find the factory method using the isFactoryMethod Predicate, perhaps
			// the specified factory method has an invalid return type or is a test method.
			// In that case, we report the invalid candidates that were found.
			if (candidates.size > 0) {
				return@condition String.format(
					"Could not find valid factory method [%s] in class [%s] but found the following invalid candidates: %s",
					factoryMethodName, clazz.name, candidates
				)
			}
			String.format("Could not find factory method [%s] in class [%s]", factoryMethodName, clazz.name)
		}
		Preconditions.condition(
			factoryMethods.size == 1
		) {
			String.format(
				"%d factory methods named [%s] were found in class [%s]: %s", factoryMethods.size,
				factoryMethodName, clazz.name, factoryMethods
			)
		}
		return factoryMethods[0]
	}

	private fun looksLikeAFullyQualifiedMethodName(factoryMethodName: String): Boolean {
		if (factoryMethodName.contains("#")) {
			return true
		}
		val indexOfFirstDot = factoryMethodName.indexOf('.')
		if (indexOfFirstDot == -1) {
			return false
		}
		val indexOfLastOpeningParenthesis = factoryMethodName.lastIndexOf('(')
		if (indexOfLastOpeningParenthesis > 0) {
			// Exclude simple/local method names with parameters
			return indexOfFirstDot < indexOfLastOpeningParenthesis
		}
		// If we get this far, we conclude the supplied factory method name "looks"
		// like it was intended to be a fully qualified method name, even if the
		// syntax is invalid. We do this in order to provide better diagnostics for
		// the user when a fully qualified method name is in fact invalid.
		return true
	}


	private fun findMethodByFullyQualifiedName(
		testClass: Class<*>,
		testMethod: Method,
		fullyQualifiedMethodName: String
	): Method {
		val methodParts = ReflectionUtils.parseFullyQualifiedMethodName(fullyQualifiedMethodName)
		val className = methodParts[0]
		val methodName = methodParts[1]
		val methodParameters = methodParts[2]
		val classLoader = ClassLoaderUtils.getClassLoader(testClass)
		val clazz = ReflectionUtils.loadRequiredClass(className, classLoader)

		// Attempt to find an exact match first.
		val method = ReflectionUtils.findMethod(clazz, methodName, methodParameters).orElse(null)
		if (method != null) {
			return method
		}

		val explicitParameterListSpecified =  //
			StringUtils.isNotBlank(methodParameters) || fullyQualifiedMethodName.endsWith("()")

		// If we didn't find an exact match but an explicit parameter list was specified,
		// that's a user configuration error.
		Preconditions.condition(!explicitParameterListSpecified) {
			String.format(
				"Could not find method [%s(%s)] in class [%s]", methodName, methodParameters,
				className
			)
		}

		// Otherwise, fall back to the same lenient search semantics that are used
		// to locate a "default" local factory method.
		return findFactoryMethodBySimpleName(clazz, testMethod, methodName)
	}


	private val isFactoryMethod: Predicate<Method> =  //
		Predicate { method: Method ->
			CollectionUtils.isConvertibleToStream(method.returnType) && !isTestMethod(method)
		}

	private fun isTestMethod(candidate: Method): Boolean {
		return AnnotationUtils.isAnnotated(candidate, Test::class.java) ||
			AnnotationUtils.isAnnotated(candidate, TestTemplate::class.java) ||
			AnnotationUtils.isAnnotated(candidate, TestFactory::class.java)
	}

	private fun validateFactoryMethod(factoryMethod: Method, testInstance: Any?) {
		Preconditions.condition(
			factoryMethod.declaringClass.isInstance(testInstance)
				|| ReflectionUtils.isStatic(factoryMethod)
		) {
			String.format(
				("Method '%s' must be static: local factory methods must be static "
					+ "unless the PER_CLASS @TestInstance lifecycle mode is used; "
					+ "external factory methods must always be static."),
				factoryMethod.toGenericString()
			)
		}
	}

	fun toArguments(item: Any?): Arguments {
		// Nothing to do except cast.
		if (item is Arguments) {
			return item
		}

		// Pass all multidimensional arrays "as is", in contrast to Object[].
		// See https://github.com/junit-team/junit5/issues/1665
		if (ReflectionUtils.isMultidimensionalArray(item)) {
			return Arguments.arguments(item)
		}

		// Special treatment for one-dimensional reference arrays.
		// See https://github.com/junit-team/junit5/issues/1665
		if (item is Array<*> && item.isArrayOf<Any>()) {
			return Arguments.arguments(*item as Array<Any?>)
		}

		// Pass everything else "as is".
		return Arguments.arguments(item)
	}

}

