package com.tegonal.minimalist.config

import ch.tutteli.kbox.mapFirst
import com.tegonal.minimalist.config.impl.checkIsPositive
import com.tegonal.minimalist.config.impl.checkNoDuplicates
import java.lang.IllegalArgumentException
import kotlin.collections.contains


/**
 * @since 2.0.0
 */
class MaxArgsLevels private constructor(levels: Map<String, Int>) {
	private val levels: Map<String, Int>

	init {
		check(levels.isNotEmpty()) {
			"You need to define at least one level"
		}
		levels.forEach { (key, level) ->
			checkIsPositive(level, "maxArgs (of level $key)")
		}
		this.levels = levels.toMap()
	}

	operator fun contains(level: String) = levels.contains(level)

	operator fun get(level: String): Int =
		levels[level] ?: throw IllegalArgumentException(
			"Level $level not defined, available levels: ${levels.keys.joinToString(", ")}"
		)

	fun levels(): Set<String> = levels.keys

	fun toHashMap(): HashMap<String, Int> = HashMap(levels)

	override fun toString(): String =
		"MaxArgLevels(${levels.entries.joinToString(", ") { (k, v) -> "$k = $v" }})"

	companion object {
		/**
		 * Creates a [MaxArgsLevels] based on the given [levels]
		 * which use one of the predefined [MaxArgsLevel] as level names.
		 */
		fun create(vararg levels: Pair<MaxArgsLevel, Int>): MaxArgsLevels {
			checkNoDuplicates(levels.map { it.first }) { duplicates ->
				"Looks like you defined some levels multiple times: ${duplicates.joinToString(", ")}"
			}
			return create(levels.associate { it.mapFirst { level -> level.name } })
		}

		/**
		 * Creates a [MaxArgsLevels] based on the given [levels]
		 * which allows to specify custom level names.
		 *
		 * Also take a look at the overload which expects one of the predefined [MaxArgsLevel] as level names.
		 */
		fun create(levels: Map<String, Int>): MaxArgsLevels =
			MaxArgsLevels(levels)
	}
}

/**
 * Predefined level names for [MaxArgsLevels].
 *
 * @since 2.0.0
 */
enum class MaxArgsLevel {
	Local, PR, Main, Nightly, Release
}
