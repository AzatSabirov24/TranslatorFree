plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.asabirov.translation"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {

    implementation(project(":core_ui"))
    implementation(project(":core"))

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
}