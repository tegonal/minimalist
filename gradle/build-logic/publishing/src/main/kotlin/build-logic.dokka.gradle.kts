import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("build-logic.gradle-conventions")
    id("ch.tutteli.gradle.plugins.dokka")
}

val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

tasks.configureEach<DokkaTask> {
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)
    }
}

tutteliDokka {
    githubUser.set("tegonal")
	modeSimple.set(false)
}

tasks.configureEach<AbstractDokkaLeafTask> {
    dokkaSourceSets.configureEach {
        jdkVersion.set(buildParameters.defaultJdkVersion)
        //TODO also set kotlin version
        includes.from(kdocDir.resolve("packages.md"))
    }
    configurePlugins()
}
