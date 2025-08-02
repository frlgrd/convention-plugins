package com.playground.convention.plugin.foundation

import com.android.build.api.dsl.LibraryExtension
import com.playground.convention.extensions.alias
import com.playground.convention.extensions.libs
import com.playground.convention.extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.kotlin.compose)
        }
        extensions.configure<LibraryExtension> {
            composeOptions {
                kotlinCompilerExtensionVersion = libs.versions.agp.get().toString()
            }
            buildFeatures {
                compose = true
            }
        }
    }
}