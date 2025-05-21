package com.tegonal.minimalist.generators.impl

import ch.tutteli.kbox.identity
import com.tegonal.minimalist.Args
import com.tegonal.minimalist.Args1
import com.tegonal.minimalist.Representation
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
abstract class TemporalRangeRandomArgsGenerator<A1>(
	from: A1,
	toExclusive: A1,
	private val temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	private val plusTyped: A1.(Long, TemporalUnit) -> A1,
) : RangeBasedRandomArgsGenerator<A1, A1>(from, toExclusive, ::identity) where A1 : Temporal, A1 : Comparable<A1> {
	private val diffInLong = temporalUnit.between(this.from, this.toExclusive)
	final override fun nextRandom(): A1 = from.plusTyped(Random.nextLong(diffInLong), temporalUnit)
}

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LocalDateRandomArgsGenerator(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeRandomArgsGenerator<LocalDate>(from, toExclusive, temporalUnit, LocalDate::plus)

/**
 * !! No backward compatibility guarantees !!
 * Reuse at your own risk
 *
 * @since 2.0.0
 */
class LocalDateTimeRandomArgsGenerator(
	from: LocalDateTime,
	toExclusive: LocalDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeRandomArgsGenerator<LocalDateTime>(from, toExclusive, temporalUnit, LocalDateTime::plus)

class ZonedDateTimeRandomArgsGenerator(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
) : TemporalRangeRandomArgsGenerator<ZonedDateTime>(from, toExclusive, temporalUnit, ZonedDateTime::plus)
