package com.tegonal.minimalist.utils.impl

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ForeverUnitSequence() : Sequence<Unit> {
	override fun iterator(): Iterator<Unit> = ForeverUnitIterator()
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ForeverUnitIterator : Iterator<Unit> {
	override fun hasNext() = true
	override fun next() {}
}
