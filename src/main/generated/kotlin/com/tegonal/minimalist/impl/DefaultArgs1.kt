// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args1].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 0.1.0
 */
internal data class DefaultArgs1<A1>(
	override val a1: A1,
	override val representation1: String? = null,
) : Args1<A1> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1
	)

	override fun withArg1(value: A1, representation: String?): Args1<A1> =
		this.copy(a1 = value, representation1 = representation)


	override fun <A2> append(
		arg1: Args1<A2>
	): Args2<A1, A2> = Args.of(
		a1 = this.a1,
		a2 = arg1.a1,
		representation1 = this.representation1,
		representation2 = arg1.representation1,
	)


	override fun <A2, A3> append(
		arg2: Args2<A2, A3>
	): Args3<A1, A2, A3> = Args.of(
		a1 = this.a1,
		a2 = arg2.a1,
		a3 = arg2.a2,
		representation1 = this.representation1,
		representation2 = arg2.representation1,
		representation3 = arg2.representation2,
	)


	override fun <A2, A3, A4> append(
		arg3: Args3<A2, A3, A4>
	): Args4<A1, A2, A3, A4> = Args.of(
		a1 = this.a1,
		a2 = arg3.a1,
		a3 = arg3.a2,
		a4 = arg3.a3,
		representation1 = this.representation1,
		representation2 = arg3.representation1,
		representation3 = arg3.representation2,
		representation4 = arg3.representation3,
	)


	override fun <A2, A3, A4, A5> append(
		arg4: Args4<A2, A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5> = Args.of(
		a1 = this.a1,
		a2 = arg4.a1,
		a3 = arg4.a2,
		a4 = arg4.a3,
		a5 = arg4.a4,
		representation1 = this.representation1,
		representation2 = arg4.representation1,
		representation3 = arg4.representation2,
		representation4 = arg4.representation3,
		representation5 = arg4.representation4,
	)


	override fun <A2, A3, A4, A5, A6> append(
		arg5: Args5<A2, A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6> = Args.of(
		a1 = this.a1,
		a2 = arg5.a1,
		a3 = arg5.a2,
		a4 = arg5.a3,
		a5 = arg5.a4,
		a6 = arg5.a5,
		representation1 = this.representation1,
		representation2 = arg5.representation1,
		representation3 = arg5.representation2,
		representation4 = arg5.representation3,
		representation5 = arg5.representation4,
		representation6 = arg5.representation5,
	)


	override fun <A2, A3, A4, A5, A6, A7> append(
		arg6: Args6<A2, A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7> = Args.of(
		a1 = this.a1,
		a2 = arg6.a1,
		a3 = arg6.a2,
		a4 = arg6.a3,
		a5 = arg6.a4,
		a6 = arg6.a5,
		a7 = arg6.a6,
		representation1 = this.representation1,
		representation2 = arg6.representation1,
		representation3 = arg6.representation2,
		representation4 = arg6.representation3,
		representation5 = arg6.representation4,
		representation6 = arg6.representation5,
		representation7 = arg6.representation6,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8> append(
		arg7: Args7<A2, A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = Args.of(
		a1 = this.a1,
		a2 = arg7.a1,
		a3 = arg7.a2,
		a4 = arg7.a3,
		a5 = arg7.a4,
		a6 = arg7.a5,
		a7 = arg7.a6,
		a8 = arg7.a7,
		representation1 = this.representation1,
		representation2 = arg7.representation1,
		representation3 = arg7.representation2,
		representation4 = arg7.representation3,
		representation5 = arg7.representation4,
		representation6 = arg7.representation5,
		representation7 = arg7.representation6,
		representation8 = arg7.representation7,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8, A9> append(
		arg8: Args8<A2, A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = Args.of(
		a1 = this.a1,
		a2 = arg8.a1,
		a3 = arg8.a2,
		a4 = arg8.a3,
		a5 = arg8.a4,
		a6 = arg8.a5,
		a7 = arg8.a6,
		a8 = arg8.a7,
		a9 = arg8.a8,
		representation1 = this.representation1,
		representation2 = arg8.representation1,
		representation3 = arg8.representation2,
		representation4 = arg8.representation3,
		representation5 = arg8.representation4,
		representation6 = arg8.representation5,
		representation7 = arg8.representation6,
		representation8 = arg8.representation7,
		representation9 = arg8.representation8,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8, A9, A10> append(
		arg9: Args9<A2, A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1,
		a2 = arg9.a1,
		a3 = arg9.a2,
		a4 = arg9.a3,
		a5 = arg9.a4,
		a6 = arg9.a5,
		a7 = arg9.a6,
		a8 = arg9.a7,
		a9 = arg9.a8,
		a10 = arg9.a9,
		representation1 = this.representation1,
		representation2 = arg9.representation1,
		representation3 = arg9.representation2,
		representation4 = arg9.representation3,
		representation5 = arg9.representation4,
		representation6 = arg9.representation5,
		representation7 = arg9.representation6,
		representation8 = arg9.representation7,
		representation9 = arg9.representation8,
		representation10 = arg9.representation9,
	)

}
