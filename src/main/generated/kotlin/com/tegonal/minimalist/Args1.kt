// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist

/**
 * Represents an [Args] with 1 argument.
 *
 * @since 1.0.0
 */
interface Args1<A1>: Args {
	/**
	 * The value of argument 1.
	 *
	 * @since 1.0.0
	 */
	val a1: A1

	/**
	 * The representation of argument 1.
	 *
	 * @since 1.0.0
	 */
	val representation1: String?

	/**
	 * Creates a new [Args1] by coping `this` [Args1] but replaces
	 * the argument 1 ([Args1.a1]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 1.
	 * @param representation The new representation to use for the argument 1 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args1].
	 *
	 * @since 1.0.0
	 */
	fun withArg1(value: A1, representation: String? = null): Args1<A1>

	/**
	 * Maps [a1] of this [Args1] with the given [transform] function resulting in a new [Args1].
	 *
	 * @param transform The function which maps [a1] to a new value.
	 *
	 * @return The newly created [Args1].
	 *
	 * @since 2.0.0
	 */
	fun <A1New> mapArg1(transform: (A1) -> A1New): Args1<A1New>

	/**
	 * Maps [a1] and its [representation1] of this [Args1] with the given [transform] function resulting in a new [Args1].
	 *
	 * @param transform The function which maps [a1] and [representation1].
	 *
	 * @return The newly created [Args1].
	 *
	 * @since 2.0.0
	 */
	fun <A1New> mapArg1WithRepresentation(transform: (A1, String?) -> Pair<A1New, String?>): Args1<A1New>


	/**
	 * Creates a new [Args2] by copying `this` [Args1] and appending the given [Args1].
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 1.0.0
	 */
	fun <A2> append(
		args: Args1<A2>
	): Args2<A1, A2>


	/**
	 * Creates a new [Args3] by copying `this` [Args1] and appending the given [Args2].
	 *
	 * @return The newly created [Args3].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3> append(
		args: Args2<A2, A3>
	): Args3<A1, A2, A3>


	/**
	 * Creates a new [Args4] by copying `this` [Args1] and appending the given [Args3].
	 *
	 * @return The newly created [Args4].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4> append(
		args: Args3<A2, A3, A4>
	): Args4<A1, A2, A3, A4>


	/**
	 * Creates a new [Args5] by copying `this` [Args1] and appending the given [Args4].
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5> append(
		args: Args4<A2, A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5>


	/**
	 * Creates a new [Args6] by copying `this` [Args1] and appending the given [Args5].
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5, A6> append(
		args: Args5<A2, A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6>


	/**
	 * Creates a new [Args7] by copying `this` [Args1] and appending the given [Args6].
	 *
	 * @return The newly created [Args7].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5, A6, A7> append(
		args: Args6<A2, A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7>


	/**
	 * Creates a new [Args8] by copying `this` [Args1] and appending the given [Args7].
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5, A6, A7, A8> append(
		args: Args7<A2, A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8>


	/**
	 * Creates a new [Args9] by copying `this` [Args1] and appending the given [Args8].
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5, A6, A7, A8, A9> append(
		args: Args8<A2, A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>


	/**
	 * Creates a new [Args10] by copying `this` [Args1] and appending the given [Args9].
	 *
	 * @return The newly created [Args10].
	 *
	 * @since 1.0.0
	 */
	fun <A2, A3, A4, A5, A6, A7, A8, A9, A10> append(
		args: Args9<A2, A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>

}
