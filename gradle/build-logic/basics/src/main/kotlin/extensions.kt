import org.gradle.api.DomainObjectCollection
import org.gradle.kotlin.dsl.withType

// TODO 0.5.0 move into own repo and fetch via gt
// copied from com.github.vlsi.gradle.dsl.configureEach, using this instead so that we don't have to import
inline fun <reified S : Any> DomainObjectCollection<in S>.configureEach(noinline configuration: S.() -> Unit) =
    withType().configureEach(configuration)
