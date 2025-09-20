package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.generators.ArgsGenerator
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.SuffixArgsGeneratorDecider

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SuffixArgsGeneratorNeverDecider : SuffixArgsGeneratorDecider {
	override fun computeSuffixArgsGenerator(annotationData: AnnotationData): ArgsGenerator<*>? = null
}
