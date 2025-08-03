import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins { `kotlin-dsl` }

group = "com.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

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
            implementationClass =
                "com.playground.conventions.plugin.ApplicationConventionPlugin"
        }
        register("dataLayer") {
            id = libs.plugins.convention.data.get().pluginId
            implementationClass = "com.playground.conventions.plugin.DataLayerConventionPlugin"
        }
        register("domainLayer") {
            id = libs.plugins.convention.domain.get().pluginId
            implementationClass = "com.playground.conventions.plugin.DomainLayerConventionPlugin"
        }
        register("presentationLayer") {
            id = libs.plugins.convention.presentation.get().pluginId
            implementationClass =
                "com.playground.conventions.plugin.PresentationLayerConventionPlugin"
        }
        register("library") {
            id = libs.plugins.convention.library.get().pluginId
            implementationClass =
                "com.playground.conventions.plugin.LibraryConventionPlugin"
        }
        register("compose") {
            id = libs.plugins.convention.compose.get().pluginId
            implementationClass =
                "com.playground.conventions.plugin.ComposeConventionPlugin"
        }
    }
}
