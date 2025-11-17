package readme.examples

import com.tegonal.variist.config.TestType
import com.tegonal.variist.providers.ArgsSource
import com.tegonal.variist.providers.ArgsSourceOptions
import org.junit.jupiter.params.ParameterizedTest

@Suppress("unused", "UNUSED_PARAMETER")
//snippet-args-source-options-profile-1-start
@ArgsSourceOptions(profile = TestType.ForAnnotation.SystemIntegration)
abstract class BaseSystemIntegrationTest : PredefinedArgsProviders {
	// some common setup or whatever

	@ParameterizedTest
	@ArgsSource("arbIntBoundsMinSize2")
	fun commonFoo(lowerBound: Int, upperBound: Int) {
		//...
	}
}
//snippet-args-source-options-profile-1-end
