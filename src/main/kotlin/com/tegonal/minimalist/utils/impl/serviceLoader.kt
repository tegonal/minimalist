package com.tegonal.minimalist.utils.impl

import java.util.*

inline fun <reified T : Any> loadService(qualifiedName: String): T {
	val klass = T::class
	val allServices = ServiceLoader.load(klass.java)
	return allServices.find { service ->
		service::class.qualifiedName?.let { it == qualifiedName } ?: false
	} ?: error(
		"The specified ${klass.simpleName} $qualifiedName could not be found, make sure it is on the classpath and defined as Service (META-INF/services)"
	)
}
