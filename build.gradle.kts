import ch.tutteli.kbox.joinToString as joinToStringAndLast

buildscript {
	dependencies {
		classpath("ch.tutteli.kbox:kbox:1.0.0")
	}
}

plugins {
	id("build-logic.published-kotlin-jvm")
}

version = "1.0.0-SNAPSHOT"
group = "com.tegonal.minimalist"
description = "Library which helps to setup and prioritise parameterized tests"


dependencies {
	api(libs.junit.jupiter.params)

	testImplementation(kotlin("test"))
	testImplementation(libs.atrium.fluent)
}

val generationFolder: ConfigurableFileCollection = project.files("src/main/generated/kotlin")
val generationTestFolder: ConfigurableFileCollection = project.files("src/test/generated/kotlin")

kotlin {
	sourceSets {
		main {
			kotlin.srcDir(generationFolder)
		}
		test {
			kotlin.srcDir(generationTestFolder)
		}
	}
}

val packageName = "com.tegonal.minimalist"
val packageNameAsPath = packageName.replace('.', '/')


fun dontModifyNotice(place: String) =
	"""
		|// -----------------------------------------------------------------------
		|// automatically generated, don't modify here but in $place
		|// -----------------------------------------------------------------------
		|
	""".trimMargin()

val dontModifyNotice = dontModifyNotice("build.gradle.kts")

fun createStringBuilder(packageName: String) = StringBuilder(dontModifyNotice)
	.append("package ").append(packageName).append("\n\n")

val numOfArgs = 10

