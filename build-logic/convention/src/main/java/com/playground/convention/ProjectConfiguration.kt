package com.playground.convention

import com.android.build.api.dsl.CommonExtension
import com.playground.convention.extensions.libs
import org.gradle.api.Project

fun CommonExtension<*, *, *, *, *, *>.configureComposeOptions(
    project: Project
) = with(project) {
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.agp.get().toString()
    }
    buildFeatures {
        compose = true
    }
}