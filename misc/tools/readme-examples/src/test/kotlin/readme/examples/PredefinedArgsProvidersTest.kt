//snippet-predefined-1a-start
package readme.examples

import com.tegonal.minimalist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest

//snippet-predefined-1a-end
import org.junit.jupiter.api.Order

@Order(1)
//snippet-predefined-1b-start
class PredefinedArgsProvidersTest : PredefinedArgsProviders {

	@ParameterizedTest
	// uses the predefined ArbArgsGenerator arbIntPositive defined in PredefinedArgsProviders
	@ArgsSource("arbIntPositive")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}
}
//snippet-predefined-1b-end
