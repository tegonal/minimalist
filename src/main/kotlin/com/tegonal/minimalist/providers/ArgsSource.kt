package com.tegonal.minimalist.providers

import org.intellij.lang.annotations.Language
import org.junit.jupiter.params.provider.ArgumentsSource
import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.Args
import java.lang.annotation.Inherited

/**
 *
 * @since 2.0.0
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ArgumentsSource(ArgsArgumentProvider::class)
@ArgsSourceLike
annotation class ArgsSource(
	@Language("jvm-method-name") val methodName: String,
)

/**
 * Marker annotation for annotations which acts as [ArgsSource], i.e. provide a `methodName: String` property which is
 * used to retrieve [ArgsGenerator] or [Args]
 * @since 2.0.0
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ArgsSourceLike

/**
 *
 * @since 2.0.0
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
annotation class ArgsSourceOptions(
	val fixedNumberOfArgs: Int = 0,
	val fixedOffset: Int = -1,
)
