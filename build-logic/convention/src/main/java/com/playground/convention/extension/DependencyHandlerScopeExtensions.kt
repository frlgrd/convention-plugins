package com.playground.convention.extension

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(
    dependency: Any
) = "implementation"(dependency)

fun DependencyHandlerScope.testImplementation(
    dependency: Any
) = "testImplementation"(dependency)

fun DependencyHandlerScope.androidTestImplementation(
    dependency: Any
) = "androidTestImplementation"(dependency)

fun DependencyHandlerScope.debugImplementation(
    dependency: Any
) = "debugImplementation"(dependency)