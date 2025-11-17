package readme.examples

//snippet-args-source-options-profile-import-start
import com.tegonal.variist.config.TestType
import com.tegonal.variist.providers.ArgsSource
import com.tegonal.variist.providers.ArgsSourceOptions
import org.junit.jupiter.params.ParameterizedTest
//snippet-args-source-options-profile-import-end
import org.junit.jupiter.api.Order

@Suppress("unused", "UNUSED_PARAMETER")
@Order(1)
//snippet-args-source-options-profile-2-start
@ArgsSourceOptions(profile = TestType.ForAnnotation.E2E)
class ArgsSourceOptionsProfileTest : BaseSystemIntegrationTest() {

	@ParameterizedTest
	@ArgsSource("arbBoolean")
	fun foo(isDefined: Boolean) {
		//...
	}

	@ParameterizedTest
	@ArgsSource("arbInt")
	@ArgsSourceOptions(profile = TestType.ForAnnotation.Unit)
	fun bar(i: Int) {
		//...
	}
}
//snippet-args-source-options-profile-2-end




