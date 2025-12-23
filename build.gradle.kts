// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
}

subprojects {
    afterEvaluate {
        if (hasProperty("android")) {
            extensions.configure<com.android.build.gradle.BaseExtension> {
                compileSdkVersion(project.libs.versions.compileSdk.get().toInt())

                createDefaultConfig(this@afterEvaluate)
                //createBuildTypes(this@afterEvaluate)
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }
                packagingOptions {
                    resources {
                        excludes += "/META-INF/gradle/incremental.annotation.processors"
                    }
                }
            }
        }
    }
}

fun com.android.build.gradle.BaseExtension.createDefaultConfig(project: Project) {
    defaultConfig {
        if (project.plugins.hasPlugin("com.android.application")) {
            applicationId = "com.mgtdev.androidshowcase"
        }
        minSdk = project.libs.versions.minSdk.get().toInt()
        targetSdk = project.libs.versions.targetSdk.get().toInt()
        compileSdkVersion(project.libs.versions.compileSdk.get().toInt())
        versionCode = 25
        versionName = "1.0.3"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

//clean will delete all our already built files and then have full rebuild project
tasks.register("clean", Delete::class) {
    delete(project.layout.buildDirectory)
}