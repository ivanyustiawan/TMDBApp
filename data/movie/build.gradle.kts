plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.tmdbapp.data.movie"
    compileSdk = 35

    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(project(":domain:movie"))
    implementation(project(":core:network"))
    implementation(project(":core:extention"))
}
