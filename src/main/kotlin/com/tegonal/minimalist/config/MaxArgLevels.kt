package com.tegonal.minimalist.config

import java.lang.IllegalArgumentException

/**
 * @since 2.0.0
 */
class MaxArgLevels(val maxArgsInGeneral: Int, levels: Map<Int, Int>) {
	private val levels: Map<Int, Int>

	init {
		check(levels.isNotEmpty()) {
			"You need to define at least level 1"
		}
		val sortedLevels = levels.keys.sorted()

		val lowestLevel = sortedLevels.first()
		check(lowestLevel == 1) {
			"The smallest level has to be level 1, given $lowestLevel"
		}
		val lowestLevelValue = levels[lowestLevel]!!
		check(lowestLevelValue > 0) {
			"Level 1 has to be greater than 0"
		}

		val highestLevel = sortedLevels.last()
		val highestLevelValue = levels[highestLevel]!!
		check(highestLevelValue <= maxArgsInGeneral) {
			"The highest level $highestLevel ($highestLevelValue) has to be less than the defined maxArgsInGeneral ($maxArgsInGeneral)"
		}

		val numOfLevels = sortedLevels.size
		sortedLevels.forEachIndexed { index, thisKey ->
			if (index + 1 < numOfLevels) {
				val nextKey = sortedLevels[index + 1]
				val thisLevel = levels[thisKey]!!
				val nextLevel = levels[nextKey]!!

				check(thisLevel < nextLevel) {
					"Level $thisKey ($thisLevel) has to be less than $nextKey ($nextLevel)"
				}
			}
		}
		this.levels = levels.toMap()
	}


	fun toHashMap(): HashMap<Int, Int> = HashMap(levels)
	fun getLevel(level: Int): Int = levels[level] ?: throw IllegalArgumentException("Level $level not defined")

	override fun toString(): String =
		"MaxArgLevels(${levels.entries.joinToString(", ") { (k, v) -> "$k = $v" }})"
}
