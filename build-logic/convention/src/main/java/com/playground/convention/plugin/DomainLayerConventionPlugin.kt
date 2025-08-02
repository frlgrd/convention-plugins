package com.playground.convention.plugin

import com.playground.convention.androidLibrary
import com.playground.convention.extensions.apply
import com.playground.convention.extensions.implementation
import com.playground.convention.extensions.libs
import com.playground.convention.extensions.plugins
import com.playground.convention.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DomainLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            apply(libs.plugins.android.library)
            apply(libs.plugins.kotlin.android)
        }
        androidLibrary {
            dependencies {
                implementation(libs.bundles.domain.implementations)
                testImplementation(libs.bundles.domain.test.implementations)
            }
        }
    }
}