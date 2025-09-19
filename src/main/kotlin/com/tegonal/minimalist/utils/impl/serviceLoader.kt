package com.tegonal.minimalist.utils.impl

import java.util.*

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <reified T : Any> loadService(qualifiedName: String): T {
	val allServices = loadServices<T>()
	return allServices.find { service ->
		service::class.qualifiedName?.let { it == qualifiedName } ?: false
	} ?: error(
		"The specified ${T::class.simpleName} $qualifiedName could not be found, make sure it is on the classpath and defined as Service (META-INF/services)"
	)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <reified T : Any> loadServices(): ServiceLoader<T> = ServiceLoader.load(T::class.java)
