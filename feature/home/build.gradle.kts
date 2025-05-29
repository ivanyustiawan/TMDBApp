plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.tmdbapp.feature.home"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTargetVersion.get().toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.coil.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":domain:movie"))
}
