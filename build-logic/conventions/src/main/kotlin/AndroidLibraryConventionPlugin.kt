import com.android.build.api.dsl.LibraryExtension
import extensions.alias
import extensions.implementation
import extensions.int
import extensions.libs
import extensions.plugins
import extensions.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.android.library)
            alias(libs.plugins.kotlin.android)
            alias(libs.plugins.convention.hilt)
        }
        configure<LibraryExtension> {
            defaultConfig {
                compileSdk = libs.versions.compileSdk.int()
                defaultConfig {
                    minSdk = libs.versions.minSdk.int()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
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
            }
        }
        dependencies {
            implementation(libs.bundles.androidx.implementations)
            testImplementation(libs.bundles.test.implementations)
        }
    }
}