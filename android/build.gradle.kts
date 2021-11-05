plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.qwwuyu.server"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0.0"
        ndkVersion = "23.1.7779620"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":common:main"))
    implementation("androidx.activity:activity-compose:1.3.0")
}