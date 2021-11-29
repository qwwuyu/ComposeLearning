plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    compileSdkVersion(Apps.compileSdkVersion)

    defaultConfig {
        applicationId = Apps.applicationId
        minSdkVersion(Apps.minSdkVersion)
        targetSdkVersion(Apps.targetSdkVersion)
        versionCode = Apps.code
        versionName = Apps.version
        ndkVersion = "23.1.7779620"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*")
    }
}

dependencies {
    implementation(project(":common:main"))
    implementation(Deps.AndroidX.Activity.activityCompose)

    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
    implementation(Deps.ArkIvanov.MVIKotlin.logging)
    implementation(Deps.ArkIvanov.MVIKotlin.timeTravel)
}