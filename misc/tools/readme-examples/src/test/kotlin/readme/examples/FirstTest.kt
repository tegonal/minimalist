//snippet-first-1a-start
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
//snippet-first-1a-end
import org.junit.jupiter.api.Order

@Order(1)
//snippet-first-1b-start
class FirstTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("myProvider")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}

	companion object {
		@JvmStatic
		fun myProvider() = 1..20
	}
}
//snippet-first-1b-end
