plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.gradle.plugin)
    alias(libs.plugins.sql.delight.gradle.plugin)
}

android {
    namespace = "com.asabirov.translation"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    kotlinOptions {
        jvmTarget = "20"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

sqldelight {
    database("TranslateDatabase") {
        packageName = "com.asabirov.translation.database"
        sourceFolders = listOf("sqldelight")
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

    // sqlDelight
    implementation(libs.bundles.sqlDelight)

    // kotlin date time
    implementation(libs.kotlin.date.time)
}