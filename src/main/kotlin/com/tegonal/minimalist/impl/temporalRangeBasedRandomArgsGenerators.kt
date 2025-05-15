package com.tegonal.minimalist.impl

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
	representationProvider: ((A1) -> String)? = null
) : RangeBasedRandomArgsGenerator<A1, Args1<A1>>(
	from,
	toExclusive,
	{ temporal -> Args.of(temporal, representation1 = representationProvider?.let { Representation(it(temporal)) }) }
) where A1 : Temporal, A1 : Comparable<A1> {
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
	representationProvider: ((LocalDate) -> String)? = null
) : TemporalRangeRandomArgsGenerator<LocalDate>(
	from, toExclusive, temporalUnit,
	LocalDate::plus, representationProvider,
)

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
	representationProvider: ((LocalDateTime) -> String)? = null
) : TemporalRangeRandomArgsGenerator<LocalDateTime>(
	from, toExclusive, temporalUnit,
	LocalDateTime::plus, representationProvider,
)

class ZonedDateTimeRandomArgsGenerator(
	from: ZonedDateTime,
	toExclusive: ZonedDateTime,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	representationProvider: ((ZonedDateTime) -> String)? = null
) : TemporalRangeRandomArgsGenerator<ZonedDateTime>(
	from, toExclusive, temporalUnit,
	ZonedDateTime::plus, representationProvider,
)
