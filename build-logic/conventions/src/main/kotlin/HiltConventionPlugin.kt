
import extension.alias
import extension.implementation
import extension.ksp
import extension.libs
import extension.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.hilt.android)
            alias(libs.plugins.google.ksp)
        }
        dependencies {
            implementation(libs.bundles.hilt)
            ksp(libs.hilt.compiler)
        }
    }
}