plugins {
    alias(libs.plugins.convention.application)
}
android {
    namespace = "com.playground"
    defaultConfig {
        applicationId = "com.playground"
        versionCode = 1
        versionName = "1.0"
    }
}
dependencies {
    implementation(project(":feature:login:presentation"))
}