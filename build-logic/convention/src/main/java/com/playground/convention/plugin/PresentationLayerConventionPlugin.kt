package com.playground.convention.plugin

import com.playground.convention.plugin.extensions.androidLibrary
import com.playground.convention.plugin.extensions.androidTestImplementation
import com.playground.convention.plugin.extensions.apply
import com.playground.convention.plugin.extensions.configureComposeOptions
import com.playground.convention.plugin.extensions.debugImplementation
import com.playground.convention.plugin.extensions.implementation
import com.playground.convention.plugin.extensions.libs
import com.playground.convention.plugin.extensions.plugins
import com.playground.convention.plugin.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class PresentationLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            apply(libs.plugins.android.library)
            apply(libs.plugins.kotlin.android)
            apply(libs.plugins.kotlin.compose)
        }
        androidLibrary {
            configureComposeOptions(this@with)
            dependencies {
                implementation(libs.bundles.presentation.implementations)
                implementation(platform(libs.androidx.compose.bom))
                testImplementation(libs.bundles.presentation.test.implementations)
                androidTestImplementation(libs.bundles.presentation.android.test.implementations)
                androidTestImplementation(platform(libs.androidx.compose.bom))
                debugImplementation(libs.bundles.presentation.debug.implementations)
            }
        }
    }
}