/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.tegonal.minimalist.export.org.junit.platform.commons.util;

import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;
import static org.apiguardian.api.API.Status.INTERNAL;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.apiguardian.api.API;

/**
 * Collection of utilities for working with {@link String Strings},
 * {@link CharSequence CharSequences}, etc.
 *
 * <h2>DISCLAIMER</h2>
 *
 * <p>These utilities are intended solely for usage within the JUnit framework
 * itself. <strong>Any usage by external parties is not supported.</strong>
 * Use at your own risk!
 *
 * @since 1.0
 */
@API(status = INTERNAL, since = "1.0")
public final class StringUtils {

	private static final Pattern ISO_CONTROL_PATTERN = compileIsoControlPattern();
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

	/**
	 * Guard against "IllegalArgumentException: Unsupported flags: 256" errors.
	 * @see <a href="https://github.com/junit-team/junit-framework/issues/1800">#1800</a>
	 */
	static Pattern compileIsoControlPattern() {
		// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#posix
		try {
			// All of the characters that Unicode refers to as 'control characters'
			return Pattern.compile("\\p{Cntrl}", UNICODE_CHARACTER_CLASS);
		}
		catch (IllegalArgumentException e) {
			// Fall-back to ASCII control characters only: [\x00-\x1F\x7F]
			return Pattern.compile("\\p{Cntrl}");
		}
	}

	private StringUtils() {
		/* no-op */
	}

	/**
	 * Determine if the supplied {@link String} is <em>blank</em> (i.e.,
	 * {@code null} or consisting only of whitespace characters).
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string is blank
	 * @see #isNotBlank(String)
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().isEmpty());
	}

	/**
	 * Determine if the supplied {@link String} is not {@linkplain #isBlank
	 * blank}.
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string is not blank
	 * @see #isBlank(String)
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * Determine if the supplied {@link String} contains any whitespace characters.
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string contains whitespace
	 * @see #containsIsoControlCharacter(String)
	 * @see Character#isWhitespace(int)
	 */
	public static boolean containsWhitespace(String str) {
		return str != null && str.codePoints().anyMatch(Character::isWhitespace);
	}

	/**
	 * Determine if the supplied {@link String} does not contain any whitespace
	 * characters.
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string does not contain whitespace
	 * @see #containsWhitespace(String)
	 * @see #containsIsoControlCharacter(String)
	 * @see Character#isWhitespace(int)
	 */
	public static boolean doesNotContainWhitespace(String str) {
		return !containsWhitespace(str);
	}

	/**
	 * Determine if the supplied {@link String} contains any ISO control characters.
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string contains an ISO control character
	 * @see #containsWhitespace(String)
	 * @see Character#isISOControl(int)
	 */
	public static boolean containsIsoControlCharacter(String str) {
		return str != null && str.codePoints().anyMatch(Character::isISOControl);
	}

	/**
	 * Determine if the supplied {@link String} does not contain any ISO control
	 * characters.
	 *
	 * @param str the string to check; may be {@code null}
	 * @return {@code true} if the string does not contain an ISO control character
	 * @see #containsIsoControlCharacter(String)
	 * @see #containsWhitespace(String)
	 * @see Character#isISOControl(int)
	 */
	public static boolean doesNotContainIsoControlCharacter(String str) {
		return !containsIsoControlCharacter(str);
	}

	/**
	 * Convert the supplied {@code Object} to a {@code String} using the
	 * following algorithm.
	 *
	 * <ul>
	 * <li>If the supplied object is {@code null}, this method returns {@code "null"}.</li>
	 * <li>If the supplied object is a primitive array, the appropriate
	 * {@code Arrays#toString(...)} variant will be used to convert it to a String.</li>
	 * <li>If the supplied object is an object array, {@code Arrays#deepToString(Object[])}
	 * will be used to convert it to a String.</li>
	 * <li>Otherwise, {@code toString()} will be invoked on the object. If the
	 * result is non-null, that result will be returned. If the result is
	 * {@code null}, {@code "null"} will be returned.</li>
	 * <li>If any of the above results in an exception, this method delegates to
	 * {@link #defaultToString(Object)}</li>
	 * </ul>
	 *
	 * @param obj the object to convert to a String; may be {@code null}
	 * @return a String representation of the supplied object; never {@code null}
	 * @see Arrays#deepToString(Object[])
	 * @see ClassUtils#nullSafeToString(Class...)
	 */
	public static String nullSafeToString(Object obj) {
		if (obj == null) {
			return "null";
		}

		try {
			if (obj.getClass().isArray()) {
				if (obj.getClass().getComponentType().isPrimitive()) {
					if (obj instanceof boolean[]) {
						return Arrays.toString((boolean[]) obj);
					}
					if (obj instanceof char[]) {
						return Arrays.toString((char[]) obj);
					}
					if (obj instanceof short[]) {
						return Arrays.toString((short[]) obj);
					}
					if (obj instanceof byte[]) {
						return Arrays.toString((byte[]) obj);
					}
					if (obj instanceof int[]) {
						return Arrays.toString((int[]) obj);
					}
					if (obj instanceof long[]) {
						return Arrays.toString((long[]) obj);
					}
					if (obj instanceof float[]) {
						return Arrays.toString((float[]) obj);
					}
					if (obj instanceof double[]) {
						return Arrays.toString((double[]) obj);
					}
				}
				return Arrays.deepToString((Object[]) obj);
			}

			// else
			String result = obj.toString();
			return result != null ? result : "null";
		}
		catch (Throwable throwable) {
			UnrecoverableExceptions.rethrowIfUnrecoverable(throwable);

			return defaultToString(obj);
		}
	}

