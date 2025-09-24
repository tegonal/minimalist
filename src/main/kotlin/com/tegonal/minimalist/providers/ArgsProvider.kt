package com.tegonal.minimalist.providers

import ch.tutteli.kbox.failIf
import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.build
import com.tegonal.minimalist.config.buildChained
import com.tegonal.minimalist.export.org.junit.platform.commons.util.*
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.generators.fromList
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.providers.impl.tupleAndTupleLikeToList
import com.tegonal.minimalist.utils.impl.FEATURE_REQUEST_URL
import com.tegonal.minimalist.utils.impl.getPropertyValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.support.ParameterDeclarations
import java.lang.reflect.Method
import java.util.function.Predicate
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.streams.asStream

/**
 * The [org.junit.jupiter.params.provider.ArgumentsProvider] for [ArgsSource].
 *
 * Copied and adapter from [org.junit.jupiter.params.provider.MethodArgumentsProvider] (Eclipse Public License 2.0)
 *
 * @since 2.0.0
 */
class ArgsProvider : ArgumentsProvider {

	@Suppress("DEPRECATION")
	override fun provideArguments(
		parameters: ParameterDeclarations,
		context: ExtensionContext
	): Stream<out Arguments> = provideArguments(context)

	// we know it is deprecated but users using an older version of JUnit (< 5.13) would fail if we would already
	// override the overload which expects ParameterDeclarations in addition. We will switch with JUnit 6
	@Suppress("OVERRIDE_DEPRECATION")
	override fun provideArguments(context: ExtensionContext): Stream<out Arguments> {
		val testClass = context.requiredTestClass
		val testMethod = context.requiredTestMethod
		val testInstance = context.testInstance.orElse(null)

		val annotations = testMethod.annotations.filter {
			it.annotationClass.annotations.any { metaAnnotation ->
				metaAnnotation.annotationClass == ArgsSourceLike::class
			}
		}

		failIf(annotations.isEmpty()) {
			"It looks like you forgot to annotate your ArgsSource annotation with ArgsSourceLike, at least couldn't find an annotation on ${testMethod.name} which itself is annotated with ArgsSourceLike"
		}
		val annotation = annotations.singleOrNull()
			?: error("For now we don't support to provide more than one ArgsSourceLike annotation, please open a feature request: $FEATURE_REQUEST_URL&title=annotate%20with%20multiple%20ArgsSourceLike")

		val methodName = annotation.getPropertyValue<String>("methodName")

		val factoryMethod = findMethod(testClass, testMethod, methodName).also {
			validateFactoryMethod(it, testInstance)
		}

		val factoryResult = context.executableInvoker.invoke(factoryMethod, testInstance)
		return factoryResultToArguments(factoryResult, testMethod, methodName).asStream()
	}

	private fun factoryResultToArguments(
		result: Any,
		testMethod: Method,
		argsSourceMethodName: String
	): Sequence<Arguments> {
		val maybeArgGenerators = toListOfMaybeArgGenerators(result)
		check(maybeArgGenerators.isNotEmpty()) { "no ArgGenerators/Args defined" }
		val (first, maybeArgGeneratorsRest) = when (val first = maybeArgGenerators.first()) {
			is ArgsGenerator<*> -> first to maybeArgGenerators.drop(1)
			else -> {
				// assuming raw values. Since no ArgsGenerator is involved, we cannot use a custom `ordered` and
				// fallback to the default `ordered`
				ordered.fromList(maybeArgGenerators) to emptyList()
			}
		}
		return generateArguments(first, maybeArgGeneratorsRest, testMethod, argsSourceMethodName)
	}

	private fun toListOfMaybeArgGenerators(result: Any): List<Any?> = tupleAndTupleLikeToList(result) ?: when (result) {
		is ArgsGenerator<*> -> listOf(result)
		is Iterable<*> -> result.toList()
		is Sequence<*> -> result.toList()
		is Array<*> -> result.toList()
		is ByteArray -> result.toList()
		is CharArray -> result.toList()
		is ShortArray -> result.toList()
		is IntArray -> result.toList()
		is LongArray -> result.toList()
		is FloatArray -> result.toList()
		is DoubleArray -> result.toList()
		is BooleanArray -> result.toList()

		else -> throw UnsupportedOperationException("don't know how to convert ${result::class.qualifiedName ?: result} into Arguments, please open a feature request: $FEATURE_REQUEST_URL&title=Convert%20${result::class}%20to%20Arguments")
	}

	private fun generateArguments(
		argsGenerator: ArgsGenerator<*>,
		restMaybeArgGenerators: List<*>,
		testMethod: Method,
		argsSourceMethodName: String,
	): Sequence<Arguments> = argsGenerator._components.let { components ->
		val annotationDataDeducers = components.buildChained<AnnotationDataDeducer>()
		val suffixArgsGeneratorDecider = components.build<SuffixArgsGeneratorDecider>()
		val genericArgsGeneratorCombiner = components.build<GenericArgsGeneratorCombiner>()
		val argsGeneratorToArgumentsConverter = components.build<ArgsGeneratorToArgumentsConverter>()

		val annotationData = annotationDataDeducers.fold(AnnotationData(argsSourceMethodName)) { data, deducer ->
			deducer.deduce(testMethod, argsSourceMethodName)?.let { data.merge(it) } ?: data
		}

		val restMaybeArgGeneratorsWithSuffix = suffixArgsGeneratorDecider
			.computeSuffixArgsGenerator(annotationData)?.let { suffixArgsGenerator ->
				restMaybeArgGenerators + listOf(suffixArgsGenerator)
			} ?: restMaybeArgGenerators

		genericArgsGeneratorCombiner.combineFirstWithRest(argsGenerator, restMaybeArgGeneratorsWithSuffix)
			.let { argsGeneratorsCombined ->
				argsGeneratorToArgumentsConverter.toArguments(annotationData, argsGeneratorsCombined)
			}
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

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
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

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
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

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
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


	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
	private val isFactoryMethod: Predicate<Method> =  //
		Predicate { method: Method ->
			CollectionUtils.isConvertibleToStream(method.returnType) && !isTestMethod(method)
		}

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
	private fun isTestMethod(candidate: Method): Boolean {
		return AnnotationUtils.isAnnotated(candidate, Test::class.java) ||
			// Note, ParameterizedTest is a subclass of TestTemplate
			AnnotationUtils.isAnnotated(candidate, TestTemplate::class.java) ||
			AnnotationUtils.isAnnotated(candidate, TestFactory::class.java)
	}

	// copied from JUnit's MethodArgumentsProvider (EPL License) and adopted to our needs
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
}

