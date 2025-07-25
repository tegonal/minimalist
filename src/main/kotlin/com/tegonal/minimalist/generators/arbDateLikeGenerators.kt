package com.tegonal.minimalist.generators

import com.tegonal.minimalist.config._components
import com.tegonal.minimalist.generators.impl.LocalDateArbArgsGenerator
import com.tegonal.minimalist.generators.impl.LocalDateTimeArbArgsGenerator
import com.tegonal.minimalist.generators.impl.ZonedDateTimeArbArgsGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

/**
 * Returns an [ArbArgsGenerator] which generates [LocalDate]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [ArbArgsGenerator] which generates [LocalDate]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.localDateFromUntil(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
): ArbArgsGenerator<LocalDate> = LocalDateArbArgsGenerator(_components, from, toExclusive, temporalUnit)

/**
 * Returns an [ArbArgsGenerator] which generates [LocalDateTime]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [ArbArgsGenerator] which generates [LocalDateTime]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.localDateTimeFromUntil(
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit,
): ArbArgsGenerator<LocalDateTime> = LocalDateTimeArbArgsGenerator(_components, from, toExclusive, temporalUnit)

/**
 * Returns an [ArbArgsGenerator] which generates [ZonedDateTime]s ranging [from] (inclusive) to [toExclusive]
 * where [temporalUnit] defines the steps.
 *
 * @return an [ArbArgsGenerator] which generates [ZonedDateTime]s ranging [from] (inclusive) to [toExclusive].
 * @since 2.0.0
 */
fun ArbExtensionPoint.zonedDateTimeFromUntil(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit,
): ArbArgsGenerator<ZonedDateTime> = ZonedDateTimeArbArgsGenerator(_components, from, toExclusive, temporalUnit)
