plugins {
	id("build-logic.kotlin-jvm")
	id("build-logic.dokka")
	id("ch.tutteli.gradle.plugins.kotlin.module.info")
	id("ch.tutteli.gradle.plugins.publish")
}

tutteliPublish {
	githubUser.set("tegonal")
	manifestVendor.set("Tegonal Genossenschaft")

	resetLicenses("EUPL-1.2")
	addDeveloper {
		id = "robstoll"
		name = "Robert Stoll"
		email = "robert.stoll@tegonal.com"
		url = "https://tutteli.ch"
		organization = "Tegonal Genossenschaft"
		organizationUrl = "https://tegonal.com"
	}
}
