import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet


fun NamedDomainObjectContainer<KotlinSourceSet>.configureLanguageSettings() {
    configureEach {
        // TODO make this configurable so that we can use a higher version in Tests.

		// we use a lower version on purpose so that it can be used in projects still using Kotlin 1.4
        val languageVersion = "1.4"
        val apiVersion = "1.4"
        languageSettings.apply {
            this.languageVersion = languageVersion
            this.apiVersion = apiVersion
            optIn("kotlin.RequiresOptIn")
        }
    }
}
