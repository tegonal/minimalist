package com.tegonal.minimalist.config.impl

import ch.tutteli.kbox.failIf


/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun String.toIntOrErrorNotValid(convertTo: String): Int =
	toIntOrNull() ?: error("$this is not a valid $convertTo")

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun checkIsPositive(value: Int, description: String) =
	check(value > 0) { "$value is not a valid $description, has to be greater than 0" }

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun failIfNegative(value: Int, description: String) =
	failIf(value < 0) { "$value is not a valid $description, has to be greater than or equal to 0" }

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
fun <T> checkNoDuplicates(list: List<T>, errMsg: (duplicates: Set<T>) -> String) {
	val seen = HashSet<T>(list.size)
	val duplicates = HashSet<T>(4)
	list.forEach {
		if (seen.add(it).not()) {
			duplicates.add(it)
		}
	}
	check(duplicates.isEmpty()) {
		errMsg(duplicates)
	}
}
