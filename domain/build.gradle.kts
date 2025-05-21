plugins{
    `kotlin-dsl`
    kotlin("kapt")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
}