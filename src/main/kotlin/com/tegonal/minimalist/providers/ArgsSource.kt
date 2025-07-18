package com.tegonal.minimalist.providers

import org.intellij.lang.annotations.Language
import org.junit.jupiter.params.provider.ArgumentsSource
import java.lang.annotation.Repeatable

/**
 * Define the method which provides the
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repeatable(ArgSources::class)
@ArgumentsSource(ArgsArgumentProvider::class)
annotation class ArgsSource(
	@Language("jvm-method-name") val methodName: String,
	/**
	 *
	 */
	val fixedNumberOfArgs: Int = 0,
	val fixedOffset: Int = -1,
)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ArgSources(
	vararg val value: ArgsSource
)
