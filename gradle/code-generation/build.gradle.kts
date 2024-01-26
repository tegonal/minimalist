plugins {
	id("build-logic.kotlin-dsl-gradle-plugin")
}

allprojects {
	group = "com.tegonal.minimalist.code-generation"
}

dependencies {
	api(buildLibs.kbox)
}
