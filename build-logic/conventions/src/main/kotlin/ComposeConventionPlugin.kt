import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extensions.alias
import extensions.androidTestImplementation
import extensions.implementation
import extensions.libs
import extensions.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        plugins {
            alias(libs.plugins.kotlin.compose)
        }

        configureComposeFor<BaseAppModuleExtension>()
        configureComposeFor<LibraryExtension>()

        dependencies {
            implementation(platform(libs.androidx.compose.bom))
            androidTestImplementation(platform(libs.androidx.compose.bom))
        }
    }
}

private inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.configureComposeFor() {
    val isExtensionAvailable = extensions.findByType(T::class.java) != null
    if (isExtensionAvailable) {
        configure<T> {
            composeOptions {
                kotlinCompilerExtensionVersion = libs.versions.agp.get().toString()
            }
            buildFeatures {
                compose = true
            }
        }
    }
}