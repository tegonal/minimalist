package readme.examples

import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.combineDependent
import com.tegonal.minimalist.generators.intFromTo
import com.tegonal.minimalist.providers.ArgsSource
import org.junit.jupiter.api.Order
import org.junit.jupiter.params.ParameterizedTest
import kotlin.test.assertTrue

@Suppress("unused")
@Order(1)
//snippet-combine-dependent-start
class CombineTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("moreThan10InSum")
	fun foo(a: Int, b: Int) {
		assertTrue(a + b > 10)
	}

	companion object {
		@JvmStatic
		fun moreThan10InSum() = arb.intFromTo(1, 10).combineDependent { a ->
			arb.intFromTo(11 - a, 10)
		}
	}
}
//snippet-combine-dependent-end

