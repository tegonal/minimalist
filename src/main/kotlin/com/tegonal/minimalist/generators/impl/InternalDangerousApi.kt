package com.tegonal.minimalist.generators.impl


@RequiresOptIn(
	message = "Using this API can cause unexpected bugs if not used very carefully, try to use other building blocks whenever possible. No backward-compatibility is guaranteed and it may change or be removed in future versions of minimalist without prior notice."
)
@Retention(AnnotationRetention.BINARY)
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION,
	AnnotationTarget.PROPERTY,
	AnnotationTarget.CONSTRUCTOR
)
annotation class InternalDangerousApi
