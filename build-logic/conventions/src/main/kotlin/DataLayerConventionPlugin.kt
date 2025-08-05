import extensions.alias
import extensions.featureDomainModule
import extensions.implementation
import extensions.libs
import extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.android.library)
        }
        dependencies {
            implementation(featureDomainModule)
            implementation(libs.bundles.data.room)
            implementation(libs.bundles.data.retrofit)
        }
    }
}