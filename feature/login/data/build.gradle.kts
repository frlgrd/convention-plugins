plugins {
    alias(libs.plugins.convention.data)
}
android {
    namespace = "com.login.data"
}
dependencies {
    implementation(libs.bundles.data.implementations)
    testImplementation(libs.bundles.data.test.implementations)
}