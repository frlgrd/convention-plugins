package com.playground.convention.plugin.foundation

import com.android.build.api.dsl.ApplicationExtension
import com.playground.convention.configureComposeOptions
import com.playground.convention.extensions.alias
import com.playground.convention.extensions.androidTestImplementation
import com.playground.convention.extensions.debugImplementation
import com.playground.convention.extensions.implementation
import com.playground.convention.extensions.libs
import com.playground.convention.extensions.plugins
import com.playground.convention.extensions.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.android.application)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.kotlin.compose)
        }
        configureAndroidApplication()
    }

    private fun Project.configureAndroidApplication() = extensions.configure<ApplicationExtension> {
        defaultConfig {
            compileSdk = 36
            defaultConfig {
                minSdk = 24
                targetSdk = 36
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
            tasks.withType<KotlinCompile>().configureEach {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
            configureComposeOptions(this@configureAndroidApplication)
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