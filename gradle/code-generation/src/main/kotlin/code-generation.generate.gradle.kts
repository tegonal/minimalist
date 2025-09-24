import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.a2
import ch.tutteli.kbox.append
import ch.tutteli.kbox.joinToString as joinToStringAndLast

val generationFolder: ConfigurableFileCollection = project.files("src/main/generated/kotlin")
//val generationFolderJava: ConfigurableFileCollection = project.files("src/main/generated/java")
val generationTestFolder: ConfigurableFileCollection = project.files("src/test/generated/kotlin")
val generationTestFolderJava: ConfigurableFileCollection = project.files("src/test/generated/java")

val mainPackageName = "com.tegonal.minimalist"
val mainPackageNameAsPath = mainPackageName.replace('.', '/')


fun dontModifyNotice(place: String) =
	"""
		|// --------------------------------------------------------------------------------------------------------------------
		|// automatically generated, don't modify here but in:
		|// $place
		|// --------------------------------------------------------------------------------------------------------------------
		|
	""".trimMargin()

val numOfArgs = 10

val generate: TaskProvider<Task> = tasks.register("generate") {

	val dontModifyNotice =
		dontModifyNotice("gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generate")

	fun createStringBuilder(packageName: String) = StringBuilder(dontModifyNotice)
		.append("package ").append(packageName).append("\n\n")

	doFirst {
		val packageDir = File(generationFolder.asPath + "/" + mainPackageNameAsPath)
		fun StringBuilder.writeToFile(fileName: String) {
			val file = packageDir.resolve(fileName)
			file.parentFile.mkdirs()
			file.writeText(this.toString())
		}

		//TODO 2.0.0 still worth to keep, now that we base most things on Tuple instead of Args?
		val argsInterface = createStringBuilder(mainPackageName)
			.append(
				"""
				 |import org.junit.jupiter.params.provider.Arguments
				 |import com.tegonal.minimalist.impl.*
 				 |
				 |/**
				 | * Represents the top-interface of arguments-representations such as [Args1], [Args2] and so on.
				 | *
				 | * @since 1.0.0
				 | */
				 |interface Args : Arguments {
				 |	/**
				 |	 * Extension point for Args (next to [Args.of] functions).
				 |	 */
				 |	companion object {
			""".trimMargin()
			)
			.appendLine()

		val argsComponents = createStringBuilder(mainPackageName)

		fun wrapIntoRepresentationIfFirst(num: Int) = if (num == 1) "?.let { r -> Representation(r) }" else ""

		fun tupleType(arity: Int, tArgs: String) = when (arity) {
			1 -> tArgs
			else -> "Tuple$arity"
		}

		fun tupleTypeWithTypeArgs(arity: Int, tArgs: String) = when (arity) {
			1 -> tArgs
			else -> "Tuple$arity<$tArgs>"
		}

		fun StringBuilder.importTupleTypes() =
			append("import ch.tutteli.kbox.append\n")
				.append("import ch.tutteli.kbox.Tuple2\n")
				.append("import ch.tutteli.kbox.Tuple3\n")
				.append("import ch.tutteli.kbox.Tuple4\n")
				.append("import ch.tutteli.kbox.Tuple5\n")
				.append("import ch.tutteli.kbox.Tuple6\n")
				.append("import ch.tutteli.kbox.Tuple7\n")
				.append("import ch.tutteli.kbox.Tuple8\n")
				.append("import ch.tutteli.kbox.Tuple9\n")

		val semiOrderedArgsLikeGeneratorCombineAll = listOf(
			Tuple("orderedCombineAllGenerated", "OrderedCombineAllKt", "OrderedArgsGenerator", "cartesian"),
			Tuple("semiOrderedCombineAllGenerated", "SemiOrderedCombineAllKt", "SemiOrderedArgsGenerator", "combine"),
			Tuple("arbCombineAllGenerated", "ArbCombineAllKt", "ArbArgsGenerator", "zip")
		).map {
			it.append(
				createStringBuilder("$mainPackageName.generators")
					.importTupleTypes()
					.appendLine()
			)
		}

		val semiOrderedArgsLikeGeneratorCartesian = listOf(
			Tuple("orderedCartesianGenerated", "OrderedCartesianKt", "OrderedArgsGenerator"),
			Tuple("semiOrderedCartesianGenerated", "SemiOrderedCartesianKt", "SemiOrderedArgsGenerator"),
		).map {
			it.append(
				StringBuilder("@file:JvmName(\"${it.a2}\")\n@file:JvmMultifileClass\n")
					.append(dontModifyNotice)
					.append("package ").append(mainPackageName).append(".generators").append("\n\n")
					.importTupleTypes()
					.appendLine()
			)
		}

		val arbZip = listOf(
			Tuple("arbZipGenerated", "ArbZipKt", "ArbArgsGenerator"),
			Tuple("semiOrderedZipGenerated", "SemiOrderedZipKt", "SemiOrderedArgsGenerator")
		).map {
			it.append(
				StringBuilder("@file:JvmName(\"${it.a2}\")\n@file:JvmMultifileClass\n")
					.append(dontModifyNotice)
					.append("package ").append(mainPackageName).append(".generators").append("\n\n")
					.importTupleTypes()
					.appendLine()
			)
		}

		val arbCombineDependent = listOf(
			Tuple("arbCombineDependentGenerated", "ArbCombineDependentKt", "ArbArgsGenerator"),
			Tuple("semiOrderedCombineDependentGenerated", "SemiOrderedCombineDependentKt", "SemiOrderedArgsGenerator")
		).map {
			it.append(
				StringBuilder("@file:JvmName(\"${it.a2}\")\n@file:JvmMultifileClass\n")
					.append(dontModifyNotice)
					.append("package ").append(mainPackageName).append(".generators").append("\n\n")
					.importTupleTypes()
					.appendLine()
			)
		}

		(1..numOfArgs).forEach { upperNumber ->
			val numbers = (1..upperNumber).toList()
			val typeArgs = numbers.joinToString(", ") { "A$it" }
			val constructorProperties = numbers.joinToString(",\n\t") { "override val a$it: A$it" }
			val representationConstructorProperties =
				numbers.joinToString(",\n\t") { "override val representation$it: String? = null" }

			val argsInterfaces = createStringBuilder(mainPackageName)
			argsInterfaces.append(
				"""
				|/**
				| * Represents an [Args] with $upperNumber argument${if (upperNumber > 1) "s" else ""}.
				| *
				| * @since 1.0.0
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
					|	 * @since 1.0.0
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
					|	 * @since 1.0.0
					|	 */
					|	val representation$it: String?
					""".trimMargin()
				).appendLine()
			}

			val defaultArgs = createStringBuilder("$mainPackageName.impl")
				.append("import com.tegonal.minimalist.*\n\n")
				.append("import org.junit.jupiter.api.Named\n\n")

			defaultArgs.append(
				"""
				|/**
				| * A simple data class based implementation of an [Args$upperNumber].
				| *
				| * !! No backward compatibility guarantees !!
                | * Reuse at your own risk
				| *
				| * @since 1.0.0
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
				val typArgsNew = numbers.joinToString(", ") { if (it == index) "A${it}New" else "A$it" }

				if (upperNumber > 1) {

					argsInterfaces.append(
						"""
						|	/**
						|	 * Creates a new [Args$upperNumber] by coping `this` [Args$upperNumber] but replaces
						|	 * the argument $index ([Args$upperNumber.a$index]) with the given [value] (and its representation with the given [representation]).
						|	 *
						|	 * @param value The new value to use for argument $index.
						|	 * @param representation The new representation to use for the argument $index where `null`
						|	 *   means let the algorithm determine a representation.
						|	 *
						|	 * @return The newly created [Args$upperNumber].
						|	 *
						|	 * @since 1.0.0
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


					argsInterfaces.append(
						"""
						|	/**
						|	 * Maps [a$index] of `this` [Args$upperNumber] with the given [transform] function resulting in a new [Args$upperNumber].
						|	 *
						|	 * @param transform The function which maps [a$index] to a new value.
						|	 *
						|	 * @return The newly created [Args$upperNumber].
						|	 *
						|	 * @since 2.0.0
						|	 */
						|	fun <A${index}New> mapArg$index(transform: (A$index) -> A${index}New): Args$upperNumber<$typArgsNew>
						|
						""".trimMargin()
					).appendLine()

					argsInterfaces.append(
						"""
						|	/**
						|	 * Maps [a$index] and its [representation$index] of `this` [Args$upperNumber] with the given [transform] function resulting in a new [Args$upperNumber].
						|	 *
						|	 * @param transform The function which maps [a$index] and [representation$index].
						|	 *
						|	 * @return The newly created [Args$upperNumber].
						|	 *
						|	 * @since 2.0.0
						|	 */
						|	fun <A${index}New> mapArg${index}WithRepresentation(transform: (A$index, String?) -> Pair<A${index}New, String?>): Args$upperNumber<$typArgsNew>
						|
						""".trimMargin()
					).appendLine()

					defaultArgs.append(
						"""
						|	override fun <A${index}New> mapArg$index(
						|		transform: (A$index) -> A${index}New
						|	): Args$upperNumber<$typArgsNew> =
						|		Args.of(
						|			${
							numbers.joinToString(",\n\t\t\t") {
								if (it != index) "a$it = a$it, representation$it = representation$it${
									wrapIntoRepresentationIfFirst(it)
								}"
								else "a$it = transform(a$it)"
							}
						}
						|		)
						|
						""".trimMargin()
					).appendLine()


					defaultArgs.append(
						"""
						|	override fun <A${index}New> mapArg${index}WithRepresentation(
						|		transform: (A$index, String?) -> Pair<A${index}New, String?>
						|	): Args$upperNumber<$typArgsNew> =
						|		transform(a$index, representation$index).let { (newA$index, newRepresentation$index) ->
						|			Args.of(
						|				${
							numbers.joinToString(",\n\t\t\t\t") {
								if (it != index) "a$it = a$it, representation$it = representation$it${
									wrapIntoRepresentationIfFirst(it)
								}"
								else "a$it = newA$it, representation$it = newRepresentation$it${
									wrapIntoRepresentationIfFirst(it)
								}"
							}
						}
						|			)
						|		}
						|
						""".trimMargin()
					).appendLine()
				}
			}

			numbers.forEach { index ->
				argsComponents.append(
					"""
					|/**
					| * Extracts [a$index][Args$upperNumber.a$index] (the $index argument) of `this` Args$upperNumber.
					| *
					| * Be aware of that you loose the [representation$index][Args$upperNumber.representation$index] this way. Should you extract
					| * the argument in order to create another Args afterwards, then ${if (upperNumber < numOfArgs) "[Args$upperNumber.append] or " else ""}
					| * one of the ${if (upperNumber > 1) "[Args$upperNumber.dropArg1] or " else ""}[Args$upperNumber.withArg1] methods might be more suited.
					| *
					| * @since 1.1.0
					| */
					|operator fun <$typeArgs> Args$upperNumber<$typeArgs>.component$index(): A$index = a$index
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
					|	 * Creates a new [Args$upperNumber3] by copying `this` [Args$upperNumber] and appending the given [Args$upperNumber2].
					|	 *
					|	 * @return The newly created [Args$upperNumber3].
					|	 *
					|	 * @since 1.0.0
					|	 */
					|	fun <$typeArgs2> append(
					|		args: Args${upperNumber2}<$typeArgs2>
					|	): Args$upperNumber3<$typeArgs3>
					|
					""".trimMargin()
				).appendLine()

				defaultArgs.append(
					"""
					|	override fun <$typeArgs2> append(
					|		args: Args${upperNumber2}<$typeArgs2>
					|	): Args$upperNumber3<$typeArgs3> = Args.of(
					|		${
						numbers.joinToString(",\n\t\t") {
							"a$it = this.a$it, representation$it = this.representation$it${
								wrapIntoRepresentationIfFirst(it)
							}"
						}
					},
					|		${numbers2.joinToString(",\n\t\t") { "a${it + upperNumber} = args.a$it, representation${it + upperNumber} = args.representation$it" }},
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
						|	 * @since 1.0.0
						|	 */
						|	fun dropArg$index(): Args${upperNumber4}<$typeArgs4>
						|
						""".trimMargin()
					).appendLine()

					defaultArgs.append(
						"""
						|	override fun dropArg$index(): Args${upperNumber4}<$typeArgs4> =
						|		Args.of(
						|			${
							numbers4WithIndex.joinToString(",\n\t\t\t") { (i, it) ->
								"a$i = this.a$it, representation$i = this.representation$it${
									wrapIntoRepresentationIfFirst(i)
								}"
							}
						},
						|		)
						|
						""".trimMargin()
					).appendLine()
				}

				if (upperNumber <= 9) {
					semiOrderedArgsLikeGeneratorCombineAll
						.forEach { (_, _, className, methodName, sb) ->
							val combine3ToX = (if (upperNumber > 3) "\n\t\t" else "") +
								(3..upperNumber).joinToString("\n\t\t") { ".$methodName(component$it()) { args, a$it -> args.append(a$it) }" }
							val otherClassName =
								if (className == "SemiOrderedArgsGenerator") "ArgsGenerator" else className
							val argsGenerators = (2..upperNumber).joinToString(",\n\t") { "$otherClassName<A$it>" }

							//TODO 2.1.0 come up with a solution which combines in one go so that we don't have to
							// create intermediate Pair, Triple .. until reaching the final TupleN
							// Moreover, such an implementation would also allow to provide a custom transform function
							sb.append(
								"""
								|/**
								| * ${if (className == "ArbArgsGenerator") "Zips" else "Combines"} the [component1] [$className] with ${if (upperNumber < 3) "the [component2] [$otherClassName]" else "all other [$otherClassName] from left to right"}
								| * resulting in a [$className] which generates [${"Tuple$upperNumber"}].
								|${
									when (className) {
										"OrderedArgsGenerator" -> {
											"""
											| *
											| * The resulting [OrderedArgsGenerator] generates the product of all [OrderedArgsGenerator.size] values before repeating.
											| *""".trimMargin()
										}

										"SemiOrderedArgsGenerator" -> {
											"""
											| *
											| * How the [ArgsGenerator]s are combined depends on their type:
											| *   - [SemiOrderedArgsGenerator]s are combined using [cartesian]
											| *   - [ArbArgsGenerator]s are combined using [zip]
											| *""".trimMargin()
										}

										else -> " *"
									}
								}
								| * @since 2.0.0
								| */
								|fun <$typeArgs> ${"Tuple$upperNumber"}<
								|	$className<A1>,
								|	$argsGenerators
								|>.combineAll(): $className<${tupleTypeWithTypeArgs(upperNumber, typeArgs)}> =
								|	component1().$methodName(component2(), ::Tuple2)$combine3ToX
								|
								""".trimMargin()
							).appendLine()
						}
				}
			}

			argsInterfaces.append("}\n")
			argsInterfaces.writeToFile("Args$upperNumber.kt")

			defaultArgs.append("}\n")
			defaultArgs.writeToFile("impl/DefaultArgs$upperNumber.kt")

			val parameters = numbers.joinToString(",\n\t\t\t") { "a$it: A$it" }
			val representationParameters = numbers.joinToString(",\n\t\t\t") {
				"representation$it: ${if (it == 1) "Representation?" else "String?"} = null"
			}
			val args = numbers.joinToString(",\n\t\t\t") { "a$it" }
			val representationArgs = numbers.joinToString(",\n\t\t\t") {
				"representation$it${if (it == 1) "?.text" else ""}"
			}

			argsInterface.append(
				"""
				|		/**
				|		 * Creates an [Args$upperNumber] based on the given arguments ${
					numbers.joinToStringAndLast(
						", ",
						lastSeparator = " and "
					) { it, sb -> sb.append("[a").append(it).append("]") }
				}
				|		 * and optionally ${
					numbers.joinToStringAndLast(
						", ",
						lastSeparator = " and "
					) { it, sb -> sb.append("[representation").append(it).append("]") }
				}.
				|		 *
				|${
					numbers.joinToString("\n") { index ->
						"""
						|		 * @param a$index the value for argument $index.
						""".trimMargin()
					}
				}
				|${
					numbers.joinToString("\n") { index ->
						"""
						|		 * @param representation$index the representation of argument $index where `null` means no custom representation.
						""".trimMargin()
					}
				}
				|		 *
				|		 * @since 2.0.0
				|		 */
				|		@JvmStatic
				|		@JvmOverloads
				|		fun <$typeArgs> of(
				|			$parameters,
				|			$representationParameters
				|		): Args$upperNumber<$typeArgs> = DefaultArgs$upperNumber(
				|			$args,
				|			$representationArgs,
				|		)
				|
				""".trimMargin()
			)

			val upperNumberPlus1 = upperNumber + 1
			if (upperNumberPlus1 <= 9) {
				val typeArgsPlus1 = (1..upperNumberPlus1).joinToString(", ") { "A$it" }
				val tupleX = tupleTypeWithTypeArgs(upperNumber, typeArgs)
				val tupleXPlus1 = tupleTypeWithTypeArgs(upperNumberPlus1, typeArgsPlus1)

				semiOrderedArgsLikeGeneratorCartesian.forEach { (_, _, className, sb) ->
					sb.append(
						"""
						|/**
						| * Combines `this` [${className}] with the given [other]&nbsp;[${className}] resulting in their
						| * cartesian product where the values are transformed into a [${"Tuple$upperNumberPlus1"}].
						| *
						| * The resulting [$className] generates
						| * [this.size][$className.size] * [other.size][$className.size] values before repeating.
						| *
						| * @param other The other [${className}] which generates values of type [A$upperNumberPlus1].
						| *
						| * @return The resulting [${className}] which represents the cartesian product and
						| *   generates values of type [${"Tuple$upperNumberPlus1"}].
						| *
						| * @since 2.0.0
						| */
						|@JvmName("cartesianToTuple${upperNumberPlus1}")
						|fun <$typeArgsPlus1> ${className}<$tupleX>.cartesian(
						|	other: ${className}<A$upperNumberPlus1>
						|): ${className}<$tupleXPlus1> = cartesian(other${
							if (upperNumber == 1) ", ::Tuple2)"
							else """) { args, otherArg ->
								|	args.append(otherArg)
								|}""".trimMargin()
						}
						|""".trimMargin()
					).appendLine()
				}

				arbZip.forEach { (_, _, className, sb) ->
					sb.append(
						"""
						|/**
						| * Zips `this` [${className}] with the given [other]&nbsp;[ArbArgsGenerator].
						| *
						| * @param other The other [ArbArgsGenerator] which generates values of type [A$upperNumberPlus1].
						| *
						| * @return The resulting [${className}] which generates values of type [${
							"Tuple$upperNumberPlus1"
						}].
						| *
						| * @since 2.0.0
						| */
						|@JvmName("zipToTuple${upperNumberPlus1}")
						|fun <$typeArgsPlus1> ${className}<$tupleX>.zip(
						|	other: ArbArgsGenerator<A$upperNumberPlus1>
						|): ${className}<$tupleXPlus1> = zip(other${
							if (upperNumber == 1) ", ::Tuple2)"
							else """) { args, otherArg ->
							 |	args.append(otherArg)
							 |}""".trimMargin()
						}
						|""".trimMargin()
					).appendLine()
				}

				arbCombineDependent.forEach { (_, _, className, sb) ->
					val tupleType = tupleType(upperNumber, "A1")
					sb.append(
						"""
						|/**
						| * Creates for each generated value of type [$tupleType] by `this` [${className}] another [${className}] with the
						| * help of the given [otherFactory] where the other generator generates values of type [A$upperNumberPlus1]
						| and then zips the value of `this` [${className}] with one values of the other [ArbArgsGenerator].
						| *
						| * @param otherFactory Builds an [ArbArgsGenerator] based on a given value of type [$tupleType].
						| *
						| * @return The resulting [${className}] which generates values of type [${"Tuple$upperNumberPlus1"}].
						| *
						| * @since 2.0.0
						| */
						|@JvmName("combineDependentToTuple${upperNumberPlus1}")
						|fun <$typeArgsPlus1> ${className}<$tupleX>.combineDependent(
						|	otherFactory: ArbExtensionPoint.($tupleX) -> ArbArgsGenerator<A$upperNumberPlus1>
						|): ${className}<$tupleXPlus1> = combineDependent(otherFactory${
							if (upperNumber == 1) ", ::Tuple2)"
							else """) { args, otherArg ->
								 |	args.append(otherArg)
								 |}""".trimMargin()
						}
						|""".trimMargin()
					).appendLine()
				}
			}
		}

		argsInterface.append(
			"""
			|  }
			|}
			""".trimMargin()
		)

		argsInterface.writeToFile("Args.kt")
		argsComponents.writeToFile("argsComponents.kt")

		semiOrderedArgsLikeGeneratorCombineAll.forEach { (fileName, _, _, _, sb) ->
			sb.writeToFile("generators/$fileName.kt")
		}
		semiOrderedArgsLikeGeneratorCartesian.forEach { (fileName, _, _, sb) ->
			sb.writeToFile("generators/$fileName.kt")
		}

		arbZip.forEach { (fileName, _, _, sb) ->
			sb.writeToFile("generators/$fileName.kt")
		}

		arbCombineDependent.forEach { (fileName, _, _, sb) ->
			sb.writeToFile("generators/$fileName.kt")
		}
	}
}
generationFolder.builtBy(generate)


val argValues = sequenceOf(
	"1",
	"2L",
	"3F",
	"4.0",
	"'c'",
	"\"string\"",
	"LocalDate.now()",
	"1.toShort()",
	"2.toByte()",
	"3.toBigInteger()"
)
val argValues2 = listOf(
	"2",
	"3L",
	"4F",
	"5.0",
	"'d'",
	"\"another string\"",
	"LocalDate.now().plusDays(2)",
	"2.toShort()",
	"3.toByte()",
	"4.toBigInteger()"
)
val argsTypeParameters = sequenceOf(
	"Int", "Long", "Float", "Double", "Char", "String", "LocalDate", "Short", "Byte", "BigInteger"
)
val argsTypeParametersJava = argsTypeParameters.map {
	when (it) {
		"Int" -> "Integer"
		"Char" -> "Character"
		else -> it
	}
}.toList()

val argValuesJava = argValues.map {
	when (it) {
		"1.toShort()" -> "(short) 1"
		"2.toByte()" -> "(byte) 2"
		"3.toBigInteger()" -> "BigInteger.valueOf(3)"
		else -> it
	}
}
val argValues2Java = argValues2.map {
	when (it) {
		"2.toShort()" -> "(short) 2"
		"3.toByte()" -> "(byte) 3"
		"4.toBigInteger()" -> "BigInteger.valueOf(4)"
		else -> it
	}
}

val generateTest: TaskProvider<Task> = tasks.register("generateTest") {
	val dontModifyNotice =
		dontModifyNotice("gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTest")

	fun createStringBuilder(packageName: String) = StringBuilder(dontModifyNotice)
		.append("@file:Suppress(\"UnusedImport\")\n\n")
		.append("package ").append(packageName).append("\n\n")

	doFirst {
		fun StringBuilder.appendTest(testName: String) = this.append(
			"""
			|import ch.tutteli.atrium.api.verbs.expect
			|import ch.tutteli.atrium.api.fluent.en_GB.*
			|import org.junit.jupiter.api.Test
			|import org.junit.jupiter.api.Named
			|import org.junit.jupiter.params.ParameterizedTest
			|import org.junit.jupiter.params.provider.MethodSource
			|import com.tegonal.minimalist.*
			|import com.tegonal.minimalist.testutils.atrium.*
			|import java.math.BigInteger
			|import java.time.LocalDate
			|
			|class $testName {
			|
			""".trimMargin()
		).appendLine()

		val packageDir = File(generationTestFolder.asPath + "/" + mainPackageNameAsPath)
		fun StringBuilder.writeToFile(fileName: String) {
			val file = packageDir.resolve(fileName)
			file.parentFile.mkdirs()
			file.writeText(this.toString())
		}

		fun wrapIntoRepresentationIfFirst(arg: String, num: Int) = if (num == 1) "Representation($arg)" else arg

		(1..numOfArgs).forEach { upperNumber ->
			val numbers = (1..upperNumber)
			val typeArgs = numbers.joinToString(", ") { "A$it" }

			val argsExpectations = createStringBuilder("$mainPackageName.testutils.atrium")
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
					|val <$typeArgs> Expect<Args$upperNumber<$typeArgs>>.a$it : Expect<A$it>
					|		get() = feature(Args$upperNumber<$typeArgs>::a$it)
					""".trimMargin()
				).appendLine()
			}
			argsExpectations.appendLine()
			numbers.forEach {
				argsExpectations.append(
					"""
					|val <$typeArgs> Expect<Args$upperNumber<$typeArgs>>.representation$it : Expect<String?>
					|		get() = feature(Args$upperNumber<$typeArgs>::representation$it)
					""".trimMargin()
				).appendLine()
			}
			argsExpectations.writeToFile("atrium/args${upperNumber}Expectations.kt")

			if (upperNumber > 1) {
				val dropTest = createStringBuilder("$mainPackageName.arguments.drop")
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
						|			${
							numbers.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep $it\"",
										it
									)
								}"
							}
						}
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
				dropTest.writeToFile("arguments/drop/Args${upperNumber}DropTest.kt")

				val withArgTest = createStringBuilder("$mainPackageName.arguments.withArg")
					.appendTest("Args${upperNumber}WithArgTest")

				val mapArgTest = createStringBuilder("$mainPackageName.arguments.mapArg")
					.appendTest("Args${upperNumber}MapArgTest")

				numbers.forEach { number ->

					withArgTest.append(
						"""
						|	@Test
						|	fun withArg$number() {
						|		val args = Args.of(
						|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep $it\"",
										it
									)
								}"
							}
						}
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

					mapArgTest.append(
						"""
						|	@Test
						|	fun mapArg${number}() {
						|		val args = Args.of(
						|			${argValues.take(upperNumber).joinToString(",\n\t\t\t") { "listOf($it)" }},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep $it\"",
										it
									)
								}"
							}
						}
						|		)
						|		val argsResult = args.mapArg$number { it.first() }
						|
						|		// no changes to args
						|		expect(args) {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"a$it.toEqual(listOf(${argValues.elementAt(it - 1)}))"
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
								"a$it.toEqual(${if (it == number) argValues.toList()[number - 1] else "args.a$it"})"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"representation$it.toEqual(${if (it == number) "null" else "args.representation$it"})"
							}
						}
						|		}
						|	}
						|
						""".trimMargin()
					).appendLine()

					mapArgTest.append(
						"""
						|	@Test
						|	fun mapArg${number}WithRepresentation() {
						|		val args = Args.of(
						|			${argValues.take(upperNumber).joinToString(",\n\t\t\t") { "listOf($it)" }},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep $it\"",
										it
									)
								}"
							}
						}
						|		)
						|		val argsResult = args.mapArg${number}WithRepresentation { arg, repr -> arg.first() to "${'$'}repr modified" }
						|
						|		// no changes to args
						|		expect(args) {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"a$it.toEqual(listOf(${argValues.elementAt(it - 1)}))"
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
								"a$it.toEqual(${if (it == number) argValues.toList()[number - 1] else "args.a$it"})"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"representation$it.toEqual(${if (it == number) "\"rep $it modified\"" else "args.representation$it"})"
							}
						}
						|		}
						|	}
						|
						""".trimMargin()
					).appendLine()
				}

				withArgTest.append("}")
				withArgTest.writeToFile("arguments/withArg/Args${upperNumber}WithArgTest.kt")

				mapArgTest.append("}")
				mapArgTest.writeToFile("arguments/mapArg/Args${upperNumber}MapArgTest.kt")
			}

			val argumentsTest = createStringBuilder("$mainPackageName.arguments.annotation")
				.appendTest("Args${upperNumber}ArgumentsTest")

			argumentsTest.append(
				"""
				|	@Test
				|	fun `get returns correct array and value not wrapped in Named if representation not specified`() {
				|		val args = Args.of(
				|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")}
				|		)
				|		expect(args.get().toList()).toContainExactly(
				|${numbers.joinToString(",\n") { "\t\t\targs.a$it" }}
				|		)
				|	}
				|
				|	@Test
				|	fun `get returns correct array and value wrapped in Named if representation specified`() {
				|		val args = Args.of(
				|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
				|			${
					numbers.joinToString(",\n\t\t\t") {
						"representation$it = ${
							wrapIntoRepresentationIfFirst(
								"\"rep $it\"",
								it
							)
						}"
					}
				}
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
				|	@Test
				|	fun `using null as representation does not wrap it into Named`() {
				|		val args = Args.of(
				|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
				|			${numbers.joinToString(",\n\t\t\t") { "representation$it = null " }}
				|		)
				|		expect(args.get().toList()).toContainExactly(
				|${numbers.joinToString(",\n") { "\t\t\targs.a$it" }}
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
				|		fun args() = listOf(Args.of(${argValues.take(upperNumber).joinToString(", ")}))
				|	}
				""".trimMargin()
			).appendLine()
			argumentsTest.append("}")
			argumentsTest.writeToFile("arguments/annotation/Args${upperNumber}ArgumentsTest.kt")

			if (upperNumber < numOfArgs) {
				val appendTest = createStringBuilder("${mainPackageName}.arguments.append")
					.appendTest("Args${upperNumber}AppendTest")


				(1..numOfArgs - upperNumber).forEach { upperNumber2 ->


					val numbers2 = 1..upperNumber2
					appendTest.append(
						"""
						|	@Test
						|	fun `append Args${upperNumber2}`() {
						|		val firstArgs = Args.of(
						|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep $it\"",
										it
									)
								}"
							}
						}
						|		)
						|		val secondArgs = Args.of(
						|			${argValues.drop(upperNumber).take(upperNumber2).joinToString(",\n\t\t\t")},
						|			${
							numbers2.joinToString(",\n\t\t\t") {
								"representation$it = ${
									wrapIntoRepresentationIfFirst(
										"\"rep ${it + upperNumber}\"",
										it
									)
								}"
							}
						}
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
				appendTest.writeToFile("arguments/append/Args${upperNumber}AppendTest.kt")
			}

			val argsComponentTest = createStringBuilder("$mainPackageName.arguments.components")
				.appendTest("Args${upperNumber}ComponentsTest")

			numbers.forEach { number ->
				argsComponentTest.append(
					"""
					|	@Test
					|	fun component$number() {
					|		val args = Args.of(
					|			${argValues.take(upperNumber).joinToString(",\n\t\t\t")},
					|			${
						numbers.joinToString(",\n\t\t\t") {
							"representation$it = ${
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}"
						}
					}
					|		)
					|		val (${"_, ".repeat(number - 1)}a$number) = args
					|		expect(a$number).toEqual(args.a$number)
					|	}
					|
					""".trimMargin()
				).appendLine()
			}

			argsComponentTest.append("}")
			argsComponentTest.writeToFile("arguments/components/Args${upperNumber}ComponentsTest.kt")
		}
	}
}
generationTestFolder.builtBy(generateTest)

