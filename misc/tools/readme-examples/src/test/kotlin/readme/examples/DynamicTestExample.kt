package readme.examples

import com.tegonal.minimalist.config.ArgsRangeOptions
import com.tegonal.minimalist.generators.generateAndTakeBasedOnDecider
import com.tegonal.minimalist.generators.intFromUntil
import com.tegonal.minimalist.generators.ordered
import com.tegonal.minimalist.providers.AnnotationData
import com.tegonal.minimalist.providers.outsideParameterizedTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Order
import readme.examples.jupiter.ReadmeTest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

@Order(2)
class DynamicTestExample : ReadmeTest {

	@Test
	fun `code-dynamic-test-1`() {
		//snippet-dynamic-test-import-insert

		//snippet-dynamic-test-1-insert
	}

	@Test
	fun `code-dynamic-test-2`() {

		//snippet-dynamic-test-2-insert
	}

	//snippet-dynamic-test-2-start
	@TestFactory
	fun orderedExample() =
		ordered.intFromUntil(1, 100)
			.generateAndTakeBasedOnDecider(
				AnnotationData.outsideParameterizedTest(
					argsRangeOptions = ArgsRangeOptions(profile = "Integration", maxArgs = 20)
				)
			)
			.map { positiveNumber ->
				dynamicTest("$positiveNumber") {
					assertTrue(positiveNumber * -1 < 0)
				}
			}
	//snippet-dynamic-test-2-end
}
