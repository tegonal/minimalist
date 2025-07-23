package com.tegonal.minimalist.utils.impl

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
inline fun <reified T : Any> Annotation.getPropertyValue(propertyName: String): T =
	getPropertyValue(propertyName, T::class)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun <T : Any> Annotation.getPropertyValue(propertyName: String, type: KClass<T>): T {
	val propertyUntyped = annotationClass.memberProperties.find { it.name == propertyName }
		?: error("The annotation $this doesn't contain a property named $propertyName")
	val propertyType = propertyUntyped.returnType.jvmErasure
	val property = if (propertyType == type) {
		@Suppress("UNCHECKED_CAST") // is actually checked
		propertyUntyped as KProperty1<out Annotation, T>
	} else error("The property $propertyName in annotation $this does not have a specified type ${type.qualifiedName} but ${propertyType.qualifiedName}")

	return property.call(this)
}
