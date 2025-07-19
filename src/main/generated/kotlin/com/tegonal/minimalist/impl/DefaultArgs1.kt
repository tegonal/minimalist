// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args1].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 1.0.0
 */
internal data class DefaultArgs1<A1>(
	override val a1: A1,
	override val representation1: String? = null,
) : Args1<A1> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1
	)


	override fun <A2> append(
		args: Args1<A2>
	): Args2<A1, A2> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
	)


	override fun <A2, A3> append(
		args: Args2<A2, A3>
	): Args3<A1, A2, A3> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
	)


	override fun <A2, A3, A4> append(
		args: Args3<A2, A3, A4>
	): Args4<A1, A2, A3, A4> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
	)


	override fun <A2, A3, A4, A5> append(
		args: Args4<A2, A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
	)


	override fun <A2, A3, A4, A5, A6> append(
		args: Args5<A2, A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
		a6 = args.a5, representation6 = args.representation5,
	)


	override fun <A2, A3, A4, A5, A6, A7> append(
		args: Args6<A2, A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
		a6 = args.a5, representation6 = args.representation5,
		a7 = args.a6, representation7 = args.representation6,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8> append(
		args: Args7<A2, A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
		a6 = args.a5, representation6 = args.representation5,
		a7 = args.a6, representation7 = args.representation6,
		a8 = args.a7, representation8 = args.representation7,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8, A9> append(
		args: Args8<A2, A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
		a6 = args.a5, representation6 = args.representation5,
		a7 = args.a6, representation7 = args.representation6,
		a8 = args.a7, representation8 = args.representation7,
		a9 = args.a8, representation9 = args.representation8,
	)


	override fun <A2, A3, A4, A5, A6, A7, A8, A9, A10> append(
		args: Args9<A2, A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = args.a1, representation2 = args.representation1,
		a3 = args.a2, representation3 = args.representation2,
		a4 = args.a3, representation4 = args.representation3,
		a5 = args.a4, representation5 = args.representation4,
		a6 = args.a5, representation6 = args.representation5,
		a7 = args.a6, representation7 = args.representation6,
		a8 = args.a7, representation8 = args.representation7,
		a9 = args.a8, representation9 = args.representation8,
		a10 = args.a9, representation10 = args.representation9,
	)

}
