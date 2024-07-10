// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist

/**
 * Represents an [Args] with 2 arguments.
 *
 * @since 1.0.0
 */
interface Args2<A1, A2>: Args {
	/**
	 * The value of argument 1.
	 *
	 * @since 1.0.0
	 */
	val a1: A1
	/**
	 * The value of argument 2.
	 *
	 * @since 1.0.0
	 */
	val a2: A2

	/**
	 * The representation of argument 1.
	 *
	 * @since 1.0.0
	 */
	val representation1: String?
	/**
	 * The representation of argument 2.
	 *
	 * @since 1.0.0
	 */
	val representation2: String?

	/**
	 * Creates a new [Args2] by coping `this` [Args2] but replaces
	 * the argument 1 ([Args2.a1]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 1.
	 * @param representation The new representation to use for the argument 1 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 1.0.0
	 */
	fun withArg1(value: A1, representation: String? = null): Args2<A1, A2>

	/**
	 * Maps [a1] of this [Args2] with the given [transform] function resulting in a new [Args2].
	 *
	 * @param transform The function which maps [a1] to a new value.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 2.0.0
	 */
	fun <A1New> mapArg1(transform: (A1) -> A1New): Args2<A1New, A2>

	/**
	 * Maps [a1] and its [representation1] of this [Args2] with the given [transform] function resulting in a new [Args2].
	 *
	 * @param transform The function which maps [a1] and [representation1].
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 2.0.0
	 */
	fun <A1New> mapArg1WithRepresentation(transform: (A1, String?) -> Pair<A1New, String?>): Args2<A1New, A2>

	/**
	 * Creates a new [Args2] by coping `this` [Args2] but replaces
	 * the argument 2 ([Args2.a2]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 2.
	 * @param representation The new representation to use for the argument 2 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 1.0.0
	 */
	fun withArg2(value: A2, representation: String? = null): Args2<A1, A2>

	/**
	 * Maps [a2] of this [Args2] with the given [transform] function resulting in a new [Args2].
	 *
	 * @param transform The function which maps [a2] to a new value.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 2.0.0
	 */
	fun <A2New> mapArg2(transform: (A2) -> A2New): Args2<A1, A2New>

	/**
	 * Maps [a2] and its [representation2] of this [Args2] with the given [transform] function resulting in a new [Args2].
	 *
	 * @param transform The function which maps [a2] and [representation2].
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 2.0.0
	 */
	fun <A2New> mapArg2WithRepresentation(transform: (A2, String?) -> Pair<A2New, String?>): Args2<A1, A2New>


	/**
	 * Creates a new [Args3] by copying `this` [Args2] and appending the given [Args1].
	 *
	 * @return The newly created [Args3].
	 *
	 * @since 1.0.0
	 */
	fun <A3> append(
		args: Args1<A3>
	): Args3<A1, A2, A3>


	/**
	 * Creates a new [Args4] by copying `this` [Args2] and appending the given [Args2].
	 *
	 * @return The newly created [Args4].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4> append(
		args: Args2<A3, A4>
	): Args4<A1, A2, A3, A4>


	/**
	 * Creates a new [Args5] by copying `this` [Args2] and appending the given [Args3].
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5> append(
		args: Args3<A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5>


	/**
	 * Creates a new [Args6] by copying `this` [Args2] and appending the given [Args4].
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6> append(
		args: Args4<A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6>


	/**
	 * Creates a new [Args7] by copying `this` [Args2] and appending the given [Args5].
	 *
	 * @return The newly created [Args7].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7> append(
		args: Args5<A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7>


	/**
	 * Creates a new [Args8] by copying `this` [Args2] and appending the given [Args6].
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8> append(
		args: Args6<A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8>


	/**
	 * Creates a new [Args9] by copying `this` [Args2] and appending the given [Args7].
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8, A9> append(
		args: Args7<A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>


	/**
	 * Creates a new [Args10] by copying `this` [Args2] and appending the given [Args8].
	 *
	 * @return The newly created [Args10].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8, A9, A10> append(
		args: Args8<A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>


	/**
	 * Creates a new [Args1] by copying `this` [Args2] but dropping its argument 1 ([Args2.a1]).
	 *
	 * @return The newly created [Args1].
	 *
	 * @since 1.0.0
	 */
	fun dropArg1(): Args1<A2>

	/**
	 * Creates a new [Args1] by copying `this` [Args2] but dropping its argument 2 ([Args2.a2]).
	 *
	 * @return The newly created [Args1].
	 *
	 * @since 1.0.0
	 */
	fun dropArg2(): Args1<A1>

}
