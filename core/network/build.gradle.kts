import java.util.Properties

plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.tmdbapp.core.network"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
    }
    buildFeatures {
        buildConfig = true
    }

    val props = Properties().apply {
        load(File(rootDir, "local.properties").inputStream())
    }
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"https://image.tmdb.org/\"")
            buildConfigField("String", "AUTH_TOKEN", props["AUTH_TOKEN"] as String)
            buildConfigField("String", "ACCOUNT_OBJECT_ID", props["ACCOUNT_OBJECT_ID"] as String)
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://release-api.themoviedb.org/\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"https://release-image.tmdb.org/\"")
            buildConfigField("String", "AUTH_TOKEN", props["AUTH_TOKEN"] as String)
            buildConfigField("String", "ACCOUNT_OBJECT_ID", props["ACCOUNT_OBJECT_ID"] as String)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTargetVersion.get()
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
