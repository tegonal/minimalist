package com.tegonal.minimalist.generators.impl

import com.tegonal.minimalist.config.ComponentFactoryContainer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit
import kotlin.random.Random

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LocalDateFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalFromUntilArbArgsGenerator<LocalDate>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	temporalUnit,
	LocalDate::plus
)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LocalDateTimeFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalFromUntilArbArgsGenerator<LocalDateTime>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	temporalUnit,
	LocalDateTime::plus
)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class ZonedDateTimeFromUntilArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalFromUntilArbArgsGenerator<ZonedDateTime>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
	temporalUnit,
	ZonedDateTime::plus
)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
abstract class TemporalFromUntilArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	seedBaseOffset: Int,
	from: T,
	toExclusive: T,
	private val temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	private val plusTyped: T.(Long, TemporalUnit) -> T,
) : OpenEndRangeBasedArbArgsGenerator<T>(
	componentFactoryContainer,
	seedBaseOffset,
	from,
	toExclusive,
) where T : Temporal, T : Comparable<T> {
	private val diffInLong = temporalUnit.between(this.from, this.toExclusive)
	final override fun nextElementInRange(random: Random): T =
		from.plusTyped(random.nextLong(0, diffInLong), temporalUnit)
}
