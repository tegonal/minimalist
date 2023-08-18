import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.AbstractDokkaTask

fun AbstractDokkaTask.configurePlugins() {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Minimalist &copy; Copyright Tegonal Genossenschaft, Switzerland &lt;info@tegonal.com&gt;"
    }
}
