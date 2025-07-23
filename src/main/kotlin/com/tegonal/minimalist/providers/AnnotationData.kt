package com.tegonal.minimalist.providers

/**
 * @since 2.0.0
 */
data class AnnotationData(
	val argsSourceMethodName: String,
	val fixedNumberOfArgs: Int? = null,
	val fixedOffset: Int? = null,
) {
	init {
		check(argsSourceMethodName.isNotBlank()) {
			"It looks like you forgot to specify the methodName (was empty or blank)"
		}
		fixedOffset?.also { check(it >= 0) }
		fixedNumberOfArgs?.also { check(it > 0) }
	}

	companion object
}

/**
 * @since 2.0.0
 */
fun AnnotationData.merge(other: AnnotationData): AnnotationData {
	check(other.argsSourceMethodName == this.argsSourceMethodName) {
		val className = AnnotationData::class.simpleName
		"looks like an error, this $className ${AnnotationData::argsSourceMethodName.name} ($argsSourceMethodName) is not the same as the other (${other.argsSourceMethodName}). Most likely the two $className should not be merged"
	}
	return copy(
		fixedNumberOfArgs = other.fixedNumberOfArgs ?: this.fixedNumberOfArgs,
		fixedOffset = other.fixedOffset ?: this.fixedOffset,
	)
}


/**
 * @since 2.0.0
 */
fun AnnotationData.Companion.fromOptions(
	argsSourceMethodName: String,
	argsSourceOptions: ArgsSourceOptions
): AnnotationData = AnnotationData(
	argsSourceMethodName = argsSourceMethodName,
	fixedNumberOfArgs = argsSourceOptions.fixedNumberOfArgs.takeIf { it > 0 },
	fixedOffset = argsSourceOptions.fixedOffset.takeIf { it >= 0 },
)
