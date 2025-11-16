package readme.examples

import com.tegonal.variist.providers.ArgsSource
import com.tegonal.variist.providers.ArgsSourceOptions
import org.junit.jupiter.api.Order
import org.junit.jupiter.params.ParameterizedTest

@Suppress("unused", "UNUSED_PARAMETER")
@Order(1)
class ArgsSourceOptionsMaxArgsTest : PredefinedArgsProviders {

	//snippet-args-source-options-max-args-start
	@ParameterizedTest
	@ArgsSource("arbBoolean")
	@ArgsSourceOptions(maxArgs = 1)
	fun loginFailure(isLocked: Boolean) {
		//...
	}
	//snippet-args-source-options-max-args-end
}

