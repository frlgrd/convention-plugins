
import extensions.alias
import extensions.libs
import extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class DomainLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.android.library)
        }
    }
}