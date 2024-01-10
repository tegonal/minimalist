// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
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
	 * @param value the new value to use for argument 1.
	 * @param representation the new representation to use for the argument 1 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 1.0.0
	 */
	fun withArg1(value: A1, representation: String? = null): Args2<A1, A2>

	/**
	 * Creates a new [Args2] by coping `this` [Args2] but replaces
	 * the argument 2 ([Args2.a2]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 2.
	 * @param representation the new representation to use for the argument 2 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args2].
	 *
	 * @since 1.0.0
	 */
	fun withArg2(value: A2, representation: String? = null): Args2<A1, A2>


	/**
	 * Creates a new [Args3] by copying `this` [Args2] and appending the given [arg1].
	 *
	 * @return The newly created [Args3].
	 *
	 * @since 1.0.0
	 */
	fun <A3> append(
		arg1: Args1<A3>
	): Args3<A1, A2, A3>


	/**
	 * Creates a new [Args4] by copying `this` [Args2] and appending the given [arg2].
	 *
	 * @return The newly created [Args4].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4> append(
		arg2: Args2<A3, A4>
	): Args4<A1, A2, A3, A4>


	/**
	 * Creates a new [Args5] by copying `this` [Args2] and appending the given [arg3].
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5> append(
		arg3: Args3<A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5>


	/**
	 * Creates a new [Args6] by copying `this` [Args2] and appending the given [arg4].
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6> append(
		arg4: Args4<A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6>


	/**
	 * Creates a new [Args7] by copying `this` [Args2] and appending the given [arg5].
	 *
	 * @return The newly created [Args7].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7> append(
		arg5: Args5<A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7>


	/**
	 * Creates a new [Args8] by copying `this` [Args2] and appending the given [arg6].
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8> append(
		arg6: Args6<A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8>


	/**
	 * Creates a new [Args9] by copying `this` [Args2] and appending the given [arg7].
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8, A9> append(
		arg7: Args7<A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>


	/**
	 * Creates a new [Args10] by copying `this` [Args2] and appending the given [arg8].
	 *
	 * @return The newly created [Args10].
	 *
	 * @since 1.0.0
	 */
	fun <A3, A4, A5, A6, A7, A8, A9, A10> append(
		arg8: Args8<A3, A4, A5, A6, A7, A8, A9, A10>
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
