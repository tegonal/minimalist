package readme.examples

import com.tegonal.variist.generators.*
import com.tegonal.variist.providers.ArgsSource
import org.junit.jupiter.api.Order
import org.junit.jupiter.params.ParameterizedTest

@Suppress("UNUSED_PARAMETER")
@Order(1)
class SuffixArgsGeneratorDeciderTest : PredefinedArgsProviders {

	//snippet-suffix-args-generator-start
	@ParameterizedTest
	@ArgsSource("arbIntPositive") // comes from PredefinedArgsProviders
	fun foo(i: Int, c: Color) {
		// the argument i comes from arbIntPositive
		// the argument c comes from the SuffixArgsGeneratorDecider
	}
	//snippet-suffix-args-generator-end

	companion object {
		@JvmStatic
		fun arbIntPositive() = arb.intFromTo(1, 10).zip(arb.fromEnum<Color>())
	}
}

