package com.tegonal.minimalist.providers

import java.lang.reflect.Method

interface AnnotationDataDeducer {
	fun deduce(testMethod: Method, argsSourceMethodName: String): AnnotationData?
}
