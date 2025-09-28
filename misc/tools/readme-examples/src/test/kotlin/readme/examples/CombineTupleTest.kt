package readme.examples

//snippet-combine-tuple-import-start
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import org.junit.jupiter.params.ParameterizedTest
//snippet-combine-tuple-import-end
import org.junit.jupiter.api.Order

@Suppress("unused", "UNUSED_PARAMETER")
@Order(1)
//snippet-combine-tuple-start
class CombineTupleTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("ageAndName")
	fun foo(age: Int, name: String) {
		//...
	}

	companion object {
		@JvmStatic
		fun ageAndName() = Tuple(
			ordered.intFromTo(15, 30),
			arb.string(minLength = 3, maxLength = 50)
		)
	}
}
//snippet-combine-tuple-end

