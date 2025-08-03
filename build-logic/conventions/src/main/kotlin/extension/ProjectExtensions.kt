package extension

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf

fun Project.plugins(block: PluginManager.() -> Unit) = pluginManager.block()

val Project.libs get() = extensionOf(target = this, extensionName = "libs") as LibrariesForLibs

val Project.featureDomain get() = ":feature:${parent!!.name}:domain"

