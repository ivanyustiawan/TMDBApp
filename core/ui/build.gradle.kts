plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.tmdbapp.core.ui"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTargetVersion.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
}