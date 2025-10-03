/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

// -----------------------------------------------------------------------------------------------------
// WARNING !!!!!!!!!!!!!!
// Copied from JUnit and exported for internal use in Minimalist only
// No backward compatibility guarantees. As soon as JUnit breaks compatibility (which can happen
// even in a patch version and that is totally fine) and we `gt update` this file to this new version
// (maybe also in a patch version), we will break compatibility as well.
// -----------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.export.org.junit.jupiter.params.provider;

import com.tegonal.minimalist.export.org.junit.platform.commons.util.ClassLoaderUtils;
import com.tegonal.minimalist.export.org.junit.platform.commons.util.Preconditions;
import com.tegonal.minimalist.export.org.junit.platform.commons.util.ReflectionUtils;
import com.tegonal.minimalist.export.org.junit.platform.commons.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestTemplate;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.tegonal.minimalist.export.org.junit.platform.commons.util.CollectionUtils.isConvertibleToStream;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.commons.support.AnnotationSupport.isAnnotated;

/**
 * @since 5.0
 *
 * Modified for Minimalist, see pull-hook.sh
 * MethodArgumentsProvider usually extends AnnotationBasedArgumentsProvider
 * (in org.junit.jupiter.params.provider). Minimalist is only interested in its static functions.
 * and has removed provideArguments
 */
public class MethodArgumentsProvider {

	private static final Predicate<Method> isFactoryMethod = //
		method -> !isTestMethod(method);

	public static Method findFactoryMethod(Class<?> testClass, Optional<Method> testMethod, String factoryMethodName) {
		String originalFactoryMethodName = factoryMethodName;

		// If the user did not provide a factory method name, find a "default" local
		// factory method with the same name as the parameterized test method.
		if (StringUtils.isBlank(factoryMethodName)) {
			Preconditions.condition(testMethod.isPresent(),
				"You must specify a method name when using @MethodSource with @ParameterizedClass");
			factoryMethodName = testMethod.get().getName();
			return findFactoryMethodBySimpleName(testClass, testMethod, factoryMethodName);
		}

		// Convert local factory method name to fully qualified method name.
		if (!looksLikeAFullyQualifiedMethodName(factoryMethodName)) {
			factoryMethodName = testClass.getName() + "#" + factoryMethodName;
		}

		// Find factory method using fully qualified name.
		Method factoryMethod = findFactoryMethodByFullyQualifiedName(testClass, testMethod, factoryMethodName);

		// Ensure factory method has a valid return type and is not a test method.
		Preconditions.condition(isFactoryMethod.test(factoryMethod), () -> format(
			"Could not find valid factory method [%s] for test class [%s] but found the following invalid candidate: %s",
			originalFactoryMethodName, testClass.getName(), factoryMethod));

		return factoryMethod;
	}

	private static boolean looksLikeAFullyQualifiedMethodName(String factoryMethodName) {
		if (factoryMethodName.contains("#")) {
			return true;
		}
		int indexOfFirstDot = factoryMethodName.indexOf('.');
		if (indexOfFirstDot == -1) {
			return false;
		}
		int indexOfLastOpeningParenthesis = factoryMethodName.lastIndexOf('(');
		if (indexOfLastOpeningParenthesis > 0) {
			// Exclude simple/local method names with parameters
			return indexOfFirstDot < indexOfLastOpeningParenthesis;
		}
		// If we get this far, we conclude the supplied factory method name "looks"
		// like it was intended to be a fully qualified method name, even if the
		// syntax is invalid. We do this in order to provide better diagnostics for
		// the user when a fully qualified method name is in fact invalid.
		return true;
	}

	// package-private for testing
	static Method findFactoryMethodByFullyQualifiedName(Class<?> testClass, Optional<Method> testMethod,
			String fullyQualifiedMethodName) {
		String[] methodParts = ReflectionUtils.parseFullyQualifiedMethodName(fullyQualifiedMethodName);
		String className = methodParts[0];
		String methodName = methodParts[1];
		String methodParameters = methodParts[2];
		ClassLoader classLoader = ClassLoaderUtils.getClassLoader(testClass);
		Class<?> clazz = ReflectionUtils.loadRequiredClass(className, classLoader);

		// Attempt to find an exact match first.
		Method factoryMethod = ReflectionSupport.findMethod(clazz, methodName, methodParameters).orElse(null);
		if (factoryMethod != null) {
			return factoryMethod;
		}

		boolean explicitParameterListSpecified = //
			StringUtils.isNotBlank(methodParameters) || fullyQualifiedMethodName.endsWith("()");

		// If we didn't find an exact match but an explicit parameter list was specified,
		// that's a user configuration error.
		Preconditions.condition(!explicitParameterListSpecified,
			() -> format("Could not find factory method [%s(%s)] in class [%s]", methodName, methodParameters,
				className));

		// Otherwise, fall back to the same lenient search semantics that are used
		// to locate a "default" local factory method.
		return findFactoryMethodBySimpleName(clazz, testMethod, methodName);
	}

	/**
	 * Find the factory method by searching for all methods in the given {@code clazz}
	 * with the desired {@code factoryMethodName} which have return types that can be
	 * converted to a {@link Stream}, ignoring the {@code testMethod} itself as well
	 * as any {@code @Test}, {@code @TestTemplate}, or {@code @TestFactory} methods
	 * with the same name.
	 * @return the single factory method matching the search criteria
	 * @throws PreconditionViolationException if the factory method was not found or
	 * multiple competing factory methods with the same name were found
	 */
	private static Method findFactoryMethodBySimpleName(Class<?> clazz, Optional<Method> testMethod,
			String factoryMethodName) {
		Predicate<Method> isCandidate = candidate -> factoryMethodName.equals(candidate.getName())
				&& !candidate.equals(testMethod.orElse(null));
		List<Method> candidates = ReflectionSupport.findMethods(clazz, isCandidate, HierarchyTraversalMode.TOP_DOWN);

		List<Method> factoryMethods = candidates.stream().filter(isFactoryMethod).collect(toList());

		Preconditions.notEmpty(factoryMethods, () -> {
			if (candidates.isEmpty()) {
				// Report that we didn't find anything.
				return format("Could not find factory method [%s] in class [%s]", factoryMethodName, clazz.getName());
			}
			// If we didn't find the factory method using the isFactoryMethod Predicate, perhaps
			// the specified factory method has an invalid return type or is a test method.
			// In that case, we report the invalid candidates that were found.
			return format(
				"Could not find valid factory method [%s] in class [%s] but found the following invalid candidates: %s",
				factoryMethodName, clazz.getName(), candidates);
		});
		Preconditions.condition(factoryMethods.size() == 1,
			() -> format("%d factory methods named [%s] were found in class [%s]: %s", factoryMethods.size(),
				factoryMethodName, clazz.getName(), factoryMethods));
		return factoryMethods.get(0);
	}

	private static boolean isTestMethod(Method candidate) {
		return isAnnotated(candidate, Test.class) || isAnnotated(candidate, TestTemplate.class)
				|| isAnnotated(candidate, TestFactory.class);
	}

	public static Method validateFactoryMethod(Method factoryMethod, Object testInstance) {
		Preconditions.condition(
			factoryMethod.getDeclaringClass().isInstance(testInstance) || Modifier.isStatic(factoryMethod.getModifiers()),
			() -> format("Method '%s' must be static: local factory methods must be static "
					+ "unless the PER_CLASS @TestInstance lifecycle mode is used; "
					+ "external factory methods must always be static.",
				factoryMethod.toGenericString()));
		return factoryMethod;
	}

}
