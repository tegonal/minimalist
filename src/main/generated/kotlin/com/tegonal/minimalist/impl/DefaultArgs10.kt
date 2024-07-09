// --------------------------------------------------------------------------------------------------------------------
// automatically generated, don't modify here but in:
// gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts
// --------------------------------------------------------------------------------------------------------------------
package com.tegonal.minimalist.impl

import com.tegonal.minimalist.*

import org.junit.jupiter.api.Named

/**
 * A simple data class based implementation of an [Args10].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 1.0.0
 */
internal data class DefaultArgs10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10>(
	override val a1: A1,
	override val a2: A2,
	override val a3: A3,
	override val a4: A4,
	override val a5: A5,
	override val a6: A6,
	override val a7: A7,
	override val a8: A8,
	override val a9: A9,
	override val a10: A10,
	override val representation1: String? = null,
	override val representation2: String? = null,
	override val representation3: String? = null,
	override val representation4: String? = null,
	override val representation5: String? = null,
	override val representation6: String? = null,
	override val representation7: String? = null,
	override val representation8: String? = null,
	override val representation9: String? = null,
	override val representation10: String? = null,
) : Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> {

	override fun get(): Array<out Any?> = arrayOf(
		representation1?.let { Named.of(representation1, a1) } ?: a1,
		representation2?.let { Named.of(representation2, a2) } ?: a2,
		representation3?.let { Named.of(representation3, a3) } ?: a3,
		representation4?.let { Named.of(representation4, a4) } ?: a4,
		representation5?.let { Named.of(representation5, a5) } ?: a5,
		representation6?.let { Named.of(representation6, a6) } ?: a6,
		representation7?.let { Named.of(representation7, a7) } ?: a7,
		representation8?.let { Named.of(representation8, a8) } ?: a8,
		representation9?.let { Named.of(representation9, a9) } ?: a9,
		representation10?.let { Named.of(representation10, a10) } ?: a10
	)

	override fun withArg1(value: A1, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a1 = value, representation1 = representation)

	override fun withArg2(value: A2, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a2 = value, representation2 = representation)

	override fun withArg3(value: A3, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a3 = value, representation3 = representation)

	override fun withArg4(value: A4, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a4 = value, representation4 = representation)

	override fun withArg5(value: A5, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a5 = value, representation5 = representation)

	override fun withArg6(value: A6, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a6 = value, representation6 = representation)

	override fun withArg7(value: A7, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a7 = value, representation7 = representation)

	override fun withArg8(value: A8, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a8 = value, representation8 = representation)

	override fun withArg9(value: A9, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a9 = value, representation9 = representation)

	override fun withArg10(value: A10, representation: String?): Args10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		this.copy(a10 = value, representation10 = representation)


	override fun dropArg1(): Args9<A2, A3, A4, A5, A6, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a2, representation1 = this.representation2?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg2(): Args9<A1, A3, A4, A5, A6, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a3, representation2 = this.representation3,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg3(): Args9<A1, A2, A4, A5, A6, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a4, representation3 = this.representation4,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg4(): Args9<A1, A2, A3, A5, A6, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a5, representation4 = this.representation5,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg5(): Args9<A1, A2, A3, A4, A6, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a6, representation5 = this.representation6,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg6(): Args9<A1, A2, A3, A4, A5, A7, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a7, representation6 = this.representation7,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg7(): Args9<A1, A2, A3, A4, A5, A6, A8, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a8, representation7 = this.representation8,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg8(): Args9<A1, A2, A3, A4, A5, A6, A7, A9, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a7, representation7 = this.representation7,
			a8 = this.a9, representation8 = this.representation9,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg9(): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A10> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a7, representation7 = this.representation7,
			a8 = this.a8, representation8 = this.representation8,
			a9 = this.a10, representation9 = this.representation10,
		)

	override fun dropArg10(): Args9<A1, A2, A3, A4, A5, A6, A7, A8, A9> =
		Args.of(
			a1 = this.a1, representation1 = this.representation1?.let { r -> Representation(r) },
			a2 = this.a2, representation2 = this.representation2,
			a3 = this.a3, representation3 = this.representation3,
			a4 = this.a4, representation4 = this.representation4,
			a5 = this.a5, representation5 = this.representation5,
			a6 = this.a6, representation6 = this.representation6,
			a7 = this.a7, representation7 = this.representation7,
			a8 = this.a8, representation8 = this.representation8,
			a9 = this.a9, representation9 = this.representation9,
		)

}
