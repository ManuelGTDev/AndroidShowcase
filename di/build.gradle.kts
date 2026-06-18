plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinSerialization)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.mgtdev.di"
}

dependencies {
    //Modules
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    testImplementation(libs.junit)
}