import extensions.alias
import extensions.androidTestImplementation
import extensions.debugImplementation
import extensions.featureDomain
import extensions.implementation
import extensions.libs
import extensions.plugins
import extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PresentationLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.library)
            alias(libs.plugins.convention.compose)
        }
        dependencies {
            implementation(project(featureDomain))
            implementation(libs.bundles.ui.implementations)
            testImplementation(libs.bundles.test.implementations)
            androidTestImplementation(libs.bundles.ui.test.implementations)
            debugImplementation(libs.bundles.debug.implementations)
        }
    }
}