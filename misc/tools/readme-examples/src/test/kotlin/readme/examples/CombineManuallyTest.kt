package readme.examples

import ch.tutteli.kbox.Tuple
import com.tegonal.minimalist.generators.*
import com.tegonal.minimalist.providers.*
import com.tegonal.minimalist.utils.BigInt
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.Order

@Suppress("unused")
@Order(1)
//snippet-combine-manually-start
class CombineManuallyTest : PredefinedArgsProviders {

	@ParameterizedTest
	@ArgsSource("numbersAndChar")
	fun bar(i: Int, l: Long, d: Double, b: BigInt, c: Char) {
		//...
	}

	companion object {
		@JvmStatic
		fun numbersAndChar() = run { // use run to let the compiler infer the return type
			val numbers = Tuple(
				arb.int().combine(arb.long()), // combines them into an ArbArgsGenerators<Tuple2<Int, Long>>
				arb.double(),
				arb.bigIntFromUntil(BigInt.ZERO, BigInt.TEN)
			).combineAll() // combines all into an ArbArgsGenerators<Tuple3<...>>

			Tuple(
				numbers, // can again be used in a tuple to define that it shall be combined
				arb.char()
			) // the ArgsArgumentProvider will flatten all tuples, resulting in 5 arguments (see bar above)
		}
	}
}
//snippet-combine-manually-end

