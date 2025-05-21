package com.tegonal.minimalist.providers

import ch.tutteli.kbox.Tuple2Like
import ch.tutteli.kbox.Tuple3Like
import ch.tutteli.kbox.Tuple4Like
import ch.tutteli.kbox.Tuple5Like
import ch.tutteli.kbox.Tuple6Like
import ch.tutteli.kbox.Tuple7Like
import ch.tutteli.kbox.Tuple8Like
import ch.tutteli.kbox.Tuple9Like
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.MinimalistConfigLoader
import com.tegonal.minimalist.utils.loadService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider
import org.junit.jupiter.params.provider.Arguments
import org.junit.platform.commons.util.AnnotationUtils
import org.junit.platform.commons.util.ClassLoaderUtils
import org.junit.platform.commons.util.CollectionUtils
import org.junit.platform.commons.util.Preconditions
import org.junit.platform.commons.util.ReflectionUtils
import org.junit.platform.commons.util.StringUtils
import java.lang.reflect.Method
import java.util.function.Predicate
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @since 2.0.0
 */
class ArgsArgumentProvider : AnnotationBasedArgumentsProvider<ArgsSource>() {

	override fun provideArguments(context: ExtensionContext, annotation: ArgsSource): Stream<out Arguments> {
		val testClass = context.requiredTestClass
		val testMethod = context.requiredTestMethod
		val testInstance = context.testInstance.orElse(null)

		val factoryMethod = findMethod(testClass, testMethod, annotation.methodName).also {
			validateFactoryMethod(it, testInstance)
		}

		val factoryResult = context.executableInvoker.invoke(factoryMethod, testInstance)
		return factoryResultToArguments(factoryResult, testMethod, annotation).stream()
	}

	private fun factoryResultToArguments(
		result: Any,
		testMethod: Method,
		annotation: ArgsSource
	): List<Arguments> = loadService<GenericToArgumentsTransformer>(MinimalistConfigLoader.config.activeArgsGeneratorCombiner)
		.toArguments(
			testMethod,
			annotation,
			toListOfMaybeArgGenerators(result)
		)


	private fun toListOfMaybeArgGenerators(result: Any): List<Any?> = when (result) {
		is ArgsGenerator<*> -> listOf(result)

		is Pair<*, *> -> listOf(result.component1(), result.component2())
		is Triple<*, *, *> -> listOf(result.component1(), result.component2(), result.component3())
		is Tuple2Like<*, *> -> listOf(result.component1(), result.component2())
		is Tuple3Like<*, *, *> -> listOf(result.component1(), result.component2(), result.component3())
		is Tuple4Like<*, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4()
		)

		is Tuple5Like<*, *, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4(),
			result.component5()
		)

		is Tuple6Like<*, *, *, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4(),
			result.component5(),
			result.component6()
		)

		is Tuple7Like<*, *, *, *, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4(),
			result.component5(),
			result.component6(),
			result.component7()
		)

		is Tuple8Like<*, *, *, *, *, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4(),
			result.component5(),
			result.component6(),
			result.component7(),
			result.component8()
		)

		is Tuple9Like<*, *, *, *, *, *, *, *, *> -> listOf(
			result.component1(),
			result.component2(),
			result.component3(),
			result.component4(),
			result.component5(),
			result.component6(),
			result.component7(),
			result.component8(),
			result.component9()
		)

		//TODO 2.1.0 support also Iterable and Sequence?
		is List<*> -> {
			result.also {
				check(result.isNotEmpty()) {
					"No ArgumentsGenerator defined"
				}
			}
		}

		else -> throw UnsupportedOperationException("don't know how to convert ${result::class.qualifiedName} into Arguments, please open a feature request: $FEATURE_REQUEST_URL&title=Convert%20${result::class}%20to%20Arguments")
	}

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
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
		val isCandidate = Predicate { candidate: Method ->
			factoryMethodName == candidate.name
				&& testMethod != candidate
		}
		val candidates = ReflectionUtils.findMethods(clazz, isCandidate)

		val factoryMethods = candidates.stream().filter(isFactoryMethod).collect(Collectors.toList())

		Preconditions.condition(factoryMethods.isNotEmpty()) {
			// If we didn't find the factory method using the isFactoryMethod Predicate, perhaps
			// the specified factory method has an invalid return type or is a test method.
			// In that case, we report the invalid candidates that were found.
			if (candidates.isNotEmpty()) {
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
			// Note, ParameterizedTest is a subclass of TestTemplate
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

	//TODO 2.0.0 remove?
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

	companion object {
		const val FEATURE_REQUEST_URL = "https://github.com/tegonal/minimalist/discussions/new?category=ideas"
	}

}

