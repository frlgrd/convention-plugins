import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import extension.alias
import extension.androidTestImplementation
import extension.implementation
import extension.libs
import extension.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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

        dependencies {
            implementation(platform(libs.androidx.compose.bom))
            androidTestImplementation(platform(libs.androidx.compose.bom))
        }
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