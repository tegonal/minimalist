package com.tegonal.minimalist.impl

import com.tegonal.minimalist.Args
import com.tegonal.minimalist.OrderedArgsGenerator

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [Array].
 *
 * !! No backward compatibility guarantees !!
 * Re-use on own risk
 *
 * @since 2.0.0
 */
class ArrayArgsGenerator<T : Args>(private val values: Array<out T>) : RandomAccessArgsGenerator<T>() {
    override fun elementAt(index: Int): T = values[index]
    override val size: Int = values.size
}
