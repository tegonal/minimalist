package com.tegonal.variist.config

import com.tegonal.variist.generators.ordered
import com.tegonal.variist.testutils.RequestedMinAndMaxArgsTest
import com.tegonal.variist.testutils.createOrderedWithCustomConfig

class VarriistConfigTest : RequestedMinAndMaxArgsTest {

	override fun setupRequestedMinArgsMaxArgs(requestedMinArgs: Int?, maxArgs: Int?) {
		createOrderedWithCustomConfig(
			ordered._components.config.copy {
				this.requestedMinArgs = requestedMinArgs
				this.maxArgs = maxArgs
			}
		).ordered
	}

	//TODO 2.1.0 add good first issues for the tests of the remaining invariants?
}
