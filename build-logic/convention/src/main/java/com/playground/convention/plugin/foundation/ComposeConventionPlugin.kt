package com.playground.convention.plugin.foundation

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.playground.convention.extension.alias
import com.playground.convention.extension.libs
import com.playground.convention.extension.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.kotlin.compose)
        }
        val isApplicationModule = extensions.findByType(BaseAppModuleExtension::class.java) != null
        if (isApplicationModule) {
            configure<BaseAppModuleExtension> { configureCompose(this@with) }
        } else {
            configure<LibraryExtension> { configureCompose(this@with) }
        }
    }

    private fun CommonExtension<*, *, *, *, *, *>.configureCompose(
        project: Project
    ) = with(project) {
        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.agp.get().toString()
        }
        buildFeatures {
            compose = true
        }
    }
}