val generate: TaskProvider<Task> = tasks.register("generate") {
	doFirst {
		val packageDir = File(generationFolder.asPath + "/" + packageNameAsPath)

		val argsOf = createStringBuilder(packageName)
			.append("import com.tegonal.minimalist.impl.*\n\n")

		StringBuilder(dontModifyNotice)
			.append("package ").append(packageName).append("\n\n")

		(1..numOfArgs).forEach { upperNumber ->
			val numbers = (1..upperNumber).toList()
			val typeArgs = numbers.joinToString(", ") { "A$it" }
			val constructorProperties = numbers.joinToString(",\n\t") { "override val a$it: A$it" }
			val parameters = numbers.joinToString(",\n\t") { "a$it: A$it" }
			val args = numbers.joinToString(",\n\t") { "a$it" }
			val representationConstructorProperties =
				numbers.joinToString(",\n\t") { "override val representation$it: String? = null" }
			val representationParameters = numbers.joinToString(",\n\t") { "representation$it: String? = null" }
			val representationArgs = numbers.joinToString(",\n\t") { "representation$it" }

			val argsInterfaces = createStringBuilder(packageName)
			argsInterfaces.append(
				"""
				|/**
				| * Represents an [Args] with $upperNumber argument${if (upperNumber > 1) "s" else ""}.
				| *
				| * @since 0.1.0
				| */
				|interface Args$upperNumber<$typeArgs>: Args {
				|
				""".trimMargin()
			)
			numbers.forEach {
				argsInterfaces.append(
					"""
					|	/**
					|	 * The value of argument $it.
					|	 *
					|	 * @since 0.1.0
					|	 */
					|	val a$it: A$it
					""".trimMargin()
				).appendLine()
			}
			argsInterfaces.appendLine()
			numbers.forEach {
				argsInterfaces.append(
					"""
					|	/**
					|	 * The representation of argument $it.
					|	 *
					|	 * @since 0.1.0
					|	 */
					|	val representation$it: String?
					""".trimMargin()
				).appendLine()
			}

			val defaultArgs = createStringBuilder("$packageName.impl")
				.append("import com.tegonal.minimalist.*\n\n")
				.append("import org.junit.jupiter.api.Named\n\n")

			defaultArgs.append(
				"""
				|/**
				| * A simple data class based implementation of an [Args$upperNumber].
				| *
				| * !! No backward compatibility guarantees !!
                | * Re-use on own risk
				| *
				| * @since 0.1.0
				| */
				|internal data class DefaultArgs$upperNumber<$typeArgs>(
			    |	$constructorProperties,
			    |	$representationConstructorProperties,
			    |) : Args$upperNumber<$typeArgs> {
				|
				|	override fun get(): Array<out Any?> = arrayOf(
				|		${
					numbers.joinToString(",\n\t\t") {
						"representation$it?.let { Named.of(representation$it, a$it) } ?: a$it"
					}
				}
				|	)
			    |
				""".trimMargin()
			)

			argsInterfaces.appendLine()
			defaultArgs.appendLine()

			numbers.forEach { index ->
				argsInterfaces.append(
					"""
					|	/**
					|	 * Creates a new [Args$upperNumber] by coping `this` [Args$upperNumber] but replaces
					|	 * the argument $index ([Args$upperNumber.a$index]) with the given [value] (and its representation with the given [representation]).
					|	 *
					|	 * @param value the new value to use for argument $index.
					|	 * @param representation the new representation to use for the argument $index where `null`
					|	 *         means let the algorithm determine a representation.
					|	 *
					|	 * @return The newly created [Args$upperNumber].
					|	 *
					|	 * @since 0.1.0
					|	 */
					|	fun withArg$index(value: A$index, representation: String? = null): Args$upperNumber<$typeArgs>
					|
				""".trimMargin()
				).appendLine()

				defaultArgs.append(
					"""
					|	override fun withArg$index(value: A$index, representation: String?): Args$upperNumber<$typeArgs> =
					|		this.copy(a$index = value, representation$index = representation)
					|
					""".trimMargin()
				).appendLine()
			}


			(1..numOfArgs - upperNumber).forEach { upperNumber2 ->
				val upperNumber3 = upperNumber + upperNumber2
				val numbers2 = (1..upperNumber2)
				val numbers3 = (1..upperNumber3)
				val typeArgs2 = numbers2.joinToString(", ") { "A${it + upperNumber}" }
				val typeArgs3 = numbers3.joinToString(", ") { "A$it" }

				argsInterfaces.appendLine()
				defaultArgs.appendLine()

				argsInterfaces.append(
					"""
					|	/**
					|	 * Creates a new [Args$upperNumber3] by copying `this` [Args$upperNumber] and appending the given [arg${upperNumber2}].
					|	 *
					|	 * @return The newly created [Args$upperNumber3].
					|	 *
					|	 * @since 0.1.0
					|	 */
					|	fun <$typeArgs2> append(
					|		arg${upperNumber2}: Args${upperNumber2}<$typeArgs2>
					|	): Args$upperNumber3<$typeArgs3>
					|
					""".trimMargin()
				).appendLine()

				defaultArgs.append(
					"""
					|	override fun <$typeArgs2> append(
					|		arg${upperNumber2}: Args${upperNumber2}<$typeArgs2>
					|	): Args$upperNumber3<$typeArgs3> = Args.of(
					|		${numbers.joinToString(",\n\t\t") { "a$it = this.a$it" }},
					|		${numbers2.joinToString(",\n\t\t") { "a${it + upperNumber} = arg${upperNumber2}.a$it" }},
					|		${numbers.joinToString(",\n\t\t") { "representation$it = this.representation$it" }},
					|		${numbers2.joinToString(",\n\t\t") { "representation${it + upperNumber} = arg${upperNumber2}.representation$it" }},
					|	)
					|
					""".trimMargin()
				).appendLine()
			}

			if (upperNumber > 1) {


				argsInterfaces.appendLine()
				defaultArgs.appendLine()

				val upperNumber4 = upperNumber - 1
				numbers.forEach { index ->
					val numbers4 = numbers.toMutableList()
					numbers4.remove(index)

					val numbers4WithIndex = numbers4.mapIndexed { i, it -> i + 1 to it }
					val typeArgs4 = numbers4.joinToString(", ") { "A${it}" }

					argsInterfaces.append(
						"""
						|	/**
						|	 * Creates a new [Args${upperNumber4}] by copying `this` [Args$upperNumber] but dropping its argument $index ([Args$upperNumber.a$index]).
						|	 *
						|	 * @return The newly created [Args$upperNumber4].
						|	 *
						|	 * @since 0.1.0
						|	 */
						|	fun dropArg$index(): Args${upperNumber4}<$typeArgs4>
						|
						""".trimMargin()
					).appendLine()

					defaultArgs.append(
						"""
						|	override fun dropArg$index() = Args.of(
						|		${numbers4WithIndex.joinToString(",\n\t\t") { (i, it) -> "a$i = this.a$it" }},
						|		${numbers4WithIndex.joinToString(",\n\t\t") { (i, it) -> "representation$i = this.representation$it" }}
						|	)
						|
						""".trimMargin()
					).appendLine()
				}
			}

			argsInterfaces.append("}\n")
			val argsFile = packageDir.resolve("Args$upperNumber.kt")
			argsFile.writeText(argsInterfaces.toString())

			defaultArgs.append("}\n")
			val defaultArgsFile = packageDir.resolve("impl/DefaultArgs$upperNumber.kt")
			defaultArgsFile.writeText(defaultArgs.toString())

			argsOf.append(
				"""
				|/**
				| * Creates an Args$upperNumber based on the given arguments ${
					numbers.joinToStringAndLast(
						", ",
						lastSeparator = " and "
					) { it, sb -> sb.append("a").append(it) }
				} and optionally ${
					numbers.joinToStringAndLast(
						", ",
						lastSeparator = " and "
					) { it, sb -> sb.append("representation").append(it) }
				}.
				| *
				| ${
					numbers.joinToString("\n") { index ->
						"""
						| * @param a$index the value for argument $index.
						| * @param representation$index the
						|""".trimMargin()
					}
				}
				| *
				| * @since 0.1.0
				| */
				|fun <$typeArgs> Args.Companion.of(
				|	$parameters,
				|	$representationParameters
				|): Args$upperNumber<$typeArgs> = DefaultArgs$upperNumber(
				|	$args,
				|	$representationArgs,
				|)
				|
				|
				""".trimMargin()
			)


		}

		val argsOfFile = packageDir.resolve("argsOf.kt")
		argsOfFile.writeText(argsOf.toString())
	}
}
generationFolder.builtBy(generate)

