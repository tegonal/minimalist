plugins {
    id("build-logic.kotlin-jvm")
	id("build-logic.dokka")
	id("ch.tutteli.gradle.plugins.kotlin.module.info")
    id("ch.tutteli.gradle.plugins.publish")
}

tutteliPublish {
	githubUser.set("tegonal")
    resetLicenses("EUPL-1.2")
}

