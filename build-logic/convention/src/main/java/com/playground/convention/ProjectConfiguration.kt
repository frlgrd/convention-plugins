package com.playground.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.playground.convention.extensions.internalAndroidApplication
import com.playground.convention.extensions.internalAndroidLibrary
import com.playground.convention.extensions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.androidApplication(
    configuration: ApplicationExtension.() -> Unit
) = extensions.configure<ApplicationExtension> {
    internalAndroidApplication {
        compileSdk = 36
        defaultConfig {
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"
            applicationId = "com.playground"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        configureCompileOptions(this@androidApplication)
        configureComposeOptions(this@androidApplication)
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName("debug") {
                applicationIdSuffix = ".debug"
                isDebuggable = true
            }
        }
        configuration.invoke(this@configure)
    }
}

fun Project.androidLibrary(
    configuration: LibraryExtension.() -> Unit = {}
) = extensions.configure<LibraryExtension> {
    internalAndroidLibrary {
        compileSdk = 36
        defaultConfig {
            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }
        configureCompileOptions(this@androidLibrary)
        configuration.invoke(this@configure)
    }
}

private fun CommonExtension<*, *, *, *, *, *>.configureCompileOptions(
    project: Project
) = with(project) {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}

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
