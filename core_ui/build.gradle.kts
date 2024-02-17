plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.asabirov.core_ui"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
}