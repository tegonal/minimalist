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


abstract class TemporalRangeRandomArgsGenerator<E>(
	from: E,
	toExclusive: E,
	private val temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	private val plusTyped: E.(Long, TemporalUnit) -> E,
	representationProvider: ((E) -> String)? = null
) : RangeBasedRandomArgsGenerator<E, Args1<E>>(
	from,
	toExclusive,
	{ date -> Args.of(date, representation1 = representationProvider?.let { Representation(it(date)) }) }
) where E : Temporal, E : Comparable<E> {
	private val diffInLong = temporalUnit.between(this.from, this.toExclusive)
	final override fun nextRandom(): E = from.plusTyped(Random.nextLong(diffInLong), temporalUnit)
}

class LocalDateRandomArgsGenerator(
	from: LocalDate,
	toExclusive: LocalDate,
	temporalUnit: TemporalUnit = ChronoUnit.DAYS,
	representationProvider: ((LocalDate) -> String)? = null
) : TemporalRangeRandomArgsGenerator<LocalDate>(
	from, toExclusive, temporalUnit,
	LocalDate::plus, representationProvider,
)

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
