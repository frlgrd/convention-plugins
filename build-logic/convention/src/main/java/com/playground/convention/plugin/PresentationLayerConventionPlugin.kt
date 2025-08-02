package com.playground.convention.plugin

import com.playground.convention.extension.alias
import com.playground.convention.extension.androidTestImplementation
import com.playground.convention.extension.debugImplementation
import com.playground.convention.extension.implementation
import com.playground.convention.extension.libs
import com.playground.convention.extension.plugins
import com.playground.convention.extension.testImplementation
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
            implementation(libs.bundles.ui.implementations)
            implementation(platform(libs.androidx.compose.bom))
            testImplementation(libs.bundles.test.implementations)
            androidTestImplementation(libs.bundles.ui.test.implementations)
            androidTestImplementation(platform(libs.androidx.compose.bom))
            debugImplementation(libs.bundles.debug.implementations)
        }
    }
}