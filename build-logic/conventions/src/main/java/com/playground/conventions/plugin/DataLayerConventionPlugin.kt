package com.playground.conventions.plugin

import com.playground.conventions.extension.alias
import com.playground.conventions.extension.domain
import com.playground.conventions.extension.implementation
import com.playground.conventions.extension.libs
import com.playground.conventions.extension.plugins
import com.playground.conventions.extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.library)
        }
        dependencies {
            implementation(project(domain))
            implementation(libs.bundles.androidx.implementations)
            testImplementation(libs.bundles.test.implementations)
        }
    }
}