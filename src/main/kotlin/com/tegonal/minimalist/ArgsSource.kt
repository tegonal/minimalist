package com.tegonal.minimalist

import org.junit.jupiter.params.provider.ArgumentsSource

/**
 * Define the method which provides the
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@java.lang.annotation.Repeatable(ArgSources::class)
@ArgumentsSource(ArgsArgumentProvider::class)
annotation class ArgsSource(val methodName: String, val fixedMaxNumberOfArgs: Int = 0, val fixedOffset: Int = 0)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ArgSources(
	vararg val value: ArgsSource
)
