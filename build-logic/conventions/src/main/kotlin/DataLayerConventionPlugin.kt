import extensions.alias
import extensions.featureDomain
import extensions.implementation
import extensions.libs
import extensions.plugins
import extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.library)
        }
        dependencies {
            implementation(project(featureDomain))
            implementation(libs.bundles.androidx.implementations)
            implementation(libs.bundles.data.api)
            testImplementation(libs.bundles.test.implementations)
        }
    }
}