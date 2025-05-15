package com.tegonal.minimalist

import com.tegonal.minimalist.impl.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit
import kotlin.random.Random

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.localDateFromUntil(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	representationProvider: ((LocalDate) -> String)? = null
): RandomArgsGenerator<Args1<LocalDate>> =
	LocalDateRandomArgsGenerator(from, toExclusive, temporalUnit, representationProvider)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.localDateTimeFromUntil(
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit,
	representationProvider: ((LocalDateTime) -> String)? = null
): RandomArgsGenerator<Args1<LocalDateTime>> =
	LocalDateTimeRandomArgsGenerator(from, toExclusive, temporalUnit, representationProvider)

/**
 * @since 2.0.0
 */
fun RandomArgsGenerator.Companion.zonedDateTimeFromUntil(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit,
	representationProvider: ((ZonedDateTime) -> String)? = null
): RandomArgsGenerator<Args1<ZonedDateTime>> =
	ZonedDateTimeRandomArgsGenerator(from, toExclusive, temporalUnit, representationProvider)
