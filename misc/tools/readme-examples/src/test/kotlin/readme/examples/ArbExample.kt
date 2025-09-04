package readme.examples

import com.tegonal.minimalist.generators.*
import readme.examples.jupiter.ReadmeTest
import kotlin.test.Test

class ArbExample : ReadmeTest {

	@Test
	fun `code-arb-1`() {
		arb.of(1, 2, 3)
		arb.fromEnum<Color>()
		arb.int()
		arb.intPositive()
		arb.longNegative()

		arb.fromList(listOf(1, 2, 3))
		arb.fromArray(arrayOf(1, 2, 3))
		//...
	}

}
