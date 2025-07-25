// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.config.ordered
import com.tegonal.minimalist.generators.impl.OrderedArgsGeneratorTransformer


/**
 * Maps the values `this` [OrderedArgsGenerator] generates to type [R] with the help of the given [transform] function.
 *
 * @param transform The transformation function which takes a [T] and produces an [R].
 *
 * @param T The type of values generated by `this` [OrderedArgsGenerator].
 * @param R the type of values generated by the resulting [OrderedArgsGenerator].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [R].
 *
 * @since 2.0.0
 */
fun <T, R> OrderedArgsGenerator<T>.map(transform: (T) -> R): OrderedArgsGenerator<R> {
	// Note, we tried to re-use OrderedArgsGeneratorTransformer for other operations by providing a generic transform
	// extension method which required a newSize in addition. Yet, we ran into 3 bugs in a row, so it seems it is
	// too dangerous to leave it into the wild. In particular one needs to take care of:
	// - that the newSize is actually correct (we cannot check this)
	// - the offset still works as it should. For instance, filtering means the offset has to happen after generation,
	//   i.e. dropping elements and this can harm performance in case there are a lot of values (which can happen easily
	//   when combining generators). The same problem applies when adding elements (e.g. via flatMap). Something like
	//   Sequence.chunked needs extra care as the resulting stream should be ordered finite, repeating after size
	return OrderedArgsGeneratorTransformer(this) { it.map(transform) }
}

/**
 * Generates [size][OrderedArgsGenerator.size] values, filters the resulting sequence so that only elements matching the
 * given [predicate] remain and creates a new [OrderedArgsGenerator] based on it.
 *
 * @param predicate which should return `true` for a given value if it shall be kept in the sequence, otherwise `false`.
 *
 * @param T The type of values generated by `this` [OrderedArgsGenerator].
 *
 * @return The resulting [OrderedArgsGenerator] which generates only values for which
 *   the given [predicate] returns `true`.
 *
 * @since 2.0.0
 */
fun <T> OrderedArgsGenerator<T>.filterMaterialised(predicate: (T) -> Boolean): OrderedArgsGenerator<T> =
	transformMaterialised { it.filter(predicate) }

/**
 * Generates [size][OrderedArgsGenerator.size] values, [transform]s and materialises them and creates a new
 * [OrderedArgsGenerator] based on it.
 *
 * @param transform The transformation function which takes a [Sequence] of type [T] and
 *   produces a finite [Sequence] of type [R] (since we materialise you will run into a OutOfMemoryException if
 *   your resulting [Sequence] is too big/infinite).
 *
 * @param T The type of values generated by `this` [OrderedArgsGenerator].
 * @param R the type of values generated by the resulting [OrderedArgsGenerator].
 *
 * @return The resulting [OrderedArgsGenerator] which generates values of type [R].
 *
 * @since 2.0.0
 */
fun <T, R> OrderedArgsGenerator<T>.transformMaterialised(
	transform: (Sequence<T>) -> Sequence<R>,
): OrderedArgsGenerator<R> =
	generate().take(size).let(transform).toList().let(_components.ordered::fromList)


