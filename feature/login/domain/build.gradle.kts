plugins {
    alias(libs.plugins.convention.domain)
}
android {
    namespace = "com.login.domain"
}
dependencies {
    implementation(libs.bundles.domain.implementations)
    testImplementation(libs.bundles.domain.test.implementations)
}