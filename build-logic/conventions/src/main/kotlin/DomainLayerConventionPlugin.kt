import extension.alias
import extension.implementation
import extension.libs
import extension.plugins
import extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DomainLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.library)
        }
        dependencies {
            implementation(libs.bundles.androidx.implementations)
            testImplementation(libs.bundles.test.implementations)
        }
    }
}