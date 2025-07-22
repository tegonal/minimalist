package com.tegonal.minimalist.testutils

import com.tegonal.minimalist.config.RandomFactory
import kotlin.random.Random

class MockedRandom(
	private val intIterator: Iterator<Int>,
	private val longIterator: Iterator<Long>,
	private val doubleIterator: Iterator<Double>,
) : Random() {
	override fun nextBits(bitCount: Int): Int = nextInt().takeUpperBits(32)

	private fun Int.takeUpperBits(bitCount: Int): Int =
		this.ushr(32 - bitCount) and (-bitCount).shr(31)


	override fun nextInt(): Int = intIterator.next()
	override fun nextInt(until: Int): Int = intIterator.next()
	override fun nextInt(from: Int, until: Int): Int = intIterator.next()
	override fun nextLong(): Long = longIterator.next()
	override fun nextLong(until: Long): Long = longIterator.next()
	override fun nextLong(from: Long, until: Long): Long = longIterator.next()
	override fun nextDouble(): Double = doubleIterator.next()
	override fun nextDouble(until: Double): Double = doubleIterator.next()
	override fun nextDouble(from: Double, until: Double): Double = doubleIterator.next()
}

class MockedRandomFactory(
	private val ints: List<Int> = emptyList(),
	private val longs: List<Long> = emptyList(),
	private val doubles: List<Double> = emptyList()
) : RandomFactory {

	override fun create(seed: Int): Random =
		MockedRandom(ints.iterator(), longs.iterator(), doubles.iterator())
}
