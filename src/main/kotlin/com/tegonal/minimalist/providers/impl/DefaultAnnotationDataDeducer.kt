package com.tegonal.minimalist.providers.impl

import com.tegonal.minimalist.export.org.junit.platform.commons.util.AnnotationUtils
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.AnnotationDataDeducer
import com.tegonal.minimalist.providers.ArgsSourceOptions
import com.tegonal.minimalist.providers.fromOptions
import com.tegonal.minimalist.providers.merge
import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Method
import kotlin.jvm.optionals.getOrNull

class DefaultAnnotationDataDeducer : AnnotationDataDeducer {

	override fun deduce(
		testMethod: Method,
		argsSourceMethodName: String
	): AnnotationData? {
		val classData = deduceData(testMethod.declaringClass, argsSourceMethodName)
		val methodData = deduceData(testMethod, argsSourceMethodName)
		return when {
			classData != null && methodData != null -> classData.merge(methodData)
			classData != null && methodData == null -> classData
			methodData != null -> methodData
			else -> null
		}
	}

	private fun deduceData(element: AnnotatedElement, argsSourceMethodName: String): AnnotationData? =
		AnnotationUtils.findAnnotation(element, ArgsSourceOptions::class.java)
			.map { AnnotationData.fromOptions(argsSourceMethodName, it) }.getOrNull()
}
