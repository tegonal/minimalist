package com.tegonal.minimalist.config

import ch.tutteli.kbox.mapFirst
import com.tegonal.minimalist.config.impl.checkNoDuplicates

class CategorizedMaxArgsLevels private constructor(maxArgsLevelPerCategory: Map<String, MaxArgsLevels>) {
	private val categoryToMaxArgsLevel: Map<String, MaxArgsLevels>

	init {
		check(maxArgsLevelPerCategory.isNotEmpty()) {
			"You need to define at least one category"
		}
		val categoryToLevels = maxArgsLevelPerCategory.map { (k, v) -> k to v.toHashMap() }
		val allLevels = categoryToLevels.flatMap { it.second.keys }.toSet()
		categoryToLevels.forEach { (category, levels) ->
			val diff = allLevels - levels.keys
			check(diff.isEmpty()) {
				"not all levels are defined in category $category, the following were defined in other categories but not in this one: ${
					diff.joinToString(", ")
				}"
			}
		}
		categoryToMaxArgsLevel = maxArgsLevelPerCategory.toMap()
	}

	operator fun contains(category: String) = categoryToMaxArgsLevel.contains(category)

	operator fun get(category: String): MaxArgsLevels =
		find(category) ?: throw IllegalArgumentException(
			"Category $category not defined, available categories: ${
				categoryToMaxArgsLevel.keys.joinToString(", ")
			}"
		)

	fun find(category: String): MaxArgsLevels? = categoryToMaxArgsLevel[category]

	fun categories(): Set<String> = categoryToMaxArgsLevel.keys

	fun toHashMap(): HashMap<String, HashMap<String, Int>> =
		HashMap<String, HashMap<String, Int>>(categoryToMaxArgsLevel.size)
			.also { it.putAll(categoryToMaxArgsLevel.entries.map { (k, v) -> k to v.toHashMap() }) }

	companion object {
		/**
		 * Creates a [CategorizedMaxArgsLevels] based on the given [levels]
		 * which use one of the predefined [MaxArgsCategory] as category names.
		 */
		fun create(vararg levels: Pair<MaxArgsCategory, MaxArgsLevels>): CategorizedMaxArgsLevels {
			checkNoDuplicates(levels.map { it.first }) { duplicates ->
				"Looks like you defined some categories multiple times: ${duplicates.joinToString(", ")}"
			}
			return create(levels.associate { it.mapFirst { category -> category.name } })
		}

		/**
		 * Creates a [CategorizedMaxArgsLevels] based on the given [levels] which allows to specify custom
		 * category names.
		 *
		 * Also take a look at the overload which expects one of the predefined [MaxArgsCategory] as category names.
		 */
		fun create(levels: Map<String, MaxArgsLevels>): CategorizedMaxArgsLevels =
			CategorizedMaxArgsLevels(levels)
	}
}
