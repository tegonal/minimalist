import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	// this plugin sets inter alia toolchain and source/targetCompatibility
	// but also applies common plugins such as gradle-convention, build-params
	id("build-logic.java")
	id("ch.tutteli.gradle.plugins.junitjacoco")
}

tasks.configureEach<KotlinCompile> {
	compilerOptions{
		jvmTarget.set(JvmTarget.fromTarget(buildParameters.defaultJdkVersion.toString()))
	}
}

tasks.configureEach<KotlinCompilationTask<*>> {
	compilerOptions {
		freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
		// suppress warnings about used kotlin version being deprecated, we use an older api/language version on purpose
		freeCompilerArgs.add("-Xsuppress-version-warnings")

		val kotlinVersion = KotlinVersion.fromVersion(buildParameters.kotlin.version)
		languageVersion.set(kotlinVersion)
		apiVersion.set(kotlinVersion)
	}
}
plugins.withId("jacoco") {
	configure<JacocoPluginExtension> {
		if (rootProject.name != "gradle-kotlin-dsl-accessors") {
			toolVersion = "0.8.14"
		}
	}
}
