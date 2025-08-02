package com.playground.convention.plugin

import com.playground.convention.extensions.alias
import com.playground.convention.extensions.androidTestImplementation
import com.playground.convention.extensions.debugImplementation
import com.playground.convention.extensions.implementation
import com.playground.convention.extensions.libs
import com.playground.convention.extensions.plugins
import com.playground.convention.extensions.testImplementation
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
            implementation(platform(libs.androidx.compose.bom))
            implementation(libs.bundles.presentation.implementations)
            testImplementation(libs.bundles.presentation.test.implementations)
            androidTestImplementation(platform(libs.androidx.compose.bom))
            androidTestImplementation(libs.bundles.presentation.android.test.implementations)
            debugImplementation(libs.bundles.presentation.debug.implementations)
        }
    }
}