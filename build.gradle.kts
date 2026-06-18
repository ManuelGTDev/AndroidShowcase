import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.devtools.ksp") version "2.3.4" apply false
    id("com.google.dagger.hilt.android") version "2.59.2" apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
    id("com.google.firebase.crashlytics") version "3.0.7" apply false
}

subprojects {
    plugins.withType<com.android.build.gradle.AppPlugin> {
        extensions.configure<ApplicationExtension>("android") {
            compileSdk = project.libs.versions.compileSdk.get().toInt()
            defaultConfig {
                applicationId = "com.mgtdev.androidshowcase"
                minSdk = project.libs.versions.minSdk.get().toInt()
                targetSdk = project.libs.versions.targetSdk.get().toInt()
                versionCode = 1
                versionName = "1.0.0"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
            packaging {
                resources {
                    excludes += "/META-INF/gradle/incremental.annotation.processors"
                }
            }
        }
    }

    plugins.withType<com.android.build.gradle.LibraryPlugin> {
        extensions.configure<LibraryExtension>("android") {
            compileSdk = project.libs.versions.compileSdk.get().toInt()
            defaultConfig {
                minSdk = project.libs.versions.minSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }
    }
}

// clean will delete all our already built files and then have full rebuild project
tasks.register("clean", Delete::class) {
    delete(project.layout.buildDirectory)
}