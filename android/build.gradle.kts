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

    signingConfigs {
        create("sign") {
            storeFile = File(project.projectDir, "test.jks")
            storePassword = "123456"
            keyAlias = "test"
            keyPassword = "123456"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("sign")
            versionNameSuffix = ".debug"
            isZipAlignEnabled = false
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            aaptOptions.cruncherEnabled = false
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("sign")
            isZipAlignEnabled = false
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName = "v${variant.mergedFlavor.versionName}_${variant.mergedFlavor.versionCode}.apk"
            }
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
    implementation(project(":common:base"))
    implementation(project(":common:main"))
    implementation(Deps.AndroidX.Activity.activityCompose)

    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
    implementation(Deps.ArkIvanov.MVIKotlin.logging)
    implementation(Deps.ArkIvanov.MVIKotlin.timeTravel)
    implementation(Deps.ArkIvanov.Decompose.decompose)
    implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
}