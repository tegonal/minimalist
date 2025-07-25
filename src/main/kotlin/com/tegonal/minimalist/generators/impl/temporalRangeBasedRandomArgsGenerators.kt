package com.tegonal.minimalist.generators.impl

import ch.tutteli.kbox.identity
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
class LocalDateArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeArbArgsGenerator<LocalDate>(
	componentFactoryContainer,
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
class LocalDateTimeArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeArbArgsGenerator<LocalDateTime>(
	componentFactoryContainer,
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
class ZonedDateTimeArbArgsGenerator(
	componentFactoryContainer: ComponentFactoryContainer,
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeArbArgsGenerator<ZonedDateTime>(
	componentFactoryContainer,
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
abstract class TemporalRangeArbArgsGenerator<T>(
	componentFactoryContainer: ComponentFactoryContainer,
	from: T,
	toExclusive: T,
	private val temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	private val plusTyped: T.(Long, TemporalUnit) -> T,
) : RangeBasedArbArgsGenerator<T, T>(
	componentFactoryContainer,
	from,
	toExclusive,
	::identity
) where T : Temporal, T : Comparable<T> {
	private val diffInLong = temporalUnit.between(this.from, this.toExclusive)
	final override fun nextRandom(random: Random): T =
		from.plusTyped(random.nextLong(0, diffInLong), temporalUnit)
}
