plugins {
    id("build-logic.published-kotlin-jvm")
}

version = "1.0.0-SNAPSHOT"
group = "com.tegonal.minimalist"
description = "Library which helps to setup and prioritise parameterized tests"


dependencies {
    api("org.junit.jupiter:junit-jupiter-params:5.10.0")

    testImplementation(kotlin("test"))
    testImplementation("ch.tutteli.atrium:atrium-fluent:1.0.0")
}
