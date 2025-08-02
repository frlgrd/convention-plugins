package com.playground.convention.extensions

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf

val Project.libs get() = extensionOf(target = this, extensionName = "libs") as LibrariesForLibs

fun Project.plugins(block: PluginManager.() -> Unit) = pluginManager.block()

fun Project.internalAndroidApplication(
    action: ApplicationDefaultConfig.() -> Unit
) = extensions.getByType(BaseAppModuleExtension::class.java).defaultConfig(action = action)

fun Project.internalAndroidLibrary(
    action: LibraryDefaultConfig.() -> Unit
) = extensions.getByType(LibraryExtension::class.java).defaultConfig(action = action)
