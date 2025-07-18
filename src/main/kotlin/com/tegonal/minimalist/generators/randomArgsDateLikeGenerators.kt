package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.LocalDateRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.LocalDateTimeRandomArgsGenerator
import com.tegonal.minimalist.generators.impl.ZonedDateTimeRandomArgsGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

/**
 * Returns an [RandomArgsGenerator] which generates [LocalDate]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [RandomArgsGenerator] which generates [LocalDate]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.localDateFromUntil(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
): RandomArgsGenerator<LocalDate> = LocalDateRandomArgsGenerator(_components, from, toExclusive, temporalUnit)

/**
 * Returns an [RandomArgsGenerator] which generates [LocalDateTime]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [RandomArgsGenerator] which generates [LocalDateTime]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.localDateTimeFromUntil(
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit,
): RandomArgsGenerator<LocalDateTime> = LocalDateTimeRandomArgsGenerator(_components, from, toExclusive, temporalUnit)

/**
 * Returns an [RandomArgsGenerator] which generates [ZonedDateTime]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [RandomArgsGenerator] which generates [ZonedDateTime]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun RandomExtensionPoint.zonedDateTimeFromUntil(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit,
): RandomArgsGenerator<ZonedDateTime> = ZonedDateTimeRandomArgsGenerator(_components, from, toExclusive, temporalUnit)
