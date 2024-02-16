@file:Suppress("DSL_SCOPE_VIOLATION")
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
//    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
//    id ("com.google.dagger.hilt.android") version "2.50" apply false
//    alias(libs.plugins.hilt.gradle.plugin)
}