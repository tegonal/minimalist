package readme.examples

import org.junit.jupiter.api.Order
import readme.examples.jupiter.ReadmeTest

import org.junit.jupiter.api.Test

@Order(2)
class FirstParameterizedTestExample : ReadmeTest {

	@Test
	fun `code-first-1`() {
		//snippet-first-1a-insert

		//snippet-first-1b-insert
	}
}
