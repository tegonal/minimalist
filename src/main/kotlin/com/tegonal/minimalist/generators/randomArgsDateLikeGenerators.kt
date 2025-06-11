package com.tegonal.minimalist.generators

import com.tegonal.minimalist.generators.impl.LocalDateRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.LocalDateTimeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.ZonedDateTimeRandomArgsGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

/**
 * @since 2.0.0
 */
fun random.localDateFromUntil(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
): RandomArgsGenerator<LocalDate> = LocalDateRandomArgsGenerator(from, toExclusive, temporalUnit)

/**
 * @since 2.0.0
 */
fun random.localDateTimeFromUntil(
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit,
): RandomArgsGenerator<LocalDateTime> = LocalDateTimeRandomArgsGenerator(from, toExclusive, temporalUnit)

/**
 * @since 2.0.0
 */
fun random.zonedDateTimeFromUntil(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit,
): RandomArgsGenerator<ZonedDateTime> = ZonedDateTimeRandomArgsGenerator(from, toExclusive, temporalUnit)
