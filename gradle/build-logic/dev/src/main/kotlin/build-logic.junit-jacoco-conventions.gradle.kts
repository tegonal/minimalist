plugins {
    id("ch.tutteli.gradle.plugins.junitjacoco")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}
