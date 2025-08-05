package extensions

import org.gradle.api.provider.Provider

fun Provider<String>.int() = get().toInt()