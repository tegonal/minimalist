package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.OrderedArgsGenerator

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [List].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListOrderedArgsGenerator<T : Args>(private val values: List<T>) : RandomAccessOrderedArgsGenerator<T>() {
	override fun elementAt(index: Int): T = values[index]
	override val size: Int = values.size
}
