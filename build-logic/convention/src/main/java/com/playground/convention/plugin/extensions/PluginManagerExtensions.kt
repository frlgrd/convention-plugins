package com.playground.convention.plugin.extensions

import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

fun PluginManager.apply(plugin: Provider<PluginDependency>) = apply(plugin.get().pluginId)
