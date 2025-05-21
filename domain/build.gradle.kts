plugins{
    `kotlin-dsl`
//    kotlin("kapt")
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
}