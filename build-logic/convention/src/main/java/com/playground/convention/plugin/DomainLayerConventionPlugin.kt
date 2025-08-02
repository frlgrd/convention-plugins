package com.playground.convention.plugin

import com.playground.convention.extension.alias
import com.playground.convention.extension.implementation
import com.playground.convention.extension.libs
import com.playground.convention.extension.plugins
import com.playground.convention.extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DomainLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.convention.library)
        }
        dependencies {
            implementation(libs.bundles.domain.implementations)
            testImplementation(libs.bundles.domain.test.implementations)
        }
    }
}