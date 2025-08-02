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
        create("applicationConventionPlugin") {
            id = libs.plugins.convention.application.get().pluginId
            implementationClass = "com.playground.convention.plugin.ApplicationConventionPlugin"
        }
        create("dataConventionPlugin") {
            id = libs.plugins.convention.data.get().pluginId
            implementationClass = "com.playground.convention.plugin.DataLayerConventionPlugin"
        }
        create("domainConventionPlugin") {
            id = libs.plugins.convention.domain.get().pluginId
            implementationClass = "com.playground.convention.plugin.DomainLayerConventionPlugin"
        }
        create("presentationConventionPlugin") {
            id = libs.plugins.convention.presentation.get().pluginId
            implementationClass =
                "com.playground.convention.plugin.PresentationLayerConventionPlugin"
        }
    }
}
