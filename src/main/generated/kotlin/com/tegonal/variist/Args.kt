// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.variist

import org.junit.jupiter.params.provider.Arguments
import com.tegonal.variist.impl.*

/**
 * Represents the top-interface of arguments-representations such as [Args1], [Args2] and so on.
 *
 * @since 1.0.0
 */
interface Args : Arguments {
	/**
	 * Extension point for Args (next to [Args.of] functions).
	 */
	companion object {
		/**
		 * Creates an [Args1] based on the given arguments [a1]
		 * and optionally [representation1].
		 *
		 * @param a1 the value for argument 1.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1> of(
			a1: A1,
			representation1: Representation? = null
		): Args1<A1> = DefaultArgs1(
			a1,
			representation1?.text,
		)
		/**
		 * Creates an [Args2] based on the given arguments [a1] and [a2]
		 * and optionally [representation1] and [representation2].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2> of(
			a1: A1,
			a2: A2,
			representation1: Representation? = null,
			representation2: String? = null
		): Args2<A1, A2> = DefaultArgs2(
			a1,
			a2,
			representation1?.text,
			representation2,
		)
		/**
		 * Creates an [Args3] based on the given arguments [a1], [a2] and [a3]
		 * and optionally [representation1], [representation2] and [representation3].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3> of(
			a1: A1,
			a2: A2,
			a3: A3,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null
		): Args3<A1, A2, A3> = DefaultArgs3(
			a1,
			a2,
			a3,
			representation1?.text,
			representation2,
			representation3,
		)
		/**
		 * Creates an [Args4] based on the given arguments [a1], [a2], [a3] and [a4]
		 * and optionally [representation1], [representation2], [representation3] and [representation4].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null
		): Args4<A1, A2, A3, A4> = DefaultArgs4(
			a1,
			a2,
			a3,
			a4,
			representation1?.text,
			representation2,
			representation3,
			representation4,
		)
		/**
		 * Creates an [Args5] based on the given arguments [a1], [a2], [a3], [a4] and [a5]
		 * and optionally [representation1], [representation2], [representation3], [representation4] and [representation5].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null
		): Args5<A1, A2, A3, A4, A5> = DefaultArgs5(
			a1,
			a2,
			a3,
			a4,
			a5,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
		)
		/**
		 * Creates an [Args6] based on the given arguments [a1], [a2], [a3], [a4], [a5] and [a6]
		 * and optionally [representation1], [representation2], [representation3], [representation4], [representation5] and [representation6].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param a6 the value for argument 6.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 * @param representation6 the representation of argument 6 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5, A6> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			a6: A6,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null,
			representation6: String? = null
		): Args6<A1, A2, A3, A4, A5, A6> = DefaultArgs6(
			a1,
			a2,
			a3,
			a4,
			a5,
			a6,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
			representation6,
		)
		/**
		 * Creates an [Args7] based on the given arguments [a1], [a2], [a3], [a4], [a5], [a6] and [a7]
		 * and optionally [representation1], [representation2], [representation3], [representation4], [representation5], [representation6] and [representation7].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param a6 the value for argument 6.
		 * @param a7 the value for argument 7.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 * @param representation6 the representation of argument 6 where `null` means no custom representation.
		 * @param representation7 the representation of argument 7 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5, A6, A7> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			a6: A6,
			a7: A7,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null,
			representation6: String? = null,
			representation7: String? = null
		): Args7<A1, A2, A3, A4, A5, A6, A7> = DefaultArgs7(
			a1,
			a2,
			a3,
			a4,
			a5,
			a6,
			a7,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
			representation6,
			representation7,
		)
		/**
		 * Creates an [Args8] based on the given arguments [a1], [a2], [a3], [a4], [a5], [a6], [a7] and [a8]
		 * and optionally [representation1], [representation2], [representation3], [representation4], [representation5], [representation6], [representation7] and [representation8].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param a6 the value for argument 6.
		 * @param a7 the value for argument 7.
		 * @param a8 the value for argument 8.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 * @param representation6 the representation of argument 6 where `null` means no custom representation.
		 * @param representation7 the representation of argument 7 where `null` means no custom representation.
		 * @param representation8 the representation of argument 8 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5, A6, A7, A8> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			a6: A6,
			a7: A7,
			a8: A8,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null,
			representation6: String? = null,
			representation7: String? = null,
			representation8: String? = null
		): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = DefaultArgs8(
			a1,
			a2,
			a3,
			a4,
			a5,
			a6,
			a7,
			a8,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
			representation6,
			representation7,
			representation8,
		)
		/**
		 * Creates an [Args9] based on the given arguments [a1], [a2], [a3], [a4], [a5], [a6], [a7], [a8] and [a9]
		 * and optionally [representation1], [representation2], [representation3], [representation4], [representation5], [representation6], [representation7], [representation8] and [representation9].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param a6 the value for argument 6.
		 * @param a7 the value for argument 7.
		 * @param a8 the value for argument 8.
		 * @param a9 the value for argument 9.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 * @param representation6 the representation of argument 6 where `null` means no custom representation.
		 * @param representation7 the representation of argument 7 where `null` means no custom representation.
		 * @param representation8 the representation of argument 8 where `null` means no custom representation.
		 * @param representation9 the representation of argument 9 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5, A6, A7, A8, A9> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			a6: A6,
			a7: A7,
			a8: A8,
			a9: A9,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null,
			representation6: String? = null,
			representation7: String? = null,
			representation8: String? = null,
			representation9: String? = null
		): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = DefaultArgs9(
			a1,
			a2,
			a3,
			a4,
			a5,
			a6,
			a7,
			a8,
			a9,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
			representation6,
			representation7,
			representation8,
			representation9,
		)
		/**
		 * Creates an [Args10] based on the given arguments [a1], [a2], [a3], [a4], [a5], [a6], [a7], [a8], [a9] and [a10]
		 * and optionally [representation1], [representation2], [representation3], [representation4], [representation5], [representation6], [representation7], [representation8], [representation9] and [representation10].
		 *
		 * @param a1 the value for argument 1.
		 * @param a2 the value for argument 2.
		 * @param a3 the value for argument 3.
		 * @param a4 the value for argument 4.
		 * @param a5 the value for argument 5.
		 * @param a6 the value for argument 6.
		 * @param a7 the value for argument 7.
		 * @param a8 the value for argument 8.
		 * @param a9 the value for argument 9.
		 * @param a10 the value for argument 10.
		 * @param representation1 the representation of argument 1 where `null` means no custom representation.
		 * @param representation2 the representation of argument 2 where `null` means no custom representation.
		 * @param representation3 the representation of argument 3 where `null` means no custom representation.
		 * @param representation4 the representation of argument 4 where `null` means no custom representation.
		 * @param representation5 the representation of argument 5 where `null` means no custom representation.
		 * @param representation6 the representation of argument 6 where `null` means no custom representation.
		 * @param representation7 the representation of argument 7 where `null` means no custom representation.
		 * @param representation8 the representation of argument 8 where `null` means no custom representation.
		 * @param representation9 the representation of argument 9 where `null` means no custom representation.
		 * @param representation10 the representation of argument 10 where `null` means no custom representation.
		 *
		 * @since 2.0.0
		 */
		@JvmStatic
		@JvmOverloads
		fun <A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> of(
			a1: A1,
			a2: A2,
			a3: A3,
			a4: A4,
			a5: A5,
			a6: A6,
			a7: A7,
			a8: A8,
			a9: A9,
			a10: A10,
			representation1: Representation? = null,
			representation2: String? = null,
			representation3: String? = null,
			representation4: String? = null,
			representation5: String? = null,
			representation6: String? = null,
			representation7: String? = null,
			representation8: String? = null,
			representation9: String? = null,
			representation10: String? = null
		): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = DefaultArgs10(
			a1,
			a2,
			a3,
			a4,
			a5,
			a6,
			a7,
			a8,
			a9,
			a10,
			representation1?.text,
			representation2,
			representation3,
			representation4,
			representation5,
			representation6,
			representation7,
			representation8,
			representation9,
			representation10,
		)
  }
}
