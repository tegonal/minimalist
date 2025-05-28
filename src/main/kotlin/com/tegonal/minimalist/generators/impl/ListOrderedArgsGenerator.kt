package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.generators.OrderedArgsGenerator

/**
 * Represents an [OrderedArgsGenerator] which is based on a given [List].
 *
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ListOrderedArgsGenerator<T>(values: List<T>) : RandomAccessOrderedArgsGenerator<T>(values.size, { values[it] })
