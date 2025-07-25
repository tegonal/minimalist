package com.tegonal.minimalist.providers

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.generators.ArgsGenerator
import org.intellij.lang.annotations.Language
import org.junit.jupiter.params.provider.ArgumentsSource
import java.lang.annotation.Inherited
import com.tegonal.minimalist.config.MinimalistConfig

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
	/**
	 * Taken into account if > 0 and should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take].
	 */
	val atMostArgs: Int = -1,

	/**
	 * Taken into account if > 0 and should influence an [ArgsRangeDecider]'s choice of [ArgsRange.take].
	 */
	val requestedMinArgs: Int = -1,

	/**
	 * Taken into account if non-empty and will take precedence over [MinimalistConfig.defaultMaxArgsLevelCategory].
	 */
	val category: String = ""
)
