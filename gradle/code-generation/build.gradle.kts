plugins {
	id("build-logic.kotlin-dsl-gradle-plugin")
}

allprojects {
	group = "com.tegonal.variist.code-generation"
}

dependencies {
	api(buildLibs.kbox)
}
