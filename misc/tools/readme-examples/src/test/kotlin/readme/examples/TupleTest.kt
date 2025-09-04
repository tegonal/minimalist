package readme.examples

//snippet-tuple-import-start
import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import org.junit.jupiter.params.ParameterizedTest
//snippet-tuple-import-end
import org.junit.jupiter.api.Order


@Order(1)
//snippet-tuple-1-start
class TupleTest : PredefinedArgsSourceProviders {

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
//snippet-tuple-1-end

