package com.tegonal.minimalist.generators

import ch.tutteli.kbox.Tuple
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class RandomArgsDateLikeGeneratorTest : AbstractRandomArgsGeneratorTest<Any>() {

	override fun createGenerators() = ZonedDateTime.now().let { nowZonedDateTime ->
		val nowLocalDateTime = nowZonedDateTime.toLocalDateTime()
		val nowLocalDate = nowZonedDateTime.toLocalDate()
		sequenceOf(
			Tuple(
				"localDateTimeFromUntil",
				modifiedRandom.zonedDateTimeFromUntil(nowZonedDateTime, nowZonedDateTime.plusHours(3), ChronoUnit.MINUTES),
				(0L until 3 * 60).map { nowZonedDateTime.plusMinutes(it) }
			),
			Tuple(
				"localDateTimeFromUntil",
				modifiedRandom.localDateTimeFromUntil(nowLocalDateTime, nowLocalDateTime.plusDays(1), ChronoUnit.HOURS),
				(0L..23).map { nowLocalDateTime.plusHours(it) }
			),
			Tuple(
				"localDateTimeFromUntil",
				modifiedRandom.localDateFromUntil(nowLocalDate, nowLocalDate.plusDays(2), ChronoUnit.DAYS),
				listOf(nowLocalDate, nowLocalDate.plusDays(1))
			),
		)
	}
}
