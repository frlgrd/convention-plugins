import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins { `kotlin-dsl` }

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

gradlePlugin {
    plugins {
        register("application") {
            id = libs.plugins.convention.application.get().pluginId
            implementationClass = "ApplicationConventionPlugin"
        }
        register("dataLayer") {
            id = libs.plugins.convention.data.get().pluginId
            implementationClass = "DataLayerConventionPlugin"
        }
        register("domainLayer") {
            id = libs.plugins.convention.domain.get().pluginId
            implementationClass = "DomainLayerConventionPlugin"
        }
        register("presentationLayer") {
            id = libs.plugins.convention.presentation.get().pluginId
            implementationClass = "PresentationLayerConventionPlugin"
        }
        register("library") {
            id = libs.plugins.convention.library.get().pluginId
            implementationClass = "LibraryConventionPlugin"
        }
        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass = "ComposeConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.convention.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
    }
}
