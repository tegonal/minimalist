// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist

/**
 * Represents an [Args] with 6 arguments.
 *
 * @since 1.0.0
 */
interface Args6<A1, A2, A3, A4, A5, A6>: Args {
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
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 1 ([Args6.a1]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 1.
	 * @param representation The new representation to use for the argument 1 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg1(value: A1, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 2 ([Args6.a2]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 2.
	 * @param representation The new representation to use for the argument 2 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg2(value: A2, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 3 ([Args6.a3]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 3.
	 * @param representation The new representation to use for the argument 3 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg3(value: A3, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 4 ([Args6.a4]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 4.
	 * @param representation The new representation to use for the argument 4 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg4(value: A4, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 5 ([Args6.a5]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 5.
	 * @param representation The new representation to use for the argument 5 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg5(value: A5, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args6] by coping `this` [Args6] but replaces
	 * the argument 6 ([Args6.a6]) with the given [value] (and its representation with the given [representation]).
	 *
	 * @param value The new value to use for argument 6.
	 * @param representation The new representation to use for the argument 6 where `null`
	 *   means let the algorithm determine a representation.
	 *
	 * @return The newly created [Args6].
	 *
	 * @since 1.0.0
	 */
	fun withArg6(value: A6, representation: String? = null): Args6<A1, A2, A3, A4, A5, A6>


	/**
	 * Creates a new [Args7] by copying `this` [Args6] and appending the given [Args1].
	 *
	 * @return The newly created [Args7].
	 *
	 * @since 1.0.0
	 */
	fun <A7> append(
		args: Args1<A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7>


	/**
	 * Creates a new [Args8] by copying `this` [Args6] and appending the given [Args2].
	 *
	 * @return The newly created [Args8].
	 *
	 * @since 1.0.0
	 */
	fun <A7, A8> append(
		args: Args2<A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8>


	/**
	 * Creates a new [Args9] by copying `this` [Args6] and appending the given [Args3].
	 *
	 * @return The newly created [Args9].
	 *
	 * @since 1.0.0
	 */
	fun <A7, A8, A9> append(
		args: Args3<A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9>


	/**
	 * Creates a new [Args10] by copying `this` [Args6] and appending the given [Args4].
	 *
	 * @return The newly created [Args10].
	 *
	 * @since 1.0.0
	 */
	fun <A7, A8, A9, A10> append(
		args: Args4<A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>


	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 1 ([Args6.a1]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg1(): Args5<A2, A3, A4, A5, A6>

	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 2 ([Args6.a2]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg2(): Args5<A1, A3, A4, A5, A6>

	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 3 ([Args6.a3]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg3(): Args5<A1, A2, A4, A5, A6>

	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 4 ([Args6.a4]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg4(): Args5<A1, A2, A3, A5, A6>

	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 5 ([Args6.a5]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg5(): Args5<A1, A2, A3, A4, A6>

	/**
	 * Creates a new [Args5] by copying `this` [Args6] but dropping its argument 6 ([Args6.a6]).
	 *
	 * @return The newly created [Args5].
	 *
	 * @since 1.0.0
	 */
	fun dropArg6(): Args5<A1, A2, A3, A4, A5>

}
