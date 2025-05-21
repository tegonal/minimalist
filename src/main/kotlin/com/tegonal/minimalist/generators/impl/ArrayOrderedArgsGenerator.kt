package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [Array].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ArrayOrderedArgsGenerator<T>(private val values: Array<out T>) : RandomAccessOrderedArgsGenerator<T>() {
	override fun elementAt(index: Int): T = values[index]
	override val size: Int = values.size
}
