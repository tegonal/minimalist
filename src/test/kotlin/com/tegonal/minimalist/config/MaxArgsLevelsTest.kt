package com.tegonal.minimalist.config

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MaxArgsLevelsTest {
	@Test
	fun `errors on duplicate levels`() {
		expect {
			MaxArgsLevels.create(
				MaxArgsLevel.Local to 2,
				MaxArgsLevel.PR to 3,
				MaxArgsLevel.Local to 4,
				MaxArgsLevel.Main to 10
			)
		}.toThrow<IllegalStateException> {
			messageToContain("Looks like you defined some levels multiple times: Local")
		}
	}
}
