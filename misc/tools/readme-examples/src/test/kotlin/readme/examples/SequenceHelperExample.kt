package readme.examples

//snippet-repeat-forever-import-start
import com.tegonal.minimalist.utils.repeatForever
//snippet-repeat-forever-import-end
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest

class SequenceHelperExample : ReadmeTest {

	@Test
	fun `code-repeat-forever`() {
		//snippet-repeat-forever-import-insert

		// creates a Sequence which yields the given constant forever
		repeatForever(constant = 1)

		// creates a Sequence which yields 1, 2, 3 forever
		repeatForever(arrayOf(1, 2, 3), offset = 0)

		// creates a Sequence which yields 2, 3, 1 forever
		repeatForever(listOf(1, 2, 3), offset = 1)

		// repeats Unit forever and can be used as a building block
		repeatForever().flatMap { _ ->
			// will repeat 11, 22, 33 forever
			sequenceOf(1, 2, 3).mapIndexed { index, it -> it + (index + 1) * 10 }
		}
	}
}
