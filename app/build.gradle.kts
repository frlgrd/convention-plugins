plugins {
    alias(libs.plugins.convention.application)
}
android {
    namespace = "com.playground"
}

dependencies {
    implementation(libs.bundles.app.implementations)
    implementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.bundles.app.test.implementations)
    androidTestImplementation(libs.bundles.app.android.test.implementations)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.bundles.app.debug.implementations)
}