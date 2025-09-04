package readme.examples

import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.intFromTo
import com.tegonal.minimalist.generators.mapIndexed
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.generators.transform
import com.tegonal.minimalist.generators.transformMaterialised
import org.junit.jupiter.api.Order
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
			sequence.flatMap {  }
		}
		ordered.intFromTo(1,2).mapIndexed {  }
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
}

