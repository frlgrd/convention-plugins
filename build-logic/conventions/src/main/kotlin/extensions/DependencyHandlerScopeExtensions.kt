package extensions

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.ksp(id: Any) = "ksp"(id)
fun DependencyHandlerScope.implementation(id: Any) = "implementation"(id)
fun DependencyHandlerScope.testImplementation(id: Any) = "testImplementation"(id)
fun DependencyHandlerScope.debugImplementation(id: Any) = "debugImplementation"(id)
fun DependencyHandlerScope.androidTestImplementation(id: Any) = "androidTestImplementation"(id)