fun StringBuilder.appendTest(testName: String) = this.append(
	"""
	|import ch.tutteli.atrium.api.verbs.expect
	|import ch.tutteli.atrium.api.fluent.en_GB.*
	|import org.junit.jupiter.api.Test
	|import org.junit.jupiter.api.Named
	|import org.junit.jupiter.params.ParameterizedTest
	|import org.junit.jupiter.params.provider.MethodSource
	|import com.tegonal.minimalist.*
	|import com.tegonal.minimalist.atrium.*
	|import java.math.BigInteger
	|import java.time.LocalDate
	|
	|class $testName {
	|
	""".trimMargin()
).appendLine()

val generateTest: TaskProvider<Task> = tasks.register("generateTest") {
	doFirst {
		val packageDir = File(generationTestFolder.asPath + "/" + packageNameAsPath)
		val argValues = sequenceOf(
			"\"string\"",
			"1",
			"2L",
			"3F",
			"4.0",
			"'c'",
			"LocalDate.now()",
			"1.toShort()",
			"2.toByte()",
			"3.toBigInteger()"
		)
		val argValues2 = listOf(
			"\"another string\"",
			"2",
			"3L",
			"4F",
			"5.0",
			"'d'",
			"LocalDate.now().plusDays(2)",
			"2.toShort()",
			"3.toByte()",
			"4.toBigInteger()"
		)
		val argsTypeParameters = sequenceOf(
			"String", "Int", "Long", "Float", "Double", "Char", "LocalDate", "Short", "Byte", "BigInteger"
		)

		(1..numOfArgs).forEach { upperNumber ->
			val numbers = (1..upperNumber)
			val typeArgs = numbers.joinToString(", ") { "A$it" }

			val argsExpectations = createStringBuilder("$packageName.atrium")
				.append(
					"""
					import ch.tutteli.atrium.creating.Expect
					import ch.tutteli.atrium.api.fluent.en_GB.*
					import com.tegonal.minimalist.*

				""".trimIndent()
				)
				.appendLine()

			numbers.forEach {
				argsExpectations.append(
					"""
					|val <$typeArgs> Expect< Args$upperNumber<$typeArgs>>.a$it : Expect<A$it>
					|		get() = feature(Args$upperNumber<$typeArgs>::a$it)
					""".trimMargin()
				).appendLine()
			}
			argsExpectations.appendLine()
			numbers.forEach {
				argsExpectations.append(
					"""
					|val <$typeArgs> Expect< Args$upperNumber<$typeArgs>>.representation$it : Expect<String?>
					|		get() = feature(Args$upperNumber<$typeArgs>::representation$it)
					""".trimMargin()
				).appendLine()
			}
			val argsExpectationsFile = packageDir.resolve("atrium/args${upperNumber}Expectations.kt")
			argsExpectationsFile.writeText(argsExpectations.toString())

			if (upperNumber > 1) {
				val dropTest = createStringBuilder("$packageName.drop")
					.appendTest("Args${upperNumber}DropTest")

				numbers.forEach { number ->

					val typeParameters = argsTypeParameters.take(upperNumber).let {
						it.take(number - 1) + it.drop(number)
					}
					val newNumbers = numbers.take(number - 1) + numbers.drop(number)

					dropTest.append(
						"""
					|	@Test
					|	fun dropArg$number() {
					|		val args = Args.of(
					|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
					|			${numbers.joinToString(",\n\t\t\t") { "representation$it = \"rep $it\"" }}
					|		)
					|		val argsResult: Args${upperNumber - 1}<${typeParameters.joinToString(", ")}> = args.dropArg$number()
					|		expect(argsResult) {
					|			${
							newNumbers.withIndex().joinToString("\n\t\t\t") { (index, it) ->
								"a${index + 1}.toEqual(args.a$it)"
							}
						}
					|			${
							newNumbers.withIndex().joinToString("\n\t\t\t") { (index, it) ->
								"representation${index + 1}.toEqual(args.representation$it)"
							}
						}
					|		}
					|	}
					|
					""".trimMargin()
					).appendLine()
				}

				dropTest.append("}")
				val dropTestFile = packageDir.resolve("drop/Args${upperNumber}DropTest.kt")
				dropTestFile.writeText(dropTest.toString())
			}

			val withArgTest = createStringBuilder("$packageName.drop")
				.appendTest("Args${upperNumber}WithArgTest")

			numbers.forEach { number ->

				withArgTest.append(
					"""
					|	@Test
					|	fun withArg$number() {
					|		val args = Args.of(
					|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
					|			${numbers.joinToString(",\n\t\t\t") { "representation$it = \"rep $it\"" }}
					|		)
					|		val argsResult = args.withArg$number(${argValues2[number - 1]}, "new rep")
					|
					|		// no changes to args
					|		expect(args) {
					|			${
						numbers.joinToString("\n\t\t\t") {
							"a$it.toEqual(${argValues.elementAt(it - 1)})"
						}
					}
					|			${
						numbers.joinToString("\n\t\t\t") {
							"representation$it.toEqual(\"rep $it\")"
						}
					}
					|		}
					|
					|		expect(argsResult) {
					|			${
						numbers.joinToString("\n\t\t\t") {
							"a$it.toEqual(${if (it == number) argValues2[number - 1] else "args.a$it"})"
						}
					}
					|			${
						numbers.joinToString("\n\t\t\t") {
							"representation$it.toEqual(${if (it == number) "\"new rep\"" else "args.representation$it"})"
						}
					}
					|		}
					|	}
					|
					""".trimMargin()
				).appendLine()
			}

			withArgTest.append("}")
			val withArgTestFile = packageDir.resolve("withArg/Args${upperNumber}WithArgTest.kt")
			withArgTestFile.writeText(withArgTest.toString())

			val argumentsTest = createStringBuilder("$packageName.arguments")
				.appendTest("Args${upperNumber}ArgumentsTest")

			argumentsTest.append(
				"""
				|	@Test
				|	fun `get returns correct array and value wrapped in Name`() {
				|		val args = Args.of(
				|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
				|			${numbers.joinToString(",\n\t\t\t") { "representation$it = \"rep $it\"" }}
				|		)
				|		expect(args.get().toList()).toContainExactly(
				|${
					numbers.joinToString(",\n") {
						"""
						|			{
						|				toBeANamedOf<${argsTypeParameters.elementAt(it - 1)}>(args.representation$it!!, args.a$it)
						|			}""".trimMargin()
					}
				}

				|		)
				|	}
				|
				|	@ParameterizedTest
				|	@MethodSource("args")
				|	fun `can use Args$upperNumber in MethodSource`(
				|		${numbers.joinToString(",\n\t\t") { "a$it: ${argsTypeParameters.elementAt(it - 1)}" }}
				|	) {
				|		${
					numbers.joinToString("\n\t\t") {
						"expect(a$it).toEqual(${argValues.elementAt(it - 1)})"
					}
				}
				|	}
				|
				|	companion object {
				|		@JvmStatic
				|		fun args() : List<Args${upperNumber}<${
					argsTypeParameters.take(upperNumber).joinToString(", ")
				}>> =
				|			listOf(Args.of(${argValues.take(upperNumber).joinToString(", ")}))
				|	}
				""".trimMargin()
			).appendLine()
			argumentsTest.append("}")
			val argumentsTestFile = packageDir.resolve("arguments/Args${upperNumber}ArgumentsTest.kt")
			argumentsTestFile.writeText(argumentsTest.toString())

			val appendTest = createStringBuilder("${packageName}.append")
				.appendTest("Args${upperNumber}AppendTest")


			(1..numOfArgs - upperNumber).forEach { upperNumber2 ->


				val numbers2 = 1..upperNumber2
				appendTest.append(
					"""
					|	@Test
					|	fun `append Arg${upperNumber2}`() {
					|		val firstArgs = Args.of(
					|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
					|			${(1..upperNumber).joinToString(",\n\t\t\t") { "representation$it = \"rep $it\"" }}
					|		)
					|		val secondArgs = Args.of(
					|			${argValues.drop(upperNumber).take(upperNumber2).joinToString(",\n\t\t\t")},
					|			${numbers2.joinToString(",\n\t\t\t") { "representation$it = \"rep ${it + upperNumber}\"" }}
					|		)
					|		val argsResult = firstArgs.append(secondArgs)
					|		expect(argsResult) {
					|			${numbers.joinToString("\n\t\t\t") { "a$it.toEqual(firstArgs.a$it)" }}
					|			${numbers.joinToString("\n\t\t\t") { "representation$it.toEqual(firstArgs.representation$it)" }}
					|			${numbers2.joinToString("\n\t\t\t") { "a${it + upperNumber}.toEqual(secondArgs.a$it)" }}
					|			${numbers2.joinToString("\n\t\t\t") { "representation${it + upperNumber}.toEqual(secondArgs.representation$it)" }}
					|		}
					|	}
					|
					""".trimMargin()
				).appendLine()
			}


			appendTest.append("}")
			val appendTestFile = packageDir.resolve("append/Args${upperNumber}AppendTest.kt")
			appendTestFile.writeText(appendTest.toString())
		}
	}
}
generationTestFolder.builtBy(generateTest)
