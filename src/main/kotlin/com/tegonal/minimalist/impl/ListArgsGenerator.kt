package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.OrderedArgsGenerator

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [List].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 2.0.0
 */
class ListArgsGenerator<T : Args>(private val values: List<T>) : RandomAccessArgsGenerator<T>() {
	override fun elementAt(index: Int): T = values[index]
	override val size: Int = values.size
}
