package readme.examples

import com.tegonal.minimalist.generators.*
import org.junit.jupiter.api.Order
import java.time.LocalDate
import kotlin.test.Test

@Order(2)
class TransformExample : PredefinedArgsProviders {

	@Test
	fun `code-transform`() {
		arb.intFromTo(1, 10).transform { sequence ->
			// return a generated value twice
			sequence.flatMap { listOf(it, it) }
		}

		ordered.intFromTo(1, 10).transformMaterialised { sequence ->
			sequence.zipWithNext()
		}
	}

	@Test
	fun `code-combine-tuple`() {
		//snippet-combine-tuple-import-insert

		//snippet-combine-tuple-insert
	}

	@Test
	fun `code-combine-manually`() {
		//snippet-combine-manually-insert
	}

	@Test
	fun `code-combine-dependent`() {
		//snippet-combine-dependent-insert
	}


	@Test
	fun `code-map`() {
		LocalDate.now().let { now -> arb.localDateFromTo(now.withDayOfYear(1), now) }
			.map { localDate -> localDate.atTime(12, 0) }

		ordered.intFromTo('A'.code, 'Z'.code).map { it.toChar() }
	}

	@Test
	fun `code-filter`() {
		arb.intFromUntil(1, 1000).filterNot { it == 523 }
		ordered.intFromUntil(1, 1000).filterNotMaterialised { it == 523 }
	}

	@Test
	fun `code-dont-filter`() {
		// prefer a progression as follows ...
		arb.fromProgression(1..1000 step 2)
		// ... instead of filter (which is slower)
		arb.intFromTo(1, 1000).filter { it % 2 == 1 }
	}
}

