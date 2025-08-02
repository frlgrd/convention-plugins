plugins {
    alias(libs.plugins.convention.presentation)
}
android {
    namespace = "com.login.presentation"
}
dependencies {
    implementation(libs.bundles.presentation.implementations)
    testImplementation(libs.bundles.presentation.test.implementations)
}