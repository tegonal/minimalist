package com.tegonal.variist.providers.impl

import com.tegonal.variist.generators.ArgsGenerator
import com.tegonal.variist.providers.AnnotationData
import com.tegonal.variist.providers.SuffixArgsGeneratorDecider

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class SuffixArgsGeneratorNeverDecider : SuffixArgsGeneratorDecider {
	override fun computeSuffixArgsGenerator(annotationData: AnnotationData): ArgsGenerator<*>? = null
}
