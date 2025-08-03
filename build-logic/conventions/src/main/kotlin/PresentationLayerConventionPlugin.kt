import extension.alias
import extension.androidTestImplementation
import extension.debugImplementation
import extension.featureDomain
import extension.implementation
import extension.libs
import extension.plugins
import extension.testImplementation
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
            implementation(platform(libs.androidx.compose.bom))
            testImplementation(libs.bundles.test.implementations)
            androidTestImplementation(libs.bundles.ui.test.implementations)
            androidTestImplementation(platform(libs.androidx.compose.bom))
            debugImplementation(libs.bundles.debug.implementations)
        }
    }
}