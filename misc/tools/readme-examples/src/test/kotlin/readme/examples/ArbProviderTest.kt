package readme.examples

import com.tegonal.variist.generators.arb
import com.tegonal.variist.generators.intFromTo
import com.tegonal.variist.providers.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest

import org.junit.jupiter.api.Order

@Order(1)
//snippet-arb-provider-start
class ArbProviderTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("arb1To50000")
	fun positiveNumberTimesMinusOneIsNegative(positiveNumber: Int) {
		assertTrue(positiveNumber * -1 < 0)
	}

	companion object {
		@JvmStatic
		fun arb1To50000() = arb.intFromTo(1, 50_000)
	}
}
//snippet-arb-provider-end
