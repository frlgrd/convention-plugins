package com.playground.convention.plugin.extensions

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs get() = extensionOf(target = this, extensionName = "libs") as LibrariesForLibs

fun Project.plugins(block: PluginManager.() -> Unit) = pluginManager.block()

fun Project.configureAndroidApplication(
    block: ApplicationExtension.() -> Unit = {}
) = extensions.configure<ApplicationExtension> {
    androidApp {
        compileSdk = 36
        defaultConfig {
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"
            applicationId = "com.playground"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        configureCompileOptions(this@configureAndroidApplication)
        configureComposeOptions(this@configureAndroidApplication)
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        block()
    }
}

fun Project.configureAndroidLibrary(
    block: LibraryExtension.() -> Unit = {}
) = extensions.configure<LibraryExtension> {
    androidLibrary {
        compileSdk = 36
        defaultConfig {
            minSdk = 24
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }
        configureCompileOptions(this@configureAndroidLibrary)
        block()
    }
}

private fun Project.androidApp(
    action: ApplicationDefaultConfig.() -> Unit
) = extensions.getByType(BaseAppModuleExtension::class.java).defaultConfig(action = action)

private fun Project.androidLibrary(
    action: LibraryDefaultConfig.() -> Unit
) = extensions.getByType(LibraryExtension::class.java).defaultConfig(action = action)

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
