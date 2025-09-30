package readme.examples

//snippet-dynamic-test-import-start
import com.tegonal.minimalist.generators.arb
import com.tegonal.minimalist.generators.generateAndTakeBasedOnDecider
import com.tegonal.minimalist.generators.string
import com.tegonal.minimalist.generators.zip
import com.tegonal.minimalist.providers.PredefinedNumberProviders.Companion.arbIntPositive
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
//snippet-dynamic-test-import-end

import org.junit.jupiter.api.Order

@Order(1)
//snippet-dynamic-test-1-start
class DynamicTest : PredefinedArgsProviders {

	@TestFactory
	fun arbExample() =
		arbIntPositive().zip(arb.string(maxLength = 20))
			.generateAndTakeBasedOnDecider()
			.map { (positiveNumber, label) ->
				dynamicTest("$positiveNumber $label") {
					assertTrue(positiveNumber * -1 < 0)
				}
			}
}
//snippet-dynamic-test-1-end
