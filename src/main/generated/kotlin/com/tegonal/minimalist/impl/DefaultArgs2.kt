// -----------------------------------------------------------------------
// automatically generated, don't modify here but in build.gradle.kts
// -----------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args2].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 1.0.0
 */
internal data class DefaultArgs2<A1, A2>(
	override val a1: A1,
	override val a2: A2,
	override val representation1: String? = null,
	override val representation2: String? = null,
) : Args2<A1, A2> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1,
		representation2?.let { Named.of(representation2, a2) } ?: a2
	)

	override fun withArg1(value: A1, representation: String?): Args2<A1, A2> =
		this.copy(a1 = value, representation1 = representation)

	override fun withArg2(value: A2, representation: String?): Args2<A1, A2> =
		this.copy(a2 = value, representation2 = representation)


	override fun <A3> append(
		arg1: Args1<A3>
	): Args3<A1, A2, A3> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg1.a1,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg1.representation1,
	)


	override fun <A3, A4> append(
		arg2: Args2<A3, A4>
	): Args4<A1, A2, A3, A4> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg2.a1,
		a4 = arg2.a2,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg2.representation1,
		representation4 = arg2.representation2,
	)


	override fun <A3, A4, A5> append(
		arg3: Args3<A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg3.a1,
		a4 = arg3.a2,
		a5 = arg3.a3,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg3.representation1,
		representation4 = arg3.representation2,
		representation5 = arg3.representation3,
	)


	override fun <A3, A4, A5, A6> append(
		arg4: Args4<A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg4.a1,
		a4 = arg4.a2,
		a5 = arg4.a3,
		a6 = arg4.a4,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg4.representation1,
		representation4 = arg4.representation2,
		representation5 = arg4.representation3,
		representation6 = arg4.representation4,
	)


	override fun <A3, A4, A5, A6, A7> append(
		arg5: Args5<A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg5.a1,
		a4 = arg5.a2,
		a5 = arg5.a3,
		a6 = arg5.a4,
		a7 = arg5.a5,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg5.representation1,
		representation4 = arg5.representation2,
		representation5 = arg5.representation3,
		representation6 = arg5.representation4,
		representation7 = arg5.representation5,
	)


	override fun <A3, A4, A5, A6, A7, A8> append(
		arg6: Args6<A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg6.a1,
		a4 = arg6.a2,
		a5 = arg6.a3,
		a6 = arg6.a4,
		a7 = arg6.a5,
		a8 = arg6.a6,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg6.representation1,
		representation4 = arg6.representation2,
		representation5 = arg6.representation3,
		representation6 = arg6.representation4,
		representation7 = arg6.representation5,
		representation8 = arg6.representation6,
	)


	override fun <A3, A4, A5, A6, A7, A8, A9> append(
		arg7: Args7<A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg7.a1,
		a4 = arg7.a2,
		a5 = arg7.a3,
		a6 = arg7.a4,
		a7 = arg7.a5,
		a8 = arg7.a6,
		a9 = arg7.a7,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg7.representation1,
		representation4 = arg7.representation2,
		representation5 = arg7.representation3,
		representation6 = arg7.representation4,
		representation7 = arg7.representation5,
		representation8 = arg7.representation6,
		representation9 = arg7.representation7,
	)


	override fun <A3, A4, A5, A6, A7, A8, A9, A10> append(
		arg8: Args8<A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1,
		a2 = this.a2,
		a3 = arg8.a1,
		a4 = arg8.a2,
		a5 = arg8.a3,
		a6 = arg8.a4,
		a7 = arg8.a5,
		a8 = arg8.a6,
		a9 = arg8.a7,
		a10 = arg8.a8,
		representation1 = this.representation1,
		representation2 = this.representation2,
		representation3 = arg8.representation1,
		representation4 = arg8.representation2,
		representation5 = arg8.representation3,
		representation6 = arg8.representation4,
		representation7 = arg8.representation5,
		representation8 = arg8.representation6,
		representation9 = arg8.representation7,
		representation10 = arg8.representation8,
	)


	override fun dropArg1() = Args.of(
		a1 = this.a2,
		representation1 = this.representation2
	)

	override fun dropArg2() = Args.of(
		a1 = this.a1,
		representation1 = this.representation1
	)

}
