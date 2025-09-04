package readme.examples

//snippet-ordered-1-start
import com.tegonal.minimalist.generators.*
//snippet-ordered-1-end

import readme.examples.jupiter.ReadmeTest
import kotlin.test.Test

class OrderedExample : ReadmeTest {

	@Test
	fun `code-ordered-1`() {
		//snippet-ordered-1-insert

		//snippet-ordered-2-insert

		ordered.of(1, 3, 2)
		ordered.fromEnum<Color>()
		ordered.fromList(listOf(6, 8, 1))
		ordered.fromArray(arrayOf(4, 2, 7))
		ordered.fromRange(1..10)
		ordered.fromProgression(1..10 step 2)

		ordered.intFromUntil(1, 5)
		ordered.longFromTo(1, 5)
		//...
	}
}

//snippet-ordered-2-start
enum class Color {
	Red, Blue, Green
}
//snippet-ordered-2-end