	/**
	 * Convert the supplied {@code Object} to a <em>default</em> {@code String}
	 * representation using the following algorithm.
	 *
	 * <ul>
	 * <li>If the supplied object is {@code null}, this method returns {@code "null"}.</li>
	 * <li>Otherwise, the String returned by this method will be generated analogous
	 * to the default implementation of {@link Object#toString()} by using the supplied
	 * object's class name and hash code as follows:
	 * {@code obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj))}</li>
	 * </ul>
	 *
	 * @param obj the object to convert to a String; may be {@code null}
	 * @return the default String representation of the supplied object; never {@code null}
	 * @see #nullSafeToString(Object)
	 * @see ClassUtils#nullSafeToString(Class...)
	 */
	public static String defaultToString(Object obj) {
		if (obj == null) {
			return "null";
		}

		return obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
	}

	/**
	 * Replace all ISO control characters in the supplied {@link String}.
	 *
	 * @param str the string in which to perform the replacement; may be {@code null}
	 * @param replacement the replacement string; never {@code null}
	 * @return the supplied string with all control characters replaced, or
	 * {@code null} if the supplied string was {@code null}
	 * @since 1.4
	 */
	@API(status = INTERNAL, since = "1.4")
	public static String replaceIsoControlCharacters(String str, String replacement) {
		Preconditions.notNull(replacement, "replacement must not be null");
		return str == null ? null : ISO_CONTROL_PATTERN.matcher(str).replaceAll(replacement);
	}

	/**
	 * Replace all whitespace characters in the supplied {@link String}.
	 *
	 * @param str the string in which to perform the replacement; may be {@code null}
	 * @param replacement the replacement string; never {@code null}
	 * @return the supplied string with all whitespace characters replaced, or
	 * {@code null} if the supplied string was {@code null}
	 * @since 1.4
	 */
	@API(status = INTERNAL, since = "1.4")
	public static String replaceWhitespaceCharacters(String str, String replacement) {
		Preconditions.notNull(replacement, "replacement must not be null");
		return str == null ? null : WHITESPACE_PATTERN.matcher(str).replaceAll(replacement);
	}

	/**
	 * Split the supplied {@link String} into up to two parts using the supplied
	 * separator character.
	 *
	 * @param separator the separator character
	 * @param value the value to split; never {@code null}
	 * @since 1.11
	 */
	@API(status = INTERNAL, since = "1.11")
	public static TwoPartSplitResult splitIntoTwo(char separator, String value) {
		Preconditions.notNull(value, "value must not be null");
		return splitIntoTwo(value, value.indexOf(separator), 1);
	}

	/**
	 * Split the supplied {@link String} into up to two parts using the supplied
	 * separator string.
	 *
	 * @param separator the separator string; never {@code null}
	 * @param value the value to split; never {@code null}
	 * @since 1.11
	 */
	@API(status = INTERNAL, since = "1.11")
	public static TwoPartSplitResult splitIntoTwo(String separator, String value) {
		Preconditions.notNull(separator, "separator must not be null");
		Preconditions.notNull(value, "value must not be null");
		return splitIntoTwo(value, value.indexOf(separator), separator.length());
	}

	private static TwoPartSplitResult splitIntoTwo(String value, int index, int length) {
		if (index == -1) {
			return new OnePart(value);
		}
		return new TwoParts(value.substring(0, index), value.substring(index + length));
	}

	/**
	 * The result of splitting a string into up to two parts.
	 *
	 * @since 1.11
	 * @see StringUtils#splitIntoTwo(char, String)
	 * @see StringUtils#splitIntoTwo(String, String)
	 */
	@API(status = INTERNAL, since = "1.11")
	public interface TwoPartSplitResult {

		/**
		 * Map the result of splitting a string into two parts or throw an exception.
		 *
		 * @param onePartExceptionCreator the exception creator to use if the string
		 * was split into a single part
		 * @param twoPartsMapper the mapper to use if the string was split into two parts
		 */
		default <T> T mapTwo(Supplier<? extends RuntimeException> onePartExceptionCreator,
				BiFunction<String, String, ? extends T> twoPartsMapper) {
			Function<String, ? extends T> onePartMapper = __ -> {
				throw onePartExceptionCreator.get();
			};
			return map(onePartMapper, twoPartsMapper);
		}

		/**
		 * Map the result of splitting a string into up to two parts.
		 *
		 * @param onePartMapper the mapper to use if the string was split into a single part
		 * @param twoPartsMapper the mapper to use if the string was split into two parts
		 */
		<T> T map(Function<String, ? extends T> onePartMapper, BiFunction<String, String, ? extends T> twoPartsMapper);

	}

	private static final class OnePart implements TwoPartSplitResult {

		private final String value;

		OnePart(String value) {
			this.value = value;
		}

		@Override
		public <T> T map(Function<String, ? extends T> onePartMapper,
				BiFunction<String, String, ? extends T> twoPartsMapper) {
			return onePartMapper.apply(this.value);
		}
	}

	private static final class TwoParts implements TwoPartSplitResult {

		private final String first;
		private final String second;

		TwoParts(String first, String second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public <T> T map(Function<String, ? extends T> onePartMapper,
				BiFunction<String, String, ? extends T> twoPartsMapper) {
			return twoPartsMapper.apply(this.first, this.second);
		}
	}

}
