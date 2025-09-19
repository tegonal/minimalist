package com.tegonal.minimalist.providers

import com.tegonal.minimalist.generators.ArgsGenerator

/**
 * Allows to define that a certain [ArgsGenerator] shall be combined as last.
 *
 * @since 2.0.0
 */
interface ArgsGeneratorSuffixDecider {

	/**
	 * Returns `null` in case no [ArgsGenerator] shall be combined last or a corresponding generator respectively.
	 */
	fun decide(annotationData: AnnotationData): ArgsGenerator<*>?
}
