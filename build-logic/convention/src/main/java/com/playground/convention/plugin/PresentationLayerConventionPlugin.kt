package com.playground.convention.plugin

import com.playground.convention.plugin.extensions.apply
import com.playground.convention.plugin.extensions.configureAndroidLibrary
import com.playground.convention.plugin.extensions.configureComposeOptions
import com.playground.convention.plugin.extensions.libs
import com.playground.convention.plugin.extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class PresentationLayerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            apply(libs.plugins.android.library)
            apply(libs.plugins.kotlin.android)
            apply(libs.plugins.kotlin.compose)
        }
        configureAndroidLibrary {
            configureComposeOptions(this@with)
        }
    }
}