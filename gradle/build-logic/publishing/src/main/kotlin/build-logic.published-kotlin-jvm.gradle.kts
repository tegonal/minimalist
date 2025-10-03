import ch.tutteli.gradle.plugins.publish.PublishPluginExtension

plugins {
	id("build-logic.kotlin-jvm")
	id("build-logic.dokka")
	id("ch.tutteli.gradle.plugins.kotlin.module.info")
}

ifIsPublishing {
	apply(plugin = "ch.tutteli.gradle.plugins.publish")
	the<PublishPluginExtension>().run {
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
}
