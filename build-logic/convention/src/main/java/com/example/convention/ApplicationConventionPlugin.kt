package com.example.convention

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ApplicationConventionPlugin : ProjectPlugin {
    override fun apply(target: Project) {
        with(target) {
            plugins {
                apply(libs.plugins.android.application)
                apply(libs.plugins.kotlin.android)
                apply(libs.plugins.kotlin.compose)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
            }
        }
    }

    private fun Project.configureKotlinAndroid(
        commonExtension: CommonExtension<*, *, *, *, *, *>
    ) {
        commonExtension.apply {
            defaultConfig {
                minSdk = 24
                compileSdk = 36
                android {
                    targetSdk = 36
                    versionCode = 1
                    versionName = "1.0"
                    applicationId = "com.example.playground"
                }
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = libs.versions.agp.get().toString()
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }

    private fun Project.android(action: ApplicationDefaultConfig.() -> Unit) = extensions
        .getByType(BaseAppModuleExtension::class.java)
        .defaultConfig(action = action)
}