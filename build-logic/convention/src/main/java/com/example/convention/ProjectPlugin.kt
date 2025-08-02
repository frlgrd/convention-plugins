package com.example.convention

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.plugin.use.PluginDependency

interface ProjectPlugin : Plugin<Project> {
    val Project.libs get() = extensionOf(target = this, extensionName = "libs") as LibrariesForLibs

    fun Project.plugins(
        block: PluginManager.() -> Unit,
    ): PluginManager = pluginManager.apply(block)

    fun PluginManager.apply(
        plugin: Provider<PluginDependency>
    ) = apply(plugin.get().pluginId)
}