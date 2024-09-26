// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args9].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 1.0.0
 */
internal data class DefaultArgs9<A1, A2, A3, A4, A5, A6, A7, A8, A9>(
	override val a1: A1,
	override val a2: A2,
	override val a3: A3,
	override val a4: A4,
	override val a5: A5,
	override val a6: A6,
	override val a7: A7,
	override val a8: A8,
	override val a9: A9,
	override val representation1: String? = null,
	override val representation2: String? = null,
	override val representation3: String? = null,
	override val representation4: String? = null,
	override val representation5: String? = null,
	override val representation6: String? = null,
	override val representation7: String? = null,
	override val representation8: String? = null,
	override val representation9: String? = null,
) : Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1,
		representation2?.let { Named.of(representation2, a2) } ?: a2,
		representation3?.let { Named.of(representation3, a3) } ?: a3,
		representation4?.let { Named.of(representation4, a4) } ?: a4,
		representation5?.let { Named.of(representation5, a5) } ?: a5,
		representation6?.let { Named.of(representation6, a6) } ?: a6,
		representation7?.let { Named.of(representation7, a7) } ?: a7,
		representation8?.let { Named.of(representation8, a8) } ?: a8,
		representation9?.let { Named.of(representation9, a9) } ?: a9
	)

	override fun withArg1(value: A1, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a1 = value, representation1 = representation)

	override fun <A1New> mapArg1(
		transform: (A1) -> A1New
	): Args9<A1New, A2, A3, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = transform(a1),
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A1New> mapArg1WithRepresentation(
		transform: (A1, String?) -> Pair<A1New, String?>
	): Args9<A1New, A2, A3, A4, A5, A6, A7, A8, A9> =
		transform(a1, representation1).let { (newA1, newRepresentation1) ->
			Args.of(
				a1 = newA1, representation1 = newRepresentation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg2(value: A2, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a2 = value, representation2 = representation)

	override fun <A2New> mapArg2(
		transform: (A2) -> A2New
	): Args9<A1, A2New, A3, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = transform(a2),
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A2New> mapArg2WithRepresentation(
		transform: (A2, String?) -> Pair<A2New, String?>
	): Args9<A1, A2New, A3, A4, A5, A6, A7, A8, A9> =
		transform(a2, representation2).let { (newA2, newRepresentation2) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = newA2, representation2 = newRepresentation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg3(value: A3, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a3 = value, representation3 = representation)

	override fun <A3New> mapArg3(
		transform: (A3) -> A3New
	): Args9<A1, A2, A3New, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = transform(a3),
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A3New> mapArg3WithRepresentation(
		transform: (A3, String?) -> Pair<A3New, String?>
	): Args9<A1, A2, A3New, A4, A5, A6, A7, A8, A9> =
		transform(a3, representation3).let { (newA3, newRepresentation3) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = newA3, representation3 = newRepresentation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg4(value: A4, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a4 = value, representation4 = representation)

	override fun <A4New> mapArg4(
		transform: (A4) -> A4New
	): Args9<A1, A2, A3, A4New, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = transform(a4),
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A4New> mapArg4WithRepresentation(
		transform: (A4, String?) -> Pair<A4New, String?>
	): Args9<A1, A2, A3, A4New, A5, A6, A7, A8, A9> =
		transform(a4, representation4).let { (newA4, newRepresentation4) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = newA4, representation4 = newRepresentation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg5(value: A5, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a5 = value, representation5 = representation)

	override fun <A5New> mapArg5(
		transform: (A5) -> A5New
	): Args9<A1, A2, A3, A4, A5New, A6, A7, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = transform(a5),
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A5New> mapArg5WithRepresentation(
		transform: (A5, String?) -> Pair<A5New, String?>
	): Args9<A1, A2, A3, A4, A5New, A6, A7, A8, A9> =
		transform(a5, representation5).let { (newA5, newRepresentation5) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = newA5, representation5 = newRepresentation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg6(value: A6, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a6 = value, representation6 = representation)

	override fun <A6New> mapArg6(
		transform: (A6) -> A6New
	): Args9<A1, A2, A3, A4, A5, A6New, A7, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = transform(a6),
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A6New> mapArg6WithRepresentation(
		transform: (A6, String?) -> Pair<A6New, String?>
	): Args9<A1, A2, A3, A4, A5, A6New, A7, A8, A9> =
		transform(a6, representation6).let { (newA6, newRepresentation6) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = newA6, representation6 = newRepresentation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg7(value: A7, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a7 = value, representation7 = representation)

	override fun <A7New> mapArg7(
		transform: (A7) -> A7New
	): Args9<A1, A2, A3, A4, A5, A6, A7New, A8, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = transform(a7),
			a8 = a8, representation8 = representation8,
			a9 = a9, representation9 = representation9
		)

	override fun <A7New> mapArg7WithRepresentation(
		transform: (A7, String?) -> Pair<A7New, String?>
	): Args9<A1, A2, A3, A4, A5, A6, A7New, A8, A9> =
		transform(a7, representation7).let { (newA7, newRepresentation7) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = newA7, representation7 = newRepresentation7,
				a8 = a8, representation8 = representation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg8(value: A8, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a8 = value, representation8 = representation)

	override fun <A8New> mapArg8(
		transform: (A8) -> A8New
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8New, A9> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = transform(a8),
			a9 = a9, representation9 = representation9
		)

	override fun <A8New> mapArg8WithRepresentation(
		transform: (A8, String?) -> Pair<A8New, String?>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8New, A9> =
		transform(a8, representation8).let { (newA8, newRepresentation8) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = newA8, representation8 = newRepresentation8,
				a9 = a9, representation9 = representation9
			)
		}

	override fun withArg9(value: A9, representation: String?): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		this.copy(a9 = value, representation9 = representation)

	override fun <A9New> mapArg9(
		transform: (A9) -> A9New
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9New> =
		Args.of(
			a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
			a2 = a2, representation2 = representation2,
			a3 = a3, representation3 = representation3,
			a4 = a4, representation4 = representation4,
			a5 = a5, representation5 = representation5,
			a6 = a6, representation6 = representation6,
			a7 = a7, representation7 = representation7,
			a8 = a8, representation8 = representation8,
			a9 = transform(a9)
		)

	override fun <A9New> mapArg9WithRepresentation(
		transform: (A9, String?) -> Pair<A9New, String?>
	): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9New> =
		transform(a9, representation9).let { (newA9, newRepresentation9) ->
			Args.of(
				a1 = a1, representation1 = representation1?.let { r -> Representation(r) },
				a2 = a2, representation2 = representation2,
				a3 = a3, representation3 = representation3,
				a4 = a4, representation4 = representation4,
				a5 = a5, representation5 = representation5,
				a6 = a6, representation6 = representation6,
				a7 = a7, representation7 = representation7,
				a8 = a8, representation8 = representation8,
				a9 = newA9, representation9 = newRepresentation9
			)
		}


	override fun <A10> append(
		args: Args1<A10>
	): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> = Args.of(
		a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
		a2 = this.a2, representation2 = this.representation2,
		a3 = this.a3, representation3 = this.representation3,
		a4 = this.a4, representation4 = this.representation4,
		a5 = this.a5, representation5 = this.representation5,
		a6 = this.a6, representation6 = this.representation6,
		a7 = this.a7, representation7 = this.representation7,
		a8 = this.a8, representation8 = this.representation8,
		a9 = this.a9, representation9 = this.representation9,
		a10 = args.a1, representation10 = args.representation1,
	)


	override fun dropArg1(): Args8<A2, A3, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a2, representation1 = this.representation2?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg2(): Args8<A1, A3, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg3(): Args8<A1, A2, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg4(): Args8<A1, A2, A3, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg5(): Args8<A1, A2, A3, A4, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg6(): Args8<A1, A2, A3, A4, A5, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg7(): Args8<A1, A2, A3, A4, A5, A6, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg8(): Args8<A1, A2, A3, A4, A5, A6, A7, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a7, representation7 = this.representation7,
			a8 = this.a9, representation8 = this.representation9,
		)

	override fun dropArg9(): Args8<A1, A2, A3, A4, A5, A6, A7, A8> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a7, representation7 = this.representation7,
			a8 = this.a8, representation8 = this.representation8,
		)

}
