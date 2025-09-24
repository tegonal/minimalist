package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.ArgsSourceOptions
import com.tegonal.minimalist.providers.BaseAnnotationDataDeducer
import com.tegonal.minimalist.providers.fromOptions

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class DefaultAnnotationDataDeducer : BaseAnnotationDataDeducer<ArgsSourceOptions>(ArgsSourceOptions::class) {

	override fun annotationToAnnotationData(
		argsSourceMethodName: String,
		annotation: ArgsSourceOptions
	): AnnotationData = AnnotationData.fromOptions(argsSourceMethodName, annotation)
}