val generateTestJava: TaskProvider<Task> = tasks.register("generateTestJava") {
	val dontModifyNotice =
		dontModifyNotice("gradle/code-generation/src/main/kotlin/code-generation.generate.gradle.kts => generateTestJava")

	fun createStringBuilder(packageName: String) = StringBuilder(dontModifyNotice)
		.append("package ").append(packageName).append("\n\n")

	doFirst {
		fun StringBuilder.appendTest(testName: String) = this.append(
			"""
			|import ch.tutteli.atrium.api.fluent.en_GB.*;
			|import ch.tutteli.atrium.creating.Expect;
			|import com.tegonal.minimalist.*;
			|import com.tegonal.minimalist.testutils.atrium.*;
			|import com.tegonal.minimalist.java.*;
			|import kotlin.Pair;
			|import kotlin.Unit;
			|import kotlin.jvm.functions.Function1;
			|import org.junit.jupiter.api.Named;
			|import org.junit.jupiter.api.Test;
			|import org.junit.jupiter.params.ParameterizedTest;
			|import org.junit.jupiter.params.provider.MethodSource;

			|import java.math.BigInteger;
			|import java.time.LocalDate;
			|import java.util.List;

			|import static ch.tutteli.atrium.api.fluent.en_GB.AnyExpectationsKt.*;
			|import static ch.tutteli.atrium.api.fluent.en_GB.ArraySubjectChangersKt.*;
			|import static ch.tutteli.atrium.api.fluent.en_GB.IterableExpectationsKt.*;
			|import static ch.tutteli.atrium.api.verbs.ExpectKt.expect;
			|import static com.tegonal.minimalist.testutils.atrium.Args1ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args2ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args3ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args4ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args5ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args6ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args7ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args8ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args9ExpectationsKt.*;
			|import static com.tegonal.minimalist.testutils.atrium.Args10ExpectationsKt.*;
			|
			|public class $testName {
			|
			""".trimMargin()
		).appendLine()

		val packageDir = File(generationTestFolderJava.asPath + "/" + mainPackageNameAsPath + "/java")
		fun StringBuilder.writeToFile(fileName: String) {
			val file = packageDir.resolve(fileName)
			file.parentFile.mkdirs()
			file.writeText(this.toString())
		}

		fun wrapIntoRepresentationIfFirst(arg: String, num: Int) = if (num == 1) "new Representation($arg)" else arg

		val packageName = "$mainPackageName.java"

		(1..numOfArgs).forEach { upperNumber ->
			val numbers = (1..upperNumber)

			if (upperNumber > 1) {
				val dropTest = createStringBuilder("$packageName.arguments.drop;")
					.appendTest("Args${upperNumber}DropTest")


				numbers.forEach { number ->

					val typeParameters = argsTypeParametersJava.take(upperNumber).let {
						it.take(number - 1) + it.drop(number)
					}
					val newNumbers = numbers.take(number - 1) + numbers.drop(number)

					dropTest.append(
						"""
						|	@Test
						|	public void dropArg$number() {
						|		var args = Args.of(
						|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		Args${upperNumber - 1}<${typeParameters.joinToString(", ")}> argsResult = args.dropArg$number();
						|		expect(argsResult, e -> {
						|			${
							newNumbers.withIndex().joinToString("\n\t\t\t") { (index, it) ->
								"toEqual(getA${index + 1}(e), args.getA$it());"
							}
						}
						|			${
							newNumbers.withIndex().joinToString("\n\t\t\t") { (index, it) ->
								"toEqual(getRepresentation${index + 1}(e), args.getRepresentation$it());"
							}
						}
						|			return Unit.INSTANCE;
						|		});
						|	}
						|
						""".trimMargin()
					).appendLine()
				}

				dropTest.append("}")
				dropTest.writeToFile("arguments/drop/Args${upperNumber}DropTest.java")

				val withArgTest = createStringBuilder("$packageName.arguments.withArg;")
					.appendTest("Args${upperNumber}WithArgTest")

				val mapArgTest = createStringBuilder("$packageName.arguments.mapArg;")
					.appendTest("Args${upperNumber}MapArgTest")

				numbers.forEach { number ->

					withArgTest.append(
						"""
						|	@Test
						|	public void withArg$number() {
						|		var args = Args.of(
						|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		var argsResult = args.withArg$number(${argValues2Java[number - 1]}, "new rep");
						|
						|		// no changes to args
						|		expect(args, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), ${argValuesJava.elementAt(it - 1)});"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), \"rep $it\");"
							}
						}
						|			return Unit.INSTANCE;
						|		});
						|
						|		expect(argsResult, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), ${if (it == number) argValues2Java[number - 1] else "args.getA$it()"});"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), ${if (it == number) "\"new rep\"" else "args.getRepresentation$it()"});"
							}
						}
						|			return Unit.INSTANCE;
						|		});
						|	}
						|
						""".trimMargin()
					).appendLine()

					mapArgTest.append(
						"""
						|	@Test
						|	public void mapArg${number}() {
						|		var args = Args.of(
						|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t") { "List.of($it)" }},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		var argsResult = args.mapArg$number(it -> it.get(0));
						|
						|		// no changes to args
						|		expect(args, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), List.of(${argValuesJava.elementAt(it - 1)}));"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), \"rep $it\");"
							}
						}
						|				return Unit.INSTANCE;
						|		});
						|
						|		expect(argsResult, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), ${if (it == number) argValuesJava.toList()[number - 1] else "args.getA$it()"});"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), ${if (it == number) "null" else "args.getRepresentation$it()"});"
							}
						}
						|				return Unit.INSTANCE;
						|		});
						|	}
						|
						""".trimMargin()
					).appendLine()

					mapArgTest.append(
						"""
						|	@Test
						|	public void mapArg${number}WithRepresentation() {
						|		var args = Args.of(
						|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t") { "List.of($it)" }},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		var argsResult = args.mapArg${number}WithRepresentation((arg, repr) -> new Pair<>(arg.get(0), repr + " modified"));
						|
						|		// no changes to args
						|		expect(args, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), List.of(${argValuesJava.elementAt(it - 1)}));"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), \"rep $it\");"
							}
						}
						|				return Unit.INSTANCE;
						|		});
						|
						|		expect(argsResult, e -> {
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getA$it(e), ${if (it == number) argValuesJava.toList()[number - 1] else "args.getA$it()"});"
							}
						}
						|			${
							numbers.joinToString("\n\t\t\t") {
								"toEqual(getRepresentation$it(e), ${if (it == number) "\"rep $it modified\"" else "args.getRepresentation$it()"});"
							}
						}
						|				return Unit.INSTANCE;
						|		});
						|	}
						|
						""".trimMargin()
					).appendLine()
				}

				withArgTest.append("}")
				withArgTest.writeToFile("arguments/withArg/Args${upperNumber}WithArgTest.java")

				mapArgTest.append("}")
				mapArgTest.writeToFile("arguments/mapArg/Args${upperNumber}MapArgTest.java")
			}
			val argumentsTest = createStringBuilder("$packageName.arguments.annotation;")
				.appendTest("Args${upperNumber}ArgumentsTest")

			argumentsTest.append(
				"""
				|	@Test
				|	public void get_returns_correct_array_and_value_not_wrapped_in_Named_if_representation_not_specified() {
				|		var args = Args.of(
				|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")}
				|		);
				|		new Pipe<>(expect(args.get()))
				|			.pipe((it) -> asList(it))
				|			.pipe(it -> IterableExpectationsKt.toContainExactly(
				|				it,
				|				${
					numbers.joinToString(",\n\t\t\t") {
						(if (it == 2) "\tnew Object[] {\n\t\t\t\t\t" else if (it > 1) "\t\t" else "") +
							"args.getA$it()" +
							(if (it == upperNumber) {
								if (it > 1) "\n\t\t\t\t}" else ",\n\t\t\t\tnew Object[0]"
							} else "")
					}
				},
				|				o -> Unit.INSTANCE
				|			));
				|	}
				|
				|	@Test
				|	public void get_returns_correct_array_and_value_wrapped_in_Named_if_representation_specified() {
				|		var args = Args.of(
				|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")},
				|			${
					numbers.joinToString(",\n\t\t\t") {
						wrapIntoRepresentationIfFirst(
							"\"rep $it\"",
							it
						)
					}
				}
				|		);
				|		new Pipe<>(expect(args.get()))
				|			.pipe((it) -> asList(it))
				|			.pipe(it -> IterableExpectationsKt.toContainExactly(it,
				|${
					numbers.joinToString(",\n") {
						(if (it == 2) "\t\t\t\tnew Function1[] {\n" else "") +
							"""
				|				${if (it == 1) "e" else "untypedE"} -> {
				|					${if (it > 1) "Expect<Object> e = (Expect) untypedE;\n\t\t\t\t\t" else ""}Class<Named<${argsTypeParametersJava[it - 1]}>> clazz = (Class) Named.class;
				|					AnyExpectationsKt.toBeAnInstanceOf(e, kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz)).transformAndAppend(eNamed -> {
				|						NamedExpectationsKt.name(eNamed, eString -> {
				|							AnyExpectationsKt.toEqual(eString, args.getRepresentation$it());
				|							return Unit.INSTANCE;
				|						});
				|						NamedExpectationsKt.payload(eNamed, eVal -> {
				|							AnyExpectationsKt.toEqual(eVal, args.getA$it());
				|							return Unit.INSTANCE;
				|						});
				|						return Unit.INSTANCE;
				|					});
				|					return Unit.INSTANCE;
				|				}""".trimMargin() +
							(if (it == upperNumber) {
								if (it > 1) "\n\t\t\t\t}" else ",\n\t\t\t\tnew Function1[0]"
							} else "")
					}
				},
				| 				o -> Unit.INSTANCE
				|			));
				|	}
				|
				|	@Test
				|	public void using_null_as_representation_does_not_wrap_it_into_Named() {
				|		var args = Args.of(
				|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")},
				|			${numbers.joinToString(",\n\t\t\t") { (if (it == 1) "(Representation) " else "(String) ") + "null" }}
				|		);
				|		new Pipe<>(expect(args.get()))
				|			.pipe(it -> asList(it))
				|			.pipe(it -> IterableExpectationsKt.toContainExactly(
				|				it,
				|				${
					numbers.joinToString(",\n\t\t\t") {
						(if (it == 2) "\tnew Object[] {\n\t\t\t\t\t" else if (it > 1) "\t\t" else "") +
							"args.getA$it()" +
							(if (it == upperNumber) {
								if (it > 1) "\n\t\t\t\t}" else ",\n\t\t\t\tnew Object[0]"
							} else "")
					}
				},
				|				o -> Unit.INSTANCE
				|			));
				|	}
				|
				|	@ParameterizedTest
				|	@MethodSource("args")
				|	public void can_use_Args${upperNumber}_in_MethodSource(
				|		${numbers.joinToString(",\n\t\t") { "${argsTypeParametersJava.elementAt(it - 1)} a$it" }}
				|	) {
				|		${
					numbers.joinToString("\n\t\t") {
						"new Pipe<>(expect(a$it))" +
							".pipe(it -> toEqual(it, ${argValuesJava.elementAt(it - 1)}));"
					}
				}
				|	}
				|
				|	static List<Args> args() {
				|		return List.of(Args.of(${argValuesJava.take(upperNumber).joinToString(", ")}));
				|	}
				""".trimMargin()
			).appendLine()
			argumentsTest.append("}")
			argumentsTest.writeToFile("arguments/annotation/Args${upperNumber}ArgumentsTest.java")

			if (upperNumber < numOfArgs) {
				val appendTest = createStringBuilder("${packageName}.arguments.append;")
					.appendTest("Args${upperNumber}AppendTest")


				(1..numOfArgs - upperNumber).forEach { upperNumber2 ->


					val numbers2 = 1..upperNumber2
					appendTest.append(
						"""
						|	@Test
						|	public void append_Args${upperNumber2}() {
						|		var firstArgs = Args.of(
						|			${argValuesJava.take(upperNumber).joinToString(",\n\t\t\t")},
						|			${
							numbers.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		var secondArgs = Args.of(
						|			${argValuesJava.drop(upperNumber).take(upperNumber2).joinToString(",\n\t\t\t")},
						|			${
							numbers2.joinToString(",\n\t\t\t") {
								wrapIntoRepresentationIfFirst(
									"\"rep $it\"",
									it
								)
							}
						}
						|		);
						|		var argsResult = firstArgs.append(secondArgs);
						|		expect(argsResult, e -> {
						|			${numbers.joinToString("\n\t\t\t") { "toEqual(getA$it(e), firstArgs.getA$it());" }}
						|			${numbers.joinToString("\n\t\t\t") { "toEqual(getRepresentation$it(e), firstArgs.getRepresentation$it());" }}
						|			${numbers2.joinToString("\n\t\t\t") { "toEqual(getA${it + upperNumber}(e), secondArgs.getA$it());" }}
						|			${numbers2.joinToString("\n\t\t\t") { "toEqual(getRepresentation${it + upperNumber}(e), secondArgs.getRepresentation$it());" }}
						|			return Unit.INSTANCE;
						|		});
						|	}
						|
						""".trimMargin()
					).appendLine()
				}


				appendTest.append("}")
				appendTest.writeToFile("arguments/append/Args${upperNumber}AppendTest.java")
			}
		}
	}
}
generationTestFolderJava.builtBy(generateTestJava)

tasks.register("generateAll") {
	dependsOn(generate)
	dependsOn(generateTest)
	dependsOn(generateTestJava)
}

private fun StringBuilder.writeToFile(file: File) {
	file.parentFile.mkdirs()
	file.writeText(this.toString())
}
