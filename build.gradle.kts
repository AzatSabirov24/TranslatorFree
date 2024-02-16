buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.hilt.agp)
    }
}

plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
}