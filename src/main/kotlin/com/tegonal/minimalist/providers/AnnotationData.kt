package com.tegonal.minimalist.providers

import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.config.merge

/**
 * @since 2.0.0
 */
class AnnotationData(
	val argsSourceMethodName: String,
	val argsRangeOptions: ArgsRangeOptions = ArgsRangeOptions(),
	/**
	 * Generic map for extensions of Minimalist (or your own custom code), intended to be filled by an
	 * [AnnotationDataDeducer] and later be consumed by e.g. [ArgsRangeDecider], [SuffixArgsGeneratorDecider].
	 */
	val extensionData: Map<String, Any> = emptyMap()
) {
	init {
		check(argsSourceMethodName.isNotBlank()) {
			"It looks like you forgot to specify the methodName (was empty or blank)"
		}
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
	return AnnotationData(
		argsSourceMethodName,
		argsRangeOptions = this.argsRangeOptions.merge(other.argsRangeOptions),
		extensionData = this.extensionData + other.extensionData
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
	argsRangeOptions = ArgsRangeOptions(
		profile = argsSourceOptions.profile.takeIf { it.isNotEmpty() },
		requestedMinArgs = argsSourceOptions.requestedMinArgs.takeIf { it > 0 },
		maxArgs = argsSourceOptions.maxArgs.takeIf { it > 0 },
	),
)
