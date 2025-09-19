package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.ArgsGeneratorSuffixDecider

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class EmptyArgsGeneratorSuffixDecider : ArgsGeneratorSuffixDecider {
	override fun decide(annotationData: AnnotationData): ArgsGenerator<*>? = null
}
