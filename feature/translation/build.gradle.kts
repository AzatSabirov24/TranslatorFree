plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.gradle.plugin)
}

android {
    namespace = "com.asabirov.translation"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

dependencies {

    implementation(project(":core_ui"))
    implementation(project(":core"))

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    // ktor
    implementation(libs.bundles.ktor)

    // coil
    implementation(libs.coil.compose)

    // hilt
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)
}