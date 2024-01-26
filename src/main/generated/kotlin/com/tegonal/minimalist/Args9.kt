// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist

/**
 * Represents an [Args] with 9 arguments.
 *
 * @since 1.0.0
 */
interface Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>: Args {
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
	 * The value of argument 3.
	 *
	 * @since 1.0.0
	 */
	val a3: A3
	/**
	 * The value of argument 4.
	 *
	 * @since 1.0.0
	 */
	val a4: A4
	/**
	 * The value of argument 5.
	 *
	 * @since 1.0.0
	 */
	val a5: A5
	/**
	 * The value of argument 6.
	 *
	 * @since 1.0.0
	 */
	val a6: A6
	/**
	 * The value of argument 7.
	 *
	 * @since 1.0.0
	 */
	val a7: A7
	/**
	 * The value of argument 8.
	 *
	 * @since 1.0.0
	 */
	val a8: A8
	/**
	 * The value of argument 9.
	 *
	 * @since 1.0.0
	 */
	val a9: A9

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
	 * The representation of argument 3.
	 *
	 * @since 1.0.0
	 */
	val representation3: String?
	/**
	 * The representation of argument 4.
	 *
	 * @since 1.0.0
	 */
	val representation4: String?
	/**
	 * The representation of argument 5.
	 *
	 * @since 1.0.0
	 */
	val representation5: String?
	/**
	 * The representation of argument 6.
	 *
	 * @since 1.0.0
	 */
	val representation6: String?
	/**
	 * The representation of argument 7.
	 *
	 * @since 1.0.0
	 */
	val representation7: String?
	/**
	 * The representation of argument 8.
	 *
	 * @since 1.0.0
	 */
	val representation8: String?
	/**
	 * The representation of argument 9.
	 *
	 * @since 1.0.0
	 */
	val representation9: String?

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 1 ([Args9.a1]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 1.
	 * @param representation the new representation to use for the argument 1 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg1(value: A1, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 2 ([Args9.a2]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 2.
	 * @param representation the new representation to use for the argument 2 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg2(value: A2, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 3 ([Args9.a3]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 3.
	 * @param representation the new representation to use for the argument 3 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg3(value: A3, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 4 ([Args9.a4]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 4.
	 * @param representation the new representation to use for the argument 4 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg4(value: A4, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 5 ([Args9.a5]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 5.
	 * @param representation the new representation to use for the argument 5 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg5(value: A5, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 6 ([Args9.a6]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 6.
	 * @param representation the new representation to use for the argument 6 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg6(value: A6, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 7 ([Args9.a7]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 7.
	 * @param representation the new representation to use for the argument 7 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg7(value: A7, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 8 ([Args9.a8]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 8.
	 * @param representation the new representation to use for the argument 8 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg8(value: A8, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args9] by coping `this` [Args9] but replaces
	 * the argument 9 ([Args9.a9]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value the new value to use for argument 9.
	 * @param representation the new representation to use for the argument 9 where `null`
	 *         means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun withArg9(value: A9, representation: String? = null): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>


	/**
	 * Creates a new [Args10] by copying `this` [Args9] and appending the given [arg1].
	 *
	 * @return The newly created [Args10].
	 *
	 * @since 1.0.0
	 */
	fun <A10> append(
		arg1: Args1<A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>


	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 1 ([Args9.a1]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg1(): Args8<A2, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 2 ([Args9.a2]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg2(): Args8<A1, A3, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 3 ([Args9.a3]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg3(): Args8<A1, A2, A4, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 4 ([Args9.a4]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg4(): Args8<A1, A2, A3, A5, A6, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 5 ([Args9.a5]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg5(): Args8<A1, A2, A3, A4, A6, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 6 ([Args9.a6]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg6(): Args8<A1, A2, A3, A4, A5, A7, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 7 ([Args9.a7]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg7(): Args8<A1, A2, A3, A4, A5, A6, A8, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 8 ([Args9.a8]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg8(): Args8<A1, A2, A3, A4, A5, A6, A7, A9>

	/**
	 * Creates a new [Args8] by copying `this` [Args9] but dropping its argument 9 ([Args9.a9]).
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun dropArg9(): Args8<A1, A2, A3, A4, A5, A6, A7, A8>

}
