//snippet-first-import-start
package readme.examples

import com.tegonal.variist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
//snippet-first-import-end
import org.junit.jupiter.api.Order

@Order(1)
//snippet-first-start
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
//snippet-first-end
