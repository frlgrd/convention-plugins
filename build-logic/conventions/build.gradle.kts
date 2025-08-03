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
        register("androdiApplication") {
            id = libs.plugins.convention.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.convention.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass = "ComposeConventionPlugin"
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
        register("hilt") {
            id = libs.plugins.convention.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
    }
}
