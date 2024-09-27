// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
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

	override fun <A1New> mapArg1(
		transform: (A1) -> A1New
	): Args2<A1New, A2> =
		Args.of(
			a1 = transform(a1),
			a2 = a2, representation2 = representation2
		)

	override fun <A1New> mapArg1WithRepresentation(
		transform: (A1, String?) -> Pair<A1New, String?>
	): Args2<A1New, A2> =
		transform(a1, representation1).let { (newA1, newRepresentation1) ->
			Args.of(
				a1 = newA1, representation1 = newRepresentation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2
			)
		}

	override fun withArg2(value: A2, representation: String?): Args2<A1, A2> =
		this.copy(a2 = value, representation2 = representation)

	override fun <A2New> mapArg2(
		transform: (A2) -> A2New
	): Args2<A1, A2New> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = transform(a2)
		)

	override fun <A2New> mapArg2WithRepresentation(
		transform: (A2, String?) -> Pair<A2New, String?>
	): Args2<A1, A2New> =
		transform(a2, representation2).let { (newA2, newRepresentation2) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = newA2, representation2 = newRepresentation2
			)
		}


	override fun <A3> append(
		args: Args1<A3>
	): Args3<A1, A2, A3> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
	)


	override fun <A3, A4> append(
		args: Args2<A3, A4>
	): Args4<A1, A2, A3, A4> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
	)


	override fun <A3, A4, A5> append(
		args: Args3<A3, A4, A5>
	): Args5<A1, A2, A3, A4, A5> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
	)


	override fun <A3, A4, A5, A6> append(
		args: Args4<A3, A4, A5, A6>
	): Args6<A1, A2, A3, A4, A5, A6> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
		a6 = args.a4, representation6 = args.representation4,
	)


	override fun <A3, A4, A5, A6, A7> append(
		args: Args5<A3, A4, A5, A6, A7>
	): Args7<A1, A2, A3, A4, A5, A6, A7> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
		a6 = args.a4, representation6 = args.representation4,
		a7 = args.a5, representation7 = args.representation5,
	)


	override fun <A3, A4, A5, A6, A7, A8> append(
		args: Args6<A3, A4, A5, A6, A7, A8>
	): Args8<A1, A2, A3, A4, A5, A6, A7, A8> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
		a6 = args.a4, representation6 = args.representation4,
		a7 = args.a5, representation7 = args.representation5,
		a8 = args.a6, representation8 = args.representation6,
	)


	override fun <A3, A4, A5, A6, A7, A8, A9> append(
		args: Args7<A3, A4, A5, A6, A7, A8, A9>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
		a6 = args.a4, representation6 = args.representation4,
		a7 = args.a5, representation7 = args.representation5,
		a8 = args.a6, representation8 = args.representation6,
		a9 = args.a7, representation9 = args.representation7,
	)


	override fun <A3, A4, A5, A6, A7, A8, A9, A10> append(
		args: Args8<A3, A4, A5, A6, A7, A8, A9, A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = args.a1, representation3 = args.representation1,
		a4 = args.a2, representation4 = args.representation2,
		a5 = args.a3, representation5 = args.representation3,
		a6 = args.a4, representation6 = args.representation4,
		a7 = args.a5, representation7 = args.representation5,
		a8 = args.a6, representation8 = args.representation6,
		a9 = args.a7, representation9 = args.representation7,
		a10 = args.a8, representation10 = args.representation8,
	)


	override fun dropArg1(): Args1<A2> =
		Args.of(
			a1 = this.a2, representation1 = this.representation2?.let { r -> Representation(r) },
		)

	override fun dropArg2(): Args1<A1> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		)

}
