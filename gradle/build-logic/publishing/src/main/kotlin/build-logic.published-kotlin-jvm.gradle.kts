plugins {
    id("build-logic.kotlin-jvm")
    id("ch.tutteli.gradle.plugins.publish")
    id("build-logic.dokka")
}

tutteliPublish {
	githubUser.set("tegonal")
    resetLicenses("EUPL-1.2")
}

