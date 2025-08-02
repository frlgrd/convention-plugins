package com.playground.convention.plugin

import com.playground.convention.androidApplication
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

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.android.application)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.kotlin.compose)
        }
        androidApplication {
            dependencies {
                implementation(libs.bundles.app.implementations)
                implementation(platform(libs.androidx.compose.bom))
                testImplementation(libs.bundles.app.test.implementations)
                androidTestImplementation(libs.bundles.app.android.test.implementations)
                androidTestImplementation(platform(libs.androidx.compose.bom))
                debugImplementation(libs.bundles.app.debug.implementations)
            }
        }
    }
}