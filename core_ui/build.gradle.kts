plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.asabirov.core_ui"
}

dependencies {

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
}