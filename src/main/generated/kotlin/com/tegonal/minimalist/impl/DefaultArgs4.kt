// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args4].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 1.0.0
 */
internal data class DefaultArgs4<A1, A2, A3, A4>(
	override val a1: A1,
	override val a2: A2,
	override val a3: A3,
	override val a4: A4,
	override val representation1: String? = null,
	override val representation2: String? = null,
	override val representation3: String? = null,
	override val representation4: String? = null,
) : Args4<A1, A2, A3, A4> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1,
		representation2?.let { Named.of(representation2, a2) } ?: a2,
		representation3?.let { Named.of(representation3, a3) } ?: a3,
		representation4?.let { Named.of(representation4, a4) } ?: a4
	)

	override fun withArg1(value: A1, representation: String?): Args4<A1, A2, A3, A4> =
		this.copy(a1 = value, representation1 = representation)

	override fun withArg2(value: A2, representation: String?): Args4<A1, A2, A3, A4> =
		this.copy(a2 = value, representation2 = representation)

	override fun withArg3(value: A3, representation: String?): Args4<A1, A2, A3, A4> =
		this.copy(a3 = value, representation3 = representation)

	override fun withArg4(value: A4, representation: String?): Args4<A1, A2, A3, A4> =
		this.copy(a4 = value, representation4 = representation)


	override fun <A5> append(
		args: Args1<A5>
	): Args5<A1, A2, A3, A4, A5> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
	)


	override fun <A5, A6> append(
		args: Args2<A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
		a6 = args.a2, representation6 = args.representation2,
	)


	override fun <A5, A6, A7> append(
		args: Args3<A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
		a6 = args.a2, representation6 = args.representation2,
		a7 = args.a3, representation7 = args.representation3,
	)


	override fun <A5, A6, A7, A8> append(
		args: Args4<A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
		a6 = args.a2, representation6 = args.representation2,
		a7 = args.a3, representation7 = args.representation3,
		a8 = args.a4, representation8 = args.representation4,
	)


	override fun <A5, A6, A7, A8, A9> append(
		args: Args5<A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
		a6 = args.a2, representation6 = args.representation2,
		a7 = args.a3, representation7 = args.representation3,
		a8 = args.a4, representation8 = args.representation4,
		a9 = args.a5, representation9 = args.representation5,
	)


	override fun <A5, A6, A7, A8, A9, A10> append(
		args: Args6<A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = args.a1, representation5 = args.representation1,
		a6 = args.a2, representation6 = args.representation2,
		a7 = args.a3, representation7 = args.representation3,
		a8 = args.a4, representation8 = args.representation4,
		a9 = args.a5, representation9 = args.representation5,
		a10 = args.a6, representation10 = args.representation6,
	)


	override fun dropArg1(): Args3<A2, A3, A4> =
		Args.of(
			a1 = this.a2, representation1 = this.representation2?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
		)

	override fun dropArg2(): Args3<A1, A3, A4> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
		)

	override fun dropArg3(): Args3<A1, A2, A4> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a4, representation3 = this.representation4,
		)

	override fun dropArg4(): Args3<A1, A2, A3> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
		)

}
