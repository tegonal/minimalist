package readme.examples

import com.tegonal.minimalist.generators.*
import org.junit.jupiter.api.Order
import java.time.LocalDate
import kotlin.test.Test

@Order(2)
class CombinatorsExample : PredefinedArgsProviders {

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
	fun `code-cartesian-1`() {
		ordered.of(1, 2).cartesian(ordered.of('A', 'B'))
	}

	@Test
	fun `code-cartesian-2`() {
		ordered.of(1, 2).cartesian(ordered.of(4, 5)) { i1, i2 ->
			i1 + i2
		}
	}

	@Test
	fun `code-zip-1`() {
		arb.intFromUntil(1, 100).zip(arb.charFromTo('A', 'Z'))
	}

	@Test
	fun `code-zip-2`() {
		arb.intFromUntil(1, 100).zip(arb.intFromUntil(1000, 2000)) { i1, i2 ->
			i1 + i2
		}
	}

	@Test
	fun `code-combine-dependent-arb`() {
		//snippet-combine-dependent-arb-insert
	}

	@Test
	fun `code-combine-dependent-ordered`() {
		//snippet-ordered-2-insert

		ordered.fromEnum<Color>().combineDependent({ color ->
			arb.hexColor(dominant = color)
		}) { _, hex -> hex }
	}

	// only a dummy method, so that it compiles
	private fun ArbExtensionPoint.hexColor(@Suppress("unused") dominant: Color): ArbArgsGenerator<String> =
		of("#FF0000")

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
	fun `code-map`() {
		val now = LocalDate.now()
		arb.localDateFromTo(now.withDayOfYear(1), now).map { localDate -> localDate.atTime(12, 0) }

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

	@Test
	fun `code-chunked`() {
		arb.intFromTo(1, 100).chunked(3)
		arb.intFromTo(1, 100).chunked(3) { it.sorted() }
		arb.charFromTo('a', 't').zip(arb.intFromTo(1, 100)).chunked(3) { it.toMap() }
	}

	@Test
	fun `code-concat`() {
		ordered.of(1, 2) + ordered.intFromTo(100, 120)

		// works with Iterable/Sequence as well
		(0..3).map {
			val offset = 10 * it
			ordered.of(0 + offset, 3 + offset)
		}.concatAll() // generates 0,3, 10,13, 20,23, 30,33, ...
	}

	@Test
	fun `code-mergeWeighted`() {
		arb.mergeWeighted(
			80 to arb.intFromUntil(100, 200),
			10 to arb.of(201),
			10 to arb.of(null)
		)
	}
}

