package com.tegonal.variist.testutils

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.notToThrow
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.kbox.Tuple
import ch.tutteli.kbox.append
import com.tegonal.variist.generators.*
import com.tegonal.variist.providers.ArgsSource
import org.junit.jupiter.params.ParameterizedTest

interface RequestedMinAndMaxArgsTest {

	fun setupRequestedMinArgsMaxArgs(requestedMinArgs: Int?, maxArgs: Int?)

	@ParameterizedTest
	@ArgsSource("requestedMinArgsMaxArgs")
	fun requestedMinArgs_maxArgs(requestedMinArgs: Int?, maxArgs: Int?, exceptionMessageContains: String) {
		expect {
			setupRequestedMinArgsMaxArgs(requestedMinArgs, maxArgs)
		}.also {
			if (exceptionMessageContains == NO_ERROR) {
				it.notToThrow()
			} else {
				it.toThrow<IllegalStateException> {
					messageToContain(exceptionMessageContains)
				}
			}
		}
	}

	companion object {
		const val NO_ERROR = "expect no error"

		@JvmStatic
		fun requestedMinArgsMaxArgs() =
			//TODO 2.0.0 replace with ordered.fromArbs()
			ordered.intFromTo(0, 8).zipDependent({ useCase ->
				when (useCase) {
					0 -> arb.of(Tuple(null, null, NO_ERROR))
					1 -> arb.intPositive().map { Tuple(it, null, NO_ERROR) }
					2 -> arb.intPositive().map { Tuple(null, it, NO_ERROR) }
					3 -> arb.intBounds(minInclusive = 1, minSize = 1).map { it.append(NO_ERROR) }
					4 -> arb.intPositive().map { Tuple(it, it, NO_ERROR) }
					5 -> arb.intFromUntil(10, Int.MAX_VALUE).zipDependent({ arb.intFromTo(1, it - 1) }) { min, max ->
						Tuple(min, max, "requestedMinArgs ($min) must be less than or equal to maxArgs ($max)")
					}

					6 -> arb.intNegativeAndZero().zipDependent({ arb.intPositive() }) { min, max ->
						Tuple(min, max, "$min is not a valid requestedMinArgs, must be greater than 0")
					}

					7 -> arb.intPositive().zipDependent({ arb.intNegativeAndZero() }) { min, max ->
						Tuple(min, max, "$max is not a valid maxArgs, must be greater than 0")
					}

					8 -> arb.intNegativeAndZero().zipDependent({ arb.intFromUntil(it, 0) }) { min, max ->
						// we know requestedMinArgs comes first, the important bit is that it still fails even if
						// min <= max
						Tuple(min, max, "$min is not a valid requestedMinArgs, must be greater than 0")
					}

					else -> error("looks like we have not covered use case $useCase")
				}
			}) { _, t -> t }
	}
}
