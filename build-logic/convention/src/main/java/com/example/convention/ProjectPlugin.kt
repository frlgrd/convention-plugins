package com.example.convention

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.plugin.use.PluginDependency

interface ProjectPlugin : Plugin<Project> {
    val Project.libs get() = extensionOf(target = this, extensionName = "libs") as LibrariesForLibs

    fun Project.plugins(closure: PluginManager.() -> Unit): PluginManager {
        return pluginManager.apply(closure)
    }

    fun PluginManager.apply(plugin: Provider<PluginDependency>) {
        apply(plugin.get().pluginId)
    }

    fun DependencyHandlerScope.implementation(id: Any) = "implementation"(id)

    fun DependencyHandlerScope.testImplementation(id: Any) = "testImplementation"(id)

    fun DependencyHandlerScope.debugImplementation(id: Any) = "debugImplementation"(id)

    fun DependencyHandlerScope.androidTestImplementation(id: Any) = "androidTestImplementation"(id)
}