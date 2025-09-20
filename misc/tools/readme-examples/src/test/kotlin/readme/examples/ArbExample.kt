package readme.examples

import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.utils.BigInt
import readme.examples.jupiter.ReadmeTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.test.Test

class ArbExample : ReadmeTest {

	@Test
	fun `code-arb-1`() {
		arb.of(1, 2, 3)
		arb.fromEnum<Color>()
		arb.fromList(listOf(1, 2, 3))
		arb.fromArray(arrayOf(1, 2, 3))
		//...

		arb.int()
		arb.intPositive()
		arb.longNegative()
		arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
		//...

		LocalDate.now().let { now ->
			arb.localDateFromUntil(now, now.plusMonths(4))
		}
		LocalDateTime.now().let { now ->
			arb.localDateTimeFromUntil(now, now.plusHours(48), ChronoUnit.MINUTES)
		}
		//...

		arb.intRange(minInclusive = 1, maxInclusive = 1000, minSize = 3, maxSize = 10)
		arb.longBounds(minInclusive = -10, maxInclusive = 10, minSize = 0)
		//...

		arb.string(minLength = 0, maxLength = 20, allowedRanges = UnicodeRanges.ASCII_PRINTABLE.ranges)
	}

}
