import com.android.build.api.dsl.ApplicationExtension
import extension.alias
import extension.androidTestImplementation
import extension.debugImplementation
import extension.implementation
import extension.libs
import extension.plugins
import extension.testImplementation
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
            alias(libs.plugins.convention.compose)
            alias(libs.plugins.convention.hilt)
        }
        configure<ApplicationExtension> {
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
                    implementation(libs.bundles.ui.implementations)
                    implementation(platform(libs.androidx.compose.bom))
                    testImplementation(libs.bundles.test.implementations)
                    androidTestImplementation(libs.bundles.ui.test.implementations)
                    androidTestImplementation(platform(libs.androidx.compose.bom))
                    debugImplementation(libs.bundles.debug.implementations)
                }
            }
        }
    }